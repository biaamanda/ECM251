/*Utilizando os componentes vistos neste material e nos
anteriores, desenvolver uma aplicação onde o usuário só 
conseguirá acessá-la através de login e senha corretos, 
pré-cadastrados e armazenados em uma tabela de
login_senha no banco de dados MySQL;

• Ao ser validado, o usuário terá acesso às suas notas e faltas do
curso do IMT, apresentados em uma tabela, preenchida com
valores também vindos do banco de dados;
• Qualquer dado alterado pelo usuário na tabela, deverá ser
armazenado no banco de dados, que também registrará a data
e a hora da última alteração realizada;

- login senha 
- cadastro e armazenamento dos dados
- notas e faltas
- alterar nota
- data hora da alteração
*/

// import javax.swing.JFrame;

// // public class Main {  
// //     public static void main( String args[] ) { 
// //         Login login = new Login();
// //         login.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
// //         login.setSize( 325, 125 ); // frame size (W, H)
// //         login.setVisible( true ); // display frame

// //    }
// // }
public class Main {  
    public static void main( String args[] ) { 
        System.out.println("=== Testing ModificationDate ===");
        ModificationDate.main(args);  // Call ModificationDate's main method
   }
}