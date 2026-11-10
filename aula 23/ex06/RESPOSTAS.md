# Exercício 6 – 1ª instância do Cliente novamente, sem sair

> Com base no ProjetoBatepapo do Exercício 1 deste material, com o Servidor em operação, executar, novamente, uma primeira instância da classe ClienteBatepapo.java, digitando algumas mensagens no seu console e, por fim, não sair do Cliente, nem do Servidor. Verificar, registrar e explicar, em detalhes, as operações do Cliente e do Servidor.

**Saída (Cliente 1):**

```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Conectado com /127.0.0.1:4000!
Digite uma mensagem (ou <sair> para finalizar):
<- Cliente 1 de novo
<- Ainda estou conectado
<-
```

**Saída (Servidor):**

```
Conectado com /127.0.0.1:53338!
<- Cliente /127.0.0.1:53338: Cliente 1 de novo
<- Cliente /127.0.0.1:53338: Ainda estou conectado
```

**Explicação:**

- O servidor ainda estava no ar e aceitou a nova conexão. Para ele, este é um cliente novo, com outra porta de origem (`53338`). O servidor não sabe que é "o mesmo" cliente.
- **Cliente:** como no ex05, mas fica parado no prompt `<- ` (no `nextLine()`), com a thread `run()` esperando mensagens da rede.
- **Servidor:** imprime as mensagens e chama `sendMsgToAll`. A lista tinha o cliente antigo (fechado) e este novo:
  - o envio para o cliente antigo falha (`checkError()`), e ele é removido da lista pelo `iterator.remove()`;
  - nada é enviado ao próprio remetente;
  - isso não aparece no console.
- Cliente e servidor continuam rodando, com uma thread do servidor dedicada a este cliente.
