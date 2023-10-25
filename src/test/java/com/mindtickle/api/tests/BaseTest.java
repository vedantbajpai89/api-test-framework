package com.mindtickle.api.tests;



import com.mindtickle.reports.ExtentReport;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.io.IOException;

public class BaseTest {
    private static Logger logger;
    @BeforeAll
    public static void beforeSuite(){
        ExtentReport.initReports();
    }

    @AfterAll
    public static void afterSuite() throws IOException {
        ExtentReport.flushReports();
    }


}
