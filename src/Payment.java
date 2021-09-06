import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;

public class Payment extends JFrame{
    private JPanel paymentPanel;
    private JButton payButton;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField cityTextField;
    private JComboBox countryComboBox;
    private JFormattedTextField cardNumberFormattedTextField;
    private JFormattedTextField expiryDateFormattedTextField;
    private JFormattedTextField CVCFormattedTextField;
    private JFormattedTextField postalCodeFormattedTextField;
    private JFormattedTextField phoneNumberFormattedTextField;
    private JFormattedTextField emailFormattedTextField;
    private JLabel backToCardButton;
    private JLabel priceLabel;

    private String[][] productArr = { // name, price, description, estimate time, location, img link
            {"Cheese",      "$9.99",    "Amazing cheese, that stinks for sure!", "10 mins~", "Tom & Jerry Factory", "D://Java//GUI//FoodApp//src//Home-Products//cheese.png"},
            {"Donuts",      "$6.66",    "Real donuts with a donut hole in the middle.", "7 mins~", "Dream Donut Shop", "D://Java//GUI//FoodApp//src//Home-Products//donut.png"},
            {"Fries",       "$5.55",    "Crispy fries with tomato sauce and ketchup.", "30 secs~", "McDonald's Brother", "D://Java//GUI//FoodApp//src//Home-Products//fast-food.png"},
            {"Hamburgers",  "$11.11",   "Premium beef patty with no lectures or pickles...", "15 mins~", "Burgers Queen","D://Java//GUI//FoodApp//src//Home-Products//hamburger.png"},
            {"Hot Dogs",    "$8.88",    "Crazy hot dogs!!!", "5 secs~", "New York Hot Dog","D://Java//GUI//FoodApp//src//Home-Products//hot-dog.png"},
            {"Nachos",      "$7.77",    "Just too delicious to describe...", "2 days~", "Mexico City","D://Java//GUI//FoodApp//src//Home-Products//nachos.png"},
            {"Nigiri",      "$99.99",   "Hokkaido blue fin tuna and many other expansive fish.", "2 weeks~", "Japan","D://Java//GUI//FoodApp//src//Home-Products//nigiri.png"},
            {"Pancakes",    "$4.44",    "Soft and sweet pancakes with free maple syrup.", "3 mins~", "Vancouver","D://Java//GUI//FoodApp//src//Home-Products//pancake.png"},
            {"Pizza",       "$15.99",   "Traditional Italian thin crust, DELIZIOSO!", "8 mins~", "Florance","D://Java//GUI//FoodApp//src//Home-Products//pizza.png"},
            {"Tacos",       "$3.33",    "Spicy fillings with cactus", "6 mins~", "Toronto","D://Java//GUI//FoodApp//src//Home-Products//taco.png"},
            {"Water",       "$0.99",    "Vancouver tap water.", "1 sec~", "Nearby Washroom","D://Java//GUI//FoodApp//src//Home-Products//water.png"}
    };

    public Payment(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(paymentPanel);
        ImageIcon img = new ImageIcon("D://Java//GUI//FoodApp//src//Home-images//unicorn.png");
        this.setIconImage(img.getImage());
        this.pack();

        // Round the border
        cardNumberFormattedTextField.setBorder(new RoundedBorder(10));
        expiryDateFormattedTextField.setBorder(new RoundedBorder(10));
        CVCFormattedTextField.setBorder(new RoundedBorder(10));

        //set jformat
        jFormatField();

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check all the input
                // notify successful payment
                // go to the shopping cart
                if (cardNumberFormattedTextField.getText().equals("____ ____ ____ ____") || cardNumberFormattedTextField.getText().indexOf('_') != -1) {
                    JOptionPane.showMessageDialog(null,"ERROR: CARD NUMBER IS INVALID!");
                }
                else if (expiryDateFormattedTextField.getText().equals("__/__") || expiryDateFormattedTextField.getText().indexOf('_') != -1) {
                    JOptionPane.showMessageDialog(null,"ERROR: EXPIRY DATE IS INVALID!");
                }
                else if (CVCFormattedTextField.getText().equals("___") || CVCFormattedTextField.getText().indexOf('_') != -1) {
                    JOptionPane.showMessageDialog(null,"ERROR: CVC IS INVALID!");
                }
                else if (firstNameTextField.getText().equals("First Name")) {
                    JOptionPane.showMessageDialog(null,"ERROR: FIRST NAME IS INVALID!");
                }
                else if (lastNameTextField.getText().equals("Last Name")) {
                    JOptionPane.showMessageDialog(null,"ERROR: LAST NAME IS INVALID!");
                }
                else if (countryComboBox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null,"ERROR: PLEASE SELECT A COUNTRY!");
                }
                else if (cityTextField.getText().equals("City")) {
                    JOptionPane.showMessageDialog(null,"ERROR: CITY IS INVALID!");
                }
                else if (postalCodeFormattedTextField.getText().equals("Postal Code") || postalCodeFormattedTextField.getText().indexOf('_') != -1) {
                    JOptionPane.showMessageDialog(null,"ERROR: POSTAL CODE IS INVALID!");
                }
                else if (emailFormattedTextField.getText().equals("Email")) {
                    JOptionPane.showMessageDialog(null, "ERROR: EMAIL IS INVALID!");
                }
                else if (phoneNumberFormattedTextField.getText().equals("Phone Number") || phoneNumberFormattedTextField.getText().indexOf('_') != -1) {
                    JOptionPane.showMessageDialog(null, "ERROR: PHONE NUMBER IS INVALID!");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Payment Successful");
                }


            }
        });


        // textfield focus format
        firstNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (firstNameTextField.getText().equals("First Name"))
                    firstNameTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (firstNameTextField.getText().isEmpty()) {
                    firstNameTextField.setText("First Name");
                }
            }
        });
        lastNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (lastNameTextField.getText().equals("Last Name"))
                    lastNameTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (lastNameTextField.getText().isEmpty()) {
                    lastNameTextField.setText("Last Name");
                }
            }
        });
        cityTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (cityTextField.getText().equals("City"))
                    cityTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (cityTextField.getText().isEmpty()) {
                    cityTextField.setText("City");
                }
            }
        });
        postalCodeFormattedTextField.addFocusListener(new FocusAdapter() {
            MaskFormatter numFormatter = null;

            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (postalCodeFormattedTextField.getText().equals("Postal Code")) {
                    postalCodeFormattedTextField.setText("");

                    // postal code
                    try {
                        numFormatter = new MaskFormatter("AAA AAA");
                        numFormatter.setPlaceholderCharacter('_');
                        numFormatter.install(postalCodeFormattedTextField);
                    } catch (java.text.ParseException d) {
                        d.printStackTrace();
                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (postalCodeFormattedTextField.getText().equals("___ ___")) {
                    numFormatter.uninstall();
                    postalCodeFormattedTextField.setText("Postal Code");
                }
            }
        });
        emailFormattedTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (emailFormattedTextField.getText().equals("Email"))
                    emailFormattedTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (emailFormattedTextField.getText().isEmpty()) {
                    emailFormattedTextField.setText("Email");
                }
            }
        });
        phoneNumberFormattedTextField.addFocusListener(new FocusAdapter() {
            MaskFormatter numFormatter = null;

            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (phoneNumberFormattedTextField.getText().equals("Phone Number")) {
                    phoneNumberFormattedTextField.setText("");

                    // phone number
                    try {
                        numFormatter = new MaskFormatter("(###) ###-####");
                        numFormatter.setPlaceholderCharacter('_');
                        numFormatter.install(phoneNumberFormattedTextField);

                    } catch (java.text.ParseException d) {
                        d.printStackTrace();
                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (phoneNumberFormattedTextField.getText().equals("(___) ___-____")) {
                    numFormatter.uninstall();
                    phoneNumberFormattedTextField.setText("Phone Number");
                }
            }
        });


        backToCardButton.addMouseListener(new MouseAdapter() {
            // go back to the shopping cart
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                JFrame frame = new Account("FoodApp-Shopping Cart");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);

                dispose();
            }

            // mouse hover effect
            @Override
            public void mouseEntered(MouseEvent e) {
                backToCardButton.setForeground(new Color(245, 91, 36));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backToCardButton.setForeground( new Color(255, 252, 252) );
            }
        });
    }

    // customize JformatedFields
    public void jFormatField() {
        MaskFormatter numFormatter = null;

        //card number
        try {
            numFormatter = new MaskFormatter("#### #### #### ####");
            numFormatter.setPlaceholderCharacter('_');
            numFormatter.install(cardNumberFormattedTextField);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        //card expiry date
        try {
            numFormatter = new MaskFormatter("##/##");
            numFormatter.setPlaceholderCharacter('_');
            numFormatter.install(expiryDateFormattedTextField);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        //CVC
        try {
            numFormatter = new MaskFormatter("###");
            numFormatter.setPlaceholderCharacter('_');
            numFormatter.install(CVCFormattedTextField);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String arg[]) {
        JFrame frame = new Payment("FoodApp-Payment");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
