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