//Uma empresa de fornecimento de energia elétrica faz a leitura mensal dos medidores de consumo. Para cada consumidor são digitados os seguintes dados: 
// código do consumidor, quantidade de kWh consumidos durante o mês, tipo do consumidor.
// Os tipos podem ser 
// 1 - residencial, preço por kWh = R$ 0,3; 
// 2 - comercial, preço por kWh = 0,5; 
// 3 - industrial, preço por kWh = 0,7. 
// Os dados devem ser lidos até que seja encontrado um consumidor com código 0 (zero). Calcular e imprimir o custo total para cada consumidor, o total de consumo para os três tipos de consumidor, a média de consumo dos tipos 1 e 2

import java.util.Scanner;

public class Ex10consumoEnergia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double totalResidential = 0;
        double totalCommercial = 0;
        double totalIndustrial = 0;

        double sumConsumption1e2 = 0;
        int count1e2 = 0;

        while (true) {
            System.out.print("\nDigite o código do consumidor (0 para sair): ");
            int code = sc.nextInt();

            if (code == 0) {
                break;
            }

            System.out.print("Digite o consumo (kWh): ");
            double consumption = sc.nextDouble();

            System.out.print("Digite o tipo (1-Residencial, 2-Comercial, 3-Industrial): ");
            int type = sc.nextInt();

            double price = 0;

            switch (type) {
                case 1:
                    price = 0.3;
                    totalResidential += consumption;
                    sumConsumption1e2 += consumption;
                    count1e2++;
                    break;
                case 2:
                    price = 0.5;
                    totalCommercial += consumption;
                    sumConsumption1e2 += consumption;
                    count1e2++;
                    break;
                case 3:
                    price = 0.7;
                    totalIndustrial += consumption;
                    break;
                default:
                    System.out.println("Tipo inválido!");
                    continue;
            }

            double cost = consumption * price;
            System.out.println("Custo total: R$ " + cost);
        }

        System.out.println("\n--- RESULTADOS FINAIS ---");
        System.out.println("Total consumo Residencial: " + totalResidential + " kWh");
        System.out.println("Total consumo Comercial: " + totalCommercial + " kWh");
        System.out.println("Total consumo Industrial: " + totalIndustrial + " kWh");

        if (count1e2 > 0) {
            double avg = sumConsumption1e2 / count1e2;
            System.out.println("Média consumo (tipos 1 e 2): " + avg + " kWh");
        } else {
            System.out.println("Não há dados para média dos tipos 1 e 2.");
        }
        sc.close();
    }
}
