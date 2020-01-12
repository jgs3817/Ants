package data_transfer;

import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
                //System.out.println(inputLine);
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
        //Next or prev button clicked
        //Database: antData
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

        //send current videoID and frame to server
        FBData sendFBData = new FBData();
        boolean fb = FBPanel.getFBState();
        int frameID = FBPanel.getFrameID();
        String videoID = MenuVideo.getVidID();
        FBData.setTempFrameID(frameID);
        //System.out.println("Frame: " + frameID);

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
                //System.out.println("Frame ID:" + dataFB.getFrameID());
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
        System.out.println("postLanding()");
        //transitioning button
        //Database: antData, frameID
        String videoID = panels.MenuVideo.getVidID();
        byte[] body = videoID.getBytes(StandardCharsets.UTF_8);

        HttpURLConnection conn = null;
        try{
            //change URL to correct page
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
                //System.out.println(inputLine);
                landingData = inputGson.fromJson(inputLine, data_transfer.LandingData.class);
                //System.out.println(landingData);
                //System.out.println("Video ID:");
                //System.out.println(landingData.getVideoID());
                //System.out.println("Frame ID:" + landingData.getFrameID());
                //System.out.println("Image Byte:");
                //System.out.println(landingData.getImageByte());
                //System.out.println("Ant Data");
                //System.out.println(landingData.getAntData());
                //System.out.println("Overlay Image Byte");
                //System.out.println(landingData.getOverlayImageByte());
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void postInit(){
        //Database: progress bar
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
        //System.out.println("Step 0");
        //get image from server
        try {
            //System.out.println("Step 0.1");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            //System.out.println("Step 0.2");
            String inputLine;
            //System.out.println("Step 0.3");
            while((inputLine = bufferedReader.readLine()) != null) {
                //System.out.println("Step 0.4");
                Gson inputGson = new Gson();
                //System.out.println("Step 0.5");
                initDataArrayList = inputGson.fromJson(inputLine, InitDataArrayList.class);
                //System.out.println("Step 1");
                //System.out.println(initDataArrayList);

                Gson initDataGson = new Gson();
                //System.out.println("Before getString");
                //System.out.println("Step 2");
                String JSonArray = initDataArrayList.getArrayJsonString().get(0);
                String JSonArray1 = initDataArrayList.getArrayJsonString().get(1);
                String JSonArray2 = initDataArrayList.getArrayJsonString().get(2);
                String JSonArray3 = initDataArrayList.getArrayJsonString().get(3);
                //System.out.println("Step 3");

                //System.out.println(JSonArray);
                initData1 = initDataGson.fromJson(JSonArray, InitData.class);
                initData2 = initDataGson.fromJson(JSonArray1, InitData.class);
                initData3 = initDataGson.fromJson(JSonArray2, InitData.class);
                initData4 = initDataGson.fromJson(JSonArray3, InitData.class);

                //System.out.println("Prog");
                //System.out.println(initData1.getProgress());
                //System.out.println("After getString");
                //initData2 = initDataGson.fromJson(initDataArrayList.getArrayJsonString().get(1), InitData.class);
                //initData3 = initDataGson.fromJson(initDataArrayList.getArrayJsonString().get(2), InitData.class);
                //initData4 = initDataGson.fromJson(initDataArrayList.getArrayJsonString().get(3), InitData.class);

                //System.out.println(initDataArrayList.getArrayJsonString().get(0));
                //System.out.println("Imagebyte1 postInit:");
                //System.out.println(initData1.getImageByte());

                //System.out.println("Imagebyte2:");
                //System.out.println(initData2.getImageByte());

//                System.out.println("Imagebyte3:");
//                System.out.println(initData3.getImageByte());

  //              System.out.println("Imagebyte4:");
//                System.out.println(initData4.getImageByte());
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("test3");
    }

    public static FBData getFBData(){
        return dataFB;
    }

    public static InitData getInitData1(){
        //System.out.println("getInitData1");
        //System.out.println(initData1);
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