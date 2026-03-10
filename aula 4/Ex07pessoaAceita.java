//Entrar com o nome, sexo e idade de uma pessoa. Se a pessoa for do sexo feminino e tiver menos que 25 anos, imprimir nome e a mensagem: ACEITA. Caso contrário, imprimir nome e a mensagem: NÃO ACEITA. (Considerar f ou F);

import java.util.Scanner;

public class Ex07pessoaAceita {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome: ");
        String name = sc.nextLine();
        System.out.print("Digite o sexo (f/m): ");
        String gender = sc.nextLine();
        String lowercaseGender = gender.toLowerCase();
        System.out.print("Digite a idade: ");
        int age = sc.nextInt();

        if (lowercaseGender.equals("f") && age < 25){
            System.out.println(name + " ACEITA");
        } else {
            System.out.println(name + " NAO ACEITA");
        }
        sc.close();
    }
}
