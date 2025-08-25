package com.phoenix.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver  = new ThreadLocal<>();

    public static void initDriver(){
        if(driver .get() == null){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver.set(new ChromeDriver(options));
        }
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){
        if(driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }
}
