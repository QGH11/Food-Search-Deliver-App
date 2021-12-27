import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;

public class shoppingCartTableComponent {
    private String name;
    private Icon img;

    public shoppingCartTableComponent(String name, Icon img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public Icon getImg() {
        return img;
    }
}
