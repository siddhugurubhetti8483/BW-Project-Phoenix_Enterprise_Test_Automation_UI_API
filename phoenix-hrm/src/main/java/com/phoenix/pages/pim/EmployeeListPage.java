package com.phoenix.pages.pim;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.phoenix.core.Waiter;

public class EmployeeListPage {
    private final WebDriver driver;
    private final Waiter wait;

    private final By empList = By.linkText("Employee List");
    private final By searchName = By.xpath("//label[text()='Employee Name']/../following-sibling::div//input");
    private final By searchId = By.xpath("//label[text()='Employee Id']/../following-sibling::div//input");
    private final By searchBtn = By.xpath("//button[text()=' Search ']");
    private final By noRecord = By.xpath("//span[text()='No Records Found']");

    public EmployeeListPage(WebDriver d){
        this.driver = d;
        this.wait = new Waiter(d);
    }

    public void openList(){
        wait.clickable(empList).click();

        wait.visible(searchBtn);
    }

    public void searchById(String id){
        openList();
        wait.visible(searchId).clear();
        wait.visible(searchId).sendKeys(id);
        wait.clickable(searchBtn).click();
    }

    public boolean isNoRecord(){
        try{
            return wait.visible(noRecord).isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public void deleteById(String empId) {
        searchById(empId);
        if(!isNoRecord()){
            WebElement deleteBtn = wait.clickable(By.xpath("//button[i[@class='oxd-icon bi-trash']]"));
            deleteBtn.click();
            
            WebElement confirmBtn = wait.clickable(By.xpath("//button[contains(.,'Yes, Delete')]"));
            confirmBtn.click();
        }
    }


    public void clickFirstRow(){
        wait.clickable(By.cssSelector("table tbody tr:first-child")).click();
    }

    public void openFirstEmployeeDetails() {
        By firstRow = By.cssSelector("table tbody tr:first-child td a"); // adjust locator
        wait.clickable(firstRow).click();
    }
}
