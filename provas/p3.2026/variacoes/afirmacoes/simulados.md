# Simulados de afirmações V/F

4 simulados no formato da prova (9 afirmações, 3 questões cada). **Gabarito em [`gabarito.md`](gabarito.md)**, com justificativa de cada afirmação.

Como treinar: 15 min por simulado, no papel. Primeiro julgue **A1–A9 (V ou F)**, depois responda Q1–Q3. Depois confira e leia a justificativa das que errou.

---

## Simulado 1: Swing e internacionalização (aulas 13–15)

**AFIRMAÇÕES:**

**A1 –** O método `getSource()` de um `ActionEvent` devolve o objeto que gerou o evento, o que permite que um único `ActionListener` trate vários botões.

**A2 –** O componente `JComboBox` gera apenas `ActionEvent`; não é possível tratá-lo com um `ItemListener`.

**A3 –** Vários `JRadioButton` só ficam mutuamente exclusivos (apenas um marcado por vez) se forem adicionados a um mesmo `ButtonGroup`.

**A4 –** Nos arquivos `.properties` de internacionalização, cada idioma deve usar chaves diferentes, pois cada idioma tem o seu próprio texto.

**A5 –** Chamar `getString()` com uma chave que não existe no `ResourceBundle` lança `MissingResourceException`.

**A6 –** Depois de trocar o `ResourceBundle` de uma tela já aberta, os textos dos componentes se atualizam automaticamente, sem nenhum código adicional.

**A7 –** Para que os títulos das colunas de uma `JTable` apareçam, ela deve ser inserida dentro de um `JScrollPane`.

**A8 –** O layout padrão de um `JPanel` é o `BorderLayout`.

**A9 –** Na classe `Calendar`, o mês de janeiro é representado pelo valor 1, por isso não é preciso somar 1 ao exibir o mês.

**QUESTÕES:**

**Q1 –** Assinale a única alternativa correta:
a) Apenas a afirmação A1 é verdadeira, dentre A1, A2 e A3;
b) As afirmações A1, A2 e A3 são verdadeiras;
c) Apenas a afirmação A2 é falsa, dentre A1, A2 e A3;
d) As afirmações A1, A2 e A3 são falsas;
e) N.D.A.

**Q2 –** Assinale a única alternativa correta:
a) As afirmações A4, A5 e A6 são verdadeiras;
b) Apenas a afirmação A5 é verdadeira, dentre A4, A5 e A6;
c) As afirmações A4, A5 e A6 são falsas;
d) Apenas a afirmação A4 é falsa, dentre A4, A5 e A6;
e) N.D.A.

**Q3 –** Assinale a única alternativa correta:
a) As afirmações A7, A8 e A9 são verdadeiras;
b) Apenas a afirmação A8 é falsa, dentre A7, A8 e A9;
c) Apenas a afirmação A7 é verdadeira, dentre A7, A8 e A9;
d) As afirmações A7, A8 e A9 são falsas;
e) N.D.A.

---

## Simulado 2: Arquivos e criptografia (aulas 16–17)

**AFIRMAÇÕES:**

**A1 –** Ao criar `new FileWriter("dados.txt", true)`, o novo conteúdo é acrescentado ao final do arquivo, sem apagar o que já existia.

**A2 –** `IOException` é uma exceção verificada (*checked*): o compilador exige que ela seja tratada com `try/catch` ou declarada com `throws`.

**A3 –** Ao criar `new Scanner(new File("x.txt"))` para um arquivo que não existe, o Java cria automaticamente um arquivo vazio com esse nome.

**A4 –** Na criptografia assimétrica, uma mensagem cifrada com a chave pública pode ser decifrada com essa mesma chave pública.

**A5 –** O AES é um algoritmo de criptografia de chave assimétrica.

**A6 –** Cifrar uma mensagem com a chave privada permite verificar a autenticidade do remetente, pois apenas o dono da chave privada poderia tê-la produzido.

**A7 –** Em geral, os algoritmos assimétricos são mais rápidos e consomem menos recursos computacionais que os simétricos.

**A8 –** Na criptografia assimétrica, a chave privada é gerada e mantida por cada participante, sem que precise ser distribuída.

**A9 –** As cifras de fluxo cifram os bits da mensagem um a um, enquanto as cifras de bloco cifram um conjunto de bits como uma única unidade.

**QUESTÕES:**

**Q1 –** Assinale a única alternativa correta:
a) As afirmações A1, A2 e A3 são verdadeiras;
b) Apenas a afirmação A3 é verdadeira, dentre A1, A2 e A3;
c) As afirmações A1, A2 e A3 são falsas;
d) Apenas a afirmação A1 é falsa, dentre A1, A2 e A3;
e) N.D.A.

**Q2 –** Assinale a única alternativa correta:
a) As afirmações A4, A5 e A6 são verdadeiras;
b) Apenas a afirmação A4 é verdadeira, dentre A4, A5 e A6;
c) Apenas a afirmação A6 é verdadeira, dentre A4, A5 e A6;
d) Apenas a afirmação A5 é falsa, dentre A4, A5 e A6;
e) N.D.A.

**Q3 –** Assinale a única alternativa correta:
a) As afirmações A7, A8 e A9 são falsas;
b) Apenas a afirmação A7 é falsa, dentre A7, A8 e A9;
c) As afirmações A7, A8 e A9 são verdadeiras;
d) Apenas a afirmação A9 é verdadeira, dentre A7, A8 e A9;
e) N.D.A.

---

## Simulado 3: Checksum, CRC, testes e `.jar` (aula 18)

**AFIRMAÇÕES:**

**A1 –** No checksum por Soma e Complemento de 2, somando o checksum aos dados originais (módulo 256), o resultado é zero.

**A2 –** Calculado com 1 byte por caractere (módulo 256), o valor do checksum por Soma e Complemento de 2 fica sempre entre 0 e 255.

**A3 –** Trocar a ordem de dois caracteres (por exemplo, `"AB"` por `"BA"`) não altera o checksum por Soma e Complemento de 2, mas normalmente altera o CRC-32.

**A4 –** Um teste unitário só funciona se o usuário digitar os dados pelo teclado durante a execução.

**A5 –** O teste de regressão consiste em reexecutar testes já existentes após uma alteração no código, para verificar que nada que funcionava deixou de funcionar.

**A6 –** O teste de integração verifica cada método de uma classe isoladamente, sem a participação das demais classes.

**A7 –** Para que um `.jar` possa ser executado com `java -jar`, o manifesto precisa indicar a classe principal (`Main-Class`).

**A8 –** No comando `jar cfe App.jar Main -C out .`, a letra `e` indica a extensão dos arquivos que serão incluídos no `.jar`.

**A9 –** O algoritmo de CRC-32 implementado bit a bit na aula usa o polinômio reverso `0xEDB88320`.

**QUESTÕES:**

**Q1 –** Assinale a única alternativa correta:
a) Apenas a afirmação A1 é verdadeira, dentre A1, A2 e A3;
b) As afirmações A1, A2 e A3 são verdadeiras;
c) Apenas a afirmação A2 é falsa, dentre A1, A2 e A3;
d) As afirmações A1, A2 e A3 são falsas;
e) N.D.A.

**Q2 –** Assinale a única alternativa correta:
a) As afirmações A4, A5 e A6 são verdadeiras;
b) As afirmações A4, A5 e A6 são falsas;
c) Apenas a afirmação A4 é falsa, dentre A4, A5 e A6;
d) Apenas a afirmação A5 é verdadeira, dentre A4, A5 e A6;
e) N.D.A.

**Q3 –** Assinale a única alternativa correta:
a) As afirmações A7, A8 e A9 são falsas;
b) Apenas a afirmação A9 é verdadeira, dentre A7, A8 e A9;
c) As afirmações A7, A8 e A9 são verdadeiras;
d) Apenas a afirmação A8 é falsa, dentre A7, A8 e A9;
e) N.D.A.

---

## Simulado 4: Misto, com pegadinhas (aulas 13–18)

**AFIRMAÇÕES:**

**A1 –** Fechar a janela de um `JFrame` encerra a execução do programa, mesmo sem configurar `setDefaultCloseOperation`.

**A2 –** O `JPasswordField` herda de `JTextField` e oferece o método `getPassword()`, que retorna um `char[]`.

**A3 –** Um `JFrame` recém-criado só aparece na tela depois da chamada a `setVisible(true)`.

**A4 –** Com RSA de 1024 bits e o padding padrão, uma única chamada a `doFinal` cifra no máximo 117 bytes; mensagens maiores exigem a divisão em blocos.

**A5 –** Converter um `byte[]` cifrado para `String` com UTF-8 e depois de volta para `byte[]` sempre devolve exatamente os mesmos bytes.

**A6 –** A codificação ISO-8859-1 associa cada byte a exatamente um caractere, por isso a conversão `byte[]` → `String` → `byte[]` não perde dados.

**A7 –** Se não existir nenhum arquivo `.properties` aplicável (nem o do `Locale` pedido, nem o do `Locale` padrão, nem o arquivo base), `ResourceBundle.getBundle` lança `MissingResourceException`.

**A8 –** Um teste de integração é, por definição, um teste que dispensa qualquer arquivo ou classe real e usa somente valores simulados.

**A9 –** Testes unitários automatizados, como o `ChecksumTest` da aula 18, exigem que o usuário confirme manualmente cada resultado.

**QUESTÕES:**

**Q1 –** Assinale a única alternativa correta:
a) Apenas a afirmação A1 é falsa, dentre A1, A2 e A3;
b) As afirmações A1, A2 e A3 são verdadeiras;
c) As afirmações A1, A2 e A3 são falsas;
d) Apenas a afirmação A3 é verdadeira, dentre A1, A2 e A3;
e) N.D.A.

**Q2 –** Assinale a única alternativa correta:
a) As afirmações A4, A5 e A6 são verdadeiras;
b) Apenas a afirmação A4 é verdadeira, dentre A4, A5 e A6;
c) Apenas a afirmação A5 é falsa, dentre A4, A5 e A6;
d) As afirmações A4, A5 e A6 são falsas;
e) N.D.A.

**Q3 –** Assinale a única alternativa correta:
a) Apenas a afirmação A8 é falsa, dentre A7, A8 e A9;
b) As afirmações A7, A8 e A9 são falsas;
c) As afirmações A7, A8 e A9 são verdadeiras;
d) Apenas a afirmação A7 é verdadeira, dentre A7, A8 e A9;
e) N.D.A.
