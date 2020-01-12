package data_transfer;
import panels.FBPanel;
import panels.MenuVideo;
import panels.VideoPanel;

import java.io.Serializable;
import java.util.ArrayList;

/*
The SubmitData class is used to:
Send data to the server about the ants' coordinates
*/

public class SubmitData implements Serializable {
    private ArrayList<ArrayList<Integer>> antData;
    private String videoID;
    private int frameID;

    public SubmitData(){
        getData();
    }

    public void getData() {
        antData = VideoPanel.getAntData();
        if(antData.get(0).get(0)==0){
            antData.remove(0);
        }
        videoID = MenuVideo.getVidID();
        frameID = FBPanel.getFrameID();
    }

    public ArrayList<ArrayList<Integer>> getAntData(){
        return antData;
    }
}