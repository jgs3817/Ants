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
    private JButton nextButton;         //button to go to next frame
    private JButton prevButton;         //button to go to previous frame
    private JButton submitButton;       //submits the ants coordinates
    private VideoPanel videoPanel;
    private static boolean fb;          //false for previous button and true for next button
    private static int frameID;
    private LandingData landingData;
    private FBData dataFB;

    public FBPanel(){
        videoPanel = new VideoPanel();
        dataFB = TalkServlet.getFBData();
        setLayout(new GridLayout(1,3));
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        submitButton = new JButton("Submit");
        add(prevButton);
        add(nextButton);
        add(submitButton);

        landingData = TalkServlet.getLandingData();
        frameID = landingData.getFrameID();

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
            //When nextButton is clicked, inform server and increment frameID
            @Override
            public void actionPerformed(ActionEvent e) {
                //send server the current frame
                fb=true;
                data_transfer.TalkServlet.postFB();
                frameID++;                                  //increments frameID when next button is clicked
                videoPanel.loadFrame();                     //refreshes the video panel to update the displayed frame of the video
            }
        });

        prevButton.addActionListener(new ActionListener() {
            //When prevButton is clicked, inform server and decrement frameID
            @Override
            public void actionPerformed(ActionEvent e) {
                //send server the current frame
                fb=false;
                data_transfer.TalkServlet.postFB();
                frameID--;                                  //decrements frameID when previous button is clicked
                videoPanel.loadFrame();                     //refreshes the video panel to update the displayed frame of the video
            }
        });
    }

    //Inform server whether the "Next" or "Previous" button is clicked
    public static boolean getFBState(){
        return fb;
    }

    //Allows the instance of video panel to be accessed in other classes
    public VideoPanel returnVideoPanel(){
        return videoPanel;
    }

    //returns current frameID
    public static int getFrameID(){
        return frameID;
    }
}