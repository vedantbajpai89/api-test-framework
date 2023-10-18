package com.mindtickle.api.tests.users;

import com.mindtickle.api.builder.users.UserReqresApi;
import com.mindtickle.api.pojos.users.UserDetails;
import com.mindtickle.api.tests.users.testdata.UserTestData;
import com.mindtickle.assertions.ResponseAssert;
import com.mindtickle.config.factory.LoggerConfigFactory;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserTest {
  private static final Logger logger = LoggerConfigFactory.configureLogger();

  @Test
  void createNewUser() {
    logger.info("Starting the test...");
    List<UserDetails> userData = UserTestData.getDataForCreateUser();
    Response response = UserReqresApi.postUsers(userData);

    ResponseAssert.assertThat(response)
      .statusCodeIs(200)
      .hasKeyWithValue("message", "ok")
      .hasContentType("application/json")
      .assertAll();
    logger.info("Test Done");
  }

  @Test
  void updateExistingUser() {
    UserDetails userData = UserTestData.getDataForUpdateUser();
    Response response = UserReqresApi.updateUsers(userData);

    ResponseAssert.assertThat(response)
            .statusCodeIs(200)
            .hasContentType("application/json")
            .assertAll();
  }

  @Test
  void getUsersForUpdatedUsername() {
    UserDetails userData = UserTestData.getDataForUpdateUser();
    Response response = UserReqresApi.getUsersForUpdatedUsername(userData);

    ResponseAssert.assertThat(response)
            .statusCodeIs(200)
            .hasContentType("application/json")
            .assertAll();
  }

}
