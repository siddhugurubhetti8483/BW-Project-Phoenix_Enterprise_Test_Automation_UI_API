package com.phoenix.pages.pim;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.phoenix.core.Waiter;

public class AddEmployeePage {
    private final WebDriver driver;
    private final Waiter wait;

    private final By addBtn = By.xpath("//a[text()='Add Employee']");
    private final By fname = By.name("firstName");
    private final By mname = By.name("middleName");
    private final By lname = By.name("lastName");
    private final By empId = By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");
    private final By saveBtn = By.cssSelector("button[type='submit']");

    public AddEmployeePage(WebDriver d){
        this.driver = d;
        this.wait = new Waiter(d);
    }

    public String addEmp(String fn, String mn, String ln){
        wait.clickable(addBtn).click();

        wait.visible(fname).sendKeys(fn);
        wait.visible(mname).sendKeys(mn);
        wait.visible(lname).sendKeys(ln);

        String id = wait.visible(empId).getAttribute("value");
        
        wait.clickable(saveBtn).click();
        return id;
    }
}
