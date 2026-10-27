# Aula 21 – Exercício 1

**a.** Classes digitadas nesta pasta.

**b.** `Falha na comunicacao: Connection refused: connect`. Não há servidor escutando na porta 3334, então a conexão é recusada e o `new Socket(...)` lança `IOException`.

**c.** `Servidor iniciado na porta 3334`. O servidor fica parado no `accept()`, esperando um cliente.

**d.** O servidor mostra o IP do cliente e imprime cada mensagem recebida. Ao digitar `sair`, o cliente fecha a conexão, o servidor sai do laço, imprime `Servidor finalizado!` e termina.

**e.** (Reiniciar o servidor antes.) A segunda instância conecta, mas suas mensagens não aparecem: o servidor chama `accept()` uma única vez e só lê o primeiro cliente. A segunda conexão fica na fila do sistema operacional, sem ser atendida. As mensagens da primeira instância continuam chegando.

**f.** Nada muda: a segunda conexão nunca foi atendida, então encerrá-la não afeta o servidor. A primeira instância continua funcionando.

**g.** `Erro ao criar o servidor!`. A porta 3334 já está em uso pela primeira instância, então o `new ServerSocket(PORTA)` lança `BindException`.
