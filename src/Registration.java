import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Registration extends JFrame {

    private JPanel registrationPanel;
    private JTextField emailTextField;
    private JTextField reEnterEmailTextField;
    private JTextField passwordsTextField;
    private JTextField reEnterPasswordsTextField;
    private JCheckBox agreeTermCheckBox;
    private JButton registerButton;
    private JLabel backToLoginLabel;

    public Registration(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registrationPanel);
        ImageIcon img = new ImageIcon("D://Java//GUI//FoodApp//src//Home-images//unicorn.png");
        this.setIconImage(img.getImage());
        this.pack();


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // store all the information in to the data base

                String emailText = emailTextField.getText();
                String reEnterEmailText = reEnterEmailTextField.getText();
                String passwordsText = passwordsTextField.getText();
                String reEnterPasswordsText = reEnterPasswordsTextField.getText();
                Boolean agreeTerm = agreeTermCheckBox.isSelected();

                // check if inputs are valid
                if (emailText.isEmpty() || reEnterEmailText.isEmpty() || passwordsText.isEmpty() || reEnterPasswordsText.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"ERROR: AT LEAST ONE SECTION IS EMPTY!");
                }
                else if (!emailText.equals(reEnterEmailText)) {
                    JOptionPane.showMessageDialog(null,"ERROR: EMAILS ARE NOT CONSISTENT!");
                }
                else if (!passwordsText.equals(reEnterPasswordsText)) {
                    JOptionPane.showMessageDialog(null,"ERROR: PASSWORDS ARE NOT CONSISTENT!");
                }
                else if (!agreeTerm) {
                    JOptionPane.showMessageDialog(null,"Terms not checked!");
                }
                else {

                    try {
                        // connect to the data base
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp-login", "root", "#");  // correct password needed
                        Statement statement = connection.createStatement();

                        String sql = "Insert into login values("+emailText+","+passwordsText+")";
                        statement.executeUpdate(sql);

                        connection.close();

                        JOptionPane.showMessageDialog(null,"REGISTRATION SUCCESSFUL");

                        //open the login form
                        JFrame frame = new Login("FoodApp-Login");
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                        frame.setVisible(true);

                        // close the registration form
                        dispose();

                    } catch (Exception f) {
                        JOptionPane.showMessageDialog(null,"ERROR: Email Is Already Registered!");
                        f.printStackTrace();
                    }

                }
            }
        });

        backToLoginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //open the login form
                JFrame frame = new Login("FoodApp-Login");
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                // close the registration form
                dispose();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backToLoginLabel.setForeground(new Color(245, 91, 36));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backToLoginLabel.setForeground( new Color(245, 245, 245) );
            }
        });

    }


    public static void main(String[] arg) {
        JFrame frame = new Registration("FoodApp-Registration Form");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
