package com.mindtickle.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class ResponseAssert extends AbstractAssert<ResponseAssert, Response> {

  private final SoftAssertions softAssertions;

  private ResponseAssert(Response response) {
    super(response, ResponseAssert.class);
    softAssertions = new SoftAssertions();
  }

  public static ResponseAssert assertThat(Response response) {
    return new ResponseAssert(response);
  }

  public ResponseAssert statusCodeIs(int expectedStatusCode) {
    softAssertions.assertThat(actual.statusCode())
      .isEqualTo(expectedStatusCode);
    return this;
  }

  public ResponseAssert hasKeyWithValue(String key, String value) {
    softAssertions.assertThat(actual.getBody().jsonPath().getString(key))
      .isEqualTo(value);
    return this;
  }

  public ResponseAssert hasKeyWithMultipleValue(String key, String value) {
    List<String> actualValues = actual.getBody().jsonPath().get(key);
    for(String actualValue : actualValues){
      softAssertions.assertThat(actualValue)
              .isEqualTo(value);
    }
    return this;
  }

  public ResponseAssert hasContentType(String contentType) {
    softAssertions.assertThat(actual.getContentType())
      .isEqualTo(contentType);
    return this;
  }

  public ResponseAssert canBeDeserializedTo(Class inputClass){
    softAssertions.assertThatCode(() ->actual.as(inputClass))
            .doesNotThrowAnyException();
    return this;
  }

  public void assertAll() {
    softAssertions.assertAll();
  }
}
