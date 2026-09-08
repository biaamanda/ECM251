// Classe "TesteCrypto.java" -- Aula 17, Exercicio 3
//
// Exercita o novo algoritmo de CryptoDummy (autoria propria):
//   - le o texto claro de "texto_claro.txt"  (reaproveita o Exercicio 1);
//   - cifra e decifra com a chave gerada em "chave.dummy";
//   - grava "texto_cifrada.txt" e "texto_decifrado.txt" (reaproveita o Exercicio 2);
//   - verifica o "round-trip": o texto decifrado deve ser IGUAL ao original;
//   - repete a decifra com uma chave ANTIGA/errada ("chave1.dummy") para
//     mostrar que, sem a chave correta, a mensagem nao e recuperada.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Arrays;

public class TesteCrypto
{  private static final String ARQ_ENTRADA   = "texto_claro.txt";
   private static final String ARQ_CIFRADA   = "texto_cifrada.txt";
   private static final String ARQ_DECIFRADO = "texto_decifrado.txt";
   private static final String CHARSET       = "ISO-8859-1";

   public static void main(String[] args) throws Exception
   {  Impressora prn = new Impressora();

      byte[] bMsgClara = Files.readAllBytes(new File(ARQ_ENTRADA).toPath());
      String sMsgClara = new String(bMsgClara, CHARSET);

      System.out.println("---------------------------------------------------------------");
      System.out.println("Mensagem Clara (String):");
      System.out.println(sMsgClara);
      System.out.println();

      // ----- Cifra e decifra com a chave correta -----
      CryptoDummy cd = new CryptoDummy();
      cd.geraChave(new File("chave.dummy"));

      cd.geraCifra(bMsgClara, new File("chave.dummy"));
      byte[] bMsgCifrada = cd.getTextoCifrado();

      cd.geraDecifra(bMsgCifrada, new File("chave.dummy"));
      byte[] bMsgDecifrada = cd.getTextoDecifrado();

      System.out.println("Mensagem Cifrada (Hexadecimal):");
      System.out.print(prn.hexBytesToString(bMsgCifrada));
      System.out.println();
      System.out.println("Mensagem Decifrada (String):");
      System.out.println(new String(bMsgDecifrada, CHARSET));
      System.out.println();

      // ----- Exercicio 2: grava os resultados em arquivos de texto -----
      grava(ARQ_CIFRADA,
            "Algoritmo: CryptoDummy (autoria propria)\n\n" +
            "Mensagem Cifrada (Hexadecimal):\n" +
            prn.hexBytesToString(bMsgCifrada) + "\n" +
            "Mensagem Cifrada (String, " + CHARSET + "):\n" +
            new String(bMsgCifrada, CHARSET) + "\n");
      grava(ARQ_DECIFRADO,
            "Algoritmo: CryptoDummy (autoria propria)\n\n" +
            "Mensagem Decifrada (String, " + CHARSET + "):\n" +
            new String(bMsgDecifrada, CHARSET) + "\n");

      // ----- Verificacao do round-trip -----
      boolean ok = Arrays.equals(bMsgClara, bMsgDecifrada);
      System.out.println("Round-trip (decifra == original)? " + (ok ? "OK" : "FALHOU"));

      // ----- Decifra com chave antiga/errada: nao deve recuperar o texto -----
      CryptoDummy cErrada = new CryptoDummy();
      cErrada.geraChave(new File("chave1.dummy"));           // outra chave qualquer
      cErrada.geraDecifra(bMsgCifrada, new File("chave1.dummy"));
      String lixo = new String(cErrada.getTextoDecifrado(), CHARSET);
      System.out.println("Decifra com chave errada: \"" + lixo + "\"");
      System.out.println("Recuperou o texto com a chave errada? " +
            (lixo.equals(sMsgClara) ? "SIM (falha de seguranca)" : "NAO (esperado)"));

      System.out.println();
      System.out.println("Arquivos gerados: " + ARQ_CIFRADA + " e " + ARQ_DECIFRADO);
   }

   private static void grava(String nome, String conteudo) throws IOException
   {  try (PrintWriter saida = new PrintWriter(new FileWriter(nome)))
      {  saida.print(conteudo);
      }
   }
}
