package com.mindtickle.api.tests.pets;

import com.mindtickle.api.builder.pets.PetsReqresApi;
import com.mindtickle.assertions.ResponseAssert;
import com.mindtickle.api.pojos.pets.PetDetails;
import com.mindtickle.api.tests.pets.testdata.PetTestData;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class PetTest {

  private static PetDetails petData;


  @BeforeAll
  static void setUpData(){
    petData = PetTestData.getDataToForPets();
  }


  @Test
  void createNewPets() {
    Response response = PetsReqresApi.createPets(petData);

    ResponseAssert.assertThat(response)
      .statusCodeIs(200)
      .hasContentType("application/json")
      .assertAll();
  }

  @Test
  void updateExistingPets() {
    Response response = PetsReqresApi.updatePets(petData);

    ResponseAssert.assertThat(response)
            .statusCodeIs(200)
            .hasContentType("application/json")
            .assertAll();
  }

  @Test
  void getPetsByStatus() {
    Response response = PetsReqresApi.getPetsByStatus("available");
    ResponseAssert.assertThat(response)
            .statusCodeIs(200)
            .hasContentType("application/json")
            //.hasKeyWithValue("status", "available")
            .assertAll();
  }

}
