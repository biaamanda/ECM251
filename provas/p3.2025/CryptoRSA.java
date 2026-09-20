// Classe "CryptoRSA.java" -- baseada na CryptoRSA da aula 17, com DUAS mudancas
// necessarias para a P3 (onde a classe original nao funciona "como esta"):
//
//  1) CHAVES SEPARADAS. A prova pede itens de menu distintos para a chave
//     publica e para a chave privada. A classe original gera as duas juntas
//     (geraParDeChaves). Se voce chamar isso uma vez em cada item de menu,
//     cada chamada gera um par DIFERENTE e o arquivo de uma chave nao combina
//     com o da outra -> BadPaddingException ao decifrar.
//     Aqui, o par e gerado uma unica vez e guardado na memoria; cada item de
//     menu apenas grava a sua metade do MESMO par.
//
//  2) MENSAGENS MAIORES QUE 117 BYTES. RSA de 1024 bits (com o padding padrao
//     PKCS#1) so cifra 117 bytes por vez; com mais que isso, doFinal() lanca
//     IllegalBlockSizeException ("Data must not be longer than 117 bytes").
//     Aqui a mensagem e cifrada em blocos (117 bytes em claro -> 128 cifrados)
//     e decifrada de 128 em 128. Para mensagens curtas o resultado e igual
//     ao da classe original (um unico bloco de 128 bytes).

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.spec.RSAKeyGenParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class CryptoRSA
{  private static final int RSAKEYSIZE = 1024;
   private static final int PADDING    = 11;    // overhead do PKCS#1 v1.5

   private byte[]  textoCifrado;
   private byte[]  textoDecifrado;

   // par gerado na sessao e o controle de qual metade ja foi gravada
   private KeyPair par;
   private boolean pubSalva;
   private boolean privSalva;

   public CryptoRSA()
   {  textoCifrado = null;
      textoDecifrado = null;
   }

   // ---------------------------------------------------------------- chaves

   // Grava a chave PUBLICA. Se nao ha par na memoria, ou se a publica desse
   // par ja foi gravada, gera um par novo (senao o clique repetido nao
   // mudaria nada). Retorna true se um par novo foi gerado.
   public boolean geraESalvaChavePublica(File fPub)
      throws IOException, GeneralSecurityException
   {  boolean novo = (par == null || pubSalva);
      if(novo) geraNovoPar();
      grava(par.getPublic(), fPub);
      pubSalva = true;
      return novo;
   }

   // Grava a chave PRIVADA (mesma logica da publica)
   public boolean geraESalvaChavePrivada(File fPvk)
      throws IOException, GeneralSecurityException
   {  boolean novo = (par == null || privSalva);
      if(novo) geraNovoPar();
      grava(par.getPrivate(), fPvk);
      privSalva = true;
      return novo;
   }

   // true quando as DUAS chaves gravadas em disco pertencem ao mesmo par
   public boolean isParCompleto()
   {  return pubSalva && privSalva;
   }

   private void geraNovoPar() throws GeneralSecurityException
   {  KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
      kpg.initialize(new RSAKeyGenParameterSpec(RSAKEYSIZE, RSAKeyGenParameterSpec.F4));
      par = kpg.generateKeyPair();
      pubSalva = false;
      privSalva = false;
   }

   // Grava a chave em formato serializado (como na aula 17)
   private static void grava(Key chave, File f) throws IOException
   {  try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f)))
      {  oos.writeObject(chave);
      }
   }

   private static Object le(File f) throws IOException, ClassNotFoundException
   {  try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f)))
      {  return ois.readObject();
      }
   }

   // Tamanho do modulo RSA em bytes (128 para 1024 bits)
   private static int tamanhoBloco(Key k)
   {  return (((RSAKey) k).getModulus().bitLength() + 7) / 8;
   }

   // ----------------------------------------------------------------- cifra

   public void geraCifra(byte[] texto, File fPub)
      throws IOException, ClassNotFoundException, GeneralSecurityException
   {  PublicKey iPub = (PublicKey) le(fPub);
      Cipher rsacf = Cipher.getInstance("RSA");
      rsacf.init(Cipher.ENCRYPT_MODE, iPub);
      int maxClaro = tamanhoBloco(iPub) - PADDING;         // 117 bytes
      ByteArrayOutputStream saida = new ByteArrayOutputStream();
      for(int i = 0; i < texto.length; i += maxClaro)
      {  int n = Math.min(maxClaro, texto.length - i);
         saida.write(rsacf.doFinal(texto, i, n));           // 128 bytes
      }
      textoCifrado = saida.toByteArray();
   }

   public byte[] getTextoCifrado()
   {  return textoCifrado;
   }

   // --------------------------------------------------------------- decifra

   public void geraDecifra(byte[] texto, File fPrv)
      throws IOException, ClassNotFoundException, GeneralSecurityException
   {  PrivateKey iPrv = (PrivateKey) le(fPrv);
      Cipher rsacf = Cipher.getInstance("RSA");
      rsacf.init(Cipher.DECRYPT_MODE, iPrv);
      int bloco = tamanhoBloco(iPrv);                       // 128 bytes
      if(texto.length % bloco != 0)
         throw new IllegalBlockSizeException("Tamanho do texto cifrado (" + texto.length
            + " bytes) nao e multiplo de " + bloco + " -- arquivo cifrado corrompido/alterado");
      ByteArrayOutputStream saida = new ByteArrayOutputStream();
      for(int i = 0; i < texto.length; i += bloco)
      {  saida.write(rsacf.doFinal(texto, i, bloco));
      }
      textoDecifrado = saida.toByteArray();
   }

   public byte[] getTextoDecifrado()
   {  return textoDecifrado;
   }
}
