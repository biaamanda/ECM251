// Demonstrating the JTextField class
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class TextFieldFrame extends JFrame {  
    private JTextField textField1; // Text field with a set size
   private JTextField textField2; // Text field with default text
   private JTextField textField3; // Text field with default text and size
   private JPasswordField passwordField; // Password field with text

   // TextFieldFrame constructor adds JTextFields to the JFrame
   public TextFieldFrame()
   {  super( "JTextField and JPasswordField" );
      setLayout( new FlowLayout() ); // set the frame's layout
      // Text field with 10 columns
      textField1 = new JTextField( 10 );
      add( textField1 ); // add textField1 to the JFrame
      // Text field with default text
      textField2 = new JTextField( "Enter text here" );
      add( textField2 ); // add textField2 to the JFrame
      // Text field with default text and 21 columns
      textField3 = new JTextField( "Uneditable text field", 21 );
      textField3.setEditable( false ); // disable editing
      add( textField3 ); // add textField3 to the JFrame
      // Password field with default text
      passwordField = new JPasswordField( "Hidden text" );
      add( passwordField ); // add passwordField to the JFrame
      // Event handlers
      TextFieldHandler handler = new TextFieldHandler();
      textField1.addActionListener( handler );
      textField2.addActionListener( handler );
      textField3.addActionListener( handler );
      passwordField.addActionListener( handler );
   }

   private class TextFieldHandler implements ActionListener
   {  // processes the text field events
      public void actionPerformed( ActionEvent event )
      {  String string = ""; // string to be displayed
         // user pressed Enter in JTextField textField1
         if ( event.getSource() == textField1 )
            string = String.format( "textField1: %s", event.getActionCommand() );
         // user pressed Enter in JTextField textField2
         else if ( event.getSource() == textField2 )
                  string = String.format( "textField2: %s", event.getActionCommand() );
         // user pressed Enter in JTextField textField3
         else if ( event.getSource() == textField3 )
                  string = String.format( "textField3: %s", event.getActionCommand() );
         // user pressed Enter in JTextField passwordField
         else if ( event.getSource() == passwordField )
                  string = String.format(
                              "passwordField: %s", new String( passwordField.getPassword() )
                                          );
         // display the JTextField's content
         JOptionPane.showMessageDialog( null, string );
      }
   }
}