import javax.swing.*;
import java.awt.*;

public class Registration extends JFrame {

    private JPanel registrationPanel;
    private JTextField emailTextField;
    private JTextField reEnterEmailTextField;
    private JTextField passwordsTextField;
    private JTextField reEnterPasswordsTextField;
    private JCheckBox agreeTermsCheckBox;
    private JButton registerButton;

    public Registration(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registrationPanel);
        this.pack();
    }


    public static void main(String[] arg) {
        JFrame frame = new Registration("FoodApp-Registration Form");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
