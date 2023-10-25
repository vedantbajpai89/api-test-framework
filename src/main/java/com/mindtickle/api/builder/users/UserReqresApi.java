package com.mindtickle.api.builder.users;

import com.aventstack.extentreports.ExtentTest;
import com.mindtickle.api.BaseRequestSpecification;
import com.mindtickle.api.pojos.users.UserDetails;
import com.mindtickle.config.factory.ApiConfigFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public final class UserReqresApi {

  private UserReqresApi() {
  }
  public  static ExtentTest test;

  private static final String GET_USERS_ENDPOINT = ApiConfigFactory.getConfig().listUserEndpoint();
  private static final String CREATE_USERS_ENDPOINT = ApiConfigFactory.getConfig().postUserEndpoint();
  private static final String UPDATE_USERS_ENDPOINT = ApiConfigFactory.getConfig().updateUserEndpoint();

  public static Response getUsersForUpdatedUsername(String userName) {
    return BaseRequestSpecification.getDefaultRequestSpec()
      .get(GET_USERS_ENDPOINT+"/"+userName);
  }

  public static Response postUsers(List<UserDetails> userDetails) {
    return BaseRequestSpecification.getDefaultRequestSpec()
      .contentType(ContentType.JSON)
      .body(userDetails)
      .post(CREATE_USERS_ENDPOINT);
  }

  public static Response updateUsers(UserDetails userDetails, String username) {
    return BaseRequestSpecification.getDefaultRequestSpec()
            .contentType(ContentType.JSON)
            .body(userDetails)
            .put(UPDATE_USERS_ENDPOINT+"/"+username);
  }
}
