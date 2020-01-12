package panels;

import data_transfer.FBData;
import data_transfer.TalkServlet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
The panels.LandingPage class is a JPanel which is a page containing
the menu of videos and a Start button to go to the TrackingPage
*/

public class LandingPage extends JPanel {
    private JLabel header;                  //To display "Select a video"
    private JButton startButton;            //On click, go to TrackingPage
    private static boolean landingFlag;     //False: do not display TrackingPage; True: display TrackingPage

    public LandingPage() {
        landingFlag = false;
        setSize(500,300);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();    //GridBag layout
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        setGridBagLayout(c,0,0,1,1);       //Setting GridBag layout
        header = new JLabel("Select a video");
        header.setFont(new Font("default", Font.BOLD, 24));
        add(header, c);
        header.setHorizontalAlignment(JLabel.CENTER);       //center align header

        setGridBagLayout(c,0,2,1,4);
        add(new MenuVideo(), c);

        startButton = new JButton("Start Tracking!");
        setGridBagLayout(c,0,7,1,3);
        add(startButton, c);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Informs server to transition from LandingPage to TrackingPage
                TalkServlet.postLanding();
                landingFlag=true;
            }
        });
    }

    private void setGridBagLayout(GridBagConstraints c, int gx, int gy, int wx, int wy){
        c.gridx = gx;
        c.gridy = gy;
        c.weightx = wx;
        c.weighty = wy;
    }

    //True when start button has been clicked. Used for the management of pages
    public static boolean getLandingFlag(){
        return landingFlag;
    }

    //Used for the management of pages
    public static void setLandingFlag(boolean state){
        landingFlag = state;
    }
}