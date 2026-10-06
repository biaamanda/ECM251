package ex01;

// Transcrição dos slides 20, 21 e 22 (passos 2, 3 e 4): classe de testes

public class OrdenaTest
{   public OrdenaTest()
    {   int proposto[]   = new int[] {10, 9};
        int esperado[]   = new int[] {9, 10};
        int inesperado[] = new int[] {9};

        Ordena teste = new Ordena();
        teste.ordenaNumerosCrescentes(proposto);

        System.out.println("Teste de Ordenação\n==================");
        System.out.println("Ficou com o mesmo tamanho: " + caso1Test(proposto.length, inesperado.length));
        System.out.println("Ordenou com sucesso......: " + caso2Test(proposto, esperado));
    }

    // Slide 21 (passo 3): métodos que executam os casos de teste
    public boolean caso1Test(int tamprop, int tamesp)
    {   boolean resp = true;
        if(tamprop != tamesp)   resp = false;
        return   resp;
    }

    public boolean caso2Test(int prop[], int esp[])
    {   return   numerosIguais(prop, esp);
    }

    // Slide 22 (passo 4): método auxiliar dos casos de teste
    public boolean numerosIguais(int nums1[], int nums2[])
    {   boolean  resultado = true;
        for(int i = 0 /*, j = 0*/; i < nums1.length; i++/*,j++*/)
        {  if(nums1[i] != nums2[i])
           {  resultado = false;
              i = nums1.length;
           }
        }
        return resultado;
    }
}
