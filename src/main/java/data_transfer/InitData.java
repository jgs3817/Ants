package data_transfer;

import java.io.Serializable;
import java.util.ArrayList;

/*
The InitData class is used to:
Receive data about the thumbnails of each video option in the video menu,
and the labelling progress of each video
*/

public class InitData implements Serializable {
    private String videoID;
    private ArrayList<Integer> progress = new ArrayList<Integer>();
    private byte [] imageByte;


    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public void setProgress(int input){
        this.progress.add(input);
    }

    public void setImageByte(byte [] imageByteInput) {
        this.imageByte = imageByteInput;
    }

    public String getVideoID() {
        return videoID;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public ArrayList<Integer> getProgress() {
        return progress;
    }

    public void printInitData(){
        System.out.println("VideoID");
        System.out.println(this.videoID);
        System.out.println("Progress");
        System.out.println(this.progress.get(0));
        System.out.println(this.progress.get(1));
        System.out.println("Image Byte");
        System.out.println(this.imageByte);
    }
}