package com.phoenix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.phoenix.core.WaitUtils;

import com.phoenix.core.Waiter;

public class LoginPage {
    private final WebDriver driver; 
    private final Waiter wait;
    
    private final By user = By.name("username");
    private final By pass = By.name("password");
    private final By submit = By.cssSelector("button[type='submit']");


    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new Waiter(driver);

        WaitUtils.waitForPageLoad(driver);

        if(!wait.visible(user).isDisplayed()){
            throw new IllegalStateException("LoginPage not loaded properly!");
        }
    }

    public void login(String us, String pas){
        wait.visible(user).sendKeys(us);
        wait.visible(pass).sendKeys(pas);
        wait.clickable(submit).click();
    }
}
