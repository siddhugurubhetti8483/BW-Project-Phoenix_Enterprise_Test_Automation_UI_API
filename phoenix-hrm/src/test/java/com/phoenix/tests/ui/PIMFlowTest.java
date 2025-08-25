package com.phoenix.tests.ui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.phoenix.core.BaseTest;
import com.phoenix.core.TestContext;
import com.phoenix.pages.DashboardPage;
import com.phoenix.pages.LoginPage;
import com.phoenix.pages.pim.AddEmployeePage;
import com.phoenix.pages.pim.EmployeeListPage;

public class PIMFlowTest extends BaseTest{

    @DataProvider(name="employees")
    public Object[][] data(){
        return new Object[][]{
            {"Ravi","K","Sharma"},
            {"Neha","P","Shaha"}
        };
    }

    @Test
    public void preLogin() throws InterruptedException{
        new LoginPage(getDriver()).login("Admin", "admin123");
        Thread.sleep(2000);
        new DashboardPage(getDriver()).openPIM();
        Thread.sleep(2000);
    }

    @Test(dataProvider = "employees", dependsOnMethods = "preLogin")
    public void testAddEmp(String fn, String mn, String ln) throws InterruptedException{
        String id = new AddEmployeePage(getDriver()).addEmp(fn, mn, ln);

        org.testng.Assert.assertNotNull(id, "Employee ID Should be captured");
        TestContext.set("lastEmpId", id);
        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "testAddEmp")
    public void searchAndValEmp(){
        String id = TestContext.get("lastEmpId");
        EmployeeListPage list = new EmployeeListPage(getDriver());
        list.searchById(id);


        org.testng.Assert.assertFalse(list.isNoRecord(), "Employee should be found in table");
    }

    @Test(dependsOnMethods = "searchAndValEmp")
    public void testEditEmpInfo() throws InterruptedException{
        org.testng.Assert.assertTrue(true, "Edit assertions go here");

        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "testEditEmpInfo")
    public void testDeleteEmpRecord(){
        String id = TestContext.get("lastEmpId");

        EmployeeListPage list = new EmployeeListPage(getDriver());
        list.searchById(id);

        org.testng.Assert.assertFalse(list.isNoRecord(), "Employee not found, so delete button won't exist!");
        list.deleteById(id);

        list.searchById(id);
        org.testng.Assert.assertTrue(list.isNoRecord(),"After delete, no record found");
    }
}
