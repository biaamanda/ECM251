// "Cola" de manipulacao de ARQUIVOS DE TEXTO (aula 16) -- as receitas que mais
// aparecem. Roda sozinho:  javac ArquivosCola.java   e   java ArquivosCola
//
// Regras de ouro:
//   - Ler/gravar arquivo LANCA IOException (checked): use try/catch ou "throws".
//   - try-with-resources (try (...) { }) fecha o arquivo sozinho -- sempre use.
//   - FileWriter(nome)        -> SOBRESCREVE o arquivo
//     FileWriter(nome, true)  -> ACRESCENTA no final (append)
//   - Caminho relativo = relativo a pasta de TRABALHO (onde o java foi executado),
//     nao a pasta do .java.
//   - Texto com acentos: informe o charset (UTF-8). Cifra/checksum: trabalhe com byte[].

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArquivosCola {

    // ---- 1) Gravar (sobrescrever) -------------------------------------------
    static void gravar(String arquivo, String conteudo) throws IOException {
        Files.write(Path.of(arquivo), conteudo.getBytes(StandardCharsets.UTF_8));
    }

    // ---- 2) Acrescentar uma linha "campo;campo;campo" (como aula 16 / Ex01) ---
    static void acrescentarRegistro(String arquivo, String nome, String senha) throws IOException {
        try (PrintWriter saida = new PrintWriter(new FileWriter(arquivo, true))) {
            saida.printf("%s;%s%n", nome, senha);
        }
    }

    // ---- 3) Ler o arquivo INTEIRO -------------------------------------------
    static String lerTudo(String arquivo) throws IOException {
        return new String(Files.readAllBytes(Path.of(arquivo)), StandardCharsets.UTF_8);
    }

    // ---- 4) Ler linha a linha e quebrar campos com split (aula 16 / Ex02) -----
    static boolean validarLogin(String arquivo, String nome, String senha) throws IOException {
        try (Scanner in = new Scanner(new File(arquivo))) {
            while (in.hasNextLine()) {
                String[] campos = in.nextLine().split(";");
                if (campos.length >= 2 && campos[0].equals(nome) && campos[1].equals(senha)) {
                    return true;
                }
            }
        }
        return false;
    }

    // ---- 5) Alternativa: BufferedReader ---------------------------------------
    static List<String> lerLinhas(String arquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        }
        return linhas;
    }

    // ---- 6) Bytes crus (cifrado, chave, qualquer coisa nao-texto) -------------
    static void gravarBytes(String arquivo, byte[] dados) throws IOException {
        Files.write(Path.of(arquivo), dados);
    }

    static byte[] lerBytes(String arquivo) throws IOException {
        return Files.readAllBytes(Path.of(arquivo));
    }

    // ---- 7) Classe File: existe? tamanho? listar pasta? (FileDemonstration) ---
    static void informacoes(String caminho) {
        File f = new File(caminho);
        if (!f.exists()) {
            System.out.println(caminho + " nao existe");
            return;
        }
        System.out.println(f.getName() + (f.isFile() ? " e arquivo" : " e pasta")
                + ", " + f.length() + " bytes, caminho absoluto: " + f.getAbsolutePath());
        if (f.isDirectory()) {
            for (String nome : f.list()) System.out.println("  - " + nome);
        }
    }

    public static void main(String[] args) throws IOException {
        String usuarios = "demo_usuarios.txt";
        new File(usuarios).delete();                       // comeca do zero

        acrescentarRegistro(usuarios, "ana", "123");
        acrescentarRegistro(usuarios, "bia", "abc");
        System.out.println("Conteudo:\n" + lerTudo(usuarios));
        System.out.println("ana/123 valido? " + validarLogin(usuarios, "ana", "123"));
        System.out.println("ana/xxx valido? " + validarLogin(usuarios, "ana", "xxx"));
        System.out.println("Linhas: " + lerLinhas(usuarios));

        gravarBytes("demo.bin", new byte[]{1, 2, (byte) 200});
        System.out.println("bytes lidos: " + lerBytes("demo.bin").length);
        informacoes(usuarios);

        new File(usuarios).delete();
        new File("demo.bin").delete();
    }
}
