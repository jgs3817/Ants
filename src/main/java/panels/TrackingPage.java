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
        c.gridx = 0;
        c.gridy = 0;
        c.weightx=1;
        c.weighty=1;
        add(new ButtonIDContainer(),c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx=5;
        c.weighty=1;
        add(new VideoFramesContainer(),c);
    }
}