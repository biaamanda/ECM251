# Aula 20 – Exercício 1 (Cliente-Servidor com Java Sockets)

## a. Projeto `SimpleClientServer`

As classes `SimpleServerTest.java` e `SimpleClientTest.java` foram digitadas em [SimpleClientServer/](SimpleClientServer/).

Para compilar e executar pelo terminal (dentro de `SimpleClientServer`):

```
javac *.java
java SimpleServerTest
java SimpleClientTest
```

---

## b. Executando somente o `SimpleClientTest`

**Saída registrada:**

```
Falha na comunicacao: Connection refused: connect
```

**Explicação:** o método `iniciaCliente()` tenta criar um `Socket` apontando para `127.0.0.1:3334`.
Como nenhum processo está escutando nessa porta (o servidor não foi iniciado), o sistema operacional
recusa a conexão TCP (responde com RST ao pedido de conexão). O construtor de `Socket` lança uma
`ConnectException` (subclasse de `IOException`), que é capturada pelo `catch` do `main`, imprimindo
a mensagem de falha. A janela do `JOptionPane` nem chega a aparecer, pois a exceção ocorre antes do
laço `do/while`, e o programa termina.

---

## c. Executando o `SimpleServerTest`

**Saída registrada:**

```
Servidor iniciado na porta 3334
```

**Explicação:** o `ServerSocket` é criado e associado (bind) à porta 3334, passando a "escutar"
conexões. Em seguida o servidor chama `servidor.accept()`, que é um método **bloqueante**: a thread
principal fica parada ali, esperando que algum cliente se conecte. Por isso o programa não termina e
nada mais é impresso até um cliente chegar.

---

## d. Servidor em execução + um cliente enviando mensagens e saindo

**Saída do servidor:**

```
Servidor iniciado na porta 3334
Cliente do IP: 127.0.0.1 conectado ao servidor
Mensagens do Cliente:
ola
mensagem 2
mensagem 3
Servidor finalizado!
```

**Saída do cliente:**

```
Cliente: 127.0.0.1:3334 conectado ao servidor!
Mensagens para o servidor:
ola
mensagem 2
mensagem 3
Cliente se desconectou do servidor!
```

**Explicação:**

1. O cliente cria o `Socket` e a conexão TCP é estabelecida; o `accept()` do servidor retorna o
   socket correspondente a esse cliente e o servidor imprime o IP dele.
2. O servidor cria um `Scanner` sobre o `InputStream` do socket e entra no laço
   `while (entrada.hasNextLine())`, que bloqueia esperando linhas.
3. A cada mensagem digitada no `JOptionPane`, o cliente a imprime no próprio console e a envia com
   `PrintStream.println(msg)`. A linha chega ao servidor, `hasNextLine()` retorna `true` e o servidor
   imprime a mensagem.
4. Ao digitar `sair`, o laço do cliente termina, ele imprime "Cliente se desconectou do servidor!" e o
   `main` acaba. O socket não é fechado explicitamente, mas ao encerrar a JVM o sistema operacional
   fecha a conexão (envia FIN).
5. No servidor, `hasNextLine()` passa a retornar `false` (fim do fluxo), o laço termina, é impresso
   "Servidor finalizado!" e o `Scanner` e o `ServerSocket` são fechados. **O servidor também termina**,
   pois só atende um cliente.

> Observação: se o usuário clicar em **Cancelar** ou fechar a janela do `JOptionPane`, `msg` fica
> `null` e `msg.equalsIgnoreCase(...)` lança `NullPointerException`, que não é tratada (o `catch` só
> pega `IOException`). O cliente termina com erro e o servidor também finaliza, pois a conexão é fechada.

---

## e. Servidor + primeira instância do cliente (sem sair) + segunda instância do cliente

> Como no item (d) o servidor se encerra quando o cliente sai, é preciso executá-lo novamente antes
> deste item.

**Saída registrada (servidor):**

```
Servidor iniciado na porta 3334
Cliente do IP: 127.0.0.1 conectado ao servidor
Mensagens do Cliente:
c1: primeira
c1: segunda
```

**Segunda instância do cliente:**

```
Cliente: 127.0.0.1:3334 conectado ao servidor!
Mensagens para o servidor:
c2: oi
c2: tudo bem?
```

**Explicação:**

- A primeira instância se conecta normalmente e sua mensagem aparece no servidor.
- A segunda instância **também informa que está conectada** e não gera erro. Isso acontece porque o
  sistema operacional mantém uma **fila de conexões pendentes (backlog)** para o `ServerSocket`: o
  *handshake* TCP é completado pelo SO mesmo que o programa não tenha chamado `accept()` de novo.
- Porém o servidor chamou `accept()` **uma única vez** e está preso no laço de leitura do primeiro
  cliente. A conexão do segundo cliente nunca é aceita pela aplicação, então as mensagens enviadas por
  ele ficam paradas no buffer do SO e **nunca aparecem no servidor**.
- Ao voltar a digitar na primeira instância, as mensagens dela continuam aparecendo normalmente no
  servidor, pois é o único cliente que ele está atendendo.

Conclusão: este servidor é **mono-cliente** e **sequencial** (sem threads); para atender vários
clientes seria preciso chamar `accept()` em laço e tratar cada cliente em uma thread separada.

---

## f. Encerrando a segunda instância e digitando na primeira

**Saída registrada (servidor):**

```
...
c1: segunda
c1: apos fechar c2
```

**Explicação:** encerrar a segunda instância apenas fecha uma conexão que estava na fila de pendentes
do SO e que nunca foi aceita pelo servidor. O servidor não percebe nada (não há mensagem, erro nem
finalização). As mensagens digitadas na primeira instância continuam chegando e sendo impressas
normalmente. As mensagens que o segundo cliente havia "enviado" são simplesmente descartadas.

---

## g. Executando uma segunda instância do `SimpleServerTest`

**Saída registrada (segunda instância):**

```
Erro ao criar o servidor!
```

**Explicação:** a porta 3334 já está associada (bind) ao `ServerSocket` da primeira instância. Uma
porta TCP só pode ter um processo escutando nela no mesmo endereço, então o construtor
`new ServerSocket(PORTA)` lança uma `BindException` ("Address already in use: bind"), subclasse de
`IOException`. Ela é capturada pelo `catch`, que imprime "Erro ao criar o servidor!", e a segunda
instância termina. A primeira instância do servidor não é afetada e continua funcionando.
