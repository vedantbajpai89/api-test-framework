package com.mindtickle.utils.fileutils;

import com.mindtickle.config.factory.TestDataConfigFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;

public class JSONFileReader {

    public static JSONArray readArrayDataFromJSONFile(String pathToFile){
        try {
            FileReader fileReader = new FileReader(System.getProperty("user.dir") + pathToFile);
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONArray userData  = new JSONArray(jsonTokener);
            fileReader.close();;
            return userData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject readObjectDataFromJSONFile(String pathToFile){
        try {
            FileReader fileReader = new FileReader(System.getProperty("user.dir") + pathToFile);
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONObject userData  = new JSONObject(jsonTokener);
            fileReader.close();;
            return userData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
