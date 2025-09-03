# Phoenix – Enterprise Test Automation Framework for OrangeHRM

## Project Goal

This project provides an end-to-end automation framework for testing the **OrangeHRM** application (Demo site).
It covers **UI testing**, **API testing**, and **BDD scenarios** to validate critical workflows like Employee Management.

---

## Application Under Test (AUT)

- **OrangeHRM Demo URL:** [https://opensource-demo.orangehrmlive.com](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login)

---

## Technology Stack

- **Language:** Java 17
- **UI Automation:** Selenium WebDriver + TestNG
- **BDD:** Cucumber (Gherkin syntax)
- **API Testing:** REST Assured
- **Reporting:** ExtentReports
- **Dependency Management & Build:** Maven
- **Browser Management:** WebDriverManager

---

## Project Structure

```
src/
 ├─ main/java/com/phoenix/core        → BaseTest, Config, DriverFactory, Waiter, WaitUtils, TestContext
 ├─ main/java/com/phoenix/pages       → LoginPage, DashboardPage
 ├─ main/java/com/phoenix/pages/pim   → AddEmployeePage, EmployeeListPage, EmployeeDetailsPage
 ├─ main/java/com/phoenix/reporting   → ExtentManager, TestListener
 └─ test/java/com/phoenix/bdd         → Cucumber steps & runner
 └─ test/java/com/phoenix/tests/ui    → AdminLoginTest, PIMFlowTest
 └─ test/java/com/phoenix/tests/api   → EmployeeApiTest
```

---

## Pre-requisites

- Java 17 installed
- Maven installed
- Chrome browser installed
- Internet connection (for WebDriverManager & API tests)

---

## Configuration

All configurable values are present in:
`src/test/resources/config.properties`

```properties
base.url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
admin.username=Admin
admin.password=admin123
timeout=10
browser=chrome
```

---

## How to Run Tests

### 1. Run all tests using Maven:

```bash
mvn clean test
```

### 2. Run specific TestNG suite:

```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### 3. Run only Cucumber BDD scenarios:

```bash
mvn test -Dtest=CucumberTestRunner
```

### 3. Run only Api Test:

```bash
mvn test -Dtest=*ApiTest
```

### 4. Run Perticular Api Test:

```bash
mvn test -Dtest=EmployeeApiTest
```

---

## Test Reports

- **ExtentReports (HTML):** `target/extent-report.html`
- **Cucumber (Console & Summary):** printed in console

Screenshots on test failures are stored in:
`target/screenshots/<testMethodName>.png`

---

## Browser Configuration

Currently uses **Chrome** via WebDriverManager.
To change browser in future:

- Update `DriverFactory.java` to add support for Firefox/Edge.
- Update `config.properties` to select browser dynamically.

---

## Key Features

- **UI Tests:** Admin login, PIM Employee CRUD flows
- **API Tests:** Create, read, delete employee via REST API
- **BDD:** Gherkin feature for updating employee details
- **Reporting:** Detailed HTML reports + screenshots on failure
- **Thread-safe TestContext:** For sharing data across tests

---

## Sample Test Scenario

**Feature:** Edit existing employee personal details
**Scenario:** Update middle name for an employee

```
Given I am logged in as admin
And I am on the PIM Employee List page
When I search employee by id "<EMP_ID>"
And I open the employee details
And I update middle name to "Prakash"
Then I should see middle name as "Prakash"
```

---

## Notes / Future Enhancements

- Add support for multiple browsers dynamically
- Add parallel execution in TestNG suite
- Extend API tests for PUT/Update endpoints
- Integrate with CI/CD pipelines for automated execution

---

## Contact / Author

- **Siddharam Gurubhetti**

- Email: [siddhugurubhetti@gmail.com](mailto:your-email@example.com)
