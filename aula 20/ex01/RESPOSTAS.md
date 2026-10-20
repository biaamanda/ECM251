# Aula 20 – Exercício 1

## a.
Classes `SimpleServerTest.java` e `SimpleClientTest.java` digitadas nesta pasta.

## b.
Saída: `Falha na comunicacao: Connection refused: connect`

Nenhum servidor está escutando na porta 3334, então o sistema operacional recusa a conexão. O `new Socket(...)` lança uma `IOException`, que cai no `catch`, e o programa termina sem abrir a janela.

## c.
Saída: `Servidor iniciado na porta 3334`

O `ServerSocket` passa a escutar a porta 3334 e o programa fica parado no `accept()`, esperando um cliente se conectar.

## d.
O cliente se conecta e o servidor mostra `Cliente do IP: 127.0.0.1 conectado ao servidor`. Cada mensagem digitada é enviada pelo socket e impressa no console do servidor. Ao digitar `sair`, o cliente termina e a conexão é fechada. O servidor detecta o fim da conexão, sai do laço, imprime `Servidor finalizado!` e também termina.

Se clicar em **Cancelar**, `msg` fica `null` e o cliente termina com `NullPointerException`.

## e.
(O servidor precisa ser iniciado de novo, pois terminou no item d.)

A segunda instância do cliente também diz que está conectada, mas as mensagens dela **não aparecem no servidor**. As mensagens da primeira instância continuam aparecendo normalmente.

Isso acontece porque o servidor chama `accept()` uma única vez e fica preso lendo o primeiro cliente. O sistema operacional aceita a segunda conexão e a deixa numa fila de espera, mas o programa nunca a atende. O servidor só atende um cliente.

## f.
Nada muda no servidor: as mensagens da primeira instância continuam chegando. Como a segunda conexão nunca foi atendida, encerrá-la não tem efeito, e as mensagens dela são perdidas.

## g.
Saída: `Erro ao criar o servidor!`

A porta 3334 já está em uso pela primeira instância. O `new ServerSocket(PORTA)` lança uma `BindException` (uma `IOException`), que cai no `catch`. A primeira instância continua funcionando normalmente.
