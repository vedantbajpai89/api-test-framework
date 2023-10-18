package com.mindtickle.api.tests.users;

import com.mindtickle.api.builder.users.UserReqresApi;
import com.mindtickle.api.pojos.users.GetUpdatedUserResponseDetails;
import com.mindtickle.api.pojos.users.UserDetails;
import com.mindtickle.api.pojos.users.CreateOrUpdateUserResponseDetails;
import com.mindtickle.api.tests.users.testdata.UserTestData;
import com.mindtickle.assertions.ResponseAssert;
import com.mindtickle.config.factory.LoggerConfigFactory;
import com.mindtickle.utils.TestDataGenerator;
import com.mindtickle.utils.fileutils.JSONFileReader;
import com.mindtickle.utils.fileutils.JSONFileWriter;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class UserTest {
  private static final Logger logger = LoggerConfigFactory.configureLogger();

  @Test
  void createNewUserWithValidData() {
    try{
      List<UserDetails> userData = UserTestData.getDataForCreateUser();
      Response response = UserReqresApi.postUsers(userData);

      ResponseAssert.assertThat(response)
              .statusCodeIs(200)
              .canBeDeserializedTo(CreateOrUpdateUserResponseDetails.class)
              .hasKeyWithValue("message", "ok")
              .hasContentType("application/json")
              .assertAll();

      JSONFileWriter.storeCreatedUserData(userData);
    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
    }
  }

  @Test
  void updateExistingUserWithValidData() {
    try{
      List<String> existingUserNames = UserTestData.getAllExistingUserNames();
      List<UserDetails> updatedUserDetails = new ArrayList<>();
      for(String username : existingUserNames){
        UserDetails userDataToUpdate = UserTestData.getDataForUpdateUser();
        Response response = UserReqresApi.updateUsers(userDataToUpdate, username);
        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .hasContentType("application/json")
                .canBeDeserializedTo(CreateOrUpdateUserResponseDetails.class)
                .assertAll();

        updatedUserDetails.add(userDataToUpdate);
      }
      JSONFileWriter.storeUpdatedUserData(updatedUserDetails);

    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);

    }
  }

  @Test
  void getUsersForUpdatedUsernameWithValidData() {
    try{
      List<String> updatedUserNames = UserTestData.getAllUpdatedUserNames();

      for(String username : updatedUserNames){
        Response response = UserReqresApi.getUsersForUpdatedUsername(username);

        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .canBeDeserializedTo(GetUpdatedUserResponseDetails.class)
                .hasContentType("application/json")
                .assertAll();
      }


    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
    }
  }

  @Test
  void updateExistingUserWithInvalidData() {
    try{
      UserDetails userDataToUpdate = UserTestData.getDataForUpdateUser();
      Response response = UserReqresApi.updateUsers(userDataToUpdate, new TestDataGenerator().getUserName());
      ResponseAssert.assertThat(response)
              .statusCodeIs(404)
              .hasContentType("application/json")
              .canBeDeserializedTo(CreateOrUpdateUserResponseDetails.class)
              .assertAll();

    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);

    }
  }

  @Test
  void getUsersForUpdatedUsernameWithInvalidData() {
    try{

      Response response = UserReqresApi.getUsersForUpdatedUsername(new TestDataGenerator().getUserName());

      ResponseAssert.assertThat(response)
              .statusCodeIs(404)
              .hasContentType("application/json")
              .assertAll();

    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
    }
  }

}
