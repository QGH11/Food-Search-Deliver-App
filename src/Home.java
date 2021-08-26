import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Home extends JFrame {
    private JPanel homePanel;
    private JTextField searchForYourFavouriteTextField;
    private JTextField addressTextField;
    private JComboBox foodCategoryComboBox;
    private JList list1;
    private JComboBox hungryLevelComboBox;
    private JSpinner quantitySpinner;
    private JComboBox deliveryOptionsComboBox;
    private JPanel headerPanel;
    private JPanel paymentPanel;
    private JLabel cartIconLabel;
    private JLabel cartTextLabel;
    private JLabel paymentLabel;
    private JButton addToCartButton;
    private JLabel Quantity;
    private JTextField postalCodeTextField;
    private JTextArea noneTextArea;

    //json
    private static FileWriter file;
    private JSONObject tempUserFoodOptions = new JSONObject();


    public Home(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(homePanel);
        this.pack();

        //update the left side panel
        updateUserOptions();

        paymentLabel.addMouseListener(new MouseAdapter() {
            // go to payment

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                storeUserOptions();

            }

            // mouse hover effect
            @Override
            public void mouseEntered(MouseEvent e) {
                paymentLabel.setForeground(new Color(245, 91, 36));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                paymentLabel.setForeground( new Color(0, 0, 0) );
            }
        });
    }

    // update the GUI user food options
    public void updateUserOptions() {
        try {
            readJson();

            String address = (String) tempUserFoodOptions.get("Address");
            String postalCode = (String) tempUserFoodOptions.get("PostalCode");
            String deliveryOption = (String) tempUserFoodOptions.get("DeliveryOption");

            addressTextField.setText(address);
            postalCodeTextField.setText(postalCode);

            String[] deliveryOptionItems = {"Pick Up", "Bot Delivery (Free)", "Human Delivery ($100)"};
            for (int i = 0; i < deliveryOptionItems.length; i++) {
                if (deliveryOptionItems[i].compareTo(deliveryOption) == 0) {
                    deliveryOptionsComboBox.setSelectedIndex(i);
                }
            }
        }
        catch(Exception e) {e.printStackTrace();}
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    // user food options class
    public class userFoodOptions {
        private String address;
        private String postalCode;
        private String foodCategory;
        private String deliveryOption;
        private String hungryLevel;
        private JSONObject userFoodOptions;

        // constructor
        public userFoodOptions() {
            address = addressTextField.getText();
            postalCode = postalCodeTextField.getText();
            foodCategory = foodCategoryComboBox.getSelectedItem().toString();
            deliveryOption = deliveryOptionsComboBox.getSelectedItem().toString();
            hungryLevel = hungryLevelComboBox.getSelectedItem().toString();
        }

        // store quotation into json object
        public void storeUserFoodOptionsJObject() {
            userFoodOptions = new JSONObject();
            userFoodOptions.put("Address", address);
            userFoodOptions.put("PostalCode", postalCode);
            userFoodOptions.put("FoodCategory", foodCategory);
            userFoodOptions.put("DeliveryOption", deliveryOption);
            userFoodOptions.put("HungryLevel", hungryLevel);
        }

        // get quotation object
        public JSONObject getUserFoodOptions() {
            return userFoodOptions;
        }
    }

    // read and return JSON file data
    public void readJson() {
        JSONParser parser = new JSONParser();

        try {
            Object userFoodOptions = parser.parse(new FileReader("./src/userFoodOptions.json"));
            JSONObject jsonObject = (JSONObject) userFoodOptions;

            tempUserFoodOptions = (JSONObject) jsonObject.get("UserFoodOptions");
        }
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch(IOException e) {e.printStackTrace();}
        catch(ParseException e) {e.printStackTrace();}
        catch(Exception e) {e.printStackTrace();}
    }

    //write into json file
    public void storeUserOptions() {
        userFoodOptions newChoice = new userFoodOptions();
        newChoice.storeUserFoodOptionsJObject();

        tempUserFoodOptions.put("UserFoodOptions", newChoice.getUserFoodOptions());

        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("./src/userFoodOptions.json");
            file.write(tempUserFoodOptions.toJSONString());
            file.flush();
            file.close();
        }
        catch (IOException d) {
            d.printStackTrace();
        }
    }


    public static void main(String[] args) {
        JFrame frame = new Home("FoodApp-Home");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
