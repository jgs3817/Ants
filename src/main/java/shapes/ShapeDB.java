package shapes;

import java.awt.*;
import java.util.ArrayList;

/*
The shapes.ShapeDB class is used to add shapes to an ArrayList to be drawn
*/

public class ShapeDB {
    private ArrayList<Shape> list;

    public ShapeDB(){
        list = new ArrayList<Shape>();
    }

    void addCircle(Point pos, Color col, int radius, String id){
        list.add(new Circle(pos,col,radius,id));
    }

    void drawShapes(Graphics g){
        for(Shape i : list){
            i.draw(g);
        }
    }
}
