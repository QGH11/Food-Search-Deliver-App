import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class shoppingCart extends JFrame {

    private JPanel mainPanel;

    public shoppingCart(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        ImageIcon img = new ImageIcon("D://Java//GUI//FoodApp//src//Home-images//unicorn.png");
        this.setIconImage(img.getImage());
        this.pack();
    }

    public static void main (String arg[]) {
        JFrame frame = new shoppingCart("FoodApp-ShoppingCart");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
