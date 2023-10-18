package com.mindtickle.config.factory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class LoggerConfigFactory {

  private LoggerConfigFactory() {
  }

  public static Logger configureLogger() {
    Properties log4jProperties = new Properties();
    try {
      FileInputStream log4jFile = new FileInputStream(System.getProperty("user.dir") +"/src/test/resources/logger-config.properties");
      log4jProperties.load(log4jFile);
      log4jFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // Configure Log4j
    PropertyConfigurator.configure(log4jProperties);
    // Get a logger
    return LogManager.getLogger(LoggerConfigFactory.class);
  }
}
