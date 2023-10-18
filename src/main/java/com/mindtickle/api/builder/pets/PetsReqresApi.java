package com.mindtickle.api.builder.pets;

import com.mindtickle.api.BaseRequestSpecification;
import com.mindtickle.api.pojos.pets.PetDetails;
import com.mindtickle.config.factory.ApiConfigFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public final class PetsReqresApi {

  private PetsReqresApi() {
  }

  private static final String GET_PETS_ENDPOINT = ApiConfigFactory.getConfig().getPetEndpoint();
  private static final String CREATE_PETS_ENDPOINT = ApiConfigFactory.getConfig().createPetEndpoint();
  private static final String UPDATE_PETS_ENDPOINT = ApiConfigFactory.getConfig().updatePetEndpoint();

  public static Response getPetsByStatus(String status) {

    return BaseRequestSpecification.getDefaultRequestSpec()
            .queryParam("status", status)
            .get(GET_PETS_ENDPOINT);
  }

  public static Response createPets(PetDetails petDetails) {
    return BaseRequestSpecification.getDefaultRequestSpec()
      .contentType(ContentType.JSON)
      .body(petDetails)
      .post(CREATE_PETS_ENDPOINT);
  }

  public static Response updatePets(PetDetails petDetails) {
    return BaseRequestSpecification.getDefaultRequestSpec()
            .contentType(ContentType.JSON)
            .body(petDetails)
            .put(UPDATE_PETS_ENDPOINT);
  }
}
