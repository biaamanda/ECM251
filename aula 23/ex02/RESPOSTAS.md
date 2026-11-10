# Exercício 2

> O que acontece se na aplicação do Exercício 1, o número de Clientes instanciados for elevado? Há um limite para a quantidade de Clientes na aplicação do Servidor fornecida? Justifique todas as respostas!

## O que acontece com muitos Clientes?

1. **Uma thread e mais memória por cliente.** Cada conexão cria uma `Thread` nova e um `SocketCliente`, com seus buffers. Com N clientes, o servidor terá cerca de N threads, quase todas paradas no `readLine()`, e a memória cresce na mesma proporção.
2. **As mensagens ficam mais lentas.** O `sendMsgToAll` repassa cada mensagem para todos os outros clientes, um por vez. Se cada um dos N clientes enviar uma mensagem, cada vez que tiver mais cliente o trabalho sera cada vez maior.
3. **Clientes podem ser derrubados.** A lista `clients` é uma `LinkedList`, que não é segura para várias threads. Se um cliente entra (`clients.add`) enquanto outra thread percorre a lista no `sendMsgToAll`, ocorre uma `ConcurrentModificationException`. Essa exceção não é tratada, então a thread do remetente morre e o `finally` fecha a conexão dele. Quanto mais clientes, mais provável.
4. **Um cliente lento pode travar os outros.** Se um cliente para de ler, o `println()` para ele acaba bloqueando quando o buffer enche. A thread de quem enviou fica presa ali e para de atender o seu cliente, e os clientes seguintes da lista deixam de receber a mensagem.
5. **Conexões em rajada podem ser recusadas.** O `new ServerSocket(PORT)` tem uma fila de espera de 50 conexões. Se muitos clientes conectarem ao mesmo tempo, mais rápido do que o `accept()` consegue atender, os excedentes recebem `Connection refused`.

## Há um limite para a quantidade de Clientes?

**No código, não:** o `while(true)` aceita clientes sem verificar quantos já estão conectados.

**Na prática, sim:**

- **Memória e threads do SO:** quando acabam, a JVM lança `OutOfMemoryError: unable to create native thread` e o servidor para de aceitar clientes.
- **Fila de 50 conexões:** clientes chegando juntos podem ser recusados.
- **Problemas do próprio código:** o custo N² dos repasses, a lista não sincronizada e o envio bloqueante devem fazer o chat ficar lento ou perder clientes antes de a memória acabar.

## Justificativa

- O modelo de uma thread por cliente é simples, mas cada cliente custa uma thread inteira, mesmo parado.
- A lista `clients` é compartilhada entre threads sem sincronização. Usar `synchronized` ou `CopyOnWriteArrayList` evitaria o problema.
- O repasse é feito um cliente por vez, na thread de quem enviou. Por isso fica lento com muitos clientes e pode travar por causa de um único cliente lento.
- Solução parcialmente escalável: atende poucos clientes.
