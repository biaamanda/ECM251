/* 2. No tópico Funções Anônimas em Java, executar e registrar o
funcionamento dos códigos apresentados nos Exemplos 4 e 5
das páginas 9 e 10, respectivamente. */

// Definindo uma interface funcional com um único método abstrato
interface Operacao {  
   int executar(int a, int b);
}

public class ExemploFuncaoAnonima {  
   public static void main(String[] args) {  // Criando uma instância da interface funcional usando uma classe anônima
      Operacao adicao = new Operacao() {  
         @Override
         public int executar(int a, int b) {  
            return a + b;
         }
      };

      // Usando a função anônima
      int resultado = adicao.executar(5, 3);
      System.out.println("Resultado: " + resultado);
   }
}
