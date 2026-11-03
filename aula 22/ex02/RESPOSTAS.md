# Exercício 2 – Funções Anônimas em Java

## Comparação

**Exemplo 4 – Classe anônima**

```java
Operacao adicao = new Operacao() {
    @Override
   public int executar(int a, int b) {
    return a + b;
   }
};
```

**Exemplo 5 – Expressão lambda**

```java
Operacao adicao = (a, b) -> a + b;
```

| Característica     | Exemplo 4                   | Exemplo 5                 |
| ------------------ | --------------------------- | ------------------------- |
| Técnica            | Classe anônima              | Expressão lambda          |
| Tipos de `a` e `b` | Declarados (`int a, int b`) | Inferidos pelo compilador |
| Saída              | `Resultado: 8`              | `Resultado: 8`            |
