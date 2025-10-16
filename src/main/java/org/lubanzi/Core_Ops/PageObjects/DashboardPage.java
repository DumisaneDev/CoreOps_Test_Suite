package org.lubanzi.Core_Ops.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * DashboardPage represents the dashboard page of the application and provides methods for interaction.
 * Extends BasePage to inherit common page operations.
 */
public class DashboardPage extends BasePage {
        /**
         * All locators below are used to identify elements on the Dashboard page.
         * They use a combination of XPath and ID-based selectors for reliable element identification.
         */
        private static final By loc_msgWelcome = By.xpath("//h1");
        private static final By loc_lblActiveProjects = By.id("lblActiveProjects");
        private static final By loc_lblEmployees = By.id("lblTeamMembers");
        private static final By loc_lblTimesheetsSubmitted = By.id("lblTimesheetsSubmitted");
        private static final By loc_lblLeaves = By.id("lblReports");
        private static final By loc_ddlLeave = By.xpath("//a[@onclick=\"toggleDropdownLeave()\"]");
        private static final By loc_lnkLeaveRequestList = By.xpath("//a[@href=\"LeaveRequestList.aspx\"]");

        /**
         * Initializes the DashboardPage with the provided WebDriver instance.
         * @param driver WebDriver instance to use for browser automation
         */
        public DashboardPage(WebDriver driver) {
                super(driver);
        }

        //all the method that handle the possible web interactions done on the page
        /**
         * Opens the leave dropdown menu by clicking on the dropdown element.
         */
        public void openLeaveDropdown(){
                clickElement(loc_ddlLeave);
        }

        /**
         * Clicks the Leave Request List link to navigate to the leave request page.
         */
        public void clickLeaveRequestList(){
                clickElement(loc_lnkLeaveRequestList);
        }

        /**
         * Navigates to the Leave Request List page by opening the dropdown and clicking the link.
         */
        public void navigateToLeaveRequestList(){
                clickElement(loc_ddlLeave);
                clickElement(loc_lnkLeaveRequestList);
        }

        //all validation methods for assertion purposes
        /**
         * Verifies if the user is on the correct dashboard page by checking URL and welcome message.
         * @param expectedURL Expected URL of the dashboard page
         * @param expectedMsg Expected welcome message text
         * @return true if URL and welcome message match expectations, false otherwise
         */
        public boolean isUserOnDashboardPage(String expectedURL, String expectedMsg){
                String welcomeMsg = getPageElementContent(loc_msgWelcome);
                String pageURL = getUrl();

                return expectedMsg.equals(welcomeMsg)
                        && waitForURLChange(expectedURL)
                        && expectedURL.equals(pageURL);
        }

        /**
         * Validates that all dashboard data elements contain expected values.
         * @param expectedProjects Expected text for active projects label
         * @param expectedEmployees Expected text for employees label
         * @param expectedTimesheets Expected text for timesheets label
         * @param expectedLeaves Expected text for leaves label
         * @return true if all data elements match expectations, false otherwise
         */
        public boolean isDashboardDataCorrect(String expectedProjects, String expectedEmployees, String expectedTimesheets, String expectedLeaves){
                String projects, employees, timesheets, leaves;

                projects = getPageElementContent(loc_lblActiveProjects);
                employees = getPageElementContent(loc_lblEmployees);
                timesheets = getPageElementContent(loc_lblTimesheetsSubmitted);
                leaves = getPageElementContent(loc_lblLeaves);

                return expectedProjects.equals(projects)
                        && expectedEmployees.equals(employees)
                        && expectedTimesheets.equals(timesheets)
                        && expectedLeaves.equals(leaves);
        }
}
