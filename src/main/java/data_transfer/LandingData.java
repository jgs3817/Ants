package data_transfer;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

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