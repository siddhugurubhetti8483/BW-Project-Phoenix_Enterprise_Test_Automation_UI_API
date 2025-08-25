package com.phoenix.tests.api;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;


@Listeners({com.phoenix.reporting.TestListener.class})
public class EmployeeApiTest {
    @BeforeClass
    public void setup(){ 
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1"; 
    }

    private Response withRetry(String method, String endpoint, String body) {
    int attempts = 0;
    while (attempts < 6) {
            Response res;
            if ("GET".equalsIgnoreCase(method)) {
                res = given().get(endpoint);
            } else if ("POST".equalsIgnoreCase(method)) {
                res = given().contentType(ContentType.JSON).body(body).post(endpoint);
            } else if ("DELETE".equalsIgnoreCase(method)) {
                res = given().delete(endpoint);
            } else {
                throw new RuntimeException("Unsupported method: " + method);
            }

            if (res.getStatusCode() == 200) {
                return res;
            } else if (res.getStatusCode() == 429) {
                try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
                attempts++;
            } else {
                return res;
            }
        }
        throw new RuntimeException("Failed after retries");
    }


    @Test(priority = 1)
    public void getEmployees(){
         Response res = withRetry("GET", "/employees", null);

        Assert.assertEquals(res.getStatusCode(), 200, "Expected 200 OK");
        Assert.assertNotNull(res.jsonPath().getList("data"), "Employees list should not be null");
        Assert.assertTrue(res.jsonPath().getList("data").size() > 0, "Employees list should not be empty");
    }

    @Test(priority = 3, dependsOnMethods = "getEmployees")
    public void createEmployee(){
        // String payload = "{\"name\":\"Phoenix QA\",\"salary\":\"12345\",\"age\":\"30\"}";
        String payload = """
                {
                  "name":"Phoenix QA",
                  "salary":"12345",
                  "age":"30"
                }
                """;
    
        Response res = withRetry("POST", "/create", payload);

        Assert.assertEquals(res.getStatusCode(), 200, "Expected 200 OK");
        Assert.assertEquals(res.jsonPath().getString("status"), "success", "Employee should be created successfully");
        Assert.assertNotNull(res.jsonPath().getString("data.id"), "Employee ID should be returned");

    }

    @Test(priority = 3, dependsOnMethods = "createEmployee")
    public void deleteEmployee(){
        // create first
        // String payload = "{\"name\":\"Temp\",\"salary\":\"1\",\"age\":\"1\"}";
         String payload = """
                {
                  "name":"Temp",
                  "salary":"1",
                  "age":"1"
                }
                """;

        Response createRes = withRetry("POST", "/create", payload);

        Assert.assertEquals(createRes.getStatusCode(), 200, "Employee creation failed");
        int id = createRes.jsonPath().getInt("data.id");
        Assert.assertTrue(id > 0, "Employee should be created with valid ID");

        // Step 2: Delete employee
        Response deleteRes = withRetry("DELETE", "/delete/" + id, null);

        Assert.assertEquals(deleteRes.getStatusCode(), 200, "Delete API should return 200");
        Assert.assertEquals(deleteRes.jsonPath().getString("status"), "success", "Employee should be deleted successfully");

    }
}
