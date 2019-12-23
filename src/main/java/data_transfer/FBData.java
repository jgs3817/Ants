package data_transfer;

import java.util.ArrayList;

public class FBData {
    private ArrayList<ArrayList<Integer>> overlayAntData;
    private String videoID;
    private static int tempFrameID;
    private int frameID;
    private byte[] imageByte;
    private byte[] FBImageByte;
    private boolean fb;
    private boolean error = false;
    private byte[] overlayImageByte;

    public FBData(){
        tempFrameID = 1;
    }

    public ArrayList<ArrayList<Integer>> getOverlayAntDataAntData() {
        return overlayAntData;
    }

    public String getVideoID(){
        return videoID;
    }

    public static int getTempFrameID(){
        return tempFrameID;
    }

    public int getFrameID(){
        return frameID;
    }

    public void setFrameID(){
        this.frameID = getTempFrameID();
    }

    public boolean getFB(){
        return fb;
    }

    public byte [] getImageByte(){return imageByte;}

    public byte[] getFBImageByte(){
        return this.FBImageByte;
    }

    public void setVideoID(String videoID){
        this.videoID = videoID;
    }

    public static void setTempFrameID(int IDframe){
        tempFrameID = IDframe;
    }

    public void setFB(boolean fb){
        this.fb=fb;
    }

    public void setImageByte(byte[] imageByteInput){this.imageByte = imageByteInput;}

    public void setFBImageByte(byte[] imageByteInput){
        FBImageByte = imageByteInput;
    }

    public void setError(boolean errorInput){error = errorInput;}

    public boolean getError(){return error;}

    public byte [] getOverlayImageByte(){return overlayImageByte;}
}