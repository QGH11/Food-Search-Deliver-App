import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Home extends JFrame {
    private JPanel homePanel;
    private JTextField searchForYourFavouriteTextField;
    private JTextField addressTextField;
    private JComboBox foodCategoryComboBox;
    private JList productLists;
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
    private JTextArea specialRequestTextArea;
    private JEditorPane productDescriptionEditorPane;
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

        //append customized jList
        appendCustomizedJList();

        // make the description pane uneditable
        productDescriptionEditorPane.setEditable(false);
        //show default product description
        showProductDescription(0);
        productLists.setSelectedIndex(0);

        //only allow jspinner positive values
        int min = 0;
        int max = 100;
        int step = 1;
        int initValue = 0;
        SpinnerModel model = new SpinnerNumberModel(initValue, min, max, step);
        quantitySpinner.setModel(model);

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

        productLists.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // update the descroption
                showProductDescription(productLists.getAnchorSelectionIndex());
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add products to cart

                // get all the information


            }
        });
    }

    // show description of hovered product
    public void showProductDescription(int index) {
            productDescriptionEditorPane.setContentType("text/html");
            String template = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "    <style>\n" +
                    "        \n" +
                    "    \n" +
                    "        ul {\n" +
                    "           list-style-type: none;\n" +
                    "        }\n" +
                    "         \n" +
                    "        h3 {\n" +
                    "            font-size: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        li {\n" +
                    "            padding-top: 5px;\n" +
                    "            font-size: 13px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "    </head>\n" +
                    "    \n" +
                    "    <body> \n" +
                    "        <div>   \n" +
                    "            <ul>\n" +
                    "                <li>\n" +
                    "                    <h3>Product Name: %s</h3>\n" +
                    "                </li>\n" +
                    "                <li>Price: %s\n" +
                    "                </li>\n" +
                    "                <li>Description: %s\n" +
                    "                </li>\n" +
                    "                <li>Estimate time: %s\n" +
                    "                </li>\n" +
                    "                <li>\n" +
                    "                    Location: %s\n" +
                    "                </li>\n" +
                    "                <li><img src=\"file:D://Java//GUI//FoodApp//src//Home-images//map.png//\">\n" +
                    "                </li>\n" +
                    "            </ul>\n" +
                    "        </div>\n" +
                    "    </body>\n" +
                    "</html>";

                String description = String.format(template, productArr[index][0], productArr[index][1], productArr[index][2], productArr[index][3],productArr[index][4]);
                productDescriptionEditorPane.setText(description);


    }

    // append products to jlist
    public void appendCustomizedJList() {
        DefaultListModel productList = new DefaultListModel();

        productList.clear();
        String cheese = "  " + String.format("%-21s %3s",productArr[0][0], productArr[0][1]).replace(' ', '-');
        String donuts = "  " + String.format("%-21s %3s",productArr[1][0], productArr[1][1]).replace(' ', '-');
        String fries = "  " + String.format("%-22s %3s",productArr[2][0], productArr[2][1]).replace(' ', '-');
        String hamburgers = "  " + String.format("%-19s %3s",productArr[3][0], productArr[3][1]).replace(' ', '-');
        String hotDogs = "  " + String.format("%-20s %3s",productArr[4][0], productArr[4][1]).replace(' ', '-');
        String nachos = "  " + String.format("%-20s %3s",productArr[5][0], productArr[5][1]).replace(' ', '-');
        String nigiri = "  " + String.format("%-22s %3s",productArr[6][0], productArr[6][1]).replace(' ', '-');
        String pancakes = "  " + String.format("%-19s %3s",productArr[7][0], productArr[7][1]).replace(' ', '-');
        String pizza = "  " + String.format("%-21s %3s",productArr[8][0], productArr[8][1]).replace(' ', '-');
        String tacos = "  " + String.format("%-20s %3s",productArr[9][0], productArr[9][1]).replace(' ', '-');
        String water = "  " + String.format("%-20s %3s",productArr[10][0], productArr[10][1]).replace(' ', '-');


        productList.addElement(new ImgsNText(cheese , new ImageIcon(productArr[0][5])));
        productList.addElement(new ImgsNText(donuts , new ImageIcon(productArr[1][5])));
        productList.addElement(new ImgsNText(fries , new ImageIcon(productArr[2][5])));
        productList.addElement(new ImgsNText(hamburgers , new ImageIcon(productArr[3][5])));
        productList.addElement(new ImgsNText(hotDogs , new ImageIcon(productArr[4][5])));
        productList.addElement(new ImgsNText(nachos , new ImageIcon(productArr[5][5])));
        productList.addElement(new ImgsNText(nigiri , new ImageIcon(productArr[6][5])));
        productList.addElement(new ImgsNText(pancakes , new ImageIcon(productArr[7][5])));
        productList.addElement(new ImgsNText(pizza , new ImageIcon(productArr[8][5])));
        productList.addElement(new ImgsNText(tacos , new ImageIcon(productArr[9][5])));
        productList.addElement(new ImgsNText(water, new ImageIcon(productArr[10][5])));
        productLists.setCellRenderer(new Renderer());

        productLists.setModel(productList);
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
