package com.mindtickle.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
  "system:properties",
  "system:env",
  "file:${user.dir}/src/test/resources/data-config.properties"
})
public interface TestDataConfig extends Config {

  @Key("create.user.schema.json")
  String createUserSchemaJSONPath();

  @Key("update.user.schema.json")
  String updateUserSchemaJSONPath();

  @Key("create.pets.schema.json")
  String createPetsSchemaJSONPath();

  @Key("update.pets.schema.json")
  String updatePetsSchemaJSONPath();

  @Key("existing.user.data.json")
  String existingUserDataJSONPath();

  @Key("updated.user.data.json")
  String updatedUserDataJSONPath();
}
