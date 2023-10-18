package com.mindtickle.utils.fileutils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mindtickle.api.pojos.users.UserDetails;
import com.mindtickle.config.factory.TestDataConfigFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONFileWriter {

    public static void storeCreatedUserData(List<UserDetails> userData){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        for (UserDetails userDetails : userData) {
            String userDetailsJson = gson.toJson(userDetails);
            jsonArray.add(JsonParser.parseString(userDetailsJson));
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") +TestDataConfigFactory.getConfig().existingUserDataJSONPath()))){
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void storeUpdatedUserData(List<UserDetails> updatedUserData){
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        for (UserDetails userDetails : updatedUserData) {
            String userDetailsJson = gson.toJson(userDetails);
            jsonArray.add(JsonParser.parseString(userDetailsJson));
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") +TestDataConfigFactory.getConfig().updatedUserDataJSONPath()))){
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
