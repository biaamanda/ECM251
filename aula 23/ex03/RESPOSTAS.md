# Exercício 3 – Executar somente o Cliente

> Com base no ProjetoBatepapo do Exercício 1 deste material, executar, somente, a classe ClienteBatePapo.java, verificar, registrar e explicar, em detalhes, o ocorrido.

**O que ocorre (saída do cliente):**

```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "SocketCliente.close()" because "this.clientSocket" is null
	at ClienteBatepapo.start(ClienteBatepapo.java:26)
	at ClienteBatepapo.main(ClienteBatepapo.java:53)
```

**Explicação:**

- Sem servidor, ninguém está escutando na porta 4000. O `new Socket(...)` lança `ConnectException` (conexão recusada).
- Como o `SocketCliente` não chegou a ser criado, `clientSocket` continua `null`.
- O `finally` do `start()` roda mesmo assim e chama `clientSocket.close()` em `null`, gerando uma `NullPointerException`. Essa exceção **substitui** a `ConnectException` original.
- O `catch` do `main` só trata `IOException`. A `NullPointerException` não é capturada, o programa termina com erro e não imprime `Erro ao iniciar o cliente` nem `Cliente finalizado!`.
- É um defeito do código: o `finally` deveria verificar `if(clientSocket != null)`. O `Client.java` da aula 22, que não tinha esse `finally`, mostrava `Connection refused` normalmente.
- Conclusão: servidor precisa ser iniciado antes do cliente.
