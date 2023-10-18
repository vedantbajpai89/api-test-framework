package com.mindtickle.api.tests.users.testdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtickle.api.pojos.users.UserDetails;
import com.mindtickle.utils.TestDataGenerator;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public final class UserTestData {
  private UserTestData() {
  }

  @SneakyThrows
  public static List<UserDetails> getDataForCreateUser() {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<UserDetails>> typeReference = new TypeReference<>() {};
    List<UserDetails> userRequestBody = mapper.readValue(new File(System.getProperty("user.dir") + "/src/test/java/com/mindtickle/api/tests/users/testdata/json/create-user.json"), typeReference);
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
            .readValue(new File(System.getProperty("user.dir") + "/src/test/java/com/mindtickle/api/tests/users/testdata/json/update-user.json"), UserDetails.class)
            .setId(data.getId())
            .setUsername(data.getUserName())
            .setFirstName(data.getFirstName())
            .setLastName(data.getLastName())
            .setEmail(data.getEmail())
            .setUserStatus(data.getUserStatus())
            .setPassword(data.getPassword())
            .setPhone(data.getPhone());

  }
}
