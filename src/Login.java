import javax.swing.*;

public class Login extends JFrame {

    private JPanel loginPanel;

    public Login(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();
    }
    public static void main(String[] args) {
        JFrame frame = new Login("Login");
        frame.setBounds(400, 100, 800, 700);
        frame.setVisible(true);
    }
}
