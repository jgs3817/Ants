package panels;

import data_transfer.FBData;
import data_transfer.TalkServlet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Video menu page: displays 4 video thumbnails for user to choose from.
// display user name, "start labelling" button that leads to the labelling window (Main).

public class LandingPage extends JPanel {
    private JLabel header;
    private JButton startButton;
    private static boolean landingFlag;

    public LandingPage() {
        landingFlag = false;
        //System.out.println("panels.LandingPage constructor called");
        setSize(500,300);
        //setLayout(new GridLayout(0,1));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        header = new JLabel("Choose a Video");
        add(header, c);
        header.setHorizontalAlignment(JLabel.CENTER);  // center align JLabel header

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 4;
        add(new MenuVideo(), c);

        c.gridx = 0;
        c.gridy = 7;
        c.weightx = 1;
        c.weighty = 1;
        startButton = new JButton("Start Tracking!");
        add(startButton, c);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TalkServlet.postLanding();
                landingFlag=true;
            }
        });
    }

    public static boolean getLandingFlag(){
        return landingFlag;
    }

    public static void setLandingFlag(boolean state){
        landingFlag = state;
    }
}