// Teste de INTEGRACAO (sem GUI) das classes da Questao 4, no mesmo estilo do
// ChecksumTest da aula 18 (sem JUnit; cada caso imprime OK/FALHOU e ha um resumo).
//
// Teste unitario   = testa UMA classe isolada (ex.: Checksum sozinho);
// Teste integracao = testa classes trabalhando JUNTAS (ex.: Checksum + CryptoRSA
//                    + arquivos: ler -> CS -> salvar -> cifrar -> decifrar).
//
// Compilar e rodar (na pasta do projeto):
//     javac -encoding UTF-8 *.java
//     java TesteIntegracao

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import javax.crypto.BadPaddingException;

public class TesteIntegracao {
    private static int total = 0;
    private static int ok = 0;

    public static void main(String[] args) throws Exception {
        testarChecksum();
        testarRSAMensagemCurta();
        testarRSAMensagemLonga();
        testarParDeChavesSeparadas();
        testarChaveErrada();
        System.out.printf("%n%d de %d testes passaram.%n", ok, total);
        if (ok != total) System.exit(1);
    }

    private static void testarChecksum() {
        Checksum c = new Checksum();
        verificar("CS de \"\" = 0", 0, c.calcularChecksum("".toCharArray()));
        verificar("CS de \"A\" = 191", 191, c.calcularChecksum("A".toCharArray()));
        verificar("CS de \"AB\" = 125", 125, c.calcularChecksum("AB".toCharArray()));
        verificar("CRC-32 de \"123456789\" = CBF43926", 0xCBF43926L, c.calcularCRC("123456789".toCharArray()));
    }

    private static void testarRSAMensagemCurta() throws Exception {
        verificar("RSA ida e volta - mensagem curta (< 117 bytes)", true, idaEVolta("Ola, P3! Checksum: 42"));
    }

    private static void testarRSAMensagemLonga() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 40; i++) sb.append("linha ").append(i).append(" com acentuação: ção ãé\n");
        verificar("RSA ida e volta - mensagem longa (varios blocos)", true, idaEVolta(sb.toString()));
    }

    // Prova o motivo de a CryptoRSA guardar o par: chave publica e privada
    // gravadas em cliques diferentes tem que ser do MESMO par.
    private static void testarParDeChavesSeparadas() throws Exception {
        File dir = Files.createTempDirectory("p3rsa").toFile();
        CryptoRSA rsa = new CryptoRSA();
        File pub = new File(dir, "chave.publica");
        File pvk = new File(dir, "chave.privada");
        rsa.geraESalvaChavePublica(pub);
        verificar("apos so a publica, o par ainda nao esta completo", false, rsa.isParCompleto());
        rsa.geraESalvaChavePrivada(pvk);
        verificar("apos publica + privada, o par esta completo", true, rsa.isParCompleto());

        byte[] claro = "mensagem de teste".getBytes(StandardCharsets.UTF_8);
        rsa.geraCifra(claro, pub);
        byte[] cifrado = rsa.getTextoCifrado();
        rsa.geraDecifra(cifrado, pvk);
        verificar("chaves de cliques diferentes combinam", true, Arrays.equals(claro, rsa.getTextoDecifrado()));
    }

    // Chave privada de OUTRO par nao pode decifrar
    private static void testarChaveErrada() throws Exception {
        File dir = Files.createTempDirectory("p3rsa2").toFile();
        CryptoRSA a = new CryptoRSA();
        CryptoRSA b = new CryptoRSA();
        a.geraESalvaChavePublica(new File(dir, "a.publica"));
        b.geraESalvaChavePrivada(new File(dir, "b.privada"));
        a.geraCifra("segredo".getBytes(StandardCharsets.UTF_8), new File(dir, "a.publica"));
        boolean falhou = false;
        try {
            b.geraDecifra(a.getTextoCifrado(), new File(dir, "b.privada"));
        } catch (BadPaddingException ex) {
            falhou = true;
        }
        verificar("chave privada de outro par NAO decifra (BadPaddingException)", true, falhou);
    }

    // Pipeline completo: mensagem -> CS -> "arquivo com CS" -> cifra -> decifra
    private static boolean idaEVolta(String mensagem) throws Exception {
        File dir = Files.createTempDirectory("p3rsa3").toFile();
        Checksum c = new Checksum();
        CryptoRSA rsa = new CryptoRSA();

        int cs = c.calcularChecksum(mensagem.toCharArray());
        byte[] comCS = (mensagem + System.lineSeparator() + "Checksum: " + cs).getBytes(StandardCharsets.UTF_8);
        File arqCS = new File(dir, "msg_com_cs.txt");
        Files.write(arqCS.toPath(), comCS);

        File pub = new File(dir, "chave.publica");
        File pvk = new File(dir, "chave.privada");
        rsa.geraESalvaChavePublica(pub);
        rsa.geraESalvaChavePrivada(pvk);

        rsa.geraCifra(Files.readAllBytes(arqCS.toPath()), pub);
        File arqCif = new File(dir, "msg_cifrada.txt");
        Files.write(arqCif.toPath(), rsa.getTextoCifrado());

        rsa.geraDecifra(Files.readAllBytes(arqCif.toPath()), pvk);
        File arqDec = new File(dir, "msg_decifrada.txt");
        Files.write(arqDec.toPath(), rsa.getTextoDecifrado());

        return Arrays.equals(comCS, Files.readAllBytes(arqDec.toPath()));
    }

    private static void verificar(String nome, long esperado, long obtido) {
        total++;
        boolean passou = esperado == obtido;
        if (passou) ok++;
        System.out.printf("[%s] %s (esperado=%d, obtido=%d)%n", passou ? "OK" : "FALHOU", nome, esperado, obtido);
    }

    private static void verificar(String nome, boolean esperado, boolean obtido) {
        total++;
        boolean passou = esperado == obtido;
        if (passou) ok++;
        System.out.printf("[%s] %s%n", passou ? "OK" : "FALHOU", nome);
    }
}
