// Classe "TelaPrincipal.java" -- GUI orientada a menus (P3 2025, Questao 4).
//
// Regras da prova que esta classe cumpre:
//  - SEM JOptionPane: todo feedback aparece na area de mensagens (JTextArea);
//  - tudo e disparado SOMENTE por cliques em itens de menu (JMenuBar);
//  - os 4 menus e 9 subitens tem exatamente os nomes do enunciado.
//
// Estado entre os cliques (a prova diz "lida", "calculado anteriormente"...):
//    mensagem  -> preenchida por "Ler arquivo de mensagem"
//    checksum  -> preenchido por "Gerar e mostrar CS" (e anulado ao reler a mensagem)
// Os itens seguintes conferem se a etapa anterior foi feita e, se nao, avisam.

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TelaPrincipal extends JFrame implements ActionListener {
    // Arquivos de trabalho: caminho relativo = pasta de trabalho da aplicacao
    private static final File ARQ_ORIGINAL  = new File("msg_original.txt");
    private static final File ARQ_COM_CS    = new File("msg_com_cs.txt");
    private static final File ARQ_PUBLICA   = new File("chave.publica");
    private static final File ARQ_PRIVADA   = new File("chave.privada");
    private static final File ARQ_CIFRADA   = new File("msg_cifrada.txt");
    private static final File ARQ_DECIFRADA = new File("msg_decifrada.txt");
    private static final String ROTULO_CS   = "Checksum: ";

    // Area de mensagens (feedback ao usuario)
    private final JTextArea areaMensagens = new JTextArea(22, 70);

    // Menu "Mensagem"
    private final JMenuItem miLer      = new JMenuItem("Ler arquivo de mensagem");
    private final JMenuItem miGerarCS  = new JMenuItem("Gerar e mostrar CS");
    private final JMenuItem miSalvarCS = new JMenuItem("Salvar arquivo de mensagem com CS");
    // Menu "Chaves"
    private final JMenuItem miChavePub  = new JMenuItem("Gerar e salvar chave pública");
    private final JMenuItem miChavePriv = new JMenuItem("Gerar e salvar chave privada");
    // Menu "Criptografia"
    private final JMenuItem miCifrar   = new JMenuItem("Cifrar e salvar a mensagem com CS cifrada");
    private final JMenuItem miDecifrar = new JMenuItem("Decifrar e salvar a mensagem com CS decifrada");
    // Menu "Geral"
    private final JMenuItem miMostrar = new JMenuItem("Mostrar a mensagem com CS decifrada");
    private final JMenuItem miSair    = new JMenuItem("Sair");

    // Objetos de negocio e estado
    private final Checksum calculadora = new Checksum();
    private final CryptoRSA rsa = new CryptoRSA();
    private final Impressora impressora = new Impressora();
    private String mensagem = null;
    private Integer checksum = null;

    public TelaPrincipal() {
        super("P3 - Mensagem, CheckSum e Criptografia RSA");

        // ----- menus -----
        JMenu mMensagem = new JMenu("Mensagem");
        mMensagem.add(miLer);
        mMensagem.add(miGerarCS);
        mMensagem.add(miSalvarCS);

        JMenu mChaves = new JMenu("Chaves");
        mChaves.add(miChavePub);
        mChaves.add(miChavePriv);

        JMenu mCripto = new JMenu("Criptografia");
        mCripto.add(miCifrar);
        mCripto.add(miDecifrar);

        JMenu mGeral = new JMenu("Geral");
        mGeral.add(miMostrar);
        mGeral.addSeparator();
        mGeral.add(miSair);

        JMenuBar barra = new JMenuBar();
        barra.add(mMensagem);
        barra.add(mChaves);
        barra.add(mCripto);
        barra.add(mGeral);
        setJMenuBar(barra);

        // ----- area de mensagens (somente leitura, com rolagem) -----
        areaMensagens.setEditable(false);
        areaMensagens.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        setLayout(new BorderLayout());
        add(new JScrollPane(areaMensagens), BorderLayout.CENTER);

        // ----- listeners: a mesma classe trata todos os itens -----
        miLer.addActionListener(this);
        miGerarCS.addActionListener(this);
        miSalvarCS.addActionListener(this);
        miChavePub.addActionListener(this);
        miChavePriv.addActionListener(this);
        miCifrar.addActionListener(this);
        miDecifrar.addActionListener(this);
        miMostrar.addActionListener(this);
        miSair.addActionListener(this);

        // ----- ajustes finais do frame -----
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        escrever("Pasta de trabalho (onde os arquivos serao lidos/gravados):\n  "
                + new File("").getAbsolutePath());
        escrever("Use os menus acima. Ordem sugerida: Mensagem > Chaves > Criptografia > Geral.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origem = e.getSource();
        try {
            if (origem == miLer) {
                lerMensagem();
            } else if (origem == miGerarCS) {
                gerarChecksum();
            } else if (origem == miSalvarCS) {
                salvarMensagemComCS();
            } else if (origem == miChavePub) {
                gerarChavePublica();
            } else if (origem == miChavePriv) {
                gerarChavePrivada();
            } else if (origem == miCifrar) {
                cifrar();
            } else if (origem == miDecifrar) {
                decifrar();
            } else if (origem == miMostrar) {
                mostrarDecifrada();
            } else if (origem == miSair) {
                dispose();
                System.exit(0);
            }
        } catch (FileNotFoundException ex) {
            escrever("ERRO: " + ex.getMessage());
        } catch (BadPaddingException | IllegalBlockSizeException ex) {
            escrever("ERRO ao decifrar (" + ex.getClass().getSimpleName() + "): a chave privada nao\n"
                    + "corresponde a chave publica usada para cifrar, ou o arquivo cifrado foi alterado.\n"
                    + "Gere as DUAS chaves de novo e cifre a mensagem outra vez.");
        } catch (IOException | GeneralSecurityException | ClassNotFoundException ex) {
            escrever("ERRO (" + ex.getClass().getSimpleName() + "): " + ex.getMessage());
        }
    }

    // ============================== Menu Mensagem ==============================

    private void lerMensagem() throws IOException {
        byte[] bytes = lerArquivo(ARQ_ORIGINAL);
        mensagem = new String(bytes, StandardCharsets.UTF_8);
        checksum = null;                                  // mensagem nova: CS antigo perdeu a validade
        escrever("Arquivo " + ARQ_ORIGINAL.getName() + " lido (" + bytes.length + " bytes):\n"
                + "-----\n" + mensagem + "\n-----");
    }

    private void gerarChecksum() {
        if (mensagem == null) {
            escrever("Leia primeiro o arquivo de mensagem (Mensagem > Ler arquivo de mensagem).");
            return;
        }
        checksum = calculadora.calcularChecksum(mensagem.toCharArray());
        escrever("CheckSum (Soma e Complemento de 2) da mensagem lida: " + checksum
                + "  (0x" + Integer.toHexString(checksum).toUpperCase() + ")");
    }

    private void salvarMensagemComCS() throws IOException {
        if (mensagem == null || checksum == null) {
            escrever("Leia a mensagem e gere o CS antes de salvar (Mensagem > Ler..., depois Gerar e mostrar CS).");
            return;
        }
        String conteudo = mensagem + System.lineSeparator() + ROTULO_CS + checksum;
        Files.write(ARQ_COM_CS.toPath(), conteudo.getBytes(StandardCharsets.UTF_8));
        escrever("Mensagem + CS salvos em " + ARQ_COM_CS.getName() + ":\n-----\n" + conteudo + "\n-----");
    }

    // =============================== Menu Chaves ===============================

    private void gerarChavePublica() throws IOException, GeneralSecurityException {
        rsa.geraESalvaChavePublica(ARQ_PUBLICA);
        escrever("Chave publica RSA (1024 bits) gerada e salva em " + ARQ_PUBLICA.getName() + ".");
        avisoDoPar();
    }

    private void gerarChavePrivada() throws IOException, GeneralSecurityException {
        rsa.geraESalvaChavePrivada(ARQ_PRIVADA);
        escrever("Chave privada RSA (1024 bits) gerada e salva em " + ARQ_PRIVADA.getName() + ".");
        avisoDoPar();
    }

    private void avisoDoPar() {
        if (rsa.isParCompleto()) {
            escrever("Par de chaves completo: chave.publica e chave.privada sao do MESMO par.");
        } else {
            escrever("Falta gravar a outra chave para completar o par (publica + privada).");
        }
    }

    // ============================ Menu Criptografia ============================

    private void cifrar() throws IOException, ClassNotFoundException, GeneralSecurityException {
        byte[] claro = lerArquivo(ARQ_COM_CS);
        lerArquivo(ARQ_PUBLICA);                          // so para dar erro claro se nao existir
        rsa.geraCifra(claro, ARQ_PUBLICA);
        byte[] cifrado = rsa.getTextoCifrado();
        Files.write(ARQ_CIFRADA.toPath(), cifrado);       // bytes crus: o arquivo e binario
        escrever("Mensagem com CS (" + claro.length + " bytes) cifrada com RSA e a chave publica.\n"
                + "Salva em " + ARQ_CIFRADA.getName() + " (" + cifrado.length + " bytes). Conteudo (hexadecimal):\n"
                + impressora.hexBytesToString(cifrado));
    }

    private void decifrar() throws IOException, ClassNotFoundException, GeneralSecurityException {
        byte[] cifrado = lerArquivo(ARQ_CIFRADA);
        lerArquivo(ARQ_PRIVADA);
        rsa.geraDecifra(cifrado, ARQ_PRIVADA);
        byte[] decifrado = rsa.getTextoDecifrado();
        Files.write(ARQ_DECIFRADA.toPath(), decifrado);
        escrever("Mensagem decifrada com RSA e a chave privada.\n"
                + "Salva em " + ARQ_DECIFRADA.getName() + " (" + decifrado.length + " bytes).");
    }

    // ================================ Menu Geral ================================

    private void mostrarDecifrada() throws IOException {
        String texto = new String(lerArquivo(ARQ_DECIFRADA), StandardCharsets.UTF_8);
        escrever("Conteudo de " + ARQ_DECIFRADA.getName() + ":\n-----\n" + texto + "\n-----");
        escrever(conferirChecksum(texto));
    }

    // Bonus: recalcula o CS da mensagem decifrada e compara com o CS que veio junto
    private String conferirChecksum(String texto) {
        int pos = texto.lastIndexOf(ROTULO_CS);
        if (pos < 0) {
            return "(Nao encontrei o CS no final do texto para conferir.)";
        }
        String original = texto.substring(0, pos);
        String sep = System.lineSeparator();
        if (original.endsWith(sep)) {
            original = original.substring(0, original.length() - sep.length());
        }
        try {
            int recebido = Integer.parseInt(texto.substring(pos + ROTULO_CS.length()).trim());
            int calculado = calculadora.calcularChecksum(original.toCharArray());
            return "Conferencia do CS: recebido=" + recebido + ", recalculado=" + calculado
                    + (recebido == calculado ? "  -> OK, mensagem integra." : "  -> DIFERENTE, mensagem alterada!");
        } catch (NumberFormatException ex) {
            return "(CS no final do texto nao e um numero valido.)";
        }
    }

    // =============================== Utilitarios ===============================

    // Le o arquivo inteiro; se nao existir, da uma mensagem que diz o que fazer
    private byte[] lerArquivo(File arquivo) throws IOException {
        if (!arquivo.exists()) {
            throw new FileNotFoundException("arquivo " + arquivo.getName() + " nao encontrado em "
                    + arquivo.getAbsoluteFile().getParent() + " (execute a etapa que o gera).");
        }
        return Files.readAllBytes(arquivo.toPath());
    }

    // Acrescenta uma mensagem na area e rola ate o final
    private void escrever(String texto) {
        areaMensagens.append(texto + "\n\n");
        areaMensagens.setCaretPosition(areaMensagens.getDocument().getLength());
    }
}
