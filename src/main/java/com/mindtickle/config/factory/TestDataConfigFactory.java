package com.mindtickle.config.factory;

import com.mindtickle.config.TestDataConfig;
import org.aeonbits.owner.ConfigCache;

public final class TestDataConfigFactory {

  private TestDataConfigFactory() {
  }

  public static TestDataConfig getConfig() {
    return ConfigCache.getOrCreate(TestDataConfig.class);
  }
}
