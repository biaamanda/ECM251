# Função Lambda (Expressão Lambda)

**Em inglês:** *Lambda Function* / *Lambda Expression*.

## O que é

Uma expressão lambda é uma função anônima: um trecho de código sem nome que pode ser guardado numa variável ou passado como argumento. Foi incluída no Java 8.

Em Java, uma lambda sempre implementa uma **interface funcional**, que é uma interface com um único método abstrato (por exemplo, `Runnable`, `Comparator`, `ActionListener`).

## Sintaxe

```java
(parametros) -> expressao
(parametros) -> { instrucoes; }
```

```java
() -> System.out.println("Oi")          // sem parâmetros
x -> x * 2                              // um parâmetro
(a, b) -> a + b                         // dois parâmetros
(a, b) -> { int s = a + b; return s; }  // bloco com return
```

## Antes e depois

Classe anônima:

```java
botao.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        enviar();
    }
});
```

Mesma coisa com lambda:

```java
botao.addActionListener(e -> enviar());
```

## Exemplos

```java
import java.util.*;
import java.util.function.*;

List<String> nomes = new ArrayList<>(List.of("Ana", "Bruno", "Carla"));

nomes.forEach(n -> System.out.println(n));              // percorrer
nomes.sort((a, b) -> b.compareTo(a));                   // ordenar (decrescente)
nomes.removeIf(n -> n.startsWith("B"));                 // filtrar

Function<Integer, Integer> dobro = x -> x * 2;
System.out.println(dobro.apply(5));                     // 10

int soma = List.of(1, 2, 3, 4).stream()
        .filter(x -> x % 2 == 0)
        .mapToInt(x -> x)
        .sum();                                         // 6
```

**Referência de método** é uma forma ainda mais curta, quando a lambda só chama um método: `nomes.forEach(System.out::println)`.

## Aplicações

- Tratamento de eventos em interfaces gráficas (botões, campos de texto).
- Ordenação e filtragem de coleções.
- Processamento de dados com a API de Streams (`filter`, `map`, `reduce`).
- Criação de threads: `new Thread(() -> tarefa()).start()`.
- Callbacks em geral (código que será executado depois).

Nesta aula, as lambdas aparecem nos botões do cliente (`enviar.addActionListener(e -> enviar())`) e na criação das threads do servidor do desafio.
