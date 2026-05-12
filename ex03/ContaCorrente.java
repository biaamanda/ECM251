package ex03;
/*b) A classe ContaCorrente tem os atributos numero e digito,
ambos int, o atributo agencia do tipo Agencia e o atributo saldo
do tipo double. Crie um construtor que recebe os atributos como
parâmetros e os métodos de acesso e os modificadores. Crie
também um método depositar() que receba um parâmetro
double com o valor do depósito e aumente o saldo da conta. Crie
também um método sacar() que receba um parâmetro double
com o valor do saque e diminua o saldo da conta
A conta não pode ficar negativa. Neste caso, deve ser dada
uma mensagem que o saque não foi efetuado e o retorno deve
ser zero. Caso contrário o retorno deve ser o valor sacado. Crie
também um método consultarSaldo() que não recebe
parâmetros e retorne o saldo. Crie, finalmente, um método
imprimirSaldo() que imprima o número da conta corrente com
dígito, o número da agência com dígito e o saldo da conta
corrente
Ainda na classe ContaCorrente, o número do conta deve ter no
máximo 4 dígitos e ser positivo. O digito da conta deve ser
validado a partir do seguinte algoritmo de módulo 11:
“Multiplique o primeiro dígito da conta por 4, o segundo por 6, o
terceiro por 8 e o quarto por 2; some tudo e calcule o resto da
divisão (módulo) da soma por 11. Este é o valor do dígito”;
- Obs: se o resultado for 10 o dígito é 0. */

public class ContaCorrente {
    
}
