# Exercício 6

> O que acontece se nas aplicações dos Exemplos 12, 13 e 14, o número de Clientes instanciados for elevado? Há um limite para a quantidade de Clientes na aplicação do Servidor fornecida? Justifique todas as respostas!

## Como foi testado

Abrir milhares de janelas do `Client.java` à mão não é viável. Por isso foi usado um programa auxiliar de teste, que abre **N conexões ao mesmo tempo** contra o servidor dos Exemplos 12 e 14, sem alteração no código. Ele envia uma mensagem por conexão, mantém todas abertas por 8 segundos e depois as fecha. Durante esse tempo, foram medidos o **número de threads** e a **memória** do processo do servidor.

Computador dos testes: Intel Core i7-1355U (12 processadores lógicos), Windows 11, Java 25.

## Testes

| Clientes conectados | Conexões aceitas | Threads do servidor | Memória do servidor | Depois que os clientes saem |
|---:|---:|---:|---:|---|
| 0 (ocioso) | — | 22 | 39 MB | — |
| 100 | 100 | 131 | 56 MB | 31 threads, 51 MB |
| 1.000 | 1.000 | 1.031 | 200 MB | 31 threads, 136 MB |
| 2.000 | 2.000 | 2.031 | 383 MB | 31 threads, 252 MB |
| 5.000 | 5.000 | 5.031 | 670 MB | 30 threads, 374 MB |

Numa execução anterior do teste com 5.000 clientes, **258 conexões foram recusadas** (`ConnectException: Connection refused`). O motivo está explicado mais abaixo.

## O que acontece com muitos Clientes?

1. **O servidor continua funcionando e atende todos ao mesmo tempo.** Até 5.000 clientes simultâneos, todas as conexões foram aceitas e todas as mensagens chegaram.

2. **O número de threads cresce junto com o número de clientes, um para um.** O servidor tem cerca de 31 threads próprias da JVM (*garbage collector*, compilador JIT etc.) e cria **mais uma thread para cada cliente**: com 5.000 clientes, eram 5.031 threads. Isso vem desta linha do `clientConnectionLoop()`:
   ```java
   new Thread(() -> clientMessageLoop(clientSocket)).start();
   ```

3. **A memória cresce na mesma proporção:** cerca de **130 KB por cliente**, de 39 MB ocioso para 670 MB com 5.000 clientes. Cada thread precisa de uma pilha (*stack*) própria, e cada `ClientSocket` guarda um `Socket`, um `BufferedReader` (com buffer de 8 KB) e um `PrintWriter`.

4. **A maior parte dessas threads fica parada.** Cada uma fica bloqueada no `readLine()` do seu cliente, esperando uma mensagem que pode demorar minutos. O servidor gasta uma thread inteira, com sua memória, para cada cliente que está apenas **esperando**.

5. **Quando os clientes saem, as threads são liberadas.** O número de threads voltou para 31 depois que as conexões foram fechadas. A memória não volta toda imediatamente (ficou em 374 MB depois do teste de 5.000) porque a JVM mantém a memória já reservada para reutilizar.

6. **Muitas conexões chegando de uma vez podem ser recusadas.** O construtor `new ServerSocket(PORT)` cria uma **fila de espera (*backlog*) de 50 conexões**. Para cada cliente, o servidor precisa aceitar a conexão, criar o `ClientSocket`, imprimir no console e criar a thread. Se os clientes chegam mais rápido do que isso, a fila enche e o SO **recusa** as conexões excedentes. Foi o que aconteceu com 258 das 5.000 conexões numa das execuções. Esse resultado nem sempre se repete, porque depende de quão rápido o servidor consegue esvaziar a fila naquele momento.

7. **O console vira um gargalo.** Cada conexão e cada mensagem geram uma linha no `System.out`, que é sincronizado: só uma thread escreve por vez. Com milhares de clientes, as threads passam a esperar umas pelas outras só para imprimir.

## Há um limite para a quantidade de Clientes?

**No código, não.** O `do { ... } while(true)` aceita clientes indefinidamente, e nada verifica quantos já estão conectados.

**Na prática, sim.** O limite vem dos **recursos do computador**, não do programa:

| Limite | De onde vem | Consequência |
|---|---|---|
| **Memória** | Cada cliente custa cerca de 130 KB (pilha da thread + objetos) | Quando acaba, a JVM lança `OutOfMemoryError: unable to create native thread` e o servidor para de aceitar clientes (não testado, ocorreria muito acima de 5.000) |
| **Threads do SO** | Cada `Thread` do Java é uma thread real do SO, que limita quantas um processo pode ter | O mesmo `OutOfMemoryError`, mesmo com memória livre |
| **Fila de conexões (*backlog* = 50)** | Parâmetro padrão do `ServerSocket` | Muitas conexões chegando juntas são recusadas (**observado**: 258 de 5.000) |
| **Desempenho do processador** | Com milhares de threads, o escalonador do SO gasta tempo só trocando de uma para outra | O servidor responde cada vez mais devagar |
| **Portas e conexões do sistema** | Cada conexão ocupa um descritor do SO. Num mesmo computador cliente, há cerca de 16 mil portas de origem disponíveis | Do lado do cliente, não dá para abrir mais conexões do que portas livres (não testado) |

Como diz a conclusão dos slides, essa solução é **parcialmente escalável**. Ela funciona bem enquanto o número de clientes fica abaixo desses limites, como numa sala de aula ou num chat pequeno, mas não aguenta dezenas de milhares de conexões.

## Justificativa

- O modelo do servidor é **uma thread por cliente**. É a solução mais simples para lidar com as operações bloqueantes (`accept()` e `readLine()`): cada thread pode ficar parada esperando o seu cliente sem atrapalhar os outros.
- Como as threads são recursos do **sistema operacional**, cada uma consome memória e tempo de escalonamento. O custo cresce linearmente com o número de clientes, como mostraram as medições: +1 thread e cerca de 130 KB por cliente.
- Por isso a quantidade de clientes é limitada pela memória e pelo SO, mesmo que o código não imponha limite nenhum.

**Como o servidor poderia ser melhorado:**

- **Pool de threads** (`Executors.newFixedThreadPool(n)`): limita o número de threads, e os clientes excedentes esperam na fila em vez de derrubar o servidor.
- **Threads virtuais** (Java 21 ou mais recente, que é o caso do Java 25): `Thread.ofVirtual().start(...)` cria threads leves, gerenciadas pela JVM em vez do SO. Mantém o código simples de "uma thread por cliente" e permite centenas de milhares de clientes.
- **Backlog maior:** `new ServerSocket(PORT, 1000)`, para aguentar picos de conexões chegando ao mesmo tempo.
- **E/S não bloqueante** (`java.nio`, com `Selector`): poucas threads atendem muitos clientes, verificando quais têm dados prontos para ler.
