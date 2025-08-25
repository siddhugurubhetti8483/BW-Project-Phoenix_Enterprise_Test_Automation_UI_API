package com.phoenix.reporting;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import com.phoenix.core.DriverFactory;

public class TestListener implements ITestListener{

    private static final ThreadLocal<ExtentTest> tl = new ThreadLocal<>();

    // public void onStart(ITestContext context) { ExtentManager.get(); }
    // public void onFinish(ITestContext context) { ExtentManager.get().flush(); }

    // public void onTestStart(ITestResult result) {
    //     tl.set(ExtentManager.get().createTest(result.getMethod().getMethodName()));
    // }

    // public void onTestSuccess(ITestResult result) { tl.get().pass("Passed"); }

    // public void onTestFailure(ITestResult result) {
    //     tl.get().fail(result.getThrowable());
    //     takeScreenshot(result.getMethod().getMethodName());
    // }

    // private void takeScreenshot(String name){
    //     try{
    //         WebDriver d = DriverFactory.getDriver();
    //         if(d==null) return;
    //         File src = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
    //         File dest = new File("target/screenshots/"+name+".png");
    //         dest.getParentFile().mkdirs();
    //         FileUtils.copyFile(src, dest);
    //         tl.get().addScreenCaptureFromPath(dest.getPath());
    //     } catch(IOException | WebDriverException ignored){}
    // }
     @Override
    public void onStart(ITestContext context) {
        ExtentManager.get();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.get().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        tl.set(ExtentManager.get().createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        tl.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        tl.get().fail(result.getThrowable());
        takeScreenshot(result.getMethod().getMethodName());
    }

    private void takeScreenshot(String name){
        try{
            WebDriver d = DriverFactory.getDriver();
            if(d == null) return;

            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            
            String path = System.getProperty("user.dir") + File.separator + "target"+ File.separator + "screenshots" + File.separator + name + ".png";

            File src = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
            File dest = new File(path);

            // folder auto create hoga
            dest.getParentFile().mkdirs(); 
            FileUtils.copyFile(src, dest);

            // report me SS attach
            tl.get().addScreenCaptureFromPath(dest.getAbsolutePath());
        } catch (IOException | WebDriverException e){
            e.printStackTrace();
        }
    }
}
