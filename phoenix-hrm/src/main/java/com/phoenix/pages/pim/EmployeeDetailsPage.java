package com.phoenix.pages.pim;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.phoenix.core.Waiter;

public class EmployeeDetailsPage {
    private final WebDriver driver;
    private final Waiter wait;

    private final By middleNameField = By.name("middleName");
    private final By saveBtn = By.cssSelector("button[type='submit']");

    public EmployeeDetailsPage(WebDriver d){ this.driver=d; this.wait=new Waiter(d); }

    public void updateMiddleName(String newValue){
        WebElement field = wait.visible(middleNameField);
        field.clear();
        field.sendKeys(newValue);
        wait.clickable(saveBtn).click();
    }

    public String getMiddleName(){
        return wait.visible(middleNameField).getAttribute("value");
    }

}
