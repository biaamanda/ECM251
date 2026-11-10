# Exercício 9 – Encerrar os Clientes 1, 2 e 3 com <sair>

> Com base no ProjetoBatepapo do Exercício 1 deste material, com o Servidor e as três instâncias dos Clientes em operação, encerrar a execução das instâncias dos Clientes, uma por vez, digitando <sair> no console para cada uma delas, na ordem Cliente 1, 2 e 3 das instâncias. Verificar, registrar e explicar, em detalhes, as operações dos Clientes e do Servidor.

**Saída (Cliente 1):**

```
<- sair
Cliente finalizado!
```

**Saída (Cliente 2):**

```
<- sair
Cliente finalizado!
```

**Saída (Cliente 3):**

```
<- sair
Cliente finalizado!
```

**Saída (Servidor):**

```
(nenhuma linha nova)
```

**Explicação:**

- **Clientes:** cada um envia `sair` ao servidor, sai do laço `do...while`, fecha o socket no `finally` e imprime `Cliente finalizado!`. Ao fechar o socket, a thread `run()` também termina, e o processo encerra normalmente.
- **Servidor:**
  - ao receber `sair`, a thread de cada cliente faz `return` antes do `printf` e fecha a conexão no `finally`;
  - por isso nada aparece no console do servidor, e os outros clientes não são avisados de que alguém saiu;
  - as três threads de cliente terminam, uma de cada vez.
- Os três clientes continuam na lista `clients`, mesmo desconectados. Eles só seriam removidos quando alguém enviasse uma mensagem e o envio para eles falhasse.
- O servidor continua rodando, parado no `accept()`, esperando novos clientes. Ele não termina quando o último cliente sai.
