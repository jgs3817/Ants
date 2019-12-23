package panels;

import javax.swing.*;
import java.awt.*;

public class PageHandler extends JFrame {
    static GraphicsConfiguration gc;

    public PageHandler(){
        setSize((new Dimension(600,340)));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}