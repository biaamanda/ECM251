/* 1. No tópico Expressão Lambda em Java, completar, viabilizar,
executar e registrar o funcionamento dos códigos parciais
apresentados nos Exemplos 1, 2 e 3 da página 5.

Os trechos abaixo foram transcritos do material e estão INCOMPLETOS
(faltam imports e as interfaces funcionais Operacao e Comparador). */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface Operacao {
    int executar(int a, int b);
}

interface Comparador<T> {
    int compare(T o1, T o2);
}

public class Exemplos { 
   public static void main(String[] args) { 

      // ---------- Exemplo 1: Lambda simples ----------
      /* Usando uma expressão lambda para criar uma função
         que adiciona dois números inteiros */
      Operacao soma = (a, b) -> a + b;
      int resultado1 = soma.executar(5, 3); 
      System.out.println("Resultado soma: " + resultado1);
      // Resultado soma: 8

      // ---------- Exemplo 2: Lambda com tipos explícitos ----------
      // Você também pode especificar os tipos de parâmetros se desejar
      Comparador<String> comparador = (String s1, String s2) -> s1.compareTo(s2);
      // resultado é um valor que indica a ordem lexicográfica
      int resultado2 = comparador.compare("maca", "banana");
      System.out.println("Resultado lexicográfico: " + resultado2);
      //Resultado lexicográfico: 11

      // ---------- Exemplo 3: Filtragem com Lambda ----------
      List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
      List<Integer> pares = numeros.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
      System.out.println("Números pares filtrados: " + pares);
      // Números pares filtrados: [2, 4, 6, 8, 10]
   }
}
