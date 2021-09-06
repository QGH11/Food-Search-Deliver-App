import javax.swing.*;

public class Account extends JFrame {

    private JPanel shoppingCartPane;

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

    public Account(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(shoppingCartPane);
        ImageIcon img = new ImageIcon("D://Java//GUI//FoodApp//src//Home-images//unicorn.png");
        this.setIconImage(img.getImage());
        this.pack();



        // append paid products to jlist
        appendCustomizedJList();
    }


    // append paid products to jlist
    public void appendCustomizedJList() {

    }

    public static void main(String arg[]) {
        JFrame frame = new Account("FoodApp-Account");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
