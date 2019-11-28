import javax.swing.*;
import java.awt.*;

public class Main {
    static GraphicsConfiguration gc; // Class field containing config info

    public static void main(String[] args) {

        ConnectToDB conn = new ConnectToDB();

        JFrame frame = new JFrame(gc); // Create a new JFrame
        frame.setSize(500, 300);
        JPanel mainContainer = new JPanel();

        mainContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx=1;
        c.weighty=1;
        mainContainer.add(new ButtonIDContainer(),c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx=5;
        c.weighty=1;
        mainContainer.add(new VideoFramesContainer(),c);

        frame.add(mainContainer);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
