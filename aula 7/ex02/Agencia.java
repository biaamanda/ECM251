package ex02;
/*d) A classe Agencia tem os atributos nome do tipo String, numero e digito do tipo int. 
Crie um construtor que receba os atributos como parâmetros e os métodos de acesso e os modificadores. O numero e o digito da Agencia devem seguir os mesmos padrões do número e do dígito da conta corrente; */

public class Agencia {
    private String name;
    private int number;
    private int digit;

    public Agencia(String name, int number) {
        if (number <= 0 || number > 9999) {
            throw new IllegalArgumentException("Numero invalido.");
        }
        this.name = name;
        this.number = number;
        this.digit = calculateDigit(number);
    }

    private int calculateDigit(int number) {
        int[] weights = {4, 6, 8, 2};
        int sum = 0;
        String numStr = String.format("%04d", number);

        for (int i = 0; i < 4; i++) {
            sum += Character.getNumericValue(numStr.charAt(i)) * weights[i];
        }
        int mod = sum % 11;
        return (mod == 10) ? 0 : mod;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public int getDigit() {
        return digit;
    }

}
