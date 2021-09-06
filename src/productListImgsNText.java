import javax.swing.*;

//model for custom row in a Jlist
public class productListImgsNText {
    private String name;
    private Icon img;


    public productListImgsNText(String text, Icon icon) {
        this.name = text;
        this.img = icon;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getImg() {
        return img;
    }

    public void setImg(Icon img) {
        this.img = img;
    }
}

