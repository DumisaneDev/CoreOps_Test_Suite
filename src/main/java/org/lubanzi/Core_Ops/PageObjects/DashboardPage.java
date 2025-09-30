package org.lubanzi.Core_Ops.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
        //TODO: Find, Declare and initialize all locators found on the Dashboard page.
        private static final By loc_msgWelcome = By.xpath("//h1");
        private static final By loc_lblActiveProjects = By.id("lblActiveProjects");
        private static final By loc_lblEmployees = By.id("lblTeamMembers");
        private static final By loc_lblTimesheetsSubmitted = By.id("lblTimesheetsSubmitted");
        private static final By loc_lblLeaves = By.id("lblReports");
        private static final By loc_ddlLeave = By.xpath("//a[@onclick=\"toggleDropdownLeave()\"]");
        private static final By loc_lnkLeaveRequestList = By.xpath("//a[@href=\"LeaveRequestList.aspx\"]");

        public DashboardPage(WebDriver driver) {
                super(driver);
        }

        //TODO: Write up all the method that handle the possible web interactions done on the page
        public void openLeaveDropdown(){
                clickElement(loc_ddlLeave);
        }

        public void clickLeaveRequestList(){
                clickElement(loc_lnkLeaveRequestList);
        }

        public void navigateToLeaveRequestList(){
                clickElement(loc_ddlLeave);
                clickElement(loc_lnkLeaveRequestList);
        }

        //TODO: Write up all validation method for assertion purposes
        public boolean isUserOnDashboardPage(String expectedURL, String expectedMsg){
                String welcomeMsg = getPageElementContent(loc_msgWelcome);
                String pageURL = getUrl();

                return expectedMsg.equals(welcomeMsg)
                        && waitForURLChange(expectedURL)
                        && expectedURL.equals(pageURL);
        }

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
