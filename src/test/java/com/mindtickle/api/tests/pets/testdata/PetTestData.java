package com.mindtickle.api.tests.pets.testdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtickle.api.pojos.pets.Category;
import com.mindtickle.api.pojos.pets.PetDetails;
import com.mindtickle.api.pojos.pets.Tags;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class PetTestData {
  private PetTestData() {
  }

  @SneakyThrows
  public static PetDetails getDataToForPets() {
    return new ObjectMapper()
            .readValue(new File(System.getProperty("user.dir") + "/src/test/java/com/mindtickle/api/tests/pets/testdata/json/create-pets.json"), PetDetails.class)
            .setId(0)
            .setCategory(getPetsCategory())
            .setName("doggie")
            .setPhotoUrls(getPhotoUrls())
            .setStatus("available")
            .setTags(getTagsForPets());

  }

  @SneakyThrows
  public static Category getPetsCategory() {
    return new Category(0, "dogs");
  }

  @SneakyThrows
  public static List<String> getPhotoUrls() {
    return new ArrayList<>(List.of(new String[]{"url1", "url2", "url3"}));
  }

  @SneakyThrows
  public static List<Tags> getTagsForPets() {
    Tags tagsObjectOne = new Tags(0, "dogs");
    Tags tagsObjectTwo = new Tags(0, "cats");
    Tags tagsObjectThree = new Tags(0, "birds");
    return new ArrayList<>(List.of(new Tags[]{tagsObjectOne, tagsObjectTwo, tagsObjectThree}));
  }
}
