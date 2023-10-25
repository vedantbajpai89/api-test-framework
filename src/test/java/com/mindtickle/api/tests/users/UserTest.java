package com.mindtickle.api.tests.users;

import com.mindtickle.api.builder.users.UserReqresApi;
import com.mindtickle.api.pojos.users.GetUpdatedUserResponseDetails;
import com.mindtickle.api.pojos.users.UserDetails;
import com.mindtickle.api.pojos.users.CreateOrUpdateUserResponseDetails;
import com.mindtickle.api.tests.BaseTest;
import com.mindtickle.api.tests.users.testdata.UserTestData;
import com.mindtickle.assertions.ResponseAssert;
import com.mindtickle.config.factory.LoggerConfigFactory;
import com.mindtickle.reports.ExtentReport;
import com.mindtickle.utils.TestDataGenerator;
import com.mindtickle.utils.fileutils.JSONFileWriter;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UserTest extends BaseTest {
  private static final Logger logger = LoggerConfigFactory.configureLogger();

  @Test
  void createNewUserWithValidData() {
    ExtentReport.createTest("Create User Test with Valid Data");
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
      ExtentReport.attachLogs(userData.toString(), response.asString());

    } catch (AssertionError e){
      ExtentReport.attachFail(e.getMessage());
    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
      ExtentReport.attachFail(e.getMessage());
    }
  }

  @Test
  void updateExistingUserWithValidData() {
    ExtentReport.createTest("Update User Test with Valid Data");
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
        ExtentReport.attachLogs(userDataToUpdate.toString(), response.asString());
      }
      JSONFileWriter.storeUpdatedUserData(updatedUserDetails);


    } catch (AssertionError e){
      ExtentReport.attachFail(e.getMessage());
    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
      ExtentReport.attachFail(e.getMessage());
    }
  }

  @Test
  void getUsersForUpdatedUsernameWithValidData() {
    ExtentReport.createTest("Get User Test with Valid Data");
    try{
      List<String> updatedUserNames = UserTestData.getAllUpdatedUserNames();

      for(String username : updatedUserNames){
        Response response = UserReqresApi.getUsersForUpdatedUsername(username);

        ResponseAssert.assertThat(response)
                .statusCodeIs(200)
                .canBeDeserializedTo(GetUpdatedUserResponseDetails.class)
                .hasContentType("application/json")
                .assertAll();

        ExtentReport.attachLogs(username, response.asString());
      }


    } catch (AssertionError e){
      ExtentReport.attachFail(e.getMessage());
    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
      ExtentReport.attachFail(e.getMessage());
    }
  }

  @Test
  void updateExistingUserWithInvalidData() {
    ExtentReport.createTest("Update Existing User Test with InValid Data");
    try{
      UserDetails userDataToUpdate = UserTestData.getDataForUpdateUser();
      Response response = UserReqresApi.updateUsers(userDataToUpdate, new TestDataGenerator().getUserName());
      ResponseAssert.assertThat(response)
              .statusCodeIs(404)
              .hasContentType("application/json")
              .canBeDeserializedTo(CreateOrUpdateUserResponseDetails.class)
              .assertAll();
      ExtentReport.attachLogs(userDataToUpdate.toString(), response.asString());

    }
    catch (AssertionError e){
      ExtentReport.attachFail(e.getMessage());
    }
    catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
      ExtentReport.attachFail(e.getMessage());
    }
  }

  @Test
  void getUsersForUpdatedUsernameWithInvalidData() {
    ExtentReport.createTest("Update Username Test with InValid Data");
    try{
      String username = new TestDataGenerator().getUserName();
      Response response = UserReqresApi.getUsersForUpdatedUsername(username);

      ResponseAssert.assertThat(response)
              .statusCodeIs(404)
              .hasContentType("application/json")
              .assertAll();
      ExtentReport.attachLogs(username, response.asString());

    } catch (AssertionError e){
      ExtentReport.attachFail(e.getMessage());
    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
      ExtentReport.attachFail(e.getMessage());
    }
  }

}
