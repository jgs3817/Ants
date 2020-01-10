package data_transfer;
import panels.FBPanel;
import panels.MenuVideo;
import panels.VideoPanel;

import java.io.Serializable;
import java.util.ArrayList;

/*
The data_transfer.SubmitData class is used to collect the necessary data to be sent
to the servlet
*/

public class SubmitData implements Serializable {
    private ArrayList<ArrayList<Integer>> antData;
    private String videoID;
    private int frameID;

    public SubmitData(){
        getData();
    }

    public void getData() {
        antData = VideoPanel.getAntData();       //gets the ArrayList antData from panels.VideoPanel
        videoID = MenuVideo.getVidID();
        frameID = FBPanel.getFrameID();
    }
}