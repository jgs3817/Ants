package panels;

import javax.swing.*;
import java.awt.*;

/*
The panels.PageHandler class is a JFrame which is used to manage which pages
should be displayed in the JFrame
*/

public class PageHandler extends JFrame {
    static GraphicsConfiguration gc;

    public PageHandler(){
        setSize((new Dimension(1200,680)));
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}