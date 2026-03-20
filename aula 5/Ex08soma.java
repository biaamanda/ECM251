//Imprimir na tela todos os números de 1 a 100 e a soma deles;

public class Ex08soma {
    public static void main(String[] args) {
        int sum = 0;

        for (int i = 1; i <= 100; i++){
            System.out.print(i + " ");
            sum += i; 
        }
        System.out.println();

        System.out.println("A soma dos numeros e: " + sum);
    }
}