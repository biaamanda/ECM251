// Classe "TesteCrypto.java" -- Aula 17, Exercicios 1 e 2
//
// Exercicio 1: em vez de receber o texto claro "em hardcode", o programa
//              le o texto claro diretamente de um arquivo .txt gerado pelo
//              Bloco de Notas (ou equivalente): "texto_claro.txt".
//
// Exercicio 2: em vez de apresentar os resultados em tela, o programa grava
//              os resultados nos arquivos de texto "texto_cifrada.txt" e
//              "texto_decifrado.txt", para leitura no Bloco de Notas.
//
// As classes CryptoDummy, CryptoAES, CryptoRSA e Impressora nao foram
// alteradas -- apenas a origem da entrada e o destino da saida mudaram.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class TesteCrypto
{  // Arquivo de entrada com o texto claro (Exercicio 1)
   private static final String ARQ_ENTRADA   = "texto_claro.txt";
   // Arquivos de saida com os resultados (Exercicio 2)
   private static final String ARQ_CIFRADA   = "texto_cifrada.txt";
   private static final String ARQ_DECIFRADO = "texto_decifrado.txt";
   // Codificacao usada para converter String <-> byte[]
   private static final String CHARSET       = "ISO-8859-1";

   public static void main(String[] args) throws Exception
   {  // Instancia objeto da classe Impressora
      Impressora prn = new Impressora();

      // ----- Exercicio 1: le o texto claro do arquivo .txt -----
      byte[] bMsgClara = Files.readAllBytes(new File(ARQ_ENTRADA).toPath());
      String sMsgClara = new String(bMsgClara, CHARSET);

      // Buffers que acumulam tudo o que antes era impresso em tela
      StringBuilder sbCifrada   = new StringBuilder();
      StringBuilder sbDecifrada = new StringBuilder();

      // Cabecalho comum aos dois relatorios: a mensagem original
      String cabecalho =
         "---------------------------------------------------------------\n" +
         "Mensagem Clara (Hexadecimal):\n" +
         prn.hexBytesToString(bMsgClara) + "\n" +
         "Mensagem Clara (String):\n" + sMsgClara + "\n\n";
      sbCifrada.append(cabecalho);
      sbDecifrada.append(cabecalho);

      byte[] bMsgCifrada;
      byte[] bMsgDecifrada;

      // ===== Criptografia Dummy =====
      CryptoDummy cdummy = new CryptoDummy();
      cdummy.geraChave(new File("chave.dummy"));
      // clone() protege o array original (a classe Dummy cifra "in place")
      cdummy.geraCifra(bMsgClara.clone(), new File("chave.dummy"));
      bMsgCifrada = cdummy.getTextoCifrado();
      cdummy.geraDecifra(bMsgCifrada.clone(), new File("chave.dummy"));
      bMsgDecifrada = cdummy.getTextoDecifrado();
      sbCifrada.append(bloco("Dummy", prn, bMsgCifrada));
      sbDecifrada.append(bloco("Dummy", prn, bMsgDecifrada));

      // ===== Criptografia AES =====
      CryptoAES caes = new CryptoAES();
      caes.geraChave(new File("chave.simetrica"));
      caes.geraCifra(bMsgClara, new File("chave.simetrica"));
      bMsgCifrada = caes.getTextoCifrado();
      caes.geraDecifra(bMsgCifrada, new File("chave.simetrica"));
      bMsgDecifrada = caes.getTextoDecifrado();
      sbCifrada.append(bloco("AES", prn, bMsgCifrada));
      sbDecifrada.append(bloco("AES", prn, bMsgDecifrada));

      // ===== Criptografia RSA =====
      CryptoRSA crsa = new CryptoRSA();
      crsa.geraParDeChaves(new File("chave.publica"), new File("chave.privada"));
      crsa.geraCifra(bMsgClara, new File("chave.publica"));
      bMsgCifrada = crsa.getTextoCifrado();
      crsa.geraDecifra(bMsgCifrada, new File("chave.privada"));
      bMsgDecifrada = crsa.getTextoDecifrado();
      sbCifrada.append(bloco("RSA", prn, bMsgCifrada));
      sbDecifrada.append(bloco("RSA", prn, bMsgDecifrada));

      // ----- Exercicio 2: grava os resultados nos arquivos de texto -----
      gravaArquivo(ARQ_CIFRADA,   sbCifrada.toString());
      gravaArquivo(ARQ_DECIFRADO, sbDecifrada.toString());

      // Unica saida em tela: confirmacao dos arquivos gerados
      System.out.println("Arquivos gerados nesta pasta:");
      System.out.println("  " + ARQ_CIFRADA);
      System.out.println("  " + ARQ_DECIFRADO);
   }

   // Monta o trecho de relatorio de um algoritmo (hexadecimal + String)
   private static String bloco(String algoritmo, Impressora prn, byte[] b)
   {  return ">>> Algoritmo " + algoritmo + "\n\n" +
             "Mensagem (Hexadecimal):\n" + prn.hexBytesToString(b) + "\n" +
             "Mensagem (String, " + CHARSET + "):\n" +
             new String(b, java.nio.charset.Charset.forName(CHARSET)) + "\n\n";
   }

   // Grava (sobrescrevendo) um arquivo de texto com o conteudo dado
   private static void gravaArquivo(String nome, String conteudo) throws IOException
   {  try (PrintWriter saida = new PrintWriter(new FileWriter(nome)))
      {  saida.print(conteudo);
      }
   }
}
