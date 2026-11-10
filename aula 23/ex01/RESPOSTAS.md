# Exercício 1 – ProjetoBatepapo

> No tópico Cliente-Servidor Java Sockets Bate-Papo, criar um projeto denominado ProjetoBatepapo, na IDE de sua preferência, digitar as classes fornecidas SocketClient.java, ServidorBatepapo.java e ClienteBatepapo.java, distribuídas entre as páginas 11 à 21 deste material, executar, analisar, concluir e registrar o funcionamento das mesmas.

## `SocketCliente.java`

**Explicação (papel da classe no projeto):**

- Embrulha um `Socket` com uma `entrada` (`BufferedReader`) e uma `saida` (`PrintWriter` com _autoflush_). É usada **pelos dois lados**: pelo servidor (uma por cliente) e pelo cliente.
- O construtor imprime `Conectado com <endereço do outro lado>!`. Por isso essa linha aparece nos dois consoles.
- `getMessage()` lê uma linha (bloqueante) e devolve `null` se a conexão cair. `sendMsg()` envia uma linha e devolve `false` se der erro. `close()` fecha tudo.

## `ServidorBatepapo.java`

**Saída (console do servidor):**

```
Servidor iniciado na porta: 4000
Aguardando conexao de um cliente!
Conectado com /127.0.0.1:58940!
<- Cliente /127.0.0.1:58940: bom dia
<- Cliente /127.0.0.1:58940: tudo bem
```

**Explicação:**

- O servidor abre a porta 4000 e espera no `accept()` (bloqueante).
- Quando o cliente conecta, o servidor:
  1. cria um `SocketCliente`;
  2. adiciona o cliente na lista `clients`;
  3. cria uma thread só para ele.
- A thread imprime cada mensagem recebida e chama `sendMsgToAll`, que repassa a mensagem **para todos os outros clientes**. Como só havia um cliente, nada foi repassado.
- O `sair` não aparece no console porque o `return` vem antes do `printf`. A conexão é fechada, mas o cliente **continua na lista**.
- O servidor não termina sozinho (`while(true)`).

## `ClienteBatepapo.java`

**Saída (console do cliente):**

```
Conectado com /127.0.0.1:4000!
Digite uma mensagem (ou <sair> para finalizar):
<- bom dia
<- tudo bem
<- sair
Cliente finalizado!
```

**Explicação:**

- O cliente conecta em `127.0.0.1:4000` e usa **duas threads**:
  - `main` (`messageLoop()`): lê o teclado e **envia**;
  - `run()`: lê a rede e **imprime o que chega** (`-> ...`).

  Assim dá para digitar e receber ao mesmo tempo. Nada chegou porque não havia outros clientes.
- Ao digitar `sair`, a mensagem é enviada ao servidor, o laço termina, o socket é fechado (o que também encerra a thread `run()`) e aparece `Cliente finalizado!`.

## Conclusão

O ProjetoBatepapo é um chat cliente-servidor:

- **Servidor:** uma thread por cliente e uma lista de conectados, usada para repassar cada mensagem aos outros.
- **Cliente:** duas threads, uma para enviar e outra para receber, o que torna a comunicação bidirecional.

Com um só cliente, o teste confirmou conexão, envio e saída. A retransmissão entre clientes aparece nos exercícios 7 e 8.
