package shapes;

import java.awt.*;

public abstract class Shape {
    protected Point pos;
    protected Color col;

    public Shape(Point initPos, Color col) {
        pos=initPos;
        this.col=col;
    }

    public abstract void draw(Graphics g);
}