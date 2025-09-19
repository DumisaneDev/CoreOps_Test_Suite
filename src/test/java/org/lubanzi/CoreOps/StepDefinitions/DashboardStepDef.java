package org.lubanzi.CoreOps.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.lubanzi.Core_Ops.PageObjects.DashboardPage;
import org.lubanzi.Core_Ops.PageObjects.LoginPage;
import org.lubanzi.Core_Ops.Utils.ConfigReader;

import static org.testng.Assert.assertTrue;


public class DashboardStepDef {
        private final LoginPage loginCoreObj = new LoginPage(Hooks.driver);
        private final DashboardPage dashboardCoreObj = new DashboardPage(Hooks.driver);

        @Given("i am logged into the system as a valid user.")
        public void i_am_logged_into_the_system_as_a_valid_user() {
                String email = ConfigReader.getProperty("Email");
                String password = ConfigReader.getProperty("Password");

                loginCoreObj.navigateToLoginPage(Hooks.TEST_URL);
                assertTrue(loginCoreObj.isUserOnLoginPage("Login form", "CoreOps"));

                loginCoreObj.loginToCoreOps(email, password);
        }

        @Given("i am on the dashboard page")
        public void i_am_on_the_dashboard_page() {
                String expectedURL = ConfigReader.getProperty("Dash_URL");
                String expectedMsg = ConfigReader.getProperty("Dash_WelcomeMsg");

                assertTrue(dashboardCoreObj.isUserOnDashboardPage(expectedURL, expectedMsg));
        }

        @Then("I should see a clear summary displaying the total count for projects, employees, submitted_timesheets and leave_Requests.")
        public void iShouldSeeAClearSummaryDisplayingTheTotalCountForProjectsEmployeesSubmitted_timesheetsAndLeave_Requests() {
                String expectedProjects = ConfigReader.getProperty("Projects");
                String expectedEmployees = ConfigReader.getProperty("Employees");
                String expectedTimesheets = ConfigReader.getProperty("TimesheetsSubmitted");
                String expectedLeaveRequests = ConfigReader.getProperty("LeaveRequests");

                assertTrue(dashboardCoreObj.isDashboardDataCorrect(expectedProjects,expectedEmployees
                        ,expectedTimesheets,expectedLeaveRequests));

        }
}
