/* 3. No tópico Criando Threads em Java, executar e registrar o
funcionamento dos códigos apresentados nos Exemplos 6, 7, 8,
9, 10 e 11, das páginas 23 até 30, respectivamente.
• Exemplo 6: SimpleThreadExample.java (1 Thread);
• Exemplo 7: MultipleThreadExample.java (várias Threads);
• Exemplos 8 a 11, via console: java MultipleThreadExample.java [N]
  (sem argumento, 1, 2 e 3). */

import java.util.Random;
public class SimpleThreadExample {  // Geracao de um bilhao de numeros aleatorios...
   private static final long TOTAL_NUMEROS = 1_000_000_000L;

   public static void main(String args[]) {  
      final int threads = 1;  // criar apenas 1 outra Thread
      System.out.println("Missao: Gerar um bilhao de numeros aleatorios!");
      System.out.printf("- Criando %d Thread(s) para isso!\n", threads);
      new SimpleThreadExample().run(); // Uma Thread por vez (linear)
   }

   public void run() {  
      System.out.println("- Iniciando Thread Principal...");
      // Le o tempo do sistema no inicio do processamento
      final double startTime = System.currentTimeMillis();
      // Sorteia um numero (randomico)
      Random rand = new Random();
      // Eleva a 10a potencia cada numero para o processamento mais complexo
      for(int i = 0; i < TOTAL_NUMEROS; i++) {  
         Math.pow(rand.nextDouble(), 10);
      }
      // Tempo de processamento em segundos
      final double totalSecs = (System.currentTimeMillis()-startTime)/1000.0;
      System.out.println("- Encerrando o processamento...");
      System.out.printf("Missao cumprida em %.2f segundos!\n", totalSecs);
      /*Missao: Gerar um bilhao de numeros aleatorios!
      - Criando 1 Thread(s) para isso!
      - Iniciando Thread Principal...
      - Encerrando o processamento...
      Missao cumprida em 47.42 segundos! */
   }
}
