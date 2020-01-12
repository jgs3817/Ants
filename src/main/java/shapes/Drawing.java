package shapes;

import javax.swing.*;
import java.awt.*;

public class Drawing extends JPanel {
    private ShapeDB shapeDB = new ShapeDB();

    public Drawing(){
        setPreferredSize(new Dimension(494,271));
    }

    public void addCircle(Point pos, Color col, int radius){
        shapeDB.addCircle(pos, col, radius);
    }

    public void paint(Graphics g){
        shapeDB.drawShapes(g);
    }

}
