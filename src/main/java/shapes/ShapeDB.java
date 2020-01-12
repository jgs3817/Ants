package shapes;

import java.awt.*;
import java.util.ArrayList;

public class ShapeDB {
    private ArrayList<Shape> list;

    public ShapeDB(){
        list = new ArrayList<Shape>();
    }

    void addCircle(Point pos, Color col, int radius){
        list.add(new Circle(pos,col,radius));
    }

    void drawShapes(Graphics g){
        for(Shape i : list){
            i.draw(g);
        }
    }
}
