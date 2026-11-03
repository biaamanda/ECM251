# Exercício 7 – ProjetoMultiClientServer

**Como foi testado:** o roteiro foi seguido na ordem do enunciado, com o servidor e três clientes. Os testes rodaram numa cópia do projeto com a porta **4001**, porque a 4000 estava ocupada por outro servidor em execução. Fora isso, o código é idêntico. Os números de porta dos clientes (`54062`, `54064`...) são escolhidos pelo SO e mudam a cada execução.

As mensagens dos clientes foram enviadas automaticamente pela entrada padrão. Por isso, nas saídas dos clientes, o texto "digitado" foi incluído à mão, para ficar como aparece num console interativo.

## a. Criar o projeto e digitar as classes

As classes `ClientSocket.java`, `Server.java` e `Client.java` foram digitadas na pasta `ProjetoMultiClientServer/`. Elas formam um projeto só, porque dependem umas das outras:
- o `Client` usa as constantes `Server.ADDRESS` e `Server.PORT`;
- o `Server` usa o `ClientSocket`.

Para compilar e rodar pelo console:

```
javac -encoding UTF-8 *.java
java Server
java Client
```

## b. Executar somente Client.java

**O que ocorre (saída):**

```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Erro ao iniciar o cliente: Connection refused: connect
Cliente finalizado!
```

**Explicação:**

- O `start()` do cliente tenta abrir uma conexão com `new Socket(Server.ADDRESS, Server.PORT)`, ou seja, com `127.0.0.1` na porta do servidor.
- Como **nenhum programa está escutando nessa porta**, o SO recusa a conexão e o construtor do `Socket` lança `ConnectException` (uma `IOException`).
- A exceção é capturada no `catch` do `main`, que imprime `Erro ao iniciar o cliente: ...`. Depois o programa segue para `Cliente finalizado!` e termina.
- O cliente **não espera** o servidor aparecer: tenta uma única vez e desiste. Por isso o servidor sempre precisa ser iniciado primeiro.

## c. Executar Server.java

**O que ocorre (saída):**

```
*v*v*v* CONSOLE DO SERVIDOR *v*v*v*
Servidor iniciado na porta: 4001
Aguardando conexao de um cliente!
```

**Explicação:**

- O `start()` cria um `ServerSocket` na porta definida e reserva essa porta no SO para receber conexões.
- O `clientConnectionLoop()` imprime `Aguardando conexao de um cliente!` e para em `serverSocket.accept()`. Esse método é **bloqueante**: a thread `main` fica parada nessa linha até algum cliente conectar.
- O programa **não termina**: fica rodando indefinidamente, esperando clientes. A mensagem `Servidor finalizado!` não aparece.

## d. Primeira instância do Client: algumas mensagens e sair

**O que ocorre (saída):**

Cliente 1:
```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Cliente 127.0.0.1:4001 conectado ao servidor!
Aguardando a digitação de uma mensagem!
Digite uma mensagem (ou <sair> para finalizar): d - mensagem 1
Digite uma mensagem (ou <sair> para finalizar): d - mensagem 2
Digite uma mensagem (ou <sair> para finalizar): sair
Cliente finalizado!
```

Servidor:
```
Cliente /127.0.0.1:54062 se conectou!
Mensagem recebida do cliente /127.0.0.1:54062: d - mensagem 1
Mensagem recebida do cliente /127.0.0.1:54062: d - mensagem 2
```

**Explicação:**

- O `accept()` do servidor é desbloqueado e devolve o `Socket` da conexão. O construtor do `ClientSocket` imprime `se conectou!` com o endereço do cliente (IP + porta de origem).
- O servidor cria uma **thread nova** só para esse cliente (`new Thread(() -> clientMessageLoop(clientSocket)).start()`) e a thread `main` volta para o `accept()`, esperando o próximo cliente.
- A thread do cliente fica em laço lendo `getMessage()` e imprime cada mensagem recebida.
- Quando chega `sair`, a condição do `while` é falsa, então a palavra `sair` **não é impressa**. O `finally` chama `clientSocket.close()` e a thread termina. O servidor não imprime nada quando um cliente sai.
- **O servidor continua rodando** depois que o cliente sai. Nos servidores das aulas 20 e 21 acontecia o contrário: com o fim do único cliente, o servidor encerrava.

## e. Primeira instância do Client novamente, sem sair

**O que ocorre (saída):**

Cliente 1:
```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Cliente 127.0.0.1:4001 conectado ao servidor!
Aguardando a digitação de uma mensagem!
Digite uma mensagem (ou <sair> para finalizar): e - cliente 1, msg 1
Digite uma mensagem (ou <sair> para finalizar): e - cliente 1, msg 2
Digite uma mensagem (ou <sair> para finalizar):
```

Servidor:
```
Cliente /127.0.0.1:54064 se conectou!
Mensagem recebida do cliente /127.0.0.1:54064: e - cliente 1, msg 1
Mensagem recebida do cliente /127.0.0.1:54064: e - cliente 1, msg 2
```

**Explicação:**

- Como o servidor continuou no ar depois do item **d**, o novo cliente conecta normalmente e é atendido por uma **nova thread**.
- Para o servidor, é um **cliente novo**: tem outra porta de origem (54064 em vez de 54062), e o servidor não guarda nenhuma memória da conexão anterior.
- O cliente fica parado no `scanner.nextLine()`, esperando o próximo texto. A thread do servidor que o atende fica parada no `readLine()`, esperando a próxima mensagem. A conexão continua aberta.

## f. Segunda instância do Client, sem sair

**O que ocorre (saída):**

Cliente 2:
```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Cliente 127.0.0.1:4001 conectado ao servidor!
Aguardando a digitação de uma mensagem!
Digite uma mensagem (ou <sair> para finalizar): f - cliente 2, msg 1
Digite uma mensagem (ou <sair> para finalizar): f - cliente 2, msg 2
Digite uma mensagem (ou <sair> para finalizar):
```

Servidor:
```
Cliente /127.0.0.1:54070 se conectou!
Mensagem recebida do cliente /127.0.0.1:54070: f - cliente 2, msg 1
Mensagem recebida do cliente /127.0.0.1:54070: f - cliente 2, msg 2
```

**Explicação:**

- **O segundo cliente é atendido mesmo com o primeiro ainda conectado.** Essa é a principal diferença em relação ao servidor da aula 21, em que a segunda instância conectava mas nunca era atendida.
- Isso funciona porque a thread `main` do servidor só faz `accept()` e repassa cada cliente para uma thread própria. A thread do cliente 1 continua bloqueada no `readLine()` dela, sem impedir a thread do cliente 2 de receber mensagens.
- Agora o servidor tem 3 threads trabalhando: `main` (no `accept()`), a do cliente 1 e a do cliente 2.

## g. Terceira instância do Client, sem sair

**O que ocorre (saída):**

Cliente 3:
```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Cliente 127.0.0.1:4001 conectado ao servidor!
Aguardando a digitação de uma mensagem!
Digite uma mensagem (ou <sair> para finalizar): g - cliente 3, msg 1
Digite uma mensagem (ou <sair> para finalizar): g - cliente 3, msg 2
Digite uma mensagem (ou <sair> para finalizar):
```

Servidor:
```
Cliente /127.0.0.1:53597 se conectou!
Mensagem recebida do cliente /127.0.0.1:53597: g - cliente 3, msg 1
Mensagem recebida do cliente /127.0.0.1:53597: g - cliente 3, msg 2
```

**Explicação:**

- Mesma situação do item **f**: o terceiro cliente ganha a sua própria thread e é atendido normalmente, com os clientes 1 e 2 ainda conectados.
- A porta de origem (53597) é menor que as anteriores. As portas de origem são escolhidas pelo SO e não seguem necessariamente uma ordem.
- Agora são 4 threads no servidor: `main` e uma para cada um dos 3 clientes.

## h. Nova rodada de mensagens nas instâncias 1, 2 e 3

**O que ocorre (saída):**

Servidor:
```
Mensagem recebida do cliente /127.0.0.1:54064: h - cliente 1, msg 3
Mensagem recebida do cliente /127.0.0.1:54070: h - cliente 2, msg 3
Mensagem recebida do cliente /127.0.0.1:53597: h - cliente 3, msg 3
```

Cada cliente mostrou apenas a sua mensagem e voltou a pedir a próxima:
```
Digite uma mensagem (ou <sair> para finalizar): h - cliente N, msg 3
Digite uma mensagem (ou <sair> para finalizar):
```

**Explicação:**

- As três conexões continuam ativas **ao mesmo tempo**, e o servidor recebe as mensagens de qualquer cliente, na ordem em que foram enviadas.
- Pelo endereço de origem, dá para saber de qual cliente veio cada mensagem: 54064 = cliente 1, 54070 = cliente 2, 53597 = cliente 3.
- Os clientes **não veem** as mensagens uns dos outros, nem recebem confirmação do servidor. A comunicação é **unidirecional** (cliente → servidor), porque o servidor nunca chama `sendMsg()` e o `Client` não lê nada do socket.
- Todas as threads do servidor imprimem no mesmo `System.out`. Se dois clientes enviassem no mesmo instante, a ordem das linhas no console seria decidida pelo escalonador do SO.

## i. Encerrar as instâncias 1, 2 e 3 com `sair`, nessa ordem

**O que ocorre (saída):**

Cada cliente:
```
Digite uma mensagem (ou <sair> para finalizar): sair
Cliente finalizado!
```

Servidor: **nenhuma linha nova**. Ele continua mostrando apenas o que já tinha impresso e segue rodando.

**Explicação:**

- Em cada cliente, o `do-while` termina com `sair`, o `start()` retorna e o `main` imprime `Cliente finalizado!`. O processo do cliente termina e o SO fecha a conexão.
- No servidor, a thread de cada cliente recebe `sair`, sai do `while` **sem imprimir nada**, fecha o `ClientSocket` no `finally` e termina. As três threads se encerram uma de cada vez, na ordem em que os clientes saíram, e nenhuma afeta as outras.
- O servidor **não avisa** que um cliente saiu, porque o código não tem essa mensagem. Essa é uma limitação: olhando só o console do servidor, não dá para saber quem ainda está conectado.
- Mesmo sem nenhum cliente, **o servidor continua no ar**, com a thread `main` bloqueada no `accept()`, esperando novas conexões. Ele não sabe quando "o último" cliente saiu. Fazer o servidor encerrar nesse momento é justamente o **Desafio 1**.

## j. Segunda instância do Server.java

**O que ocorre (saída):**

```
*v*v*v* CONSOLE DO SERVIDOR *v*v*v*
Erro ao iniciar o servidor: Address already in use: bind
Servidor finalizado!
```

**Explicação:**

- A segunda instância tenta criar um `ServerSocket` na **mesma porta**, que já está reservada pela primeira instância, ainda em execução.
- O SO permite que só um processo escute numa porta (no mesmo endereço) por vez. Por isso o `new ServerSocket(PORT)` lança `BindException` ("Address already in use: bind"), uma subclasse de `IOException`.
- O `catch` do `main` imprime o erro, depois aparece `Servidor finalizado!` e a segunda instância termina. É o único caso em que essa mensagem aparece.
- **A primeira instância não é afetada** e continua funcionando normalmente.
- Para rodar dois servidores ao mesmo tempo, cada um precisaria de uma porta diferente.
