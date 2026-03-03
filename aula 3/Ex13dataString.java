//Entre com uma data em uma variável do tipo String no formato dd/mm/aa e imprimir dia, mês e ano separados.

import java.util.Scanner;

public class Ex13dataString {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite uma data no formato dd/mm/aa: ");
        String data = sc.nextLine();

        String[] partes = data.split("/");
        System.out.println("Dia: " + partes[0]);
        System.out.println("Mês: " + partes[1]);
        System.out.println("Ano: " + partes[2]);

        sc.close();
    }
}
