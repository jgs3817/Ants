package data_transfer;

import com.google.gson.Gson;
import panels.FBPanel;
import panels.MenuVideo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/*
The data_transfer.TalkServlet class is used to establish a connection with the
servlet and to submit the ant data to the servlet using POST
*/

public class TalkServlet {
    private static FBData dataFB;
    private static InitData initData;
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
                System.out.println(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void postSubmit() throws IOException {
        //Submit button
        SubmitData submitData = new SubmitData();
        Gson gson = new Gson();
        String jsonString = gson.toJson(submitData);
        byte[] body = jsonString.getBytes(StandardCharsets.UTF_8);

        HttpURLConnection conn = null;
        try{
            URL myURL = new URL("http://localhost:8080/AntsServlet/submitpage");
            //URL myURL = new URL("http://servletants.herokuapp.com/submitpage");

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
        //Next or prev button clicked
        //Database: antData
        HttpURLConnection conn = null;
        try{
            URL myURL = new URL("http://localhost:8080/AntsServlet/FBpage");
            conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //send current videoID and frame to server
        FBData sendFBData = new FBData();
        boolean fb = FBPanel.getFBState();
        int frameID = FBPanel.getFrameID();
        String videoID = MenuVideo.getVidID();
        FBData.setTempFrameID(frameID);
        System.out.println("Frame: " + frameID);

        sendFBData.setFB(fb);
        sendFBData.setFrameID();
        sendFBData.setVideoID(videoID); //servlet need to change filepath

        //System.out.println("FB state:");
        //System.out.println(sendFBData.getFB());
        //System.out.println("Frame ID:");
        //System.out.println(sendFBData.getFrameID());
        //System.out.println("Vid ID:");
        //System.out.println(sendFBData.getVideoID());

        Gson sendGson = new Gson();
        String jsonString = sendGson.toJson(sendFBData);
        byte[] body = jsonString.getBytes(StandardCharsets.UTF_8);

        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body,0,body.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get videoID and frame from server
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            while((inputLine = bufferedReader.readLine()) != null) {
                Gson inputGson = new Gson();
                dataFB = inputGson.fromJson(inputLine, FBData.class);
                /*System.out.println("Ant data:");
                System.out.println(dataFB.getAntData());*/
                //System.out.println("Video ID:");
                //System.out.println(dataFB.getVideoID());
                //System.out.println("Frame ID:");
                //System.out.println(dataFB.getFrameID());
                //System.out.println("Image Byte:");
                //System.out.println(Arrays.toString(dataFB.getImageByte()));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FBState=true;
    }

    public static void postLanding(){
        //transitioning button
        //Database: antData, frameID
        String videoID = panels.MenuVideo.getVidID();
        byte[] body = videoID.getBytes(StandardCharsets.UTF_8);

        HttpURLConnection conn = null;
        try{
            //change URL to correct page
            URL myURL = new URL("http://localhost:8080/AntsServlet/landingpage");
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
                landingData = inputGson.fromJson(inputLine, data_transfer.LandingData.class);
                //System.out.println("Ant data:");
                //System.out.println(landingData.getAntData());
                //System.out.println("Video ID:");
                //System.out.println(landingData.getVideoID());
                //System.out.println("Frame ID doPost:" + landingData.getFrameID());
                //System.out.println("Image Byte:");
                //System.out.println(landingData.getImageByte());
                //System.out.println("Overlay Ant Data");
                //System.out.println(landingData.getOverlayAntData());
                //System.out.println("Overlay Image Byte");
                //System.out.println(landingData.getOverlayImageByte());
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void postInit(){/*
        //Database: progress bar
        HttpURLConnection conn = null;
        try{
            URL myURL = new URL("http://localhost:8080/AntsServlet/init");
            conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //get image from server
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            while((inputLine = bufferedReader.readLine()) != null) {
                Gson inputGson = new Gson();
                initData = inputGson.fromJson(inputLine, InitData.class);
                /*System.out.println("Ant data:");
                System.out.println(dataFB.getAntData());*/
        //System.out.println("Video ID:");
        //System.out.println(dataFB.getVideoID());
        //System.out.println("Frame ID:");
        //System.out.println(dataFB.getFrameID());
        //System.out.println("Image Byte:");
        //System.out.println(Arrays.toString(dataFB.getImageByte()));
            /*}
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String initString = "Hi init page";
        byte[] body = initString.getBytes(StandardCharsets.UTF_8);
        data_transfer.InitDataArrayList initDataArrayList = new data_transfer.InitDataArrayList();
        HttpURLConnection conn = null;
        try{
            //change URL to correct page
            URL myURL = new URL("http://localhost:8080/AntsServlet/init");
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
                initDataArrayList = inputGson.fromJson(inputLine, data_transfer.InitDataArrayList.class);
            }
            System.out.println(initDataArrayList);
            ArrayList<String> jsonStringArray = new ArrayList<String>();
            jsonStringArray = initDataArrayList.getArrayJsonString();
            for(String i : jsonStringArray){
                Gson inputGson = new Gson();
                data_transfer.InitData initData = inputGson.fromJson(i, data_transfer.InitData.class);
                initData.printInitData();
            }
            //System.out.println(initDataArrayList.get(0).getVideoID());
            System.out.println("test1");
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static FBData getFBData(){
        return dataFB;
    }

    public static InitData getInitData(){
        return initData;
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