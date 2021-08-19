import javax.swing.*;

public class Home extends JFrame {
    private JPanel homePanel;

    public Home(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(homePanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Home("FoodApp-Home");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
