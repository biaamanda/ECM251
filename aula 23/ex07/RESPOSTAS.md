# Exercício 7 – 2ª instância do Cliente

> Com base no ProjetoBatepapo do Exercício 1 deste material, com o Servidor e a primeira instância do Cliente em operação, executar uma segunda instância da classe ClienteBatepapo.java, digitando algumas mensagens no seu console e no console da instância do outro Cliente, alternadamente e, por fim, não sair dos Clientes, nem do Servidor. Verificar, registrar e explicar, em detalhes, as operações dos Clientes e do Servidor.

Portas de origem: `53338` = Cliente 1, `53341` = Cliente 2.

**Saída (Cliente 1)**:

```
<-
-> Cliente /127.0.0.1:53341: Oi, sou o cliente 2
Digite uma mensagem (ou <sair> para finalizar):
<- Oi cliente 2, aqui e o cliente 1
<-
-> Cliente /127.0.0.1:53341: Tudo bem?
Digite uma mensagem (ou <sair> para finalizar):
<- Tudo otimo!
<-
```

**Saída (Cliente 2):**

```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Conectado com /127.0.0.1:4000!
Digite uma mensagem (ou <sair> para finalizar):
<- Oi, sou o cliente 2
<-
-> Cliente /127.0.0.1:53338: Oi cliente 2, aqui e o cliente 1
Digite uma mensagem (ou <sair> para finalizar):
<- Tudo bem?
<-
-> Cliente /127.0.0.1:53338: Tudo otimo!
Digite uma mensagem (ou <sair> para finalizar):
<-
```

**Saída (Servidor):**

```
Conectado com /127.0.0.1:53341!
<- Cliente /127.0.0.1:53341: Oi, sou o cliente 2
<- Cliente /127.0.0.1:53338: Oi cliente 2, aqui e o cliente 1
<- Cliente /127.0.0.1:53341: Tudo bem?
<- Cliente /127.0.0.1:53338: Tudo otimo!
```

**Explicação:**

- Agora o bate-papo funciona: o que um cliente digita aparece no outro.
- **Servidor:**
  - aceitou o Cliente 2 e criou uma segunda thread, então há uma thread por cliente atendendo os dois ao mesmo tempo;
  - a cada mensagem, imprime `<- Cliente ...` e o `sendMsgToAll` envia `Cliente <endereço>: <mensagem>` para o outro cliente.
- **Clientes:**
  - a thread `run()` recebe a mensagem e imprime `-> Cliente ...: ...`, mesmo com a thread `main` parada no `nextLine()`;
  - por isso a mensagem aparece "no meio" do prompt `<- ` vazio, seguida de um novo `Digite uma mensagem...`;
  - o remetente não recebe a própria mensagem.
- Clientes se identificam pelo IP e pela porta de origem (programa não tem nomes de usuário).
