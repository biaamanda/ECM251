//Imprimir na tela o produto de todos os números de 120 a 300;

import java.math.BigInteger;

public class Ex07produto {
    public static void main(String[] args) {
        BigInteger multiplication = BigInteger.ONE;

        for (int i = 120; i <= 300; i++){
            multiplication = multiplication.multiply(BigInteger.valueOf(i));
        }

        System.out.println("Produto dos numeros: " + multiplication);
    }
}