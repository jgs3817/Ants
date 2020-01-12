package shapes;

import javax.swing.*;
import java.awt.*;

/*
The shapes.Drawing class is a JPanel where the visual feedback is drawn on
*/

public class Drawing extends JPanel {
    private ShapeDB shapeDB = new ShapeDB();

    public Drawing(){
        setPreferredSize(new Dimension(1070,598));
    }

    public void addCircle(Point pos, Color col, int radius, String id){
        shapeDB.addCircle(pos, col, radius, id);
    }

    public void paint(Graphics g){
        shapeDB.drawShapes(g);
    }

}
