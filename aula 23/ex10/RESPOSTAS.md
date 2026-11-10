# Exercício 10 – Segunda instância do Servidor

> Com base no ProjetoBatepapo do Exercício 1 deste material, ainda com a primeira instância do Servidor em operação, executar, a seguir, uma segunda instância da classe Server.java. Verificar, registrar e explicar, em detalhes, as operações dos Servidores.

**Saída (Servidor 1):**

```
(nenhuma linha nova; continua rodando)
```

**Saída (Servidor 2):**

```
*v*v*v* CONSOLE DO SERVIDOR *v*v*v*
Erro ao iniciar o servidor: Address already in use: bind
Servidor finalizado!
```

**Explicação:**

- **Servidor 2:**
  - tenta criar `new ServerSocket(4000)`, mas a porta 4000 já está em uso pelo Servidor 1;
  - o SO não permite dois programas escutando na mesma porta, e o construtor lança `BindException` (uma `IOException`): `Address already in use: bind`;
  - o `catch` do `main` mostra `Erro ao iniciar o servidor: ...`, e o programa segue para `Servidor finalizado!` e termina;
  - é o único caso em que `Servidor finalizado!` aparece.
- **Servidor 1:** não é afetado. Continua no `accept()`, esperando clientes.
- Para rodar dois servidores ao mesmo tempo, o segundo precisaria de outra porta (por exemplo, 3334).
