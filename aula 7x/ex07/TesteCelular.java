import javax.swing.JOptionPane;

public class TesteCelular {
    public static void main(String[] args) {
        String brand = JOptionPane.showInputDialog(null, "Marca do celular:", "Celular", JOptionPane.QUESTION_MESSAGE);
        String model = JOptionPane.showInputDialog(null, "Modelo:", "Celular", JOptionPane.QUESTION_MESSAGE);
        String storageStr = JOptionPane.showInputDialog(null, "Capacidade de armazenamento (GB):", "Celular", JOptionPane.QUESTION_MESSAGE);

        int storageCapacity = Integer.parseInt(storageStr.trim());

        Celular celular = new Celular(brand, model, storageCapacity);

        JOptionPane.showMessageDialog(null, celular.fichaTecnica(), "Ficha Técnica do Celular", JOptionPane.INFORMATION_MESSAGE);

    }
}