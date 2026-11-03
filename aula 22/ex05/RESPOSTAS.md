# Exercício 5 – Cliente-Servidor Java Sockets e Threads

> No tópico Cliente-Servidor Java Sockets e Threads, executar e registrar o funcionamento dos códigos apresentados nos Exemplos 12, 13 e 14, das páginas 39 até 47, respectivamente.

**Como foi testado:** um servidor e quatro clientes:

1. um cliente envia duas mensagens e digita `sair`;
2. dois clientes (A e B) conectados **ao mesmo tempo**, enviando mensagens intercaladas;
3. um cliente encerrado à força, sem digitar `sair`.

Os testes rodaram numa cópia do projeto com a porta **4001**, porque a 4000 estava ocupada por outro servidor em execução. Fora isso, o código é idêntico.

## Exemplo 12 – `Server.java`

**Saída (console do servidor):**

```
*v*v*v* CONSOLE DO SERVIDOR *v*v*v*
Servidor iniciado na porta: 4001
Aguardando conexao de um cliente!
Cliente /127.0.0.1:64788 se conectou!
Mensagem recebida do cliente /127.0.0.1:64788: Ola servidor!
Mensagem recebida do cliente /127.0.0.1:64788: Segunda mensagem
Cliente /127.0.0.1:64790 se conectou!
Cliente /127.0.0.1:64791 se conectou!
Mensagem recebida do cliente /127.0.0.1:64790: Mensagem 1 do cliente A
Mensagem recebida do cliente /127.0.0.1:64791: Mensagem 1 do cliente B
Mensagem recebida do cliente /127.0.0.1:64790: Mensagem 2 do cliente A
Mensagem recebida do cliente /127.0.0.1:64791: Mensagem 2 do cliente B
Cliente /127.0.0.1:64795 se conectou!
Mensagem recebida do cliente /127.0.0.1:64795: Vou cair sem dizer sair
```

**Explicação:**

- **Início:** o `main` cria o `Server` e chama `start()`, que abre um `ServerSocket` na porta definida em `PORT`. Em seguida, o `clientConnectionLoop()` fica parado em `serverSocket.accept()`, que é **bloqueante**: o programa espera ali até algum cliente conectar.
- **Uma thread por cliente:** a cada conexão, o `accept()` devolve um `Socket`, que é envolvido num `ClientSocket`. Em seguida, uma thread nova é criada para atender só aquele cliente:
  ```java
  new Thread(() -> clientMessageLoop(clientSocket)).start();
  ```
  O `Runnable` da thread é escrito como uma **expressão lambda**. Logo depois, o laço volta ao `accept()` para esperar o próximo cliente.
- **Vários clientes ao mesmo tempo:** os clientes A (porta 64790) e B (porta 64791) ficaram conectados juntos, e as mensagens dos dois chegaram **intercaladas**. Cada thread fica bloqueada no `getMessage()` (`readLine()`) do *seu* cliente, então um cliente esperando não impede o outro de ser atendido. Essa é a diferença em relação ao servidor da aula 21, que atendia um cliente por vez.
- **Identificação do cliente:** todos os clientes vêm do mesmo IP (`127.0.0.1`), e quem os diferencia é a **porta de origem** (64788, 64790...), escolhida automaticamente pelo SO.
- **Saída de um cliente:** quando chega `sair` ou `null` (conexão encerrada), o `while` termina, o `finally` chama `clientSocket.close()` e a thread daquele cliente acaba. O servidor continua no ar para os outros.
- **Cliente encerrado à força:** o último cliente foi encerrado sem digitar `sair`. O `readLine()` recebeu fim de fluxo e devolveu `null` (ou uma `IOException`, que o `getMessage()` converte em `null`), e o laço terminou normalmente. O servidor não travou nem mostrou erro.
- **Fim do programa:** o servidor **nunca termina sozinho**, porque o `do { ... } while(true)` é infinito. A linha `Servidor finalizado!` só aparece se o servidor não conseguir iniciar (por exemplo, com a porta ocupada). Para encerrá-lo, é preciso parar o processo (`Ctrl+C`).

## Exemplo 13 – `Client.java`

**Saída (console do cliente que digitou duas mensagens e `sair`):**

```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Cliente 127.0.0.1:4001 conectado ao servidor!
Aguardando a digitação de uma mensagem!
Digite uma mensagem (ou <sair> para finalizar): Ola servidor!
Digite uma mensagem (ou <sair> para finalizar): Segunda mensagem
Digite uma mensagem (ou <sair> para finalizar): sair
Cliente finalizado!
```

Os clientes A e B mostraram a mesma sequência, com as suas próprias mensagens. Nos testes, as mensagens foram enviadas automaticamente pela entrada padrão. Por isso o texto digitado foi incluído acima à mão, para a saída ficar como aparece num console interativo.

**Explicação:**

- **Conexão:** o `start()` cria um `Socket` para `Server.ADDRESS` e `Server.PORT`. O cliente usa as constantes da classe `Server`, então os dois precisam ser compilados juntos. Se o servidor não estiver rodando, o `new Socket(...)` lança `IOException` e aparece `Erro ao iniciar o cliente: Connection refused`.
- **Envio:** o `PrintWriter` é criado com *autoflush* (`true`), então cada `saida.println(msg)` envia a linha imediatamente pela rede.
- **Laço de mensagens:** o `messageLoop()` lê do teclado com `scanner.nextLine()` (também bloqueante) e envia tudo o que for digitado, **inclusive** a palavra `sair`. É ela que avisa o servidor para encerrar aquela conexão. Depois, o laço termina e o `main` imprime `Cliente finalizado!`.
- **Sem resposta do servidor:** o cliente só **envia**. Ele não tem nenhum código que leia do socket, então nunca recebe nada de volta. A comunicação é **unidirecional** (cliente → servidor).
- **Nada é fechado explicitamente:** o cliente não chama `clientSocket.close()`. A conexão só é encerrada quando o processo termina e o SO libera o socket.

## Exemplo 14 – `ClientSocket.java`

**Explicação (papel da classe no projeto):**

O `ClientSocket` **não tem `main` e não é executado sozinho**. É uma classe auxiliar que o **servidor** usa para representar cada cliente conectado. Ela junta, num objeto só, o `Socket` da conexão, o fluxo de entrada (`BufferedReader`) e o fluxo de saída (`PrintWriter`).

| Método | O que faz | Onde apareceu no teste |
|---|---|---|
| Construtor | Guarda o socket, cria os fluxos de entrada e saída e imprime a conexão | `Cliente /127.0.0.1:64788 se conectou!` |
| `getRemoteSocketAddress()` | Retorna o IP e a porta do cliente | O `/127.0.0.1:64790` de cada mensagem recebida |
| `getMessage()` | Lê uma linha (`readLine()`). Retorna `null` se a conexão foi encerrada ou se deu erro | Cada `Mensagem recebida do cliente ...` |
| `close()` | Fecha a entrada, a saída e o socket | Chamado no `finally` quando o cliente sai (não imprime nada) |
| `sendMsg(String)` | Envia uma linha ao cliente e indica se deu certo | **Nunca é chamado** |

Vantagens de ter essa classe:

- **Encapsulamento:** o servidor não precisa lidar com `InputStreamReader`, `BufferedReader` ou `IOException` em cada leitura. Ele só chama `getMessage()` e trata o `null`.
- **Um objeto por cliente:** cada thread do servidor recebe o *seu* `ClientSocket`, então os dados de um cliente nunca se misturam com os de outro. Os atributos são `final`, ou seja, não mudam depois de criados.
- **Pronta para ser bidirecional:** o `sendMsg()` já existe, mas ninguém o usa. Por isso essa versão só transmite do cliente para o servidor. Usá-lo para devolver as mensagens ao cliente é exatamente o **Desafio 2**.
