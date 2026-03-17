//Faça um algoritmo para imprimir na tela uma tabela de conversão de polegadas para centímetros. Deseja-se que a tabela conste de valores desde 1 até 20 polegadas. Lembre-se que 1 polegada equivale a 2,54 cm

public class Ex16polegadaCm {
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++){
            double pol = i*2.54;
            System.out.println(i + " cm -> " + pol + " polegadas");
        }
    }
}
