import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JPanel loginPanel;
    private JButton loginButton;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JPanel registrationPanel;
    private JLabel registerButton;

    public Login(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get all the input
                // check if user exist
                // go to home frame
                String username = usernameTextField.getText();
                String passwords = passwordTextField.getText();

                // check for empty input
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"ERROR: USERNAME IS EMPTY!");
                }
                else if (passwords.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"ERROR: PASSWORDS IS EMPTY!");
                }
                else {

                }
            }
        });
    }




    public static void main(String[] args) {
        JFrame frame = new Login("FoodApp-Login");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
