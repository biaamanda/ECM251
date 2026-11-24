import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * Gera um .jar executável SEM precisar do comando "jar" (só do java/javac).
 * Útil em computadores onde "jar --version" não é reconhecido.
 *
 * Uso (na pasta onde estão os .class):
 *   javac CriaJar.java                      (só na primeira vez)
 *   java CriaJar Servidor.jar Servidor
 *   java CriaJar Cliente.jar  Cliente
 *
 * Coloca no .jar todos os .class da pasta (e subpastas, se usar package)
 * e escreve o MANIFEST.MF com a classe principal (Main-Class).
 * Compatível com Java 8 ou superior.
 */
public class CriaJar {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Uso: java CriaJar NomeDoArquivo.jar ClasseComMain [pasta dos .class]");
            System.out.println("Ex.: java CriaJar Servidor.jar Servidor");
            return;
        }
        String nomeJar = args[0];
        String classePrincipal = args[1];
        File pasta = new File(args.length > 2 ? args[2] : ".");

        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, classePrincipal);

        int total;
        try (JarOutputStream jar = new JarOutputStream(new FileOutputStream(nomeJar), manifest)) {
            total = adicionar(jar, pasta, "");
        }

        File classeMain = new File(pasta, classePrincipal.replace('.', '/') + ".class");
        if (!classeMain.exists()) {
            System.out.println("ATENÇÃO: não achei " + classeMain.getPath() + ". Compilou antes? O nome da classe está certo?");
        }
        System.out.println("Criado " + nomeJar + " com " + total + " arquivos .class (Main-Class: " + classePrincipal + ")");
    }

    // Percorre a pasta e adiciona todos os .class, mantendo as subpastas (packages)
    private static int adicionar(JarOutputStream jar, File pasta, String prefixo) throws IOException {
        int total = 0;
        File[] arquivos = pasta.listFiles();
        if (arquivos == null) return 0;
        for (File f : arquivos) {
            if (f.isDirectory()) {
                total += adicionar(jar, f, prefixo + f.getName() + "/");
            } else if (f.getName().endsWith(".class") && !f.getName().startsWith("CriaJar")) {
                jar.putNextEntry(new JarEntry(prefixo + f.getName()));
                try (InputStream in = new FileInputStream(f)) {
                    byte[] buffer = new byte[8192];
                    int lidos;
                    while ((lidos = in.read(buffer)) != -1) jar.write(buffer, 0, lidos);
                }
                jar.closeEntry();
                total++;
            }
        }
        return total;
    }
}
