package panels;

import panels.ButtonIDContainer;

import javax.swing.*;
import java.awt.*;

/*
The panels.TrackingPage class is used to create a JFrame which has the main container
for holding the panels.ButtonIDContainer and panels.VideoFramesContainer
*/

public class TrackingPage extends JPanel{

    public TrackingPage() {
        //System.out.println("panels.TrackingPage constructor called");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        setGridBagLayout(c,0,0,1,1);
        add(new ButtonIDContainer(),c);

        setGridBagLayout(c,1,0,5,1);
        add(new VideoFramesContainer(),c);
    }

    public void setGridBagLayout(GridBagConstraints c, int gx, int gy, int wx, int wy){
        c.gridx = gx;
        c.gridy = gy;
        c.weightx = wx;
        c.weighty = wy;
    }
}