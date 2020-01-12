package panels;

import panels.FBPanel;

import javax.swing.*;
import java.awt.*;

/*
The panels.VideoFramesContainer class is a JPanel that is used to set the layout of
the frames of the video (panels.VideoPanel) and the forward, backward and submit
buttons (panels.FBPanel)
*/

public class VideoFramesContainer extends JPanel {

    public VideoFramesContainer(){
        //System.out.println("Video frames container constructor called");
        //setBackground(Color.red);
        setSize(250, 300);

        //setBackground(Color.orange);
        setSize(250, 300);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        FBPanel fbPanel = new FBPanel();

        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx=1;
        c.weighty=6;
        add(fbPanel.returnVideoPanel(),c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty=1;
        c.gridx = 0;
        c.gridy = 1;
        add(fbPanel, c);
    }
}