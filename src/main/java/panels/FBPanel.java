package panels;

import data_transfer.FBData;
import data_transfer.LandingData;
import data_transfer.TalkServlet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
The panels.FBPanel class is a JPanel that contains 3 buttons:
    1. Forward button to go to next frame of the video
    2. Back button to go to the previous frame of the video
    3. Submit button to submit the data to the servlet
*/

public class FBPanel extends JPanel {
    private JButton nextButton;      //button to go to next frame
    private JButton prevButton;         //button to go to previous frame
    private JButton submitButton;       //submits the ants coordinates
    private VideoPanel videoPanel;
    private static boolean fb;          //false for previous button and true for next button
    private static int frameID;
    private LandingData landingData;

    public FBPanel(){
        //System.out.println("panels.FBPanel constructor called");
        videoPanel = new VideoPanel();
        setLayout(new GridLayout(1,3));
        nextButton = new JButton("Next");
        prevButton = new JButton("Prev");
        submitButton = new JButton("Submit");
        add(prevButton);
        add(nextButton);
        add(submitButton);

        landingData = TalkServlet.getLandingData();
        frameID = landingData.getFrameID();

        System.out.println("Get Landing: " + frameID);

        submitButton.addActionListener(new ActionListener() {
            //When submitButton is clicked, submit data to servlet
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TalkServlet.postSubmit();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            //When submitButton is clicked, submit data to servlet
            @Override
            public void actionPerformed(ActionEvent e) {
                //send servlet the current frame and video
                fb=true;
                frameID++;
                //FBData.setFrameID(FBData.getFrameID()+1);
                data_transfer.TalkServlet.postFB();
                videoPanel.loadFrame();
            }
        });

        prevButton.addActionListener(new ActionListener() {
            //When submitButton is clicked, submit data to servlet
            @Override
            public void actionPerformed(ActionEvent e) {
                //send servlet the current frame and video
                fb=false;
                frameID--;
                data_transfer.TalkServlet.postFB();
                videoPanel.loadFrame();
            }
        });
    }

    public static boolean getFBState(){
        return fb;
    }

    public VideoPanel returnVideoPanel(){
        return videoPanel;
    }

    public static int getFrameID(){
        return frameID;
    }
}