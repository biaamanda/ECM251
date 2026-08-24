import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Login extends JFrame {  
    private JTextField textField1;
   private JPasswordField passwordField;
   private JButton saveButton;
   private JLabel label1;
   private JLabel label2;

   public Login()
   {  super( "JTextField and JPasswordField" );
      setLayout( new BorderLayout( 5, 5 ) ); // set the frame's layout
      
      // Create panel for login and password fields with GridLayout
      JPanel fieldPanel = new JPanel();
      fieldPanel.setLayout( new GridLayout( 2, 2, 5, 5 ) );
      
      label1 = new JLabel("Login: ");
      fieldPanel.add( label1 );
      textField1 = new JTextField( 8 );
      fieldPanel.add( textField1 ); // add textField1 to the panel
      // Password field with default text
      label2 = new JLabel("Password: ");
      fieldPanel.add( label2 );
      passwordField = new JPasswordField( "     " );
      fieldPanel.add( passwordField ); // add passwordField to the panel
      
      add( fieldPanel, BorderLayout.CENTER ); // add fieldPanel to center
      
      // Create panel for button with FlowLayout to center it
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout( new FlowLayout( FlowLayout.CENTER ) );
      saveButton = new JButton( "Save" );
      buttonPanel.add( saveButton );
      
      add( buttonPanel, BorderLayout.SOUTH );
      
      // Event handlers
      TextFieldHandler handler = new TextFieldHandler();
      textField1.addActionListener( handler );
      passwordField.addActionListener( handler );
      saveButton.addActionListener( handler );
   }

   private class TextFieldHandler implements ActionListener {  
      public void actionPerformed( ActionEvent event ) {  
        String string = ""; // string to be displayed
         // user pressed Enter in JTextField textField1
         if ( event.getSource() == textField1 )
            string = String.format( "textField1: %s", event.getActionCommand() );
         // user pressed Enter in JTextField passwordField
         else if ( event.getSource() == passwordField )
                  string = String.format(
                              "passwordField: %s", new String( passwordField.getPassword() )
                                          );
         // user clicked Save button
         else if ( event.getSource() == saveButton )
                  string = String.format( "Login saved! User: %s, Password: %s", 
                              textField1.getText(), new String( passwordField.getPassword() ) );
         // display the JTextField's content
         JOptionPane.showMessageDialog( null, string );
      }
   }
}