package com.mindtickle.api.tests.pets;

import com.mindtickle.api.builder.pets.PetsReqresApi;
import com.mindtickle.assertions.ResponseAssert;
import com.mindtickle.api.pojos.pets.PetDetails;
import com.mindtickle.api.tests.pets.testdata.PetTestData;
import com.mindtickle.config.factory.LoggerConfigFactory;
import com.mindtickle.reports.ExtentReport;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;


class PetTest {
  private static final Logger logger = LoggerConfigFactory.configureLogger();

  @Test
  void createNewPetsWithValidData() {
    try{
      PetDetails createPetsData = PetTestData.getDataToCreatePets();
      Response response = PetsReqresApi.createPets(createPetsData);

      ResponseAssert.assertThat(response)
              .statusCodeIs(200)
              .hasContentType("application/json")
              .assertAll();
    } catch (AssertionError e){
      ExtentReport.attachFail(e.getMessage());
    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
    }
  }

  @Test
  void updateExistingPetsWithValidData() {
    try{
      PetDetails updatePetsData = PetTestData.getDataToUpdatePets();
      Response response = PetsReqresApi.updatePets(updatePetsData);

      ResponseAssert.assertThat(response)
              .statusCodeIs(200)
              .hasContentType("application/json")
              .assertAll();
    } catch (AssertionError e){
      ExtentReport.attachFail(e.getMessage());
    } catch(Exception e){
      logger.error("An error occurred during the test: " + e.getMessage(), e);
    }
  }

  @Test
  void getPetsByStatusWithValidData() {
   try{
     String petStatus = PetTestData.getStatusForPets();
     Response response = PetsReqresApi.getPetsByStatus(petStatus);
     ResponseAssert.assertThat(response)
             .statusCodeIs(200)
             .hasContentType("application/json")
             .hasKeyWithMultipleValue("status", petStatus)
             .assertAll();
   } catch (AssertionError e){
     ExtentReport.attachFail(e.getMessage());
   } catch(Exception e){
     logger.error("An error occurred during the test: " + e.getMessage(), e);
   }
  }

}
