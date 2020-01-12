package data_transfer;

import com.google.gson.Gson;
import panels.FBPanel;
import panels.MenuVideo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/*
The TalkServlet class is used to establish a client-server connection
to send requests to the server and receive responses from the server
*/

public class TalkServlet {
    private static FBData dataFB;
    private static InitData initData1;
    private static InitData initData2;
    private static InitData initData3;
    private static InitData initData4;
    private static InitDataArrayList initDataArrayList;
    private static LandingData landingData;
    private static boolean FBState;

    static void makeGetRequest(){
        try {
            URL myURL = new URL("http://localhost:8080/AntsServlet/mainpage");
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");

            BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null){
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //To submit the coordinates of individual ants to the server
    public static void postSubmit() throws IOException {
        SubmitData submitData = new SubmitData();
        Gson gson = new Gson();
        String jsonString = gson.toJson(submitData);
        byte[] body = jsonString.getBytes(StandardCharsets.UTF_8);

        HttpURLConnection conn = null;
        try{
            //URL myURL = new URL("http://localhost:8080/AntsServlet/submitpage");
            URL myURL = new URL("http://servletants.herokuapp.com/submitpage");

            conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body,0,body.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the body of the request
        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body, 0, body.length);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
    }

    //To inform the server that the "Next" or "Previous" button has been clicked
    //Requests the next video frames to be displayed, along with ant coordinates
    public static void postFB(){
        HttpURLConnection conn = null;
        try{
            //URL myURL = new URL("http://localhost:8080/AntsServlet/FBpage");
            URL myURL = new URL("http://servletants.herokuapp.com/fbpage");

            conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FBData sendFBData = new FBData();
        boolean fb = FBPanel.getFBState();
        int frameID = FBPanel.getFrameID();
        String videoID = MenuVideo.getVidID();

        sendFBData.setFB(fb);
        sendFBData.setFrameID(frameID);
        sendFBData.setVideoID(videoID);

        Gson sendGson = new Gson();
        String jsonString = sendGson.toJson(sendFBData);
        byte[] body = jsonString.getBytes(StandardCharsets.UTF_8);

        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body,0,body.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            while((inputLine = bufferedReader.readLine()) != null) {
                Gson inputGson = new Gson();
                dataFB = inputGson.fromJson(inputLine, FBData.class);
                if(dataFB.getFB()){
                    int frame = dataFB.getFrameID();
                    frame = frame+1;
                    dataFB.setFrameID(frame);
                }
                else{
                    int frame = dataFB.getFrameID();
                    frame = frame-1;
                    dataFB.setFrameID(frame);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FBState=true;
    }

    //To initialise the first frame of the TrackingPage along with the labelled ant coordinates, if any
    public static void postLanding(){
        String videoID = panels.MenuVideo.getVidID();
        byte[] body = videoID.getBytes(StandardCharsets.UTF_8);

        HttpURLConnection conn = null;
        try{
            //URL myURL = new URL("http://localhost:8080/AntsServlet/landingpage");
            URL myURL = new URL("http://servletants.herokuapp.com/landingpage");
            conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body,0,body.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            while((inputLine = bufferedReader.readLine()) != null) {
                Gson inputGson = new Gson();
                landingData = inputGson.fromJson(inputLine, LandingData.class);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //To initialise the thumbnails of the videos and their progress in the LandingPage
    public static void postInit(){
        HttpURLConnection conn = null;
        try{
            //URL myURL = new URL("http://localhost:8080/AntsServlet/init");
            URL myURL = new URL("http://servletants.herokuapp.com/init");
            conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            while((inputLine = bufferedReader.readLine()) != null) {
                Gson inputGson = new Gson();
                initDataArrayList = inputGson.fromJson(inputLine, InitDataArrayList.class);

                Gson initDataGson = new Gson();
                String JSonArray = initDataArrayList.getArrayJsonString().get(0);
                String JSonArray1 = initDataArrayList.getArrayJsonString().get(1);
                String JSonArray2 = initDataArrayList.getArrayJsonString().get(2);
                String JSonArray3 = initDataArrayList.getArrayJsonString().get(3);

                initData1 = initDataGson.fromJson(JSonArray, InitData.class);
                initData2 = initDataGson.fromJson(JSonArray1, InitData.class);
                initData3 = initDataGson.fromJson(JSonArray2, InitData.class);
                initData4 = initDataGson.fromJson(JSonArray3, InitData.class);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FBData getFBData(){
        return dataFB;
    }

    public static InitData getInitData1(){
        return initData1;
    }

    public static InitData getInitData2(){
        return initData2;
    }

    public static InitData getInitData3(){
        return initData3;
    }

    public static InitData getInitData4(){
        return initData4;
    }

    public static LandingData getLandingData(){
        return landingData;
    }

    public TalkServlet(){
    }
}