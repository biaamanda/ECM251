import javax.swing.JOptionPane;

public class TesteCarro {
    public static void main(String[] args) {
        String brand = JOptionPane.showInputDialog(null, "Marca do carro:", "Carro", JOptionPane.QUESTION_MESSAGE);
        String model = JOptionPane.showInputDialog(null, "Modelo:", "Carro", JOptionPane.QUESTION_MESSAGE);
        String yearStr = JOptionPane.showInputDialog(null, "Ano de fabricação:", "Carro", JOptionPane.QUESTION_MESSAGE);

        int year = Integer.parseInt(yearStr.trim());
        Carro carro = new Carro(brand, model, year);

        JOptionPane.showMessageDialog(null, carro.toString(), "Dados do carro", JOptionPane.INFORMATION_MESSAGE);
    }
}
