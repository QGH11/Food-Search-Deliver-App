import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame {

    private JPanel loginPanel;
    private JButton loginButton;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JLabel registerLabel;

    public Login(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();
        ImageIcon img = new ImageIcon("D://Java//GUI//FoodApp//src//Home-images//unicorn.png");
        this.setIconImage(img.getImage());


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

                    try {
                        // connect to the data base
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp-login", "root", "#"); // correct password needed
                        Statement statement = connection.createStatement();

                        String sql = "Select username from login where username='"+username+"'";

                        ResultSet rs = statement.executeQuery(sql);

                        // if username is true
                        if (rs.next()) {
                            String sql2 = "Select passwords from login where passwords='"+passwords+"'";
                            ResultSet rs2 = statement.executeQuery(sql2);

                            // check for passwords
                            if (rs2.next()) {
                                //open the home page
                                JFrame frame = new Home("FoodApp-Home");
                                frame.setLocationRelativeTo(null);
                                frame.setResizable(false);
                                frame.setVisible(true);

                                // close the login form
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null,"ERROR: Passwords Does Not Match!");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"ERROR: Username Is Not Existed!");
                        }

                        connection.close();

                    } catch (Exception f) {
                        f.printStackTrace();
                    }
                }
            }
        });

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //open the login form
                JFrame frame = new Registration("FoodApp-Registration");
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                // close the registration form
                dispose();

            }


            //mouse hover effect
            @Override
            public void mouseEntered(MouseEvent e) {
                registerLabel.setForeground(new Color(245, 91, 36));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerLabel.setForeground( new Color(0, 0, 0) );
            }

        });
    }

    public static void main(String[] args) {
        JFrame frame = new Login("FoodApp-Login");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
