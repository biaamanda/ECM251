# Resumo TEÓRICO – P4 ECM251

Baseado nas 3 P4 (pastas p4.2023, p4.2024 e p4.2025). As respostas de alunos citadas vêm das cópias em `OneDrive/3 ano/LP/Provas` e `LP/ProvasLP/P4`:
- **2023** (28/11/2023): `p4.2023/p4-2023.pdf`
- **2024** (19/11/2024): `p4.2024/p4-2024.pdf` → **teoria idêntica à de 2023**
- **2025** (18/11/2025): `p4.2025/p4-2025.pdf` → 2 questões repetidas + **2 novas sobre threads**

As provas têm **4 testes de 1 ponto** e a questão prática de 6. **Não há gabarito oficial** na pasta. As respostas abaixo são minha análise feita pelos slides, comparada com o que os alunos marcaram.

---

## 0. Como o professor monta as questões

1. **Ele copia frases dos slides e troca uma palavra.** Leia cada afirmação procurando a palavra "intrusa". Exemplos reais:
   - "cliente; servidor; **banco de dados**; comunicação..." → banco de dados **não** está na lista do slide.
   - "**sempre duas** aplicações Java Cliente" → o slide não fala em número fixo.
   - "o controlador de aplicação – **View**" → o controlador é o **Controller**.
   - "**não é considerado** um design pattern" → é considerado.
2. **Palavras absolutas** ("sempre", "nunca", "único", "precisa", "não possui") costumam indicar a afirmação falsa. "**Uma única** aplicação Servidor" é exceção: é verdadeira, porque o slide diz "**uma** aplicação Java Servidor".
3. **Questões de asserção–razão ("I PORQUE II"):**
   1. Julgue a I sozinha (V ou F).
   2. Julgue a II sozinha (V ou F).
   3. Só se as duas forem V: pergunte "**I acontece por causa de II?**". Se sim, "II justifica I" (normalmente a alternativa **b**). Se forem só fatos verdadeiros sem relação de causa, "não são justificativas" (**d**).
   - "**I justifica II**" (**c**) quase nunca é a certa, porque a razão vem depois do PORQUE, ou seja, é a II.
   - ⚠️ As letras mudam de uma prova para outra. Em 2023 a **e)** era "I verdadeira, II falsa"; em 2025, a **e)** da questão de Stream virou "I **falsa**, II verdadeira". **Leia sempre o texto da alternativa, não só a letra.**

---

## 1. Arquitetura Cliente-Servidor (Aula 20) — caiu em TODOS os anos

**Definição:** modelo fundamental da Eng. de Software que distribui funções e responsabilidades. O sistema é dividido em **Cliente** e **Servidor**, que interagem **via rede e protocolos** para realizar tarefas através de **transações e interações**.

**Principais características (lista do slide, decore):**

| Característica | Resumo |
|---|---|
| **Cliente** | **Solicita** serviços/recursos, apresenta a **interface** ao usuário, coleta entradas, envia pedidos e **espera** a resposta |
| **Servidor** | **Fornece** serviços/recursos, **aguarda** solicitações, processa e responde. Tem recursos que o cliente não tem → **fonte de autoridade** |
| **Comunicação** | Por protocolos de rede (HTTP, TCP/IP, personalizados). **Síncrona**: o cliente espera a resposta. **Assíncrona**: o cliente continua executando enquanto espera |
| **Distribuição de tarefas** | Lógica de negócio, dados e recursos → **servidor**. Interação e apresentação → **cliente** |
| **Escalabilidade** | Adicionar clientes/servidores. Ex.: **3 camadas** (cliente, servidor de aplicação, servidor de BD) |

→ **Banco de dados NÃO é uma característica.** Ele só aparece como exemplo de camada e de uso.

**Usos:** aplicativos Web (o navegador é o cliente), sistemas de banco de dados, outros sistemas distribuídos.

**Aplicações Java Cliente-Servidor:**
- **Uma** aplicação Java **Servidor**: gerencia as aplicações dos clientes e **intermedia** a comunicação entre eles pela rede.
- Aplicações Java **Cliente** (sem número fixo): conectadas ao servidor e aos outros clientes **através do servidor**, usando diferentes tipos de dispositivos em rede.

---

## 2. Sockets (Aulas 20 e 21)

- **Socket** = recurso do **Sistema Operacional** que permite que aplicações diferentes (em máquinas diferentes ou na mesma) se comuniquem trocando mensagens em rede.
- "Socket" = **tomada**: só tem utilidade quando algo é conectado a ela.
- Em programação: **canal de comunicação bidirecional** entre duas aplicações, com um socket de um lado conectado ao socket do outro. Depois de conectados, os dados trafegam entre eles. Cabe às aplicações decidir se enviam e/ou recebem.
- O servidor tem um socket que abre uma **porta** e fica "**escutando**". O cliente se conecta nele.
- **Para cada cliente**, o servidor cria **um socket** próprio. Cada cliente também cria o seu.
- Todo socket precisa de **endereço IP + número de porta**. A porta é o **ponto final (endpoint)** de comunicação.
  - Analogia: **IP = endereço do prédio**, **porta = número do apartamento**. Sem a porta, o SO não sabe para qual aplicação entregar a mensagem.
- **TCP/IP:** **solicita confirmação** de entrega. **UDP:** **não** solicita.
- **Classes Java:** `ServerSocket` (servidor espera conexões, `accept()`) e `Socket` (cliente conecta).

---

## 3. Stream (Aula 20) — caiu em TODOS os anos

- "Stream" = corrente, fluxo, riacho.
- Usado para **imprimir, ler e gravar** dados de/em dispositivos de **entrada (input)** e **saída (output)**. Com ele se envia ou recebe dados **por meio de um fluxo**.
- **Exemplos de stream:** arquivos e canais de comunicação, como os **sockets**.
- "No contexto apresentado, esses dispositivos serão os **canais de comunicação entre o cliente e o servidor, ou seja, os sockets**."
- **A partir do Java 8**, o conceito foi ampliado: Stream também é o recurso para processar dados de forma **sequencial (serial)** ou **simultânea (paralela)**. **O significado depende do contexto.**

---

## 4. Comunicação Bidirecional (Aula 21)

- O cliente envia e o servidor responde. Também pode ser o contrário: o **servidor envia e o cliente responde**, e isso é a **comunicação bidirecional**.
- Os dois **trocam informações de forma interativa**, enviando e recebendo ao longo da conexão.
- **Útil em tempo real:** jogos online, **chat**, streaming de áudio/vídeo.
- **Elementos (lista do slide):**
  1. **Sockets**: `Socket` no cliente, `ServerSocket` no servidor.
  2. **Protocolos**: definem a estrutura dos dados e das mensagens. Ex.: **TCP** e **WebSocket**.
  3. **Threads**: a comunicação pode ser **bloqueante** (espera dados do outro lado); com threads o servidor atende vários clientes ao mesmo tempo.
  4. **Frameworks/bibliotecas**: **Java NIO (New I/O)**, **Apache MINA**, **Netty**, bibliotecas de WebSocket.

**Evolução dos exemplos de aula (cai como afirmação):**

| Aula | Vários clientes? | Servidor responde? | Observação |
|---|---|---|---|
| 20 | ❌ 1 por vez | ❌ só cliente → servidor | Servidor no console, cliente Swing simples |
| 21 | ❌ 1 por vez | ✅ eco, **quando iniciado pelo cliente** | — |
| 22 | ✅ (threads) | ❌ só cliente → servidor | Servidor multicliente |
| 23 | ✅ | ✅ bate-papo | Threads **no servidor e no cliente** |

---

## 5. Threads (Aula 22) — 2 questões novas na P4 2025

**Conceitos:**
- **Thread** (fio, linha, caminho): recurso do **SO** que permite que uma aplicação execute **várias tarefas simultaneamente**, cada uma um caminho de execução diferente.
- **Execução paralela:** várias CPUs ou núcleos, cada thread num núcleo → simultâneo **de fato**.
- **Execução concorrente:** **uma** CPU/núcleo; o SO alterna as threads em alta velocidade → **falsa impressão** de paralelismo.
- **Multitarefa:** alternância entre aplicações (baixar arquivo, ouvir música e jogar "ao mesmo tempo").
- **Multitarefa preemptiva:** é o **SO** que decide o tempo máximo de CPU de cada tarefa e controla a fila. Windows, Linux, macOS, iOS e Android.
- **Quantum / Time-Slice:** tempo de CPU de cada tarefa, definido pelo SO, ajustável pela **prioridade**.
- **Scheduler:** componente do SO que define o tempo de alternância e a **ordem** de execução.
- **Thread principal:** todo processo tem **pelo menos uma**, criada automaticamente. Em Java, é a **JVM** que a cria, pedindo ao SO. Sem threads extras, todo o código roda nela.
- Threads do **mesmo processo compartilham a mesma memória** → facilita a programação, mas pode gerar **conflitos/erros** quando várias threads usam a mesma variável (**condição de corrida**, **bloqueios**).
- Pacotes: `java.lang.Thread`, `java.util.concurrent`.

**Por que usar threads (8 motivos do slide):** desempenho (multicore), **responsividade** (GUI não trava), concorrência, tarefas paralelas, resposta a eventos (servidores), **escalabilidade**, divisão de tarefas, temporização.

**Threads no servidor (Aulas 22 e 23):**
- `accept()`, `nextLine()`, `readLine()`, `getMessage()` são **bloqueantes**: "travam" a thread até o evento acontecer.
- Por isso, com uma thread só, o servidor fica dedicado a **um cliente**.
- Solução: **uma thread por cliente**, "**uma das soluções possíveis**" segundo o slide.
- Num **chat** são necessárias **3 coisas**: comunicação **bidirecional** + métodos **bloqueantes** + **multithread no servidor E no cliente**.
- **Limite:** a solução é **parcialmente escalável**, porque sempre haverá um **limite de threads permitido pelo SO** e, portanto, um limite de clientes simultâneos. Ainda assim é adequada quando se sabe que esse limite não será atingido.
- Experimento do bilhão de números: mais threads aceleram **até o número de núcleos**. Depois disso não há ganho.

---

## 6. Expressão Lambda e Funções Anônimas (Aula 22)

- **Lambda (Java 8):** cria **funções anônimas** de forma concisa. Permite passar funcionalidade como argumento e implementar **interfaces funcionais**.
- Sintaxe: `(parâmetros) -> expressão`. Os tipos dos parâmetros podem ser omitidos ou explícitos.
- Usada com **streams** e métodos como `forEach`, `filter`, `map`, `reduce`.
- **Função anônima** em Java = **classe anônima** que implementa o método de uma interface funcional. Útil para passar um método como argumento e criar **callbacks**.
- A lambda **simplifica** a classe anônima: `Operacao soma = (a, b) -> a + b;`

---

## 7. TDD e JUnit (Aula 19) — ainda não caiu, mas está no conteúdo

- **Código confiável:** objetivo primário e inegociável. Passa por **Testes de Validação** (atende ao que foi pedido, do ponto de vista do **cliente**) e **Testes de Verificação** (sem **Erros, Defeitos e Falhas**). Erro achado tarde = mais custo, prazo e recursos.
- **Código limpo:** (1) **simples** (**KISS** – *Keep It Simple, Stupid*); (2) **organizado** (indentação, espaçamento); (3) **sem duplicação** (**DRY** – *Don't Repeat Yourself*); (4) **métodos atômicos** (uma tarefa por método). Livro: **Robert Martin**, *Código Limpo*. "Código limpo = código de fácil manutenção."
- **TDD** (*Test-Driven Development*): **Kent Beck, 2003**. Desenvolvimento guiado por testes automatizados.
  - **2 regras:** (1) só escrever código novo se o teste automatizado **falhar**; (2) **eliminar duplicação**.
  - **Mantra:** **Vermelho → Verde → Refatorar**. *Test first*, *baby steps*.
  - **Benefícios:** menos defeitos (unitário e integração), **menos esforço no final ao custo de um aumento moderado no início**, mais qualidade, manutenção mais fácil, **testes = documentação viva**, projeto mais **modular**.
- **Framework:** estrutura com ferramentas, bibliotecas, padrões e diretrizes. Exemplos: Rails e Django (**MVC**), React, Angular, React Native, Flutter, Unity, Unreal, TensorFlow, PyTorch, Robot Framework, **JUnit**.
- **JUnit:** framework de **teste de unidade** em Java.
  - Anotações: `@Test`, `@Before`, `@After`.
  - Asserções: `assertEquals()`, `assertTrue()`, `assertFalse()`. Se uma falhar, o teste é "**não aprovado**".
  - Executa testes individualmente, em grupos ou em **suítes**. Integra com Eclipse, IntelliJ e VS Code. Versões **JUnit 4 e 5**.

---

## 8. Padrões de Projeto e MVC — caiu em 2023 e 2024 (não está nos slides 19–23)

- **Design Patterns** ("Gang of Four": **Gamma, Helm, Johnson, Vlissides**, *Design Patterns: Elements of Reusable Object-Oriented Software*): **soluções típicas** para problemas **recorrentes**. **Não são modelos concluídos**, são **esboços** de como resolver.
  - **Benefícios:** melhor comunicação da equipe e aprendizado, mais facilidade de correção e manutenção, **reuso** de soluções, menos recursos no projeto.
- **MVC (Model-View-Controller):** padrão de **arquitetura** de software, também considerado **design pattern**.
  - **Model** = dados e lógica de negócio.
  - **View** = apresentação/interface com o usuário.
  - **Controller** = componente **entre os dois**, que separa o acesso a dados e a lógica de negócio da apresentação e da interação com o usuário.
  - Mudar a interface **não afeta** a manipulação dos dados, e os dados podem ser reorganizados **sem mudar** a interface.

---

# GABARITO COMENTADO (todas as P4)

## Q – Cliente-Servidor (2023 Q1, 2024 Q1, 2025 Q2)
I. Características incluem "banco de dados" → **F**
II. Uma única aplicação Java Servidor que gerencia e intermedia → **V**
III. "Sempre duas" aplicações Cliente → **F**
IV. Socket = canal bidirecional entre duas aplicações → **V**

✅ **II e IV**. Os dois alunos marcaram isso.

## Q – MVC (2023 Q2, 2024 Q2)
I. "controlador de aplicação – View" → **F** (o controlador é o Controller)
II. Mudar a interface não afeta os dados → **V**
III. Controller como componente entre os dois → **V**
IV. "Não é considerado design pattern" → **F**

✅ **II e III**. O aluno de 2023 marcou isso.

## Q – Padrões de Projeto, asserção-razão (2023 Q3, 2024 Q3)
I (abordagem comprovada para problemas recorrentes) → **V**. II (diretrizes e soluções amplamente aceitas que melhoram eficiência, qualidade e manutenção) → **V**.

✅ Minha resposta: **b** (a II justifica a I: os padrões são uma abordagem comprovada *porque* são soluções amplamente aceitas).
⚠️ O aluno de 2023 marcou **c**. Sem gabarito oficial, considero **b** a mais coerente com a lógica do "PORQUE".

## Q – Stream, asserção-razão (2023 Q4, 2024 Q4, 2025 Q1)
II (stream serve para ler e gravar dados em dispositivos de E/S, por meio de um fluxo) → **V**, é a definição do slide.
I (os streams são os canais cliente-servidor, ou seja, os sockets) → **ponto de dúvida**:
- **A favor de V:** o slide diz literalmente "esses dispositivos serão os canais de comunicação entre o cliente e o servidor, ou seja, os sockets".
- **A favor de F:** tecnicamente, o socket é o **canal** e o stream é o **fluxo** que passa por ele (`socket.getInputStream()`). Na P4 2025, o professor **mudou a alternativa e) para "I falsa e II verdadeira"**, e foi essa que o aluno marcou.

Respostas dos alunos: 2023 → **c**; 2025 → **e**.
✅ Minha recomendação: se a alternativa "**I falsa, II verdadeira**" existir, como na P4 2025, marque-a, porque o professor a criou de propósito. Se não existir, como em 2023 e 2024, a mais coerente é **b** (I e II verdadeiras, e a definição geral II justifica o caso particular I).

## Q – Thread por cliente, asserção-razão (P4 2025 Q3) 🆕
I. Um servidor de chat precisa de uma thread separada por cliente → **V** (é a solução do slide).
II. "...porque o `ServerSocket` **não possui capacidade nativa de diferenciar fluxos** de clientes distintos" → **F**. Cada `accept()` já devolve um `Socket` **diferente**, com seus próprios streams. O motivo real das threads é que os métodos são **bloqueantes**.

✅ Minha resposta: **e) I verdadeira e II falsa**.
⚠️ O aluno marcou **b**. Pelos slides, a justificativa II está errada.

## Q – Multithreading no servidor de chat (P4 2025 Q4) 🆕
I. Cada thread gerencia leitura e escrita daquele cliente → aceitável como **V** (cada thread cuida da comunicação do seu cliente)
II. O limite de clientes depende do limite de threads do SO → **V** (frase do slide)
III. Sem threads, o servidor atende todos, só que com mais latência → **F** (sem threads ele fica preso a **um** cliente, porque a leitura é bloqueante)
IV. Threads do mesmo processo compartilham memória → condição de corrida → **V** (frase do slide)

Alternativas: a) I, II, IV · b) I, II · c) I, II, III · d) II, III, IV · e) I, III, IV. Como não existe "II e IV" e a III é falsa:
✅ **a) I, II e IV**. O aluno marcou isso.

---

## Temas que ainda NÃO caíram, mas são candidatos fortes
- **Paralela × concorrente**, **multitarefa preemptiva**, **quantum**, **scheduler**, **thread principal criada pela JVM**.
- **TCP × UDP** (confirmação de entrega).
- **IP + Porta** (prédio × apartamento; porta = endpoint).
- **Síncrona × assíncrona**.
- **Lambda** `(parâmetros) -> expressão` × classe anônima; **interface funcional**.
- **TDD**: Kent Beck 2003, as 2 regras, Vermelho-Verde-Refatorar, benefícios. **JUnit**: anotações e asserções.
- **Elementos da comunicação bidirecional**: Sockets, Protocolos (TCP, WebSocket), Threads, Frameworks (NIO, MINA, Netty).
- **Chat** exige: bidirecional + bloqueante + **multithread nos dois lados**.
