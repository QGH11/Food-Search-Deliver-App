import javax.swing.*;
import java.awt.*;

public class productListRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        // assign to value that is passed
        productListImgsNText is = (productListImgsNText) value;
        setText(is.getName());
        setIcon(is.getImg());

        // set background and foreground colors
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setEnabled(true);
        setFont(list.getFont());

        return this;
    }
}
