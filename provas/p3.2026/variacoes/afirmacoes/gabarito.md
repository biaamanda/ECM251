# Gabarito dos simulados

Só abra depois de resolver. As respostas foram deduzidas por mim a partir do conteúdo das aulas. Os fatos técnicos de Java (eventos, layouts, `FileWriter`, `Calendar`, `ResourceBundle`, ISO-8859-1, checksum × CRC, limite de 117 bytes do RSA) foram **conferidos por execução**.

## Resumo das respostas

| Simulado | Q1 | Q2 | Q3 |
|---|:-:|:-:|:-:|
| 1 – Swing e i18n | **c** | **b** | **c** |
| 2 – Arquivos e criptografia | **e** | **c** | **b** |
| 3 – Checksum, testes, jar | **b** | **d** | **d** |
| 4 – Misto | **a** | **c** | **d** |

---

## Simulado 1: Swing e internacionalização

| | V/F | Por quê |
|---|:-:|---|
| A1 | **V** | `getSource()` identifica quem disparou; é assim que um só `actionPerformed` compara `origem == botao1`, `origem == botao2`… |
| A2 | **F** | O combo gera **também** `ItemEvent` (2 por troca: DESELECTED e SELECTED) e aceita `ItemListener` |
| A3 | **V** | A exclusão mútua vem do `ButtonGroup`; sem ele, dois radios podem ficar marcados |
| A4 | **F** | As **chaves são iguais** em todos os idiomas; só os valores mudam |
| A5 | **V** | Chave inexistente → `MissingResourceException` |
| A6 | **F** | Trocar o bundle não muda a tela: é preciso reaplicar `setText(bn.getString(...))` em cada componente |
| A7 | **V** | Fora de um `JScrollPane` o cabeçalho não é exibido (a menos que você o adicione à mão via `getTableHeader()`) |
| A8 | **F** | `JPanel` usa **`FlowLayout`**; o `BorderLayout` é o do `JFrame` |
| A9 | **F** | Janeiro é **0**; ao exibir, some 1 |

- **Q1:** V, F, V → *"apenas A2 é falsa"* → **c**
- **Q2:** F, V, F → *"apenas A5 é verdadeira"* → **b**
- **Q3:** V, F, F → *"apenas A7 é verdadeira"* → **c**

---

## Simulado 2: Arquivos e criptografia

| | V/F | Por quê |
|---|:-:|---|
| A1 | **V** | O segundo argumento `true` liga o modo *append* |
| A2 | **V** | `IOException` não é `RuntimeException`: o compilador obriga a tratar ou declarar |
| A3 | **F** | `new Scanner(new File(...))` lança `FileNotFoundException`; **não cria** o arquivo |
| A4 | **F** | Cifrou com a pública → só a **privada** decifra |
| A5 | **F** | AES é **simétrico** (o assimétrico da aula é o RSA) |
| A6 | **V** | Slide da aula 17: cifrar com a privada garante **autenticidade** |
| A7 | **F** | É o contrário: assimétrico é **mais lento** e usa **mais** recursos |
| A8 | **V** | A privada é gerada localmente por cada participante e **nunca precisa ser distribuída** |
| A9 | **V** | Definição de cifra de fluxo × cifra de bloco (slides da aula 17) |

- **Q1:** V, V, F → a combinação certa seria "apenas A3 é falsa", que **não está** entre as alternativas → **e (N.D.A.)**
- **Q2:** F, F, V → *"apenas A6 é verdadeira"* → **c**
- **Q3:** F, V, V → *"apenas A7 é falsa"* → **b**

---

## Simulado 3: Checksum, CRC, testes e `.jar`

| | V/F | Por quê |
|---|:-:|---|
| A1 | **V** | É a propriedade do complemento de 2: `soma + checksum ≡ 0 (mod 256)` |
| A2 | **V** | Com 1 byte (mod 256) o resultado está sempre em **0–255** |
| A3 | **V** | A soma ignora a ordem (`"AB"` e `"BA"` → 125). O CRC-32 é sensível à ordem (`30694C07` × `824D4E7E`) |
| A4 | **F** | Teste unitário é **automatizado**, sem entrada do usuário (`ChecksumTest`) |
| A5 | **V** | É a definição de teste de regressão |
| A6 | **F** | Isolar cada método é o teste **unitário**. Integração testa módulos **juntos** |
| A7 | **V** | Sem `Main-Class` no manifesto, `java -jar` não sabe onde começar |
| A8 | **F** | `e` = ***e**ntry point* (a classe principal, `Main`), não extensão |
| A9 | **V** | Polinômio reverso `0xEDB88320`, exatamente o do `Checksum.calcularCRC` |

- **Q1:** V, V, V → *todas verdadeiras* → **b**
- **Q2:** F, V, F → *"apenas A5 é verdadeira"* → **d**
- **Q3:** V, F, V → *"apenas A8 é falsa"* → **d**

---

## Simulado 4: Misto, com pegadinhas

| | V/F | Por quê |
|---|:-:|---|
| A1 | **F** | O padrão do `JFrame` é `HIDE_ON_CLOSE`: só esconde. Para encerrar: `EXIT_ON_CLOSE` |
| A2 | **V** | `JPasswordField extends JTextField`; `getPassword()` → `char[]` |
| A3 | **V** | O `JFrame` nasce invisível |
| A4 | **V** | 128 bytes de módulo − 11 de padding = **117**. Acima disso: `IllegalBlockSizeException` |
| A5 | **F** | Bytes cifrados são arbitrários; UTF-8 os altera (sequências inválidas viram `�`). Perde dados |
| A6 | **V** | ISO-8859-1 é 1 byte ↔ 1 caractere, sem perda (é o `CHARSET` do `TesteCrypto`) |
| A7 | **V** | Sem nenhum arquivo aplicável (nem o base) → `MissingResourceException` |
| A8 | **F** | Integração combina componentes **reais** (classes, arquivos…); usar só valores simulados não é a definição |
| A9 | **F** | O ponto do teste automatizado é justamente **não** depender do usuário |

- **Q1:** F, V, V → *"apenas A1 é falsa"* → **a**
- **Q2:** V, F, V → *"apenas A5 é falsa"* → **c**
- **Q3:** V, F, F → *"apenas A7 é verdadeira"* → **d**

---

## Observações

- No Simulado 4, **A8** é a afirmação mais "interpretável". Se na sua prova a definição vista em aula for diferente da que usei, siga a da aula.
- Nas afirmações sobre autenticidade (S2-A6), a prova usa a formulação dos slides da aula 17 ("a chave privada é usada para cifrar… garante-se que apenas o dono da chave poderia tê-la editado"). Na prática, isso é o que se chama de assinatura digital.
