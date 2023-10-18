package com.mindtickle.api.tests.users.testdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtickle.api.pojos.users.UserDetails;
import com.mindtickle.config.factory.TestDataConfigFactory;
import com.mindtickle.utils.TestDataGenerator;
import com.mindtickle.utils.fileutils.JSONFileReader;
import lombok.SneakyThrows;
import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class UserTestData {
  private UserTestData() {
  }

  @SneakyThrows
  public static List<UserDetails> getDataForCreateUser() {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<UserDetails>> typeReference = new TypeReference<>() {};
    List<UserDetails> userRequestBody = mapper.readValue(new File(System.getProperty("user.dir") + TestDataConfigFactory.getConfig().createUserSchemaJSONPath()), typeReference);
    TestDataGenerator data = new TestDataGenerator();
    for(UserDetails userDetails :userRequestBody){
        userDetails.setId(data.getId())
            .setUsername(data.getUserName())
            .setFirstName(data.getFirstName())
            .setLastName(data.getLastName())
            .setEmail(data.getEmail())
            .setUserStatus(data.getUserStatus())
            .setPassword(data.getPassword())
            .setPhone(data.getPhone());
    }
    return userRequestBody;
  }

  @SneakyThrows
  public static UserDetails getDataForUpdateUser() {
    TestDataGenerator data = new TestDataGenerator();
    return new ObjectMapper()
            .readValue(new File(System.getProperty("user.dir") + TestDataConfigFactory.getConfig().updateUserSchemaJSONPath()), UserDetails.class)
            .setId(data.getId())
            .setUsername(data.getUserName())
            .setFirstName(data.getFirstName())
            .setLastName(data.getLastName())
            .setEmail(data.getEmail())
            .setUserStatus(data.getUserStatus())
            .setPassword(data.getPassword())
            .setPhone(data.getPhone());

  }

  public static List<String> getAllExistingUserNames() {
    JSONArray existingUserData = JSONFileReader.readArrayDataFromJSONFile(TestDataConfigFactory.getConfig().existingUserDataJSONPath());
    List<String> usernameList = new ArrayList<>();
    for(int index = 0; index < existingUserData.length(); index++){
      String username = existingUserData.getJSONObject(index).getString("username");
      usernameList.add(username);
    }
    return usernameList;
  }

  public static List<String> getAllUpdatedUserNames() {
    JSONArray existingUserData = JSONFileReader.readArrayDataFromJSONFile(TestDataConfigFactory.getConfig().updatedUserDataJSONPath());
    List<String> usernameList = new ArrayList<>();
    for(int index = 0; index < existingUserData.length(); index++){
      String username = existingUserData.getJSONObject(index).getString("username");
      usernameList.add(username);
    }
    return usernameList;
  }
}
