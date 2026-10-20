# Aula 20 – Cliente-Servidor com Java Sockets

Exercícios de entrega (slides 41 a 45 do TP20).

| Pasta | Conteúdo |
|-------|----------|
| [ex01/SimpleClientServer](ex01/SimpleClientServer/) | Projeto `SimpleClientServer` com as classes fornecidas `SimpleServerTest` e `SimpleClientTest` (item a) |
| [ex01/RESPOSTAS.md](ex01/RESPOSTAS.md) | Registro e explicação dos itens b a g |
| [ex02](ex02/) | `SimpleClientTest` com interface gráfica própria (BorderLayout + GridLayout + FlowLayout): campo de mensagem, histórico, campo de status e botões **Enviar**, **Limpar** e **Sair**. Sem console e sem `JOptionPane` |
| [desafio](desafio/) | `SimpleServer2Test` (retransmite cada mensagem de volta ao cliente) e `SimpleClient2Test` (GUI do ex02 + thread que recebe o eco e o mostra junto com as mensagens enviadas) |

## Como executar

Abra dois terminais na pasta do exercício: um para o servidor e outro para o cliente.

```
javac *.java
java SimpleServerTest      # ou SimpleServer2Test no desafio
java SimpleClientTest      # ou SimpleClient2Test no desafio
```

- O servidor sempre deve ser iniciado **antes** do cliente. No ex02 e no desafio, se o servidor não
  estiver no ar, o status mostra a falha e o botão **Enviar** tenta conectar de novo.
- O servidor atende **um** cliente e termina quando esse cliente sai.
- O desafio usa a porta **3335**, para não conflitar com o servidor dos exercícios 1 e 2 (porta 3334).
