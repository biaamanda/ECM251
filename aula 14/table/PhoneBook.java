package table;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PhoneBook extends JFrame
{   JPanel background;
    JTable table;
    JScrollPane bar;
    Object [][] data =
    {
        {"20080001", "Antonio", "11 98888-0001", "antonio@gmail.com"},
        {"20080002", "Jose", "11 98111-3333", "jose@hotmail.com"},
        {"20080003", "Ricardo", "13 9876-5432", "ricardo@bol.com.br"},
        {"20080004", "Roberto", "11 98765-4321", "roberto@gmail.com"},
        {"20080005", "Valter", "11 2666-6666", "valter@yahoo.com.br"}
    };

    String [] column = {"ID", "Name", "Phone", "Email"};

    public PhoneBook()
    { super ("Contacts");
    }

    public void makeWindow()
    {   background = new JPanel();
        background.setLayout(new GridLayout(1, 1));
        table = new JTable(data, column);
        bar = new JScrollPane(table);
        background.add(bar);
        getContentPane().add(background);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 125);
        setVisible(true);
    }
}