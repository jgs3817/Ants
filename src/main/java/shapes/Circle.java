package shapes;

import java.awt.*;

/*
The shapes.Circle class encapsulates the information describing a circle and can draw it in
an AWT Graphics object using AWT library methods
*/

public class Circle extends Shape {
    private int rad; // Fields
    private String lastButton;
    private String text;

    public Circle(Point initPos, Color col, int radius, String id){
        super(initPos, col); // The constructor
        rad=radius; // Initialize fields
        text = id;
    }

    @Override
    public void draw(Graphics g) { // A method that draws the object in g

        if(g instanceof Graphics2D) {
            g.setColor(col);
            g.fillOval(pos.x, pos.y, rad, rad);
            g.setColor(new Color(0));

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setFont(new Font("default", Font.BOLD, 12));
            g2.drawString(text, pos.x + (int) (0.32 * rad), pos.y + (int) (0.7 * rad));
        }
    }
}