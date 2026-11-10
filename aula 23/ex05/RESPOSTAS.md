# Exercício 5 – 1ª instância do Cliente: mensagens e sair

> Com base no ProjetoBatepapo do Exercício 1 deste material, com o Servidor em operação, executar uma primeira instância da classe ClienteBatepapo.java, digitando algumas mensagens no seu console e, por fim, sair do Cliente, sem sair do Servidor. Verificar, registrar e explicar, em detalhes, as operações do Cliente e do Servidor.

**Saída (Cliente 1):**

```
*v*v*v* CONSOLE DO CLIENTE *v*v*v*
Conectado com /127.0.0.1:4000!
Digite uma mensagem (ou <sair> para finalizar):
<- Ola, sou o cliente 1
<- Segunda mensagem
<- sair
Cliente finalizado!
```

**Saída (Servidor):**

```
Conectado com /127.0.0.1:53334!
<- Cliente /127.0.0.1:53334: Ola, sou o cliente 1
<- Cliente /127.0.0.1:53334: Segunda mensagem
```

**Explicação:**

- **Cliente:**
  - conecta na porta 4000 e inicia a thread de leitura (`run()`);
  - na thread `main`, envia cada linha digitada;
  - ao digitar `sair`, envia `sair`, fecha o socket e termina com `Cliente finalizado!`.
- **Servidor:**
  - o `accept()` devolve a conexão, e o servidor mostra a porta de origem do cliente (`53334`), adiciona o cliente em `clients` e cria uma thread para ele;
  - a thread imprime cada mensagem e chama `sendMsgToAll`, mas não há outros clientes, então nada é repassado;
  - ao receber `sair`, o `return` acontece antes do `printf` (por isso `sair` não aparece), e o `finally` fecha a conexão;
  - o cliente continua na lista `clients`, mesmo desconectado;
  - o servidor volta a esperar no `accept()`.
