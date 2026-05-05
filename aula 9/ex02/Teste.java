package ex02;

import java.util.ArrayList;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Shape> formas = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n===== FORMAS GEOMÉTRICAS =====");
            System.out.println("1. Quadrado");
            System.out.println("2. Círculo");
            System.out.println("3. Triângulo");
            System.out.println("4. Cubo");
            System.out.println("5. Esfera");
            System.out.println("6. Tetraedro");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Digite o lado do quadrado: ");
                    int ladoQuadradoInt = scanner.nextInt();

                    System.out.print("Digite o lado do quadrado: ");
                    double ladoQuadradoDouble = scanner.nextDouble();

                    Square quadrado = new Square(ladoQuadradoDouble);
                    formas.add(quadrado);

                    System.out.println("Area: " + quadrado.calcularArea(ladoQuadradoInt));
                    System.out.println("Perímetro: " + quadrado.calcularPerimetro(ladoQuadradoInt));
                    System.out.println("Area: " + quadrado.calcularArea());
                    System.out.println("Perímetro: " + quadrado.calcularPerimetro());
                    break;

                case 2:
                    System.out.print("Digite o raio do círculo: ");
                    int raioCirculoInt = scanner.nextInt();

                    System.out.print("Digite o raio do círculo: ");
                    double raioCirculoDouble = scanner.nextDouble();

                    Circle circulo = new Circle(raioCirculoDouble);
                    formas.add(circulo);

                    System.out.println("Area: " + circulo.calcularArea(raioCirculoInt));
                    System.out.println("Perímetro: " + circulo.calcularPerimetro(raioCirculoInt));
                    System.out.println("Area: " + circulo.calcularArea());
                    System.out.println("Perímetro: " + circulo.calcularPerimetro());
                    break;

                case 3:
                    System.out.print("Digite o lado do triângulo: ");
                    int ladoTrianguloInt = scanner.nextInt();

                    System.out.print("Digite o lado do triângulo: ");
                    double ladoTrianguloDouble = scanner.nextDouble();

                    Triangle triangulo = new Triangle(ladoTrianguloDouble);
                    formas.add(triangulo);

                    System.out.println("Area: " + triangulo.calcularArea(ladoTrianguloInt));
                    System.out.println("Perímetro: " + triangulo.calcularPerimetro(ladoTrianguloInt));
                    System.out.println("Area: " + triangulo.calcularArea());
                    System.out.println("Perímetro: " + triangulo.calcularPerimetro());
                    break;

                case 4:
                    System.out.print("Digite o lado do cubo: ");
                    int ladoCuboInt = scanner.nextInt();

                    System.out.print("Digite o lado do cubo: ");
                    double ladoCuboDouble = scanner.nextDouble();

                    Cube cubo = new Cube(ladoCuboDouble);
                    formas.add(cubo);

                    System.out.println("Volume: " + cubo.calcularVolume(ladoCuboInt));
                    System.out.println("Area total: " + cubo.calcularAreaTotal(ladoCuboInt));
                    System.out.println("Volume: " + cubo.calcularVolume());
                    System.out.println("Area total: " + cubo.calcularAreaTotal());
                    break;

                case 5:
                    System.out.print("Digite o raio da esfera: ");
                    int raioEsferaInt = scanner.nextInt();

                    System.out.print("Digite o raio da esfera: ");
                    double raioEsferaDouble = scanner.nextDouble();

                    Sphere esfera = new Sphere(raioEsferaDouble);
                    formas.add(esfera);

                    System.out.println("Volume: " + esfera.calcularVolume(raioEsferaInt));
                    System.out.println("Area total: " + esfera.calcularAreaTotal(raioEsferaInt));
                    System.out.println("Volume: " + esfera.calcularVolume());
                    System.out.println("Area total: " + esfera.calcularAreaTotal());
                    break;

                case 6:
                    System.out.print("Digite o lado do tetraedro: ");
                    int ladoTetraInt = scanner.nextInt();

                    System.out.print("Digite o lado do tetraedro: ");
                    double ladoTetraDouble = scanner.nextDouble();

                    Tetrahedron tetraedro = new Tetrahedron(ladoTetraDouble);
                    formas.add(tetraedro);

                    System.out.println("Volume: " + tetraedro.calcularVolume(ladoTetraInt));
                    System.out.println("Area total: " + tetraedro.calcularAreaTotal(ladoTetraInt));
                    System.out.println("Volume: " + tetraedro.calcularVolume());
                    System.out.println("Area total: " + tetraedro.calcularAreaTotal());
                    break;

                case 0:
                    System.out.println("Encerrando programa...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}