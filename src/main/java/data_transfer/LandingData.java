package data_transfer;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
The LandingData class is used to:
Send data to the server about which video has been selected for labelling
Receive data about the last labelled frame, and the video's current
and overlay images to display in the TrackingPage
*/

public class LandingData {
    private ArrayList<ArrayList<Integer>> antData;
    private String videoID;
    private int frameID;
    private byte[] imageByte;

    public ArrayList<ArrayList<Integer>> getAntData() {
        return antData;
    }

    public String getVideoID() {
        return videoID;
    }

    public int getFrameID() {
        return frameID;
    }

    public byte[] getImageByte() {
        return this.imageByte;
    }

    public void setFrameID(int id){
        frameID = id;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public void setAntData(ArrayList<ArrayList<Integer>> antDataInput) {
        this.antData = new ArrayList<>(antDataInput.stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList()));
    }

    public void setImageByte(byte[] imageByteInput) {
        this.imageByte = imageByteInput;
    }
}