package com.phoenix.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected String baseUrl = Config.get("base.url");
    //"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";


    @BeforeClass(alwaysRun = true)
    public void setUp(){
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(baseUrl);
        WaitUtils.waitForPageLoad(DriverFactory.getDriver());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
