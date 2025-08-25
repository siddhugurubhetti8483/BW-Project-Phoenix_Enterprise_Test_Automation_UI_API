package com.phoenix.reporting;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    
    // public static ExtentReports get(){
    //     if(extent==null){
    //         ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-report.html");
    //         extent = new ExtentReports();
    //         extent.attachReporter(spark);
    //     }
    //     return extent;
    // }
    public static ExtentReports get() {
        if (extent == null) {
            // timestamp generate
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String path = System.getProperty("user.dir") + "/target/extent-report_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setDocumentTitle("Phoenix Automation Report");
            spark.config().setReportName("HRM Test Results");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }
}
