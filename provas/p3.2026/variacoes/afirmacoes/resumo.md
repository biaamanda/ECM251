# Afirmações V/F: o que costuma cair (resumo rápido)

Complementa a seção 3 do [README](../../README.md). Aqui o foco é **só o que rende ponto nas Q1–Q3**.

## Como a prova monta as questões

- 9 afirmações (A1–A9) e 3 questões (Q1–Q3). Cada questão pega **3 afirmações** e pede a combinação certa:
  *todas V*, *todas F*, *"apenas Ax é verdadeira"*, *"apenas Ax é falsa"*, ou **N.D.A.**
- Em 2025 os assuntos foram: A1–A2 Swing/eventos · A3–A4 i18n · A5 arquivos · A6–A7 criptografia · A8–A9 testes.
  Em 2026 o conteúdo é o mesmo intervalo (aulas 13–18), então espere algo parecido. **Isso é uma inferência minha a partir de uma única prova.**
- Em 2025 cada trio tinha exatamente **uma** falsa. Não conte com isso em 2026: N.D.A. é resposta possível (o Simulado 2, Q1, treina isso).
- Método: **julgue cada afirmação V/F primeiro**, só depois olhe as alternativas. Se a sua combinação não aparece, marque N.D.A.

## Como uma afirmação falsa é fabricada

| Padrão | Exemplo (2025 ou treino) |
|---|---|
| **Herança/pertencimento trocado** | "`JPasswordField` herda direto de `JComponent`" (na verdade, de `JTextField`) |
| **Capacidade negada ou limitada** | "arquivos de texto: só leitura" · "não possui métodos adicionais" |
| **Absoluto** | "exaustivo", "todas", "sempre", "somente", "diretamente", "nunca" |
| **Conceitos trocados** | simétrica ↔ assimétrica · teste unitário ↔ de integração · smoke ↔ regressão |
| **Detalhe técnico invertido** | `FileWriter` acrescenta por padrão · mês começa em 1 · layout padrão do `JPanel` |
| **Chave/valor trocados (i18n)** | "cada idioma tem chaves diferentes" (as chaves são iguais; os valores mudam) |

## Swing e eventos (aulas 13–14)

- `JButton`, `JTextField` (Enter), `JPasswordField`, `JMenuItem` → **`ActionEvent`**.
- `JComboBox`, `JCheckBox`, `JRadioButton` → **`ItemEvent`** (e também `ActionEvent`). O combo dispara 2 `ItemEvent` por troca (DESELECTED + SELECTED).
- `e.getSource()` diz quem gerou o evento: um só listener atende vários componentes.
- `JPasswordField` **estende `JTextField`**; `getPassword()` devolve `char[]`; `setEchoChar()`.
- Layout padrão: **`JPanel` = `FlowLayout`**; **content pane do `JFrame` = `BorderLayout`**.
- `JFrame` nasce **invisível** (`setVisible(true)`) e fechar a janela só a **esconde** (`HIDE_ON_CLOSE`) até você usar `EXIT_ON_CLOSE`.
- `JRadioButton` só é exclusivo se estiver num **`ButtonGroup`**.
- `JTable` mostra o cabeçalho **só dentro de `JScrollPane`**.
- Barra de menus: `setJMenuBar(...)`. Hierarquia: `JMenuBar` → `JMenu` → `JMenuItem`.
- `Calendar.MONTH` começa em **0** (janeiro). `HOUR_OF_DAY` vai de 0 a 23.
- NetBeans: `initComponents()` é código gerado, **não se edita à mão**.

## Internacionalização (aula 15)

- Padrão dos arquivos: `<base>_<idioma>_<PAÍS>.properties` (`Ex1_pt_BR`, `Ex1_en_US`, …); formato `chave=valor`.
- **Mesmas chaves** em todos os idiomas; só os valores mudam.
- `ResourceBundle.getBundle(base, locale)` carrega; `getString(chave)` devolve `String`.
- Chave inexistente, ou nenhum arquivo aplicável → **`MissingResourceException`**.
- Busca: `pt_BR` → `pt` → Locale padrão do PC → arquivo base sem sufixo. Por isso sempre crie o **arquivo base**.
- Trocar o bundle **não atualiza a tela sozinho**: é preciso reaplicar `setText(...)` em cada componente.
- `Locale.US`, `Locale.FRANCE`, `Locale.ITALY`, `new Locale("pt","BR")`.

## Arquivos (aula 16)

- `new FileWriter(nome)` **sobrescreve**; `new FileWriter(nome, true)` **acrescenta**.
- `try-with-resources` fecha o arquivo sozinho, **inclusive se houver exceção**.
- `IOException` é **checked**: tratar (`try/catch`) ou declarar (`throws`).
- `new Scanner(new File(x))` com arquivo inexistente → `FileNotFoundException` (**não cria** o arquivo).
- `File.exists()` não abre nem cria nada. Também: `isFile`, `isDirectory`, `length`, `list`.
- Java **escreve** arquivos sem problema (`FileWriter`, `PrintWriter`, `Files.write`).

## Criptografia (aula 17)

| | Simétrica | Assimétrica |
|---|---|---|
| Chaves | **1** (mesma p/ cifrar e decifrar) | **par** pública + privada |
| Velocidade | em geral **mais rápida**, menos recursos | mais lenta, mais recursos |
| Ponto fraco | **gerenciar/distribuir** a chave secreta | proteger a chave privada |
| Exemplos | **AES**, 3DES, Blowfish, RC4, IDEA | **RSA**, ECC, PGP |

- Pública **cifra**, privada **decifra** → confidencialidade. Privada **cifra** → autenticidade.
- A chave **privada nunca é distribuída**; cada participante gera a sua.
- Simétrica: **cifra de fluxo** (bit a bit) × **cifra de bloco** (grupo de bits como unidade; AES = 128 bits).
- `CryptoDummy` (soma de número aleatório) = segurança **muito baixa**, só didático.
- Texto cifrado é **binário** (`byte[]`). `byte[]` → `String` → `byte[]` só é seguro com **ISO-8859-1**; com UTF-8 há perda.
- RSA-1024 com o padding padrão: no máximo **117 bytes** por `doFinal`.
- Chaves foram gravadas **serializadas** (`ObjectOutputStream`).

## Checksum e CRC (aula 18)

- **Soma e Complemento de 2:** soma dos bytes **mod 256**, depois `(~soma + 1) & 0xFF`.
  Resultado sempre entre **0 e 255**; `soma dos dados + checksum ≡ 0 (mod 256)`.
- Ele **detecta** erro; **não corrige**.
- A soma **ignora a ordem**: `"AB"` e `"BA"` dão o mesmo checksum (125). O **CRC-32** dá valores diferentes.
- Erros que se compensam passam despercebidos na soma (`"AD"` e `"BC"` → mesmo checksum, 123).
- **CRC-32:** polinômio reverso **`0xEDB88320`**, início `0xFFFFFFFF`, XOR final `0xFFFFFFFF`; `"123456789"` → `0xCBF43926`.

## Testes (aula 18)

| Tipo | Ideia | Pegadinha |
|---|---|---|
| **Unitário** | uma unidade **isolada** (método/classe), **automatizado**, sem entrada do usuário | não é o que testa "interação entre módulos" |
| **Integração** | vários módulos **juntos** | não isola cada método |
| **Smoke** | **rápido e superficial**: "liga e o essencial funciona?" | **não** é exaustivo |
| **Regressão** | reexecutar testes após mudança: "quebrei algo que funcionava?" | |
| **Sistema / aceitação** | sistema inteiro / validação pelo cliente | |

## `.jar` (Q4, mas pode aparecer)

- `java -jar` exige `Main-Class` no manifesto. `jar cfe App.jar Main -C out .` → **c**riar, **f**ile, **e** = *entry point* (a classe principal, **não** "extensão").
