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
        setSize(250, 300);

        //setBackground(Color.orange);
        setSize(250, 300);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        FBPanel fbPanel = new FBPanel();

        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.FIRST_LINE_START;

        setGridBagLayout(c,0,0,1,6);
        add(fbPanel.returnVideoPanel(),c);

        setGridBagLayout(c,0,1,1,1);
        add(fbPanel, c);
    }

    private void setGridBagLayout(GridBagConstraints c, int gx, int gy, int wx, int wy){
        c.gridx = gx;
        c.gridy = gy;
        c.weightx = wx;
        c.weighty = wy;
    }
}