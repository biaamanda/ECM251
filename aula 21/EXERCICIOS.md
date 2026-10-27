# Aula 21 – Cliente-Servidor Java Sockets Bidirecional

## Exercício 1

a. Criar um projeto denominado SimpleClientServer na IDE de sua preferência e digitar as classes fornecidas `SimpleServerTest.java` e `SimpleClientTest.java`;

b. Executar, somente, a classe `SimpleClientTest.java`, verificar e registrar o que ocorre. Explique, em detalhes, o que ocorre;

c. Executar a classe `SimpleServerTest.java`, verificar e registrar o que ocorre. Explique, em detalhes, o que ocorre;

d. Sem encerrar a execução da classe `SimpleServerTest.java`, executar, a seguir, a classe `SimpleClientTest.java`, digitando algumas mensagens na interface com o usuário, uma após a outra e, por fim, optar por sair. Verificar e registrar o que ocorre. Explique, em detalhes, o que ocorre;

e. Sem encerrar a execução da classe `SimpleServerTest.java`, executar, a seguir, a classe `SimpleClientTest.java`, digitando uma mensagem, apenas, na interface com o usuário e não optar por sair. Sem encerrar a execução da classe `SimpleServerTest.java`, nem da primeira instância de execução da classe `SimpleClientTest.java`, executar outra instância da classe `SimpleClientTest.java`. Digitar algumas mensagens na segunda instância da classe `SimpleClientTest.java`, sem sair dela, verificar e registrar o que ocorre. Repetir a digitação para a primeira instância da classe `SimpleClientTest.java`, sem sair dela também. Explique, em detalhes, o que ocorre;

f. Encerrar a execução da segunda instância da classe `SimpleClientTest.java`, digitando algumas mensagens na primeira instância da classe `SimpleClientTest.java`. Verificar e registrar o que ocorre. Explique, em detalhes, o que ocorre;

g. Executar uma segunda instância da classe `SimpleServerTest.java`, ainda com a primeira instância dessa classe em execução. Verificar e registrar o que ocorre. Explique, em detalhes, o que ocorre.

## Exercício 2

Criar uma interface gráfica para a classe `SimpleClientTest.java`, através dos modelos de layout estudados anteriormente nesta disciplina (FlowLayout, BorderLayout, GridLayout ou os automaticamente gerados pela IDE NetBeans), eliminando toda e qualquer forma de comunicação com o usuário através do console e/ou por interface Swing como foi fornecida, criando nessa nova interface gráfica os campos específicos para as mensagens a serem enviadas ao servidor, respondidas ao cliente e para as mensagens de status da comunicação, além da inclusão dos botões e suas funcionalidades para Enviar, Limpar e Sair, na própria interface.

## Exercício 3

Baseada na solução do Exercício 2 deste material, criar uma interface para o servidor, via console, onde será exibida a mensagem que deveria ser retransmitida automaticamente para seu cliente (vide o servidor do Exercício 2), podendo, então, o usuário editá-la, ou confirmá-la, pelo próprio console do servidor, antes de ser reenviada para o cliente, criando para isso a classe `SimpleServer2Test.java`.

## Exercício 4

- Pesquisar, entender, estudar, resumir, exemplificar e dar aplicações, em PDF, o conceito de Função Lambda, também conhecida por Expressão Lambda, além de suas respectivas traduções literais para o idioma Inglês;
- Pesquisar, entender, estudar, resumir, exemplificar e dar aplicações, em PDF, o conceito de Threads em Java, também conhecida por Java Threads.

## Exercício Desafio

Estudar, pesquisar, desenvolver e testar novas classes `ServerTest.java`, `ClientTest.java`, e as demais que se fizerem necessárias, baseadas nas respectivas classes fornecidas, fazendo com que a nova classe servidor permita a conexão de mais de um cliente ao mesmo tempo, gerenciando corretamente suas respectivas comunicações.

## Exercícios (L1/2, L2/2 e L3/2)

Terminar, entregar e apresentar ao professor para avaliação, os exercícios propostos na aula de teoria, deste material.
