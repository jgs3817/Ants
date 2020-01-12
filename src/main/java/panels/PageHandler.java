package panels;

import javax.swing.*;
import java.awt.*;

public class PageHandler extends JFrame {
    static GraphicsConfiguration gc;

    public PageHandler(){
        setSize((new Dimension(1200,680)));
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}