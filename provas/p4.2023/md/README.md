# P4 2023 – Chat Cliente-Servidor (ECM251)

Chat com **um servidor** e **exatamente dois clientes**, com comunicação bidirecional entre eles.
- **Clientes:** interface gráfica (Swing), sem nada no console.
- **Servidor:** console com o status das conexões e todas as mensagens trocadas, identificando quem mandou.

---

## 1. Arquivos

| Arquivo | O que é |
|---|---|
| `Servidor.java` | Servidor do chat: abre a porta 4000, aceita até 2 clientes, cria uma thread por cliente e repassa as mensagens |
| `Cliente.java` | Janela do chat (Swing): conecta ao servidor, envia mensagens e tem uma thread que fica ouvindo o que chega |
| `SocketCliente.java` | "Embrulho" de **uma** conexão: o `Socket` + o canal de leitura + o canal de escrita. Usado pelos dois lados |
| `p4-2023.pdf` | Enunciado da prova |
| `out/` | Pasta gerada pelo `javac` com os `.class` |
| `Servidor.jar` / `Cliente.jar` | Executáveis gerados pelo `jar` (ver seção 4) |

---

## 2. Métodos de cada classe

### SocketCliente
| Método | O que faz |
|---|---|
| `SocketCliente(Socket)` | Recebe um socket já conectado e cria a `entrada` (`BufferedReader`) e a `saida` (`PrintWriter` com autoFlush). Imprime "Conectado com IP:porta" |
| `getRemoteSocketAddress()` | Devolve o IP:porta do outro lado. É usado para identificar o remetente no console do servidor |
| `getMessage()` | Lê **uma linha**. É **bloqueante** (espera chegar algo). Devolve `null` quando o outro lado desconectou |
| `sendMsg(String)` | Envia uma linha. Devolve `false` se o envio falhou |
| `close()` | Fecha a entrada, a saída e o socket |

### Servidor
| Método | O que faz | Caso de teste |
|---|---|---|
| `main` | Cria o servidor e chama `iniciar()`. Se a porta já estiver em uso, mostra o erro e termina | 3 |
| `iniciar()` | Abre o `ServerSocket` na porta 4000, avisa no console e chama `aguardarConexoes()` | 2 |
| `aguardarConexoes()` | Loop infinito em `accept()`. Se já houver 2 clientes, chama `recusarCliente`; senão, adiciona o cliente à lista e cria uma **thread** com `atenderCliente` | 4, 5 |
| `recusarCliente(SocketCliente)` | Manda `SERVIDOR_CHEIO` ao 3º cliente, fecha a conexão dele e registra a recusa no console | 5 |
| `atenderCliente(SocketCliente)` | Roda na thread de cada cliente: lê as mensagens em loop, mostra no console com o remetente e chama `repassarMensagem`. Termina com `null` ou `"sair"` e, no `finally`, chama `removerCliente` | Requisito II |
| `repassarMensagem(SocketCliente, String)` | Envia a mensagem a todos **menos o remetente**, ou seja, sem eco na origem. Se o envio a alguém falhar, remove essa pessoa da lista | 6, 7 |
| `removerCliente(SocketCliente)` | Tira o cliente da lista, fecha a conexão e mostra quantos clientes continuam conectados. Assim a vaga é liberada | — |

**Por que threads?** `accept()` e `readLine()` são bloqueantes. Com uma thread só, o servidor ficaria preso esperando um cliente e não atenderia o outro. A thread `main` só aceita conexões, e cada cliente conversa na sua própria thread.

### Cliente (`extends JFrame implements Runnable`)
| Método | O que faz | Caso de teste |
|---|---|---|
| `Cliente()` | Chama `montarTela()` e depois `conectar()` | — |
| `montarTela()` | Monta a janela: conversa ao centro, campo de mensagem, botões **Enviar / Limpar / Sair** e área de **status**. Enter também envia, e o X da janela equivale a Sair | — |
| `status(String)` | Escreve na área "Status da comunicação" | 4 |
| `limpar()` | Apaga a área de conversa (só na tela local) | — |
| `conectar()` | Abre o socket para `127.0.0.1:4000`. Se der certo, mostra o status e inicia a thread `run()`. Se não houver servidor, mostra o erro e fecha | 1, 4 |
| `run()` | Thread que fica **ouvindo** o servidor. Mostra as mensagens recebidas; se receber `SERVIDOR_CHEIO`, avisa e fecha | 5, 6, 7 |
| `enviarMensagem()` | Lê o campo, envia ao servidor, mostra "Eu: ..." na conversa e limpa o campo. Digitar `sair` encerra o cliente | 6, 7 |
| `exibirMensagem(String)` | Acrescenta uma linha na conversa usando `SwingUtilities.invokeLater`, porque quem chama é a thread do `run()` | — |
| `sair()` | Avisa o servidor (`"sair"`), fecha a conexão e a janela | — |
| `main` | Cria a janela na thread do Swing | — |

**Por que duas threads no cliente?** Uma é a do Swing, que trata os cliques e o envio. A outra é a do `run()`, que fica presa em `getMessage()` esperando o servidor. Se fosse uma só, a janela travaria esperando mensagem.

---

## 3. Compilar e rodar pelas classes (para testar)

Em todos os terminais, primeiro entre na pasta:
```
cd C:\Users\amand\www\ECM251\provas\p4.2023
```

Compilar (gera os `.class` na pasta `out`):
```
javac --release 17 -encoding UTF-8 -d out *.java
```
- `--release 17`: gera código que roda em Java 17 ou mais novo. Sem isso, sai para o Java 25 e pode dar `UnsupportedClassVersionError` no PC do professor.
- `-encoding UTF-8`: evita problemas com os acentos.

Rodar:
```
java -cp out Servidor
java -cp out Cliente
```

---

## 4. Gerar os .jar (obrigatório na prova)

Sem um .jar funcionando, a questão é anulada.

O .jar é um zip com os `.class` e um arquivo `META-INF/MANIFEST.MF` que diz qual classe tem o `main` (`Main-Class`). Os dois .jar levam as 3 classes; só muda a classe principal.

No meu PC o `jar` não está no PATH, então uso o caminho completo. **PowerShell**, depois de compilar (seção 3):
```
& "C:\Program Files\Java\jdk-25.0.2\bin\jar.exe" cfe Servidor.jar Servidor -C out .
& "C:\Program Files\Java\jdk-25.0.2\bin\jar.exe" cfe Cliente.jar Cliente -C out .
```
- `c` = criar, `f` = nome do arquivo, `e` = classe principal (entry point).
- `-C out .` = pegar tudo que está dentro da pasta `out`.

Conferir a classe principal (opcional):
```
& "C:\Program Files\Java\jdk-25.0.2\bin\jar.exe" tf Cliente.jar
```

---

## 5. Instruções de execução para o professor (colar no .docx)

> Requisito: Java 17 ou superior instalado.
>
> 1. Abra um terminal (Prompt de Comando ou PowerShell) na pasta onde estão `Servidor.jar` e `Cliente.jar`.
> 2. **Servidor:** execute `java -jar Servidor.jar` e mantenha esse terminal aberto. O console mostra o status das conexões e todas as mensagens trocadas, identificando o remetente (IP:porta).
> 3. **Clientes:** execute `Cliente.jar` duas vezes, com duplo clique ou com `java -jar Cliente.jar` em outros terminais. Cada execução abre uma janela de chat.
> 4. Digite a mensagem no campo "Mensagem" e clique em **Enviar** (ou tecle Enter). A mensagem aparece na janela do outro cliente. **Limpar** apaga a conversa da janela e **Sair** (ou o X) encerra o cliente.
> 5. Para encerrar o servidor, pressione **Ctrl+C** no terminal dele.
>
> ⚠️ Execute o **servidor pelo terminal**, não com duplo clique. Com duplo clique ele roda sem console e mantém a porta 4000 ocupada; nesse caso, encerre o processo `java.exe` pelo Gerenciador de Tarefas.

---

## 6. Casos de teste (na ordem do enunciado)

| # | O que fazer | Resultado esperado |
|---|---|---|
| 1 | Rodar um Cliente **sem** servidor | Janela de erro "Servidor indisponível" e o cliente fecha |
| 2 | Rodar o Servidor | Console: "Servidor iniciado na porta: 4000" / "Aguardando conexao de um cliente!" |
| 3 | Rodar um 2º Servidor | "Erro ao iniciar o servidor: Address already in use: bind" e ele termina |
| 4 | Rodar o Cliente 1 e o Cliente 2 | Status "Conectado a /127.0.0.1:4000. Aguardando mensagens..."; o servidor mostra os dois conectados |
| 5 | Rodar um 3º Cliente | Janela "Já há 2 clientes conectados." e ele fecha; o servidor mostra "Conexão recusada" |
| 6 | Escrever no Cliente 1 | Aparece no Cliente 2 e **não** volta para o Cliente 1; o servidor mostra a mensagem com o remetente |
| 7 | Escrever no Cliente 2 | Aparece no Cliente 1 e **não** volta para o Cliente 2 |

**Erros comuns:**
- `Connection refused` no cliente → o servidor não está rodando.
- `Address already in use` → já existe um servidor aberto na porta 4000. Feche-o com Ctrl+C ou pelo Gerenciador de Tarefas.
- `UnsupportedClassVersionError` → compile com `--release 17` e gere os .jar de novo.
