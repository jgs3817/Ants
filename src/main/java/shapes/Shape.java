package shapes;

import java.awt.*;

/*
The shapes.Shape class is used to determine the position and colour of a shape
In future development, shapes other than circles can be used
*/

public abstract class Shape {
    protected Point pos;
    protected Color col;

    public Shape(Point initPos, Color col) {
        pos=initPos;
        this.col=col;
    }

    public abstract void draw(Graphics g);
}