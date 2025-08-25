package com.phoenix.core;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Waiter(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement visible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement clickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
