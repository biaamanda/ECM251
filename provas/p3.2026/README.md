# ECM251 – P3 2026: guia de estudo (aulas 13 a 18)

## O que tem nesta pasta

| Arquivo / pasta                                | Para quê                                               |
| ---------------------------------------------- | --------------------------------------------------------------------------------------------- |
| `README.md` (este)                             | Resumo, gabarito comentado da P3 2025, dicas, armadilhas, checklist                           |
| `questao4-modelo/`                             | **Solução completa** da Questão 4 de 2025 (GUI de menus + Checksum + RSA + arquivos + `.jar`) |
| `questao4-modelo/TesteIntegracao.java`         | Teste de integração no estilo do `ChecksumTest` da aula 18                                    |
| `questao4-modelo/compilar.bat` / `.sh`         | Compila e gera o `App.jar`                                                                    |
| `variacoes/TelaComponentes.java`               | Componentes Swing + eventos de cada um (Q1–Q3 e GUIs)                                         |
| `variacoes/TelaI18n.java` + `Tela*.properties` | Internacionalização com **troca de idioma pelo menu**                                         |
| `variacoes/ArquivosCola.java`                  | Receitas de leitura/gravação de arquivos (aula 16)                                            |

---

## 0. Leia isto antes de usar o código em prova

O enunciado de 2025 diz que a Questão 4 deve ser feita **"sem o uso de Inteligência Artificial, utilizando-se como base apenas os códigos e técnicas vistos e praticados durante as aulas"**. Consultar material próprio é permitido (regra 3), mas o código desta pasta foi escrito com ajuda de IA e inclui coisas que não são "exatamente da aula". Não posso decidir por você o que conta como violação, então:

- **Use esta pasta para estudar** (ler, entender, redigitar sozinha), não para colar na prova.
- Se quiser levar como consulta, **pergunte ao professor antes** se material gerado por IA é aceito na consulta.
- A tabela abaixo mostra o que é da aula e o que foi acrescentado, para você saber explicar cada parte.

| Peça do modelo                                             | Origem                                                  |
| ---------------------------------------------------------- | ------------------------------------------------------- |
| `Checksum.calcularChecksum` / `calcularCRC`                | Aula 18 (igual)                                         |
| `Impressora.hexBytesToString`                              | Aula 17 (igual)                                         |
| `CryptoRSA`                                                | Aula 17, **com 2 mudanças** (seção 5, armadilhas 1 e 2) |
| Leitura/gravação (`Files`, `FileWriter`)                   | Aulas 16–18                                             |
| `implements ActionListener` + `getSource()`                | Aulas 14–16                                             |
| `JMenuBar` / `JMenu` / `JMenuItem`                         | Aula 15 (Ex01 pede itens de menu). Padrão do Swing      |
| Estado entre cliques, checagem de etapa, conferência do CS | Acrescentado (não é da aula)                            |

---

## 1. Como foi a P3 2025 (o formato provável da de 2026)

- **90 min**, prática, nos computadores do IMT (ou o seu). Permanência mínima 30 min. Atraso tolerado: 30 min.
- **Sem internet**, exceto o Canvas em 2 momentos (baixar a prova e subir a entrega) – **avise o professor** antes de cada um.
- **Consulta liberada** a material seu **guardado localmente** (pendrive, HD). Proibido Drive/OneDrive/GitHub, mesmo o seu.
- **Q1–Q3 (1 ponto cada = 3):** 9 afirmações V/F (A1–A9), cada questão pede uma combinação de 3 delas.
- **Q4 (7 pontos):** programa completo em Java, POO, GUI, mais o **`.jar` (geração obrigatória)**.

| Parte da Q4       | Pontos  | O que pedia                                                                            |
| ----------------- | ------- | -------------------------------------------------------------------------------------- |
| I – GUI de menus  | 1       | 4 menus / 9 subitens, **sem `JOptionPane`**, feedback só na área de mensagens          |
| II – Mensagem     | 1+1+1   | ler `msg_original.txt`; calcular CS (Soma e Complemento de 2); salvar `msg_com_cs.txt` |
| III – Chaves      | 0,5+0,5 | gerar e salvar `chave.publica` e `chave.privada` (RSA)                                 |
| IV – Criptografia | 0,5+0,5 | cifrar → `msg_cifrada.txt`; decifrar → `msg_decifrada.txt`                             |
| V – Geral         | 0,5+0,5 | mostrar `msg_decifrada.txt` na GUI; `Sair`                                             |

**Plano de tempo sugerido (90 min):** V/F 15 min → Q4 partes I e II 25 min → III e IV 25 min → V, jar e teste na pasta limpa 15 min → `.docx` + zip 10 min.
Não gaste mais de 15 min nas V/F: valem 3 pontos, a Q4 vale 7.

---

## 2. Gabarito comentado da P3 2025

> O gabarito oficial não está no repositório. As respostas abaixo foram **deduzidas** por mim a partir do conteúdo das aulas. A única com margem de dúvida é a A3 (indicada).

|     | Afirmação (resumo)                                                                       |  Resp.  | Por quê                                                                                                                                      |
| --- | ---------------------------------------------------------------------------------------- | :-----: | -------------------------------------------------------------------------------------------------------------------------------------------- |
| A1  | `JComboBox` gera `ItemEvent`, como `JCheckBox` e `JRadioButton`                          |  **V**  | Os três disparam `ItemEvent` (e também `ActionEvent`)                                                                                        |
| A2  | `JPasswordField` herda direto de `JComponent` e não tem métodos além dos de `JTextField` |  **F**  | Herda de `JTextField`; tem `getPassword()` (`char[]`) e `setEchoChar()`                                                                      |
| A3  | i18n exige `.properties` por idioma no padrão `<nome>_<idioma>_<país>.properties`        | **V\*** | É o padrão da aula (`Ex1_pt_BR.properties`). _Rigorosamente o país é opcional (`Ex1_pt.properties` funciona); a prova quer o padrão da aula_ |
| A4  | `ResourceBundle` carrega os arquivos e `getString()` lê o valor pela chave               |  **V**  | Exatamente o `TelaExemplo` da aula 15                                                                                                        |
| A5  | Arquivos de texto em Java só permitem leitura                                            |  **F**  | `FileWriter`, `PrintWriter`, `Files.write`                                                                                                   |
| A6  | Chave simétrica: mesma chave cifra/decifra, geralmente mais rápida que a assimétrica     |  **V**  | Slides da aula 17                                                                                                                            |
| A7  | Desafio da simétrica: gerenciar e distribuir a chave secreta com segurança               |  **V**  | "Gerenciamento de chave"                                                                                                                     |
| A8  | _Smoke Test_ verifica de forma abrangente e exaustiva todas as camadas                   |  **F**  | Smoke test é **rápido e superficial**: "o sistema liga e o básico crítico funciona?"                                                         |
| A9  | Teste de integração verifica a interação entre módulos                                   |  **V**  | É a definição                                                                                                                                |

**Q1** = A1 V, A2 F, A3 V → _"apenas A2 é falsa"_ → **c**
**Q2** = A4 V, A5 F, A6 V → _"apenas A5 é falsa"_ → **d**
**Q3** = A7 V, A8 F, A9 V → _"apenas A8 é falsa"_ → **b**

> Se você discordar da A3 (marcar F), a Q1 vira **e) N.D.A.**. Só faz sentido se o professor tiver ensinado que o país é opcional. Na dúvida, siga o padrão da aula.

**Como resolver esse tipo de questão rápido:** classifique cada afirmação V/F **primeiro** e só depois olhe as alternativas. Palavras-gatilho de afirmação falsa: _"apenas"_, _"somente"_, _"não possui"_, _"exaustivo"_, _"limitado a"_, _"sempre"_, _"nunca"_, _"diretamente"_.

---

## 3. Resumo por aula

### Aula 13 e 14 – Swing, eventos, campos de senha, tabela, ícones, datas

- Componentes: `JFrame`, `JPanel`, `JLabel`, `JTextField`, `JPasswordField`, `JButton`, `JTextArea` (+ `JScrollPane`), `JTable`, `JComboBox`, `JCheckBox`, `JRadioButton` (+ `ButtonGroup`), `JOptionPane`.
- **Layouts:** `BorderLayout` (padrão do `JFrame`; NORTH/SOUTH/EAST/WEST/CENTER), `FlowLayout` (padrão do `JPanel`), `GridLayout(linhas, colunas, hgap, vgap)`.
- **Eventos** (a tabela que mais cai em V/F):

  | Componente  | Evento   | Listener    |
 
  | `JButton`, `JTextField` (Enter), `JPasswordField`, `JMenuItem` | `ActionEvent`  | `ActionListener.actionPerformed` |
  | `JComboBox`, `JCheckBox`, `JRadioButton` | `ItemEvent` (também `ActionEvent`) | `ItemListener.itemStateChanged` |
  | `JList`   | `ListSelectionEvent`   | `ListSelectionListener`  |
  | mouse / teclado  | `MouseEvent` / `KeyEvent`  | `MouseListener` / `KeyListener`  |

  No `ItemListener` do combo, confira `e.getStateChange() == ItemEvent.SELECTED`, senão o código roda 2 vezes (DESELECTED + SELECTED).

- **`JPasswordField`** _estende `JTextField`_. Use `getPassword()` (retorna `char[]`, não `String`); `setEchoChar('*')`.
- **`JFrame` nasce invisível:** precisa de `setVisible(true)`. Fechar a janela só a esconde: para encerrar use `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`.
- `pack()` ajusta ao conteúdo; `setSize(l, a)` fixa; `setLocationRelativeTo(null)` centraliza.
- **`JTable` precisa de `JScrollPane`**, senão os títulos das colunas não aparecem. `new JTable(Object[][] dados, String[] colunas)`; para linhas dinâmicas use `DefaultTableModel.addRow`.
- **Menu:** `JMenuBar` → `JMenu` → `JMenuItem`. A barra entra com **`setJMenuBar(barra)`**, não com `add()`.
- **Ícones:** `new ImageIcon(getClass().getResource("lock.png"))`; `new JLabel(icone)`; `setIconImage(icone.getImage())`.
- **Data/hora:** `Calendar.getInstance()`; **`MONTH` começa em 0** (some 1 para exibir); `HOUR_OF_DAY` (0–23); `c.getTime()`.
- **NetBeans:** o editor visual gera `initComponents()` (código bloqueado, não edite) e usa referências de método (`this::botaoActionPerformed`).

### Aula 15 – Internacionalização (i18n)

- Um `.properties` por idioma: `base_idioma_PAÍS.properties` (`Ex1_pt_BR`, `Ex1_en_US`, `Ex1_fr_FR`, `Ex1_it_IT`), com as **mesmas chaves** e valores traduzidos. Formato `chave=valor`, comentário com `#`.
- `ResourceBundle bn = ResourceBundle.getBundle("Ex1", locale);` depois `bn.getString("chave")` (retorna `String`).
- `Locale`: `Locale.US`, `Locale.FRANCE`, `Locale.ITALY`, `Locale.forLanguageTag("pt-BR")` (`new Locale("pt","BR")` é obsoleto no Java 19+, mas a aula usa).
- Busca: `pt_BR` → `pt` → Locale padrão do PC → **arquivo base sem sufixo**. Crie sempre o arquivo base, senão `MissingResourceException`.
- **Trocar idioma ao vivo:** recarregar o bundle e **reaplicar os textos** de todos os componentes (`TelaI18n.java` mostra: `JRadioButtonMenuItem` + `ButtonGroup` + `aplicarTextos()`).
- Chave inexistente → `MissingResourceException`.

### Aula 16 – Arquivos de texto

- `new FileWriter(nome)` **sobrescreve**; `new FileWriter(nome, true)` **acrescenta**. `PrintWriter.printf("%s;%s%n", ...)`.
- Ler: `Scanner(new File(nome))` + `hasNextLine()/nextLine()`; `linha.split(";")`; ou `Files.readAllBytes`.
- Sempre `try-with-resources` (fecha sozinho). `IOException` é _checked_: trate ou declare.
- `File`: `exists()`, `isFile()`, `isDirectory()`, `length()`, `getAbsolutePath()`, `list()`.
- Caminho relativo = **pasta de trabalho** (onde o `java` foi executado), não a pasta do `.java`.
- Formato de cadastro da aula: um registro por linha, campos separados por `;` (`RA;NOME;SOBRENOME;P1;P2;P3;P4`).

### Aula 17 – Criptografia

|             | Simétrica    | Assimétrica  |
| Chaves      | **uma** chave para cifrar e decifrar   | **par** pública/privada   |
| Velocidade  | geralmente **mais rápida**   | mais lenta, mais recursos |
| Ponto fraco | **distribuir/gerenciar** a chave secreta  | proteger a chave privada  |
| Algoritmos  | AES, 3DES, Blowfish, RC4, IDEA, Twofish| RSA, ECC, PGP, GnuPG      |
| Na aula     | `CryptoDummy` (soma de nº aleatório, segurança baixa), `CryptoAES` | `CryptoRSA`  |

- **Confidencialidade:** cifra com a chave **pública**; só a **privada** decifra. **Autenticidade:** cifra com a **privada** (só o dono poderia ter feito).
- A chave privada **nunca** é distribuída. A pública é livre.
- Simétricas se dividem em **cifras de fluxo** (bit a bit) e **de bloco** (AES: blocos de 128 bits).
- Java: `KeyGenerator.getInstance("AES")`, `KeyPairGenerator.getInstance("RSA")`, `Cipher.getInstance(...)`, `cipher.init(ENCRYPT_MODE/DECRYPT_MODE, chave)`, `doFinal(byte[])`. Chaves gravadas **serializadas** com `ObjectOutputStream`.
- Texto cifrado é `byte[]` binário. Se converter para `String`, use um charset 1:1 como **ISO-8859-1** (o `TesteCrypto` da aula faz isso); com UTF-8 há perda de bytes.
- `Impressora.hexBytesToString(byte[])` mostra o cifrado em hexadecimal, 16 bytes por linha.
- `CryptoDummy` da aula cifra **"in place"** (altera o array recebido): passe `texto.clone()`.

### Aula 18 – Checksum, CRC e testes

- **Soma e Complemento de 2** (`Checksum.calcularChecksum`): soma dos bytes **módulo 256**; depois `(~soma + 1) & 0xFF`. Propriedade: `soma dos dados + checksum ≡ 0 (mod 256)`.
  Exemplos verificados: `""`→0, `"A"`→191, `"AB"`→125.
- **CRC-32** (`calcularCRC`): polinômio reverso `0xEDB88320`, início `0xFFFFFFFF`, XOR final `0xFFFFFFFF`. Padrão em ZIP/PNG/Ethernet. `"123456789"` → `0xCBF43926`. Detecta erros (inclusive trocas de ordem) melhor que a soma simples.
- **Tipos de teste:**

  | Teste                   | O que faz                                                                                     |
  | ----------------------- | --------------------------------------------------------------------------------------------- |
  | **Unitário**            | uma unidade isolada (um método/classe), automatizado, sem entrada do usuário (`ChecksumTest`) |
  | **Integração**          | várias unidades **juntas** (`TesteIntegracao`: arquivo → CS → RSA → arquivo)                  |
  | **Smoke**               | verificação **rápida e superficial**: "liga e o essencial funciona?" (não é exaustivo)        |
  | **Regressão**           | reexecutar testes após uma mudança para ver se nada quebrou                                   |
  | **Sistema / aceitação** | sistema inteiro / pelo cliente                                                                |

- Estilo da aula: método `verificar(nome, esperado, obtido)` que imprime `[OK]`/`[FALHOU]` e um resumo, sem JUnit.

---

## 4. Banco de afirmações V/F para treinar

| #   | Afirmação   |   Resp.    |
| 1   | `JButton` gera `ActionEvent`.  |    V    |
| 2   | `JCheckBox` gera `ItemEvent`.    |     V    |
| 3   | Pressionar Enter num `JTextField` gera `ActionEvent`.  |   V   |
| 4   | `JPasswordField.getPassword()` retorna `String`.    |    F (`char[]`)   |
| 5   | `JPasswordField` herda diretamente de `JComponent`.   |    F (de `JTextField`)   |
| 6   | O layout padrão do content pane de um `JFrame` é `BorderLayout`.    |    V    |
| 7   | O layout padrão de um `JPanel` é `FlowLayout`.   |   V   |
| 8   | Um `JFrame` aparece na tela assim que é construído.   |   F (precisa `setVisible(true)`)    |
| 9   | Por padrão, fechar um `JFrame` encerra a JVM.  | F (só esconde; use `EXIT_ON_CLOSE`) |
| 10  | Um `JTable` mostra os títulos das colunas mesmo sem `JScrollPane`.   |  F   |
| 11  | A barra de menus entra no frame com `setJMenuBar`.    |    V    |
| 12  | Vários `JRadioButton` do mesmo `ButtonGroup` podem estar marcados juntos.  |  F   |
| 13  | Em `Calendar`, janeiro é `MONTH == 0`.  |      V   |
| 14  | `ResourceBundle.getString()` retorna `Object`.  |  F (`String`)  |
| 15  | Para trocar o idioma da aplicação é preciso recompilar.  |  F  |
| 16  | Sem arquivo base nem arquivo do Locale, `getBundle` lança `MissingResourceException`.   |   V    |
| 17  | `new FileWriter("a.txt")` acrescenta ao final do arquivo.  |  F (sobrescreve; use `, true`)  |
| 18  | `try-with-resources` fecha o arquivo automaticamente.    |   V   |
| 19  | `File.exists()` exige que o arquivo esteja aberto.   |   F   |
| 20  | RSA e AES são algoritmos simétricos.  | F (AES simétrico; RSA assimétrico)  |
| 21  | Simétrica é, em geral, mais rápida que assimétrica.   |   V   |
| 22  | Na assimétrica, a chave **privada** é compartilhada com quem vai enviar mensagens.  |   F   |
| 23  | Cifrar com a chave privada garante autenticidade.   |   V   |
| 24  | RSA-1024 com `Cipher.getInstance("RSA")` cifra qualquer tamanho em uma única chamada de `doFinal`. |  F (máx. 117 bytes)  |
| 25  | Checksum por Soma e Complemento de 2: `soma + checksum ≡ 0 (mod 256)`.    |   V   |
| 26  | CRC-32 usa o polinômio reverso `0xEDB88320`. |   V   |
| 27  | Teste unitário verifica a interação entre módulos.   |   F (é o de integração)   |
| 28  | Smoke test é exaustivo.   |    F    |
| 29  | Teste de regressão reexecuta testes após mudanças.    |   V     |
| 30  | Para `java -jar` funcionar, o manifesto precisa de `Main-Class`.   |    V    |

---

## 5. Questão 4 – como resolver (e onde se erra)

### Mapa: parte da prova → código do modelo

| Parte         | Onde está no modelo  |
| I – GUI       | `TelaPrincipal` construtor: `JMenuBar`, 4 `JMenu`, 9 `JMenuItem`, `JTextArea` só leitura; feedback via `escrever()` |
| II – Mensagem | `lerMensagem()`, `gerarChecksum()`, `salvarMensagemComCS()`  |
| III – Chaves  | `gerarChavePublica()` / `gerarChavePrivada()` → `CryptoRSA.geraESalvaChave*` |
| IV – Cripto   | `cifrar()` / `decifrar()` → `CryptoRSA.geraCifra / geraDecifra` |
| V – Geral     | `mostrarDecifrada()` e `Sair` (`dispose(); System.exit(0);`)  |

Fluxo de dados: `msg_original.txt` → (CS) → `msg_com_cs.txt` → (RSA + `chave.publica`) → `msg_cifrada.txt` → (RSA + `chave.privada`) → `msg_decifrada.txt` → tela.

### Armadilhas (todas verificadas por execução)

1. **Chave pública e privada em cliques separados.** A `CryptoRSA.geraParDeChaves(pub, priv)` da aula gera **as duas juntas**. Se você chamá-la uma vez em cada item de menu, cada chamada cria um par **diferente** e os arquivos não combinam → `BadPaddingException` ao decifrar. Solução do modelo: gerar o par uma vez, guardar na memória do objeto, cada item grava a sua metade do **mesmo** par. Alternativa mais simples: chamar `geraParDeChaves` uma única vez (na 1ª chave pedida), e no outro item só avisar que já foi gravada.
2. **RSA só cifra 117 bytes por vez** (chave de 1024 bits com padding padrão). Com mensagem maior, `doFinal` lança `IllegalBlockSizeException: Data must not be longer than 117 bytes`. Uma mensagem "qualquer texto" + CS passa disso fácil. O modelo cifra em blocos de 117 (→ 128 bytes cifrados) e decifra de 128 em 128. **Teste sempre com um texto grande.** (AES não tem esse limite.)
3. **`msg_cifrada.txt` é binário.** Grave com `Files.write(path, byte[])`, não converta para `String` "no olho". No Bloco de Notas vai parecer lixo, e é o esperado. O modelo mostra o cifrado em hexadecimal na GUI (`Impressora`).
4. **Ordem dos cliques.** O usuário (ou o professor) pode clicar fora de ordem. Guarde o estado (`mensagem`, `checksum`) e, se faltar etapa, escreva na área de mensagens o que fazer, **sem `JOptionPane`** e sem estourar exceção.
5. **Sem `JOptionPane` nem botões.** A Parte I proíbe `JOptionPane`; erros também vão para a área de mensagens. Tudo por clique em item de menu.
6. **Nomes exatos** dos menus/itens, como no enunciado (copie e cole do PDF), incluindo acentos: `Gerar e salvar chave pública`.
7. **Pasta de trabalho.** "Na mesma pasta da aplicação" = arquivos com caminho relativo (`new File("msg_original.txt")`). Rode o `.jar` **de dentro da pasta** onde estão o `msg_original.txt` e o jar. O modelo mostra a pasta de trabalho na 1ª linha da tela, então você vê na hora se estiver errada.
8. **Acentos.** Escreva os `.java` em UTF-8 e compile com `-encoding UTF-8`. Ler o texto como UTF-8 e cifrar/decifrar como `byte[]` preserva tudo (verificado com "ação, coração").
9. **`Sair`** = `dispose(); System.exit(0);` (fechar a GUI **e** encerrar o processo).
10. **i18n em `.jar`** (se aparecer): o nome do bundle é _case-sensitive_ dentro do jar (`getBundle("tela")` falha se o arquivo for `Tela_*.properties`, **mesmo funcionando na IDE no Windows**), e os `.properties` precisam estar **dentro** do jar. Na aula 15, `TelaExemplo` usa `"ex1"` para arquivos `Ex1_*` – funciona na IDE, quebraria no jar.

### Gerar o `.jar`

Linha de comando (dentro da pasta dos `.java`):

```bash
javac -encoding UTF-8 -d out *.java
jar cfe App.jar Main -C out .
java -jar App.jar
```

`jar cfe`: **c**riar, **f**ile = nome, **e**ntry point = classe com `main` (vira o `Main-Class` do manifesto). Se usar `.properties`, copie-os para `out/` antes do `jar`. Se `jar` não for reconhecido, use o caminho completo do JDK (ex.: `C:\Program Files\Java\jdk-25.0.2\bin\jar.exe`).

- **NetBeans:** Propriedades do projeto → Run → _Main Class_; depois _Clean and Build_ gera `dist/NomeDoProjeto.jar`.
- **Eclipse:** File → Export → Java → _Runnable JAR file_, com _Launch configuration_ = sua classe principal.

**Depois de gerar, teste o jar numa pasta limpa** (só `App.jar` + `msg_original.txt`) e clique todos os itens na ordem. É o que o professor vai fazer.

### O que entregar

- o `.docx` da prova, com seu **RA e nome**, as respostas das Q1–Q3 e as **instruções exatas de execução** da Q4 (o professor segue _só_ o que você escrever; se não rodar, a questão é dada como errada). Exemplo: _"Abra o terminal na pasta `questao4`, execute `java -jar App.jar`. Requer JDK 17 ou superior."_
- uma pasta por questão com **todos os `.java`**, o **`.jar` funcional**, `msg_original.txt` e os **arquivos gerados** (`msg_com_cs.txt`, `chave.publica`, `chave.privada`, `msg_cifrada.txt`, `msg_decifrada.txt`).

Gere os arquivos rodando o jar uma vez, na ordem, **antes** de zipar.

---

## 6. Variações prováveis e como adaptar o modelo

| Se a prova trocar…  | Faça       |
| **RSA → AES** (chave simétrica) | Um único item "Gerar e salvar chave". Use `CryptoAES` da aula 17 sem mexer: `aes.geraChave(f)`, `aes.geraCifra(bytes, f)`, `aes.getTextoCifrado()`, `aes.geraDecifra(cifrado, f)`, `aes.getTextoDecifrado()`. Sem limite de tamanho e sem a armadilha do par (_verificado com 219 bytes_). |
| **Checksum → CRC-32**  | `checksum` vira `Long`; chame `calculadora.calcularCRC(mensagem.toCharArray())`; exiba/grave em hexadecimal (`Long.toHexString(crc).toUpperCase()`), como o `Ex03` da aula 18. |
| **Cifra → Dummy**  | `CryptoDummy` da aula 17 (`ex02`): passe `texto.clone()`. Baixa segurança, só didático.  |
| **Idioma pelo menu**   | `variacoes/TelaI18n.java`        |
| **Login com senha**  | `JPasswordField` + `getPassword()`; validar contra arquivo `nome;senha` (`ArquivosCola.validarLogin`)     |
| **Tabela**   | `JTable` + `DefaultTableModel` dentro de `JScrollPane` (`TelaComponentes.java`)  |
| **Data/hora da operação**       | `Calendar.getInstance()` (cuidado: `MONTH` começa em 0)    |
| **Pedir testes**   | copie o estilo do `ChecksumTest` (aula 18) ou `TesteIntegracao.java`   |

---

## 7. Checklist final

- [ ] Li o enunciado inteiro e conferi **os nomes exatos** dos menus, itens e arquivos.
- [ ] Sem `JOptionPane`; feedback só na área de mensagens; tudo por menu.
- [ ] Testei com mensagem **curta e longa** (> 117 bytes se RSA).
- [ ] Testei clicando **fora de ordem** (não estoura exceção).
- [ ] `Sair` fecha a janela e encerra o processo.
- [ ] Gerei o `.jar` e testei **numa pasta limpa**, na ordem do enunciado.
- [ ] Rodei o jar uma vez para gerar `msg_com_cs`, chaves, `msg_cifrada`, `msg_decifrada`.
- [ ] `.docx` com RA, nome, Q1–Q3 e **instruções de execução**.
- [ ] `.zip` nomeado `RA - NOME COMPLETO`, uma pasta por questão.
- [ ] Avisei o professor antes de acessar o Canvas (baixar e subir).

---

## Como isto foi validado

- `questao4-modelo` compilado com JDK 25; `TesteIntegracao`: **10/10**.
- GUI real acionada por cliques programáticos nos itens de menu, usando o `App.jar` numa pasta limpa: ordem correta, ordem errada, par de chaves dessincronizado e `Sair` (encerra a JVM): **14/14**. `java -jar App.jar` abre sem exceção.
- `TelaI18n` (troca entre 4 idiomas pelo menu), `TelaComponentes` (eventos, senha, tabela) e `ArquivosCola` executados.
- Confirmados por execução: limite de 117 bytes do RSA original, `getBundle` _case-sensitive_ dentro do jar, e AES da aula com mensagem longa.
