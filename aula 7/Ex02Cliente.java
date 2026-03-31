/*Crie as classes Cliente, ContaCorrente e Agencia conforme abaixo:
a) A classe Cliente possui os atributos nome e cpf, ambos do tipo String, e um atributo conta do tipo ContaCorrente. Crie um construtor que receba os atributos como parâmetros e os métodos de acesso e os modificadores 
b) A classe ContaCorrente tem os atributos numero e digito, ambos int, o atributo agencia do tipo Agencia e o atributo saldo do tipo double. Crie um construtor que receba os atributos como parâmetros e os métodos de acesso e os modificadores. Crie também um método depositar() que receba um parâmetro double com o valor do depósito e aumente o saldo da conta. Crie também um
método sacar() que receba um parâmetro double com o valor do saque e diminua o saldo da conta;
A conta não pode ficar negativa. Neste caso, deve ser dada uma mensagem que o saque não foi efetuado e o retorno deve ser zero. Caso contrário o retorno deve ser o valor sacado. Crie também um método consultarSaldo() que não receba parâmetros e retorne o saldo. Crie, finalmente,
um método imprimirSaldo() que imprima o numero da conta corrente com dígito, o número da agência com
dígito e o saldo da conta corrente;
Ainda na classe ContaCorrente, o número da conta deve ter no máximo 4 dígitos e ser positivo. O digito da conta deve ser validado a partir do seguinte algoritmo de módulo 11: multiplique o primeiro dígito da conta por 4, o segundo por 6, o terceiro por 8 e o quarto por 2; some
tudo e calcule o resto da divisão (módulo) da soma por 11. Este é o valor do dígito.
Obs.: se o resultado for 10 o dígito é 0;
d) A classe Agencia tem os atributos nome do tipo String, numero e digito do tipo int. Crie um construtor que receba os atributos como parâmetros e os métodos de acesso e os modificadores. O numero e o digito da Agencia devem seguir os mesmos padrões do número e do dígito da conta corrente;
e) Para testar, faça uma classe CaixaEletronico, que irá conter o método main(). Nele, instancie um cliente com os seguintes dados:
Nome: Ademar Apior
CPF: 123231518-12
Conta Corrente: 1234 Dígito: 4
Agencia: 7890 Dígito: 5
Saldo Inicial: 150.00

Operações:
- sacar 140.0 (sucesso);
- consultar saldo (resultado é 10.0);
- sacar 200.0 (falha);
- consultar saldo (resultado é 10.0);
- depositar 25.45 (sucesso);
- imprimir saldo (além dos dados de cliente, conta
e agencia, o saldo deve ser 35.45)*/

public class Ex02Cliente {
    
}
