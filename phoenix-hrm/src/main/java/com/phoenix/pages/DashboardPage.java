package com.phoenix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.phoenix.core.WaitUtils;
import com.phoenix.core.Waiter;

public class DashboardPage {
    private final WebDriver driver;
    private final Waiter wait;
    private final By pimMenu = By.xpath("//span[text()='PIM']");

    public DashboardPage(WebDriver d){
        this.driver = d;
        this.wait = new Waiter(d);

        WaitUtils.waitForPageLoad(driver);
        
        if(!wait.visible(pimMenu).isDisplayed()){
            throw new IllegalStateException("DashboardPage not loaded properly!");
        }
    }

    public void openPIM(){
        wait.clickable(pimMenu).click();
    }
}
