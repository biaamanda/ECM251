// Classe "CryptoDummy.java" -- Aula 17, Exercicio 3
//
// Algoritmo de criptografia SIMETRICA de autoria propria, em substituicao
// ao "Dummy" original (que apenas somava um numero randomico).
//
// Para cada byte da mensagem, na posicao i, aplica-se, em sequencia:
//   1) soma modular dependente da chave e da posicao:  b = b + k*(i+1)  (mod 256)
//   2) XOR com um "keystream" derivado da chave e da posicao: b = b ^ ((k+i) & 0xFF)
//   3) rotacao circular de ROT bits a esquerda dentro do byte
//
// Todas as tres operacoes sao reversiveis; a decifra aplica as inversas na
// ordem contraria (rotacao a direita, XOR, subtracao modular).
//
// Melhorias de seguranca em relacao ao Dummy original:
//   - a transformacao varia a cada posicao (nao e uma simples soma constante);
//   - combina difusao (rotacao) com confusao (XOR + soma), dificultando a
//     analise por frequencia;
//   - a chave nunca e 0 (chave 0 tornaria as etapas 1 e 2 inofensivas).
// Ainda assim, e um algoritmo didatico: NAO deve ser usado em producao.

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CryptoDummy
{  private static final int ROT = 3;          // bits de rotacao circular por byte

   private byte[] textoCifrado;
   private byte[] textoDecifrado;

   public CryptoDummy()
   {  textoCifrado = null;
      textoDecifrado = null;
   }

   // Gera a chave simetrica (inteiro de 1 a 255) e grava serializada
   public void geraChave(File fDummy) throws IOException
   {  int dk = 1 + (int) (Math.random() * 255);   // 1..255 (evita a chave 0)
      try (ObjectOutputStream oos =
               new ObjectOutputStream(new FileOutputStream(fDummy)))
      {  oos.writeObject(dk);
      }
   }

   // Le a chave simetrica do arquivo serializado (0..255)
   private static int leChave(File fDummy) throws IOException, ClassNotFoundException
   {  try (ObjectInputStream ois =
               new ObjectInputStream(new FileInputStream(fDummy)))
      {  return ((Integer) ois.readObject()) & 0xFF;
      }
   }

   // Rotacao circular a esquerda de n bits dentro de um byte (8 bits)
   private static int rotl(int v, int n)
   {  v &= 0xFF;
      return ((v << n) | (v >>> (8 - n))) & 0xFF;
   }

   // Rotacao circular a direita de n bits dentro de um byte (8 bits)
   private static int rotr(int v, int n)
   {  v &= 0xFF;
      return ((v >>> n) | (v << (8 - n))) & 0xFF;
   }

   public void geraCifra(byte[] texto, File fDummy)
         throws IOException, ClassNotFoundException
   {  int k = leChave(fDummy);
      byte[] saida = new byte[texto.length];      // nao altera o array de entrada
      for (int i = 0; i < texto.length; i++)
      {  int b = texto[i] & 0xFF;
         b = (b + k * (i + 1)) & 0xFF;             // 1) soma modular por posicao
         b = b ^ ((k + i) & 0xFF);                 // 2) XOR com keystream
         b = rotl(b, ROT);                         // 3) rotacao circular a esquerda
         saida[i] = (byte) b;
      }
      textoCifrado = saida;
   }

   public byte[] getTextoCifrado() throws Exception
   {  return textoCifrado;
   }

   public void geraDecifra(byte[] texto, File fDummy)
         throws IOException, ClassNotFoundException
   {  int k = leChave(fDummy);
      byte[] saida = new byte[texto.length];
      for (int i = 0; i < texto.length; i++)
      {  int b = texto[i] & 0xFF;
         b = rotr(b, ROT);                         // inverso de 3)
         b = b ^ ((k + i) & 0xFF);                 // inverso de 2)
         b = (b - k * (i + 1)) & 0xFF;             // inverso de 1)
         saida[i] = (byte) b;
      }
      textoDecifrado = saida;
   }

   public byte[] getTextoDecifrado() throws Exception
   {  return textoDecifrado;
   }
}
