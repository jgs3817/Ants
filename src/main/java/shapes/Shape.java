package shapes;

import java.awt.*;

public abstract class Shape {
    public Point pos;
    public Color col;

    public Shape(Point initPos,Color col) {
        pos=initPos;
        this.col=col;
    }

    abstract void draw(Graphics g);
}