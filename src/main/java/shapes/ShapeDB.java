package shapes;

import java.awt.*;
import java.util.ArrayList;

public class ShapeDB {
    private ArrayList<Shape> shapes;

    public ShapeDB(){
        shapes = new ArrayList<Shape>();
    }

    public void addCircle(Point pos,Color col, int radius){
        shapes.add(new Circle(pos,col,radius));
    }

    public void drawShapes(Graphics g){
            for(Shape i:shapes) {
            i.draw(g);
        }
    }
}
