package panels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.*;

/*
The panels.ProgressBar is a JPanel which is used to generate the progress bars
*/

public class ProgressBar extends JPanel{

    public ProgressBar(int maxframe, int latestframe){

        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(maxframe);
        progressBar.setValue(latestframe);
        progressBar.setStringPainted(true);

        add(progressBar);
    }
}