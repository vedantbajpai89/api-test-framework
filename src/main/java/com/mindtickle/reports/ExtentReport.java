package com.mindtickle.reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {
    private ExtentReport(){};
    private static ExtentReports extent;
    public  static ExtentTest test;

    public static void initReports(){
        if(Objects.isNull(extent)){
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("API  Automation Report");
            spark.config().setReportName("API Tests Report");
        }
    }

    public static void flushReports() throws IOException {
        if(Objects.nonNull(extent)){
            extent.flush();
        }
        Desktop.getDesktop().browse(new File("index.html").toURI());
    }

    public static void createTest(String testcase){
        test = extent.createTest(testcase);
    }

    public static void attachLogs(String request, String response){
        test.log(Status.INFO,"Request:-"+request);
        test.log(Status.INFO,"Response:-"+response);
    }

    public static void attachFail(String logs){
        test.log(Status.FAIL,"Response Error Logs:-"+logs);
    }
}
