import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.Renderer;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class shoppingCart extends JFrame {

    private JPanel mainPanel;
    private JButton continuePaymentButton;
    private JTable shoppingListJTable;

    private String[][] productArr = { // name, price, description, estimate time, location, img link
        {"Cheese",      "$9.99",    "Amazing cheese, that stinks for sure!", "10 mins~", "Tom & Jerry Factory", "src/Home-Products/cheese.png"},
        {"Donuts",      "$6.66",    "Real donuts with a donut hole in the middle.", "7 mins~", "Dream Donut Shop", "src/Home-Products/donut.png"},
        {"Fries",       "$5.55",    "Crispy fries with tomato sauce and ketchup.", "30 secs~", "McDonald's Brother", "src/Home-Products/fast-food.png"},
        {"Hamburgers",  "$11.11",   "Premium beef patty with no lectures or pickles...", "15 mins~", "Burgers Queen","src/Home-Products/hamburger.png"},
        {"Hot Dogs",    "$8.88",    "Crazy hot dogs!!!", "5 secs~", "New York Hot Dog","src/Home-Products/hot-dog.png"},
        {"Nachos",      "$7.77",    "Just too delicious to describe...", "2 days~", "Mexico City","src/Home-Products/nachos.png"},
        {"Nigiri",      "$99.99",   "Hokkaido blue fin tuna and many other expansive fish.", "2 weeks~", "Japan","src/Home-Products/nigiri.png"},
        {"Pancakes",    "$4.44",    "Soft and sweet pancakes with free maple syrup.", "3 mins~", "Vancouver","src/Home-Products/pancake.png"},
        {"Pizza",       "$15.99",   "Traditional Italian thin crust, DELIZIOSO!", "8 mins~", "Florance","src/Home-Products/pizza.png"},
        {"Tacos",       "$3.33",    "Spicy fillings with cactus", "6 mins~", "Toronto","src/Home-Products/taco.png"},
        {"Water",       "$0.99",    "Vancouver tap water.", "1 sec~", "Nearby Washroom","src/Home-Products/water.png"}
    };

    public shoppingCart(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        ImageIcon img = new ImageIcon("src/Home-images/unicorn.png");
        this.setIconImage(img.getImage());
        this.pack();

        //append the customized table
        setShoppingTable();
    }

    // set the shopping table
    public void setShoppingTable() {
        tableModel m = new tableModel();
        shoppingListJTable.setModel(m);

        shoppingListJTable.setRowHeight(100);
        shoppingListJTable.getColumnModel().getColumn(0).setCellRenderer(new shoppingCartTableJLabelRenderer());
    }

    class tableModel extends DefaultTableModel{
        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }
    }

    // table customized panel
    public class MyPanel extends JPanel {
        public MyPanel(shoppingCartTableComponent v) {
            JLabel titleJLabel = new JLabel(v.getName());
            titleJLabel.setIcon(v.getImg());
            add(titleJLabel);


            int min = 0;
            int max = 100;
            int step = 1;
            int initValue = 0;
            SpinnerModel model = new SpinnerNumberModel(initValue, min, max, step);
            JSpinner quantityJSpinner = new JSpinner(model);
            add(quantityJSpinner);

            JLabel deleteJLabel = new JLabel();
            deleteJLabel.setIcon(new ImageIcon("src/Payment-images/cancel.png"));
            add(deleteJLabel);
        }
    }

    public class shoppingCartTableJLabelRenderer extends DefaultTableCellRenderer{
        shoppingCartTableComponent tableComponent = new shoppingCartTableComponent("a",new ImageIcon("src/Home-Products/cheese.png"));
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            MyPanel component = new MyPanel(tableComponent);
            return component;
        }
    }

    public static void main (String arg[]) {
        JFrame frame = new shoppingCart("FoodApp-ShoppingCart");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
