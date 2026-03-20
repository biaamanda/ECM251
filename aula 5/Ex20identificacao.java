//Entrar com o nome, idade e sexo de 20 pessoas. Imprimir o nome sempre que a pessoa for do sexo masculino e tiver mais de 21 anos;

import java.util.Scanner;

public class Ex20identificacao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 20; i++) {
            System.out.println("\nPessoa " + i);
        
            System.out.printf("Digite seu nome: ");
            String name = sc.nextLine();
            System.out.printf("Digite sua idade: ");
            int age = sc.nextInt();
            sc.nextLine(); // limpa o ENTER do nextInt()
            System.out.printf("Digite seu sexo(m/f): ");
            String gender = sc.nextLine();
            String lowercaseGender = gender.toLowerCase();

            if (lowercaseGender.equals("m") && age > 21){
                System.out.printf("Nome: " + name);
            }
        }
        sc.close();
    }
}
