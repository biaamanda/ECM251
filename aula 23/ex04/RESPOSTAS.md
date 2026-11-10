# Exercício 4 – Executar o Servidor

> Com base no ProjetoBatepapo do Exercício 1 deste material, executar a classe ServidorBatepapo.java, verificar, registrar e explicar, em detalhes, a operação do Servidor, sem sair dele.

**O que ocorre (saída do servidor):**

```
*v*v*v* CONSOLE DO SERVIDOR *v*v*v*
Servidor iniciado na porta: 4000
Aguardando conexao de um cliente!
```

**Explicação:**

- O `start()` cria um `ServerSocket` na porta 4000, reservando a porta no SO.
- O `clientConnectionLoop()` imprime `Aguardando conexao de um cliente!` e para no `accept()`, que é bloqueante: a thread `main` fica esperando um cliente.
- A lista `clients` começa vazia, e nenhuma thread de cliente foi criada ainda.
- O servidor não termina: fica no `while(true)` indefinidamente.
