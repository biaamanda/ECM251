//Escrever um algoritmo de urna eletrônica, para uma eleição com 4 candidatos. O usuário vota, digitando o número do candidato (1,2, 3 ou 4). O número de eleitores é desconhecido. Quando for digitado o valor -1, o algoritmo encerra a eleição, escrevendo o percentual de votos de cada candidato e o total de eleitores que participaram da eleição

import java.util.Scanner;

public class Ex24urna {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int votes1 = 0, votes2 = 0, votes3 = 0, votes4 = 0;
        int totalVoters = 0;

        System.out.println("Urna Eletrônica - Candidatos: 1, 2, 3, 4 (-1 para encerrar)");

        while (true) {
            System.out.print("Vote no candidato: ");
            int vote = sc.nextInt();

            if (vote == -1) {
                break;
            }

            switch (vote) {
                case 1:
                    votes1++;
                    totalVoters++;
                    break;
                case 2:
                    votes2++;
                    totalVoters++;
                    break;
                case 3:
                    votes3++;
                    totalVoters++;
                    break;
                case 4:
                    votes4++;
                    totalVoters++;
                    break;
                default:
                    System.out.println("Voto inválido! Digite 1, 2, 3 ou 4.");
                    break;
            }
        }

        System.out.println("\n--- Resultado da Eleiçao ---");
        System.out.println("Total de eleitores: " + totalVoters);

        if (totalVoters > 0) {
            System.out.printf("Candidato 1: %d votos (%.1f%%)%n", votes1, (votes1 * 100.0 / totalVoters));
            System.out.printf("Candidato 2: %d votos (%.1f%%)%n", votes2, (votes2 * 100.0 / totalVoters));
            System.out.printf("Candidato 3: %d votos (%.1f%%)%n", votes3, (votes3 * 100.0 / totalVoters));
            System.out.printf("Candidato 4: %d votos (%.1f%%)%n", votes4, (votes4 * 100.0 / totalVoters));
        } else {
            System.out.println("Nenhum voto foi registrado.");
        }
        sc.close();
    }
}
