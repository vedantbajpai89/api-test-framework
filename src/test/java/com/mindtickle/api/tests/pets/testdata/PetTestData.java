package com.mindtickle.api.tests.pets.testdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtickle.api.pojos.pets.Category;
import com.mindtickle.api.pojos.pets.PetDetails;
import com.mindtickle.api.pojos.pets.Tags;
import com.mindtickle.config.factory.TestDataConfigFactory;
import com.mindtickle.utils.TestDataGenerator;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PetTestData {

  private static int petId;
  private static String petName;
  private PetTestData() {
  }

  @SneakyThrows
  public static PetDetails getDataToCreatePets() {
    TestDataGenerator data = new TestDataGenerator();
    petId = data.getId();
    petName = data.getPetName();
    return new ObjectMapper()
            .readValue(new File(System.getProperty("user.dir") + TestDataConfigFactory.getConfig().createPetsSchemaJSONPath()), PetDetails.class)
            .setId(petId)
            .setCategory(getPetsCategory())
            .setName(petName)
            .setPhotoUrls(getPhotoUrls())
            .setStatus(data.getPetStatus())
            .setTags(getTagsForPets());

  }

  public static PetDetails getDataToUpdatePets() throws IOException {
    TestDataGenerator data = new TestDataGenerator();
    petId = data.getId();
    petName = data.getPetName();
    return new ObjectMapper()
            .readValue(new File(System.getProperty("user.dir") + TestDataConfigFactory.getConfig().updatePetsSchemaJSONPath()), PetDetails.class)
            .setId(petId)
            .setCategory(getPetsCategory())
            .setName(petName)
            .setPhotoUrls(getPhotoUrls())
            .setStatus(data.getPetStatus())
            .setTags(getTagsForPets());

  }

  @SneakyThrows
  public static Category getPetsCategory() {
    return new Category(petId, petName);
  }

  @SneakyThrows
  public static List<String> getPhotoUrls() {
    TestDataGenerator data = new TestDataGenerator();
    return data.getUrls();
  }

  @SneakyThrows
  public static List<Tags> getTagsForPets() {
    TestDataGenerator data = new TestDataGenerator();
    List<Tags> tagList = new ArrayList<>();
    int range = data.getRandomRange();
    for(int index = 0; index < range; index++){
      tagList.add(new Tags(data.getId(), data.getPetName()));
    }
    return tagList;
  }

  public static String getStatusForPets(){
    TestDataGenerator data = new TestDataGenerator();
    return data.getPetStatus();
  }
}
