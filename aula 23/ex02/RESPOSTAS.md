# Exercício 2

> O que acontece se na aplicação do Exercício 1, o número de Clientes instanciados for elevado? Há um limite para a quantidade de Clientes na aplicação do Servidor fornecida? Justifique todas as respostas!

## Como foi testado

Um programa auxiliar abriu N clientes contra o `ServidorBatepapo`, sem alterar o código do servidor (só a porta, para 4001). Cada cliente enviou uma mensagem, e foram medidos threads, memória e tempo de entrega.

Computador dos testes: Intel Core i7-1355U, Windows 11, Java 25.

| Clientes | Threads do servidor | Memória | Mensagens repassadas | Tempo |
|---:|---:|---:|---:|---:|
| 0 | 22 | 39 MB | — | — |
| 100 | 132 | 69 MB | 9.900 | 0,4 s |
| 1.000 | 1.031 | 302 MB | 999.000 | 5,3 s |
| 2.000 | 2.030 | 421 MB | 3.998.000 | 18,6 s |
| 3.000 | 3.030 | 525 MB | 8.997.000 | 38,8 s |

## O que acontece com muitos Clientes?

1. **Uma thread e mais memória por cliente.** O servidor cria uma thread para cada cliente, e a memória sobe junto.
2. **As mensagens ficam lentas.** Cada mensagem é repassada para todos os outros (N × (N−1) envios). Dobrar os clientes quase quadruplica o tempo.
3. **Clientes são derrubados.** A lista `clients` (`LinkedList`) não é segura para várias threads. Quando um cliente entra enquanto outro está enviando, ocorre `ConcurrentModificationException`, e a thread do remetente morre e a conexão dele é fechada. No teste com clientes conectando e conversando ao mesmo tempo, **85% das threads morreram**.
4. **Um cliente lento trava o chat.** O `println()` para um cliente que não lê fica bloqueado, e quem enviou a mensagem fica preso junto. No teste, o servidor parou depois de 1.223 mensagens.
5. **Conexões em rajada são recusadas.** A fila do `ServerSocket` tem 50 vagas. Com 100 clientes conectando juntos, 29 foram recusados (`Connection refused`).

## Há um limite para a quantidade de Clientes?

**No código, não:** o `while(true)` aceita clientes sem parar.

**Na prática, sim:**

- **Memória e threads do SO:** ao acabarem, ocorre `OutOfMemoryError` (não atingido até 3.000 clientes).
- **Fila de 50 conexões:** clientes chegando juntos são recusados.
- **Problemas do código:** o custo N² dos repasses, a lista não sincronizada e o envio bloqueante fazem o chat falhar **antes** de faltar memória.

## Justificativa

- Com **uma thread por cliente**, cada cliente custa uma thread inteira, mesmo parado.
- A lista `clients` é compartilhada entre threads **sem sincronização**. Usar `synchronized` ou `CopyOnWriteArrayList` resolveria.
- O repasse é feito **um cliente por vez, na thread de quem enviou**. Por isso fica lento com muitos clientes e trava com um cliente lento.
- Como diz o material, a solução é **parcialmente escalável**: serve para poucos clientes, mas não para milhares.
