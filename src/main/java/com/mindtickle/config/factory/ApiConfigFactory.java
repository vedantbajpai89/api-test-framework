package com.mindtickle.config.factory;

import com.mindtickle.config.ApiConfig;
import org.aeonbits.owner.ConfigCache;

public final class ApiConfigFactory {

  private ApiConfigFactory() {
  }

  public static ApiConfig getConfig() {
    return ConfigCache.getOrCreate(ApiConfig.class);
  }
}
