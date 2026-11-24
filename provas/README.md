# Provas P4 — ECM251 Linguagens de Programação I

Guia das pastas `p4.2023`, `p4.2024` e `p4.2025`: como compilar e gerar o .jar, o que muda de uma prova para outra e os códigos testados.
A teoria e o gabarito comentado estão em [Resumo_Teoria_P4.md](Resumo_Teoria_P4.md).

---

## ⚙️ Passo a passo: compilar e gerar o .jar (computador só com o Java instalado)

Na prova, o .jar **precisa rodar**: em 2023 e 2024 a questão inteira é anulada se não rodar; em 2025 cada .jar com problema tira 1 ponto. São **dois** programas: o **Servidor** e o **Cliente**.

### Passo 1 — Abrir o terminal na pasta dos arquivos `.java`
- No Explorador de Arquivos, abra a pasta onde estão `Servidor.java`, `Cliente.java` e `SocketCliente.java`.
- Clique na barra de endereço, digite `powershell` e aperte **Enter**. O terminal abre já naquela pasta.
- Confira se os arquivos aparecem:

```powershell
dir *.java
```

Se aparecer `Invalid filename: *.java` mais adiante, é porque você **não está na pasta certa**.

### Passo 2 — Ver se o Java responde

```powershell
java -version
```
```powershell
javac -version
```
```powershell
jar --version
```

- Os 3 responderam? Pule para o **Passo 4**.
- `java` e `javac` responderam, mas o **`jar` "não é reconhecido"**? Isso é **normal** em computador com o Java da Oracle (como os da faculdade): o instalador só coloca `java`, `javac`, `javaw` e `jshell` no PATH; o `jar` existe, mas fica escondido na pasta do JDK. Vá para o **Passo 3**, ou pule direto para o **Passo 5 – opção B**, que não precisa do `jar`.
- Nem o `javac` respondeu? Vá para o **Passo 3**.

> **O que é o quê:** a extensão de Java do VS Code só ajuda a editar (cores, autocompletar, botão Run). Quem compila e gera o .jar é o **JDK** (Java Development Kit), instalado à parte. Ele traz o `java` (roda), o `javac` (compila) e o `jar` (empacota).

### Passo 3 — Achar o JDK do computador e ensinar ao terminal onde ele está
O nome da pasta muda de computador para computador (`jdk-17`, `jdk-21.0.2`, `jdk1.8.0_401`…). **Não copie o caminho deste guia**: descubra o do computador da prova:

```powershell
Get-ChildItem "C:\Program Files" -Recurse -Filter jar.exe -ErrorAction SilentlyContinue | Select-Object -ExpandProperty DirectoryName
```

A resposta mostra uma ou mais pastas terminadas em `\bin`, por exemplo `C:\Program Files\Java\jdk-21\bin`. Copie **uma delas** (de preferência a que tem `jdk` no nome) e cole no comando abaixo, no lugar de `PASTA_QUE_APARECEU`. Vale só para essa janela do terminal:

```powershell
$env:Path += ";PASTA_QUE_APARECEU"
```

Repita o Passo 2 para confirmar.

- A busca não achou nada? Tente também `C:\Program Files (x86)` e `C:\Users\SEU_USUARIO\.jdks` (JDKs baixados pelo IntelliJ).
- Achou, mas não quer mexer no PATH? Use o caminho completo entre aspas no Passo 5: `& "PASTA_QUE_APARECEU\jar.exe" cfe Servidor.jar Servidor *.class`
- Não achou o `jar` em lugar nenhum? Use a **opção B ou C do Passo 5**.
- Se nem o `javac` existir (só `jre`, sem `jdk`), não há compilador: use a **opção C do Passo 5** (IDE).

### Passo 4 — Compilar

```powershell
javac --release 8 -Xlint:-options -encoding UTF-8 *.java
```

- Transforma os `.java` em `.class`.
- `--release 8` faz o programa rodar até em computador com Java antigo.
- `-Xlint:-options` só esconde os avisos de "obsolete".
- `-encoding UTF-8` evita problema com os acentos.
- Se aparecer **erro**, é erro no código: leia o nome do arquivo e o número da linha.
- **Mudou algum `.java`? Compile de novo**, senão roda a versão antiga.

### Passo 5 — Gerar os dois .jar (3 opções)

#### Opção A — com o comando `jar` (se o `jar --version` respondeu)
```powershell
jar cfe Servidor.jar Servidor *.class
```
```powershell
jar cfe Cliente.jar Cliente *.class
```

- `c` = **c**riar; `f` = nome do arquivo; `e` = classe que tem o `main`.
- Os dois levam **todos** os `.class` (`*.class`); só muda a classe principal.

#### Opção B — SEM o comando `jar`, só com `java`/`javac` ✅ (testada com o `jar` fora do PATH)
Use a ferramenta [`ferramentas/CriaJar.java`](ferramentas/CriaJar.java). **Leve uma cópia no pendrive / no material da prova.**
1. Copie o `CriaJar.java` para a mesma pasta dos seus `.java`.
2. Compile tudo junto (Passo 4 — o `*.java` já inclui o `CriaJar.java`).
3. Gere os dois .jar:
```powershell
java CriaJar Servidor.jar Servidor
```
```powershell
java CriaJar Cliente.jar Cliente
```
- Ela coloca todos os `.class` da pasta no .jar (menos ela mesma) e escreve o `MANIFEST.MF` com a classe principal — exatamente o que o `jar cfe` faz.
- Rode numa pasta que só tenha os arquivos da questão (ela também entra nas subpastas, para o caso de usar `package`).
- **Não entregue** o `CriaJar.java` junto com a questão.

#### Opção C — pela IDE (sem terminal)
- **VS Code** (com o Extension Pack for Java): abra a **pasta** da questão (*File → Open Folder*) → no Explorer, seção **JAVA PROJECTS** → ícone **Export Jar** (seta ➜) → escolha a classe principal (**Servidor**). Repita escolhendo **Cliente** e renomeie os arquivos para `Servidor.jar` e `Cliente.jar` (o VS Code dá o nome da pasta para os dois).
- **IntelliJ:** *File → Project Structure → Artifacts → + → JAR → From modules with dependencies* (escolha a classe main) → *Build → Build Artifacts*. Um artifact para cada classe principal.
- **Eclipse:** *File → Export → Java → Runnable JAR file*.
- Pela IDE não dá para escolher o `--release 8`: o .jar sai para a versão do Java daquele computador. Como o professor corrige no mesmo tipo de máquina, normalmente não há problema.

### Passo 6 — Testar os .jar
Feche qualquer servidor antigo antes (**Ctrl+C** no terminal dele, ou Gerenciador de Tarefas → `java.exe`). Use **um terminal para cada programa** (todos na pasta do Passo 1):

| Terminal | Comando | Para quê |
|---|---|---|
| 1 | `java -jar Servidor.jar` | Servidor. **Deixe aberto**: mostra o status e as mensagens |
| 2 | `java -jar Cliente.jar` | Cliente 1 |
| 3 | `java -jar Cliente.jar` | Cliente 2 |
| 4 | `java -jar Cliente.jar` | Cliente 3 (deve ser recusado) |

Rode **todos os casos de teste** do enunciado (ver a tabela de comparação abaixo). Teste também o **duplo clique** no `Cliente.jar`.
⚠️ **Nunca** abra o `Servidor.jar` com duplo clique: ele fica sem console e prende a porta 4000.

Para testar sem gerar o .jar (enquanto desenvolve): `java Servidor` e `java Cliente`, depois do Passo 4.

### Passo 7 — Escrever no .docx da prova como executar
O professor segue **exatamente** o que você escrever. Modelo:

> Requisito: Java 8 ou superior.
> 1. Abra um terminal na pasta `Questao5` e execute `java -jar Servidor.jar`. Mantenha o terminal aberto: nele aparecem o status das conexões e as mensagens trocadas, com o remetente (IP:porta).
> 2. Execute o `Cliente.jar` duas vezes (duplo clique ou `java -jar Cliente.jar` em outros terminais). Cada execução abre uma janela de chat.
> 3. Digite no campo "Mensagem" e clique em **Enviar** (ou Enter). **Limpar** apaga a conversa; **Sair** (ou o X) encerra o cliente.
> 4. Para encerrar o servidor, pressione **Ctrl+C** no terminal dele. Não abra o `Servidor.jar` com duplo clique.

### Passo 8 — Montar a entrega
```
RA_NomeCompleto.zip
├── P4_RA_Nome.docx        ← respostas Q1–Q4 + RA e nome + instruções do Passo 7
└── Questao5/
    ├── Servidor.java   Cliente.java   SocketCliente.java
    ├── Servidor.jar    Cliente.jar
    └── LEIA-ME.txt     ← as mesmas instruções
```

```powershell
Compress-Archive -Path .\P4_RA_Nome.docx, .\Questao5 -DestinationPath .\RA_NomeCompleto.zip
```

Não coloque os `.class` soltos no .zip.

### Se estiver no Prompt de Comando (cmd) em vez do PowerShell
Os comandos dos Passos 4 a 6 são iguais. Muda só o Passo 3:

```bat
where /r "C:\Program Files" jar.exe
```
```bat
set PATH=%PATH%;PASTA_QUE_APARECEU
```

### Erros comuns
| Mensagem | Causa | Solução |
|---|---|---|
| `Invalid filename: *.java` | terminal fora da pasta dos `.java` | Passo 1 |
| `'jar' is not recognized` (mas `java` e `javac` funcionam) | o instalador da Oracle não põe o `jar` no PATH | Passo 3, ou **Passo 5 – opção B** (`CriaJar`) |
| `'javac' is not recognized` | o terminal não sabe onde está o JDK (ou só há JRE) | Passo 3, ou Passo 5 – opção C (IDE) |
| `no main manifest attribute` | faltou o `e` ou o nome da classe no `jar cfe` | refaça o Passo 5 |
| `NoClassDefFoundError` | o .jar ficou sem alguma classe, ou o nome do `main` está errado | use `*.class` e confira o nome |
| `UnsupportedClassVersionError` | compilou sem `--release 8` e o Java do PC é antigo | refaça o Passo 4 |
| `Unable to access jarfile` | o .jar não existe nessa pasta | refaça o Passo 5 |
| `Address already in use: bind` | já existe um servidor aberto na porta 4000 | **Ctrl+C** no servidor antigo ou Gerenciador de Tarefas → `java.exe` |
| `Connection refused` / "Servidor indisponível" | o cliente abriu sem servidor rodando | abra o `Servidor.jar` primeiro |
| Acentos como `conex�o` no console | codificação do terminal do Windows | não é erro; pode ignorar |

---

## 📊 Comparação das três provas

Todas: **90 min**, sem internet (só o LMS para baixar e entregar), consulta só ao material **salvo no computador ou pendrive**, **4 testes de 1 pt + 1 questão prática de 6 pts** em Java orientado a objetos.

### Questão prática

| | **P4 2023** (28/11/2023) | **P4 2024** (19/11/2024) | **P4 2025** (18/11/2025) |
|---|---|---|---|
| **O que construir** | Chat entre **exatamente 2 clientes** através de um servidor | igual | igual |
| **Cliente** | Interface em **janela (Swing)**, nada de console | igual | igual, e cada um se identifica como **Cliente #1 / #2** |
| **Servidor** | **Console**: status das conexões + todas as mensagens com o remetente | igual | igual |
| **Máx. de clientes** | **2**; o 3º é recusado e fecha | 2 | 2 |
| **Cliente sem servidor** | erro e fecha | igual | igual |
| **2º servidor** | erro de porta em uso | igual | igual |
| **Aviso ao Cliente 1** | — | 🆕 "conectado, comunicação **com o servidor** ativa" | 🆕 "Cliente #1 conectado, **aguardando o segundo cliente**" |
| **Aviso ao Cliente 2** | "comunicação com o outro cliente ativa" | igual | igual, como "Cliente #2" |
| **Eco das mensagens** | sem eco na origem | sem eco na origem | 🆕 sem eco na origem **nem no servidor** |
| **Quando alguém sai** | — | — | 🆕 se um cliente **ou o servidor** sair, o **outro cliente fecha sozinho** |
| **Nº de casos de teste** | 7 | 8 | 9 |
| **.jar com problema** | **questão anulada** | **questão anulada** | **−1 ponto** por .jar |
| **Uso de IA** | — | — | 🆕 **proibido** |
| **Plataforma** | Open LMS | CanvasLMS | CANVAS LMS |
| **O código precisa ter** | thread por cliente, limite de 2 (`SERVIDOR_CHEIO`), repasse sem eco | + avisos `AGUARDANDO` / `CONECTADO` | + número do cliente (`ID:n`), `DESCONECTAR` e fechar quando o servidor cai |

### Questões teóricas

| Tema | **2023** | **2024** | **2025** | Resposta |
|---|---|---|---|---|
| Arquitetura Cliente-Servidor (I–IV) | Q1 | Q1 | Q2 | **II e IV** |
| MVC (I–IV) | Q2 | Q2 | — | **II e III** |
| Padrões de Projeto (asserção-razão) | Q3 | Q3 | — | **b** (sem gabarito oficial) |
| Stream (asserção-razão) | Q4 | Q4 | Q1 | 2023/24: **b** · 2025: **e** ("I falsa, II verdadeira", alternativa nova) |
| Thread por cliente no chat (asserção-razão) | — | — | 🆕 Q3 | **e** |
| Multithreading no servidor de chat (I–IV) | — | — | 🆕 Q4 | **a) I, II e IV** |

**Em uma frase:** 2023 e 2024 têm a **mesma teoria** e quase a mesma prática (2024 só acrescenta o aviso ao Cliente 1). Em **2025** saem MVC e Padrões de Projeto, entram **2 questões de threads**, e a prática passa a exigir **numeração dos clientes** e **desconexão em cadeia**.

---

## 💻 Códigos

Códigos Java **testados** com clientes simulados (os casos do servidor; as janelas precisam ser testadas à mão). Os arquivos `.java` estão nas pastas `p4.2023` e `p4.2024`.

| Pasta | Código | .jar | Testado |
|---|---|---|---|
| `p4.2023` | ✅ | ❌ (gerar com os Passos 4 e 5) | ✅ servidor |
| `p4.2024` | ✅ | ✅ | ✅ `Servidor.jar` casos 2–8 · ⏳ janelas do `Cliente.jar` |
| `p4.2025` | ❌ (ver seção 3) | ❌ | ✅ versão da seção 3 testada numa cópia |

### Como gerar e rodar
Na pasta dos `.java`:
```powershell
javac --release 8 -Xlint:-options -encoding UTF-8 *.java
```
```powershell
jar cfe Servidor.jar Servidor *.class
```
```powershell
jar cfe Cliente.jar Cliente *.class
```
Depois: `java -jar Servidor.jar` em um terminal e `java -jar Cliente.jar` em outros. Passo a passo completo no início deste arquivo.
Se o `jar` não for reconhecido (comum nos computadores da faculdade): `java CriaJar Servidor.jar Servidor` e `java CriaJar Cliente.jar Cliente` (Passo 5 – opção B).

---

### 1. Base — chat com 2 clientes (P4 2024)
**Use quando:** a questão pedir comunicação entre clientes através de um servidor (chat).
**Ideia:**
- O **servidor** fica num loop de `accept()`. Cada cliente aceito ganha uma **thread** própria, porque `readLine()` é **bloqueante**. A lista `clients` controla o limite de 2.
- O **cliente** tem **duas threads**: a do Swing (botões/envio) e a do `run()`, que fica ouvindo o servidor.
- Servidor e cliente "conversam" com **palavras de controle**: `SERVIDOR_CHEIO`, `AGUARDANDO`, `CONECTADO`.

**Fluxo:**
| Evento | Servidor faz | Cliente mostra |
|---|---|---|
| Cliente 1 entra | adiciona, cria thread, manda `AGUARDANDO` | "Comunicação com o servidor ativa" + "Aguardando o segundo cliente" |
| Cliente 2 entra | adiciona, cria thread, manda `CONECTADO` para os **dois** | "Comunicação com o outro cliente ativa" |
| Cliente 3 entra | manda `SERVIDOR_CHEIO` e fecha | aviso e fecha |
| Mensagem | mostra no console e repassa para **todos menos o remetente** | `-> Cliente IP:porta: texto` |

#### SocketCliente.java — "embrulho" de UMA conexão (usado pelos dois lados)
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketCliente {
    private final Socket socket;            // a conexão (IP + porta dos dois lados)
    private final BufferedReader entrada;   // para LER o que chega
    private final PrintWriter saida;        // para ESCREVER o que sai

    public SocketCliente(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Conectado com " + socket.getRemoteSocketAddress() + "!");
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.saida = new PrintWriter(socket.getOutputStream(), true);   // true = autoFlush
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    // BLOQUEANTE: espera chegar uma linha. null = o outro lado desconectou
    public String getMessage() {
        try {
            return entrada.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    // false = o envio falhou
    public boolean sendMsg(String msg) {
        saida.println(msg);
        return !saida.checkError();
    }

    public void close() {
        try {
            entrada.close();
            saida.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Erro ao fechar o socket: " + ex.getMessage());
        }
    }
}
```

#### Servidor.java — console, até 2 clientes, 1 thread por cliente
```java
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;

public class Servidor {
    public static final String ENDERECO = "127.0.0.1";
    public static final int PORT = 4000;
    public static final int MAX_CLIENTES = 2;

    private ServerSocket serverSocket;
    private final LinkedList<SocketCliente> clients = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
        try {
            Servidor server = new Servidor();
            server.iniciar();
        } catch (IOException ex) {                       // caso 3: porta em uso
            System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
        }
        System.out.println("Servidor finalizado!");
    }

    public void iniciar() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta: " + PORT);
        aguardarConexoes();
    }

    private void aguardarConexoes() throws IOException {
        System.out.println("Aguardando a conexão de até 2 clientes");   // caso 2
        while (true) {
            SocketCliente clientSocket = new SocketCliente(serverSocket.accept());
            if (clients.size() >= MAX_CLIENTES) {
                recusarCliente(clientSocket);                          // 3º cliente
            } else {
                System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + " conectado!");
                clients.add(clientSocket);                             // ① registra
                new Thread(() -> atenderCliente(clientSocket)).start(); // ② thread dele

                if (clients.size() == 1) {                             // ③ avisa
                    clientSocket.sendMsg("AGUARDANDO");
                } else {
                    for (SocketCliente c : clients) {
                        c.sendMsg("CONECTADO");
                    }
                    System.out.println("Dois clientes conectados: comunicação entre eles ativa.");
                }
            }
        }
    }

    private void recusarCliente(SocketCliente cliente) {
        cliente.sendMsg("SERVIDOR_CHEIO");
        cliente.close();
        System.out.println("Conexão recusada: já há 2 clientes.");
    }

    // roda DENTRO da thread de cada cliente
    private void atenderCliente(SocketCliente cliente) {
        String msg;
        try {
            while ((msg = cliente.getMessage()) != null) {
                if ("sair".equalsIgnoreCase(msg)) break;
                System.out.println("<- Cliente " + cliente.getRemoteSocketAddress() + ": " + msg);
                repassarMensagem(cliente, msg);
            }
        } finally {
            removerCliente(cliente);                                   // libera a vaga
        }
    }

    // envia para todos MENOS o remetente (sem eco na origem)
    private void repassarMensagem(SocketCliente remetente, String msg) {
        Iterator<SocketCliente> iterator = clients.iterator();
        while (iterator.hasNext()) {
            SocketCliente clientSocket = iterator.next();
            if (!remetente.equals(clientSocket)) {
                if (!clientSocket.sendMsg("Cliente " + remetente.getRemoteSocketAddress() + ": " + msg)) {
                    iterator.remove();
                }
            }
        }
    }

    private void removerCliente(SocketCliente cliente) {
        clients.remove(cliente);
        cliente.close();
        System.out.println("Cliente " + cliente.getRemoteSocketAddress() + " desconectou. Conectados: " + clients.size());
    }
}
```

#### Cliente.java — janela Swing + thread que ouve o servidor
```java
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Cliente extends JFrame implements Runnable {
    private static final String ENDERECO = "127.0.0.1";
    private static final int PORT = 4000;

    private SocketCliente conexao;

    private final JTextArea areaConversa = new JTextArea(15, 40);
    private final JTextField campoMensagem = new JTextField(30);
    private final JTextArea areaStatus = new JTextArea(4, 40);
    private final JButton botaoEnviar = new JButton("Enviar");
    private final JButton botaoLimpar = new JButton("Limpar");
    private final JButton botaoSair = new JButton("Sair");

    public Cliente() {
        super("Chat - Cliente");
        montarTela();
        conectar();
    }

    private void montarTela() {
        areaConversa.setEditable(false);
        areaConversa.setLineWrap(true);
        JScrollPane rolagemConversa = new JScrollPane(areaConversa);
        rolagemConversa.setBorder(BorderFactory.createTitledBorder("Conversa"));

        JPanel linhaMensagem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linhaMensagem.add(new JLabel("Mensagem:"));
        linhaMensagem.add(campoMensagem);

        JPanel linhaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linhaBotoes.add(botaoEnviar);
        linhaBotoes.add(botaoLimpar);
        linhaBotoes.add(botaoSair);

        JPanel envio = new JPanel(new GridLayout(2, 1));
        envio.add(linhaMensagem);
        envio.add(linhaBotoes);

        areaStatus.setEditable(false);
        JScrollPane rolagemStatus = new JScrollPane(areaStatus);
        rolagemStatus.setBorder(BorderFactory.createTitledBorder("Status da comunicação"));

        JPanel rodape = new JPanel(new BorderLayout());
        rodape.add(envio, BorderLayout.NORTH);
        rodape.add(rolagemStatus, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(rolagemConversa, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        botaoEnviar.addActionListener(e -> enviarMensagem());
        campoMensagem.addActionListener(e -> enviarMensagem());   // Enter também envia
        botaoLimpar.addActionListener(e -> limpar());
        botaoSair.addActionListener(e -> sair());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);             // X = Sair
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        campoMensagem.requestFocusInWindow();
    }

    private void status(String s) {
        SwingUtilities.invokeLater(() -> areaStatus.append(s + "\n"));
    }

    private void limpar() {
        areaConversa.setText("");
    }

    private void conectar() {
        try {
            conexao = new SocketCliente(new Socket(ENDERECO, PORT));
            status("Conectado ao servidor. Comunicação com o servidor ativa");   // caso 4
            new Thread(this).start();                                           // -> run()
        } catch (IOException e) {                                               // caso 1
            JOptionPane.showMessageDialog(this, "Servidor indisponível: " + e.getMessage());
            System.exit(0);
        }
    }

    // thread que fica OUVINDO o servidor
    @Override
    public void run() {
        String msg;
        while ((msg = conexao.getMessage()) != null) {
            if (msg.equals("SERVIDOR_CHEIO")) {                                 // caso 6
                JOptionPane.showMessageDialog(this, "Já há 2 clientes conectados.");
                System.exit(0);
            }
            if (msg.equals("AGUARDANDO")) {
                status("Aguardando a conexão do segundo cliente...");
                continue;                                                       // não vai para a conversa
            }
            if (msg.equals("CONECTADO")) {                                      // caso 5
                status("Comunicação bidirecional com o outro cliente ativa. Aguardando mensagens...");
                continue;
            }
            exibirMensagem("-> " + msg);
        }
        status("Conexão encerrada.");
    }

    private void enviarMensagem() {
        String msg = campoMensagem.getText().trim();
        if (msg.isEmpty()) return;
        if (msg.equalsIgnoreCase("sair")) {
            sair();
            return;
        }
        if (conexao.sendMsg(msg)) {
            exibirMensagem("<- Eu: " + msg);
            campoMensagem.setText("");
        } else {
            status("Erro ao enviar.");
        }
    }

    private void exibirMensagem(String msg) {
        SwingUtilities.invokeLater(() -> areaConversa.append(msg + "\n"));
    }

    private void sair() {
        if (conexao != null) {
            conexao.sendMsg("sair");
            conexao.close();
        }
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cliente());
    }
}
```

---

### 2. P4 2023 — o que muda em relação à base
**Use quando:** o enunciado **não** pedir o aviso ao Cliente 1 (7 casos de teste).
A base já resolve a 2023. Se quiser deixar igual ao código da pasta `p4.2023`:
- **Servidor:** em `aguardarConexoes`, o bloco ③ (`AGUARDANDO` / `CONECTADO`) é opcional.
- **Cliente:** em `run()`, os `if` de `AGUARDANDO` e `CONECTADO` também.

---

### 3. P4 2025 — o que muda em relação à base
**Use quando:** o enunciado pedir **Cliente #1 / #2** e que **o outro cliente feche sozinho** quando alguém sair.
**Ideia:** mais 2 palavras de controle:
- `ID:1` / `ID:2` → o servidor diz ao cliente qual é o número dele.
- `DESCONECTAR` → quando um cliente sai, o servidor manda o outro fechar.
- Se o **servidor** cair, `getMessage()` devolve `null` → o cliente avisa e fecha.

✅ Testado numa cópia (com clientes simulados): `ID:1`+`AGUARDANDO`, `ID:2`+`CONECTADO`, `SERVIDOR_CHEIO`, mensagens sem eco, `DESCONECTAR` para o outro quando um sai, vaga liberada (novo cliente volta a ser `#1`), e o `null` quando o servidor cai.

#### Servidor.java (2025) — substitui o da base
```java
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;

public class Servidor {
    public static final String ENDERECO = "127.0.0.1";
    public static final int PORT = 4000;
    public static final int MAX_CLIENTES = 2;

    private ServerSocket serverSocket;
    private final LinkedList<SocketCliente> clients = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR *v*v*v*");
        try {
            new Servidor().iniciar();
        } catch (IOException ex) {
            System.out.println("Erro: não foi possível iniciar o servidor. Já existe um servidor na porta " + PORT + " (" + ex.getMessage() + ")");
        }
        System.out.println("Servidor finalizado!");
    }

    public void iniciar() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta " + PORT + ". Aguardando a conexão de até 2 clientes...");
        aguardarConexoes();
    }

    private void aguardarConexoes() throws IOException {
        while (true) {
            SocketCliente cliente = new SocketCliente(serverSocket.accept());
            synchronized (this) {
                if (clients.size() >= MAX_CLIENTES) {
                    recusarCliente(cliente);
                    continue;
                }
                clients.add(cliente);
                int numero = clients.size();                       // 1 ou 2
                System.out.println("Cliente #" + numero + " (" + cliente.getRemoteSocketAddress() + ") conectado!");
                cliente.sendMsg("ID:" + numero);                   // o cliente fica sabendo se é o #1 ou o #2
                if (numero == 1) {
                    cliente.sendMsg("AGUARDANDO");
                } else {
                    for (SocketCliente c : clients) c.sendMsg("CONECTADO");
                    System.out.println("Dois clientes conectados: comunicação entre eles ativa.");
                }
            }
            new Thread(() -> atenderCliente(cliente)).start();
        }
    }

    private void recusarCliente(SocketCliente cliente) {
        cliente.sendMsg("SERVIDOR_CHEIO");
        cliente.close();
        System.out.println("Conexão recusada: já há 2 clientes.");
    }

    private void atenderCliente(SocketCliente cliente) {
        String msg;
        try {
            while ((msg = cliente.getMessage()) != null) {
                if ("sair".equalsIgnoreCase(msg)) break;
                System.out.println("<- Cliente " + cliente.getRemoteSocketAddress() + ": " + msg);
                repassarMensagem(cliente, msg);
            }
        } finally {
            removerCliente(cliente);
        }
    }

    private synchronized void repassarMensagem(SocketCliente remetente, String msg) {
        for (SocketCliente c : clients) {
            if (!c.equals(remetente)) c.sendMsg("Cliente " + remetente.getRemoteSocketAddress() + ": " + msg);
        }
    }

    // 2025: quando um sai, o servidor manda o outro desconectar também
    private synchronized void removerCliente(SocketCliente cliente) {
        if (!clients.remove(cliente)) return;                      // já tinha sido removido
        cliente.close();
        System.out.println("Cliente " + cliente.getRemoteSocketAddress() + " desconectou.");
        for (SocketCliente c : new ArrayList<>(clients)) {
            c.sendMsg("DESCONECTAR");
            System.out.println("Pedindo ao cliente " + c.getRemoteSocketAddress() + " que se desconecte.");
        }
    }
}
```
- `synchronized`: as threads dos dois clientes mexem na mesma lista; isso evita que uma atrapalhe a outra.
- `if (!clients.remove(cliente)) return;`: quando o outro cliente fecha por causa do `DESCONECTAR`, a thread dele também chama `removerCliente`; essa linha evita repetir o aviso.
- **"Sem ecoar no servidor"** conflita com o requisito II (mostrar as mensagens no console). Aqui o servidor **mostra** no console mas **não devolve** a mensagem a ninguém além do destinatário. Na prova, vale confirmar com o professor.

#### Cliente.java (2025) — mudanças em relação à base
**1. Dois atributos novos** (junto com `conexao`):
```java
    private int numero;                    // #1 ou #2 (o servidor informa)
    private volatile boolean saindo;       // true quando o próprio usuário clicou em Sair
```

**2. `run()` novo + método `encerrar`:**
```java
    @Override
    public void run() {
        String msg;
        while ((msg = conexao.getMessage()) != null) {
            if (msg.equals("SERVIDOR_CHEIO")) {
                JOptionPane.showMessageDialog(this, "Já há 2 clientes conectados.");
                System.exit(0);
            }
            if (msg.startsWith("ID:")) {
                numero = Integer.parseInt(msg.substring(3));
                setTitle("Chat - Cliente #" + numero);
                continue;
            }
            if (msg.equals("AGUARDANDO")) {
                status("Cliente #" + numero + " aberto e conectado ao servidor. Aguardando a conexão de um segundo cliente...");
                continue;
            }
            if (msg.equals("CONECTADO")) {
                status("Cliente #" + numero + ": comunicação bidirecional com o outro cliente ativa. Aguardando mensagens...");
                continue;
            }
            if (msg.equals("DESCONECTAR")) {                        // o outro cliente saiu
                encerrar("O outro cliente se desconectou. Este cliente também será encerrado.");
                return;
            }
            exibirMensagem("-> " + msg);
        }
        // getMessage() devolveu null: o servidor caiu
        if (!saindo) encerrar("O servidor se desconectou. Este cliente será encerrado.");
    }

    // avisa o usuário e fecha o cliente
    private void encerrar(String aviso) {
        saindo = true;
        JOptionPane.showMessageDialog(this, aviso);
        conexao.close();
        System.exit(0);
    }
```

**3. Primeira linha do `sair()`:**
```java
    private void sair() {
        saindo = true;          // novo: assim o run() não mostra "o servidor caiu" quando é você que está saindo
        if (conexao != null) {
            ...
```

**Casos de teste da 2025:**
| # | O que fazer | Resultado esperado |
|---|---|---|
| 1 | Cliente sem servidor | "Servidor indisponível" e fecha |
| 2 | Servidor | "Aguardando a conexão de até 2 clientes" |
| 3 | 2º servidor | "não foi possível iniciar… já existe um servidor na porta 4000" |
| 4 | Cliente 1 | título "Cliente #1" + "aguardando a conexão de um segundo cliente" |
| 5 | Cliente 2 | título "Cliente #2" + "comunicação bidirecional com o outro cliente ativa" (o #1 também mostra) |
| 6 | Cliente 3 | "Já há 2 clientes conectados." e fecha |
| 7, 8 | Mensagens 1 → 2 e 2 → 1 | aparecem no outro, sem eco na origem |
| 9 | Fechar o Cliente 1 (ou 2) | o outro mostra "O outro cliente se desconectou" e fecha |
| 9 | **Ctrl+C** no servidor | os clientes mostram "O servidor se desconectou" e fecham |
