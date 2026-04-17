import java.util.Scanner;

public class TesteAnimal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Animal[] animais = new Animal[2];
        for (int i = 0; i < 2; i++) {
            System.out.println("--- Animal " + (i + 1) + " ---");
            System.out.print("Espécie: ");
            String especie = scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Idade (anos): ");
            int idade = scanner.nextInt();
            scanner.nextLine();
            animais[i] = new Animal(especie, nome, idade);
        }

        Animal a1 = animais[0];
        Animal a2 = animais[1];

        System.out.println();
        System.out.println("Comparaçao de idade:");
        System.out.println(a1.getName() + " (" + a1.getSpecies() + "): " + a1.getAge() + " ano(s)");
        System.out.println(a2.getName() + " (" + a2.getSpecies() + "): " + a2.getAge() + " ano(s)");

        if (a1.getAge() > a2.getAge()) {
            System.out.println(a1.getName() + " é mais velho que " + a2.getName() + ".");
        } else if (a2.getAge() > a1.getAge()) {
            System.out.println(a2.getName() + " é mais velho que " + a1.getName() + ".");
        } else {
            System.out.println("Ambos têm a mesma idade.");
        }

        scanner.close();
    }
}
