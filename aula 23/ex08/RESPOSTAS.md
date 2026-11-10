# Exercício 8 – 3ª instância do Cliente

> Com base no ProjetoBatepapo do Exercício 1 deste material, com o Servidor e as duas instâncias dos Clientes em operação, executar uma terceira instância da classe ClienteBatepapo.java, digitando algumas mensagens no seu console e nos consoles das instâncias dos outros Clientes, alternadamente e, por fim, não sair dos Clientes, nem do Servidor. Verificar, registrar e explicar, em detalhes, as operações dos Clientes e do Servidor.

Portas de origem: `53338` = Cliente 1, `53341` = Cliente 2, `53343` = Cliente 3.

**Saída (Cliente 1)**:

```
<-
-> Cliente /127.0.0.1:53343: Cheguei, sou o cliente 3               //cliente 3
Digite uma mensagem (ou <sair> para finalizar):
<- Bem-vindo, cliente 3                                             //cliente 1
<-
-> Cliente /127.0.0.1:53341: Ola, cliente 3                         //cliente 2
Digite uma mensagem (ou <sair> para finalizar):
<-
-> Cliente /127.0.0.1:53343: Obrigado!
Digite uma mensagem (ou <sair> para finalizar):
<-
```

**Saída (Cliente 2)** (continuação):

```
<-
-> Cliente /127.0.0.1:53343: Cheguei, sou o cliente 3
Digite uma mensagem (ou <sair> para finalizar):
<-
-> Cliente /127.0.0.1:53338: Bem-vindo, cliente 3
Digite uma mensagem (ou <sair> para finalizar):
<- Ola, cliente 3
<-
-> Cliente /127.0.0.1:53343: Obrigado!
Digite uma mensagem (ou <sair> para finalizar):
<-
```

**Saída (Cliente 3):**

```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Conectado com /127.0.0.1:4000!
Digite uma mensagem (ou <sair> para finalizar):
<- Cheguei, sou o cliente 3
<-
-> Cliente /127.0.0.1:53338: Bem-vindo, cliente 3
Digite uma mensagem (ou <sair> para finalizar):
<-
-> Cliente /127.0.0.1:53341: Ola, cliente 3
Digite uma mensagem (ou <sair> para finalizar):
<- Obrigado!
<-
```

**Saída (Servidor):**

```
Conectado com /127.0.0.1:53343!
<- Cliente /127.0.0.1:53343: Cheguei, sou o cliente 3
<- Cliente /127.0.0.1:53338: Bem-vindo, cliente 3
<- Cliente /127.0.0.1:53341: Ola, cliente 3
<- Cliente /127.0.0.1:53343: Obrigado!
```

**Explicação:**

- O servidor aceitou o Cliente 3 e criou a terceira thread. Agora `clients` tem três clientes, cada um com sua thread.
- Cada mensagem é impressa no servidor e repassada pelo `sendMsgToAll` para os outros dois clientes. Com três clientes, cada mensagem gera dois envios.
- Todos os clientes receberam todas as mensagens dos outros, na mesma ordem em que o servidor as recebeu.
- O Cliente 3 não recebe as mensagens antigas, porque o servidor não guarda histórico: ele só repassa para quem está na lista no momento.
