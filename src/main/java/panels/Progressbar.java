package panels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.*;

public class Progressbar extends JPanel{
    public Progressbar(int maxframe, int latestframe){
        // JFrame f = new JFrame("JProgressBar Sample");
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Container content = f.getContentPane();

        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(maxframe);
        progressBar.setValue(latestframe);
        progressBar.setStringPainted(true);
        // Border border = BorderFactory.createTitledBorder("Reading...");
        // progressBar.setBorder(border);

        add(progressBar);
        //f.setSize(300, 100);
        //f.setVisible(true);
        //setSize(200,200);
    }
}


