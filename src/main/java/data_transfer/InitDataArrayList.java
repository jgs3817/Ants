package data_transfer;

import java.io.Serializable;
import java.util.ArrayList;

/*
The InitDataArrayList class is used to:
Store InitData objects for initialisation of the LandingPage
*/

public class InitDataArrayList implements Serializable {

    private ArrayList<String> arrayJsonString = new ArrayList<String>();

    public void addInitData(String input){
        arrayJsonString.add(input);
    }

    public ArrayList<String> getArrayJsonString(){
        return arrayJsonString;
    }
}