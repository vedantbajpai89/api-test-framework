package com.mindtickle.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
  "system:properties",
  "system:env",
  "file:${user.dir}/src/test/resources/api-config.properties"
})
public interface ApiConfig extends Config {

  @Key("api.baseurl")
  String apiBaseUrl();

  @Key("list.users")
  String listUserEndpoint();

  @Key("create.users")
  String postUserEndpoint();

  @Key("update.users")
  String updateUserEndpoint();

  @Key("create.pets")
  String createPetEndpoint();

  @Key("update.pets")
  String updatePetEndpoint();

  @Key("list.pets")
  String getPetEndpoint();
}
