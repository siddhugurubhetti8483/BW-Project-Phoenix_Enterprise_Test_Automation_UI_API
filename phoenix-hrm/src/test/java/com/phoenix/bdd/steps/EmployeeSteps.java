package com.phoenix.bdd.steps;

import com.phoenix.pages.DashboardPage;
import com.phoenix.pages.LoginPage;
import com.phoenix.pages.pim.EmployeeDetailsPage;
import com.phoenix.pages.pim.EmployeeListPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.phoenix.core.DriverFactory.getDriver;

public class EmployeeSteps {

    @Given("I am logged in as admin")
    public void login(){
        new LoginPage(getDriver()).login("Admin", "admin123");
    }

    @Given("I am on the PIM Employee List page")
    public void openPIM(){
        new DashboardPage(getDriver()).openPIM();
        new EmployeeListPage(getDriver()).openList();
    }

    @When("I search employee by id {string}")
    public void searchById(String id){
        new EmployeeListPage(getDriver()).searchById(id);
    }

    @When("I open the employee details")
    public void openDetails(){
        /* click table row to open details */
        //  new EmployeeListPage(getDriver()).clickFirstRow();
        new EmployeeListPage(getDriver()).openFirstEmployeeDetails();
    }

    @When("I update middle name to {string}")
    public void updateMiddle(String val){
        /* edit field and save */
        new EmployeeDetailsPage(getDriver()).updateMiddleName(val);
    }

    @Then("I should see middle name as {string}")
    public void assertMiddle(String expected){
        /* assert field value equals expected */
        String actual = new EmployeeDetailsPage(getDriver()).getMiddleName();
        org.testng.Assert.assertEquals(actual, expected, "Middle name should be updated");
    }
}
