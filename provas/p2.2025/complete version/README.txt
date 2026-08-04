CEM - Consumo de Energia Mensal
================================

ESTRUTURA
---------
src/Comodo.java       -> classe de modelo (cômodo: nome, consumo, custo)
src/Cliente.java      -> classe de modelo (cliente: nome, valor do kWh, lista de cômodos)
src/ClienteDAO.java   -> classe de acesso ao MySQL (salvar / buscarPorNome)
src/Main.java         -> classe da GUI (Swing), com menu "Opções"
src/ClienteTest.java  -> classe de teste do modelo (sem GUI e sem banco),
                         valida os cálculos usando os mesmos números do
                         exemplo da prova (Sala/Cozinha/Quarto)
sql/schema.sql        -> script de criação do banco "cem_db" e das tabelas
dist/CEM.jar          -> aplicação já compilada e empacotada (classe
                         principal: Main)

COMO PREPARAR O BANCO
----------------------
1. Tenha um servidor MySQL rodando localmente (porta padrão 3306).
2. Execute o script:
       mysql -u root -p < sql/schema.sql
3. Se seu usuário/senha do MySQL forem diferentes de root/senha, ajuste
   as constantes USER e PASSWORD no início de ClienteDAO.java (e
   recompile) antes de gerar o .jar final.

DRIVER JDBC DO MYSQL (MUITO IMPORTANTE)
----------------------------------------
Como a prova não permite acesso à internet durante a execução, o
Connector/J (mysql-connector-j-x.x.x.jar) precisa estar baixado
previamente na sua máquina e adicionado ao classpath, tanto para
compilar quanto para rodar/gerar o .jar. Neste ambiente de teste eu não
tinha esse driver disponível (sem acesso à internet), por isso o
dist/CEM.jar já incluso aqui roda a GUI e os cálculos normalmente, mas
"Salvar no B.D." e "Ler do B.D." só vão funcionar na sua máquina, com o
driver no classpath.

Para compilar/rodar com o driver:
    javac -cp .:mysql-connector-j-8.x.x.jar -d out src/*.java
    java  -cp out:mysql-connector-j-8.x.x.jar Main

Para gerar o .jar final incluindo o driver, a forma mais simples no
VS Code / IntelliJ é usar a opção de "Build Artifact" com o driver como
dependência, ou extrair o driver e recolocar suas classes junto das
suas antes de empacotar.

COMO RODAR A APLICAÇÃO (sem banco, só para ver a GUI)
-------------------------------------------------------
    java -jar dist/CEM.jar

COMO RODAR A CLASSE DE TESTE (modelo, sem GUI/banco)
------------------------------------------------------
    javac -d out src/Comodo.java src/Cliente.java src/ClienteTest.java
    java -cp out ClienteTest

TEST CODE
---------
O código "OBQFG" (o mesmo do cabeçalho da sua prova) está hard-coded em
Main.java, na constante TEST_CODE, e é exibido em um campo cinza
(não editável) na GUI, exatamente como pedido no enunciado - ele não é
gravado no banco de dados.

O QUE FOI CORRIGIDO EM RELAÇÃO AO SEU Main.java ORIGINAL
------------------------------------------------------------
- Erro de sintaxe: "new GridLayout(4, 3,)" tinha uma vírgula sobrando.
- O layout antigo só criava rótulos (labels) de cabeçalho, sem os
  campos de texto de fato (Nome do Cliente, Valor do kWh, Cômodo,
  Consumo, Custo, Test Code) - eles foram todos implementados.
- Os métodos actionPerformed/readFromDatabase/saveToDatabase estavam
  comentados e vazios - foram implementados de fato, junto com a classe
  ClienteDAO para a persistência em MySQL.
- Foi adicionado o JMenuBar com o menu "Opções" (e mnemônicos) pedido
  no enunciado, no lugar dos JButtons que você tinha.
- Foi adicionado o cálculo automático do custo (ao sair do campo de
  consumo ou do valor do kWh), atendendo ao requisito de "cálculos
  espontâneos e instantâneos".
