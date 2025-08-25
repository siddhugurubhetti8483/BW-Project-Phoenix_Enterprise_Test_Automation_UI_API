package com.phoenix.tests.ui;

import org.openqa.selenium.WebDriver;

import com.phoenix.core.BaseTest;
import com.phoenix.pages.DashboardPage;
import com.phoenix.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLoginTest extends BaseTest{

    public AdminLoginTest() {
        // default constructor required by TestNG 
    }

    @Test
    public void testAdmLogin(){
        new LoginPage(getDriver()).login("Admin", "admin123");

        // Step 2: Verify Dashboard loaded
        DashboardPage dashboard = new DashboardPage(getDriver());
        Assert.assertNotNull(dashboard, "Dashboard page should be loaded after login");
        // WebDriver d = getDriver();  
        // new LoginPage(d).login("Admin", "admin123");
        // new DashboardPage(d).openPIM();
    }
}
