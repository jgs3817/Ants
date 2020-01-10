package shapes;

import javax.swing.*;
import java.awt.*;

public class Drawing extends JPanel {
    private ShapeDB shapeDB;

    public Drawing(){
        setPreferredSize(new Dimension(494, 271));
        setBackground(new Color(0,0, 0, 50));
        //setOpaque(false);
        shapeDB = new ShapeDB();
    }

    public void addCircle(Point pos, Color col, int rad){
        shapeDB.addCircle(pos, col, rad);
    }

    public void paint(Graphics g){
        shapeDB.drawShapes(g);
    }

}
