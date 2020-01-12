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
The data_transfer.TalkServlet class is used to establish a connection with the
servlet and to submit the ant data to the servlet using POST
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
        //System.out.println("Sending frame: " + frameID);
        String videoID = MenuVideo.getVidID();
        FBData.setTempFrameID(frameID);

        sendFBData.setFB(fb);
        sendFBData.setFrameID();
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
                //System.out.println("Receiving frame: " + dataFB.getFrameID());
                if(dataFB.getFB()){
                    int frame = dataFB.getFrameID();
                    dataFB.setTempFrameID(frame+1);
                }
                else{
                    int frame = dataFB.getFrameID();
                    dataFB.setTempFrameID(frame-1);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FBState=true;
    }

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

    public static boolean getFBState(){
        return FBState;
    }

    public static void setFBState(boolean state){
        FBState = state;
    }

    public TalkServlet(){
    }
}