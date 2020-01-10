package shapes;

import java.awt.*;
/**
 * The shapes.Circle class encapsulates the information describing a circle and can draw it in
 * an AWT Graphics object using AWT library methods
 * */
public class Circle extends Shape {
    private int rad; // Fields

    public Circle(Point initPos, Color col, int radius){
        super(initPos, col); // The constructor
        rad=radius; // Initialize fields
    }

    @Override
    public void draw(Graphics g) { // A method that draws the object in g
        g.setColor(col);
        g.fillOval(pos.x,pos.y,rad, rad);
    }
}