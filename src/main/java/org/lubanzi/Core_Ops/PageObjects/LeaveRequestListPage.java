package org.lubanzi.Core_Ops.PageObjects;

import org.lubanzi.Core_Ops.Utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeaveRequestListPage represents the page for viewing and managing leave requests.
 * Extends BasePage to inherit common page operations.
 */
public class LeaveRequestListPage extends BasePage {
        /**
         * All locators below are used to identify elements on the Leave Request List page.
         * They use a combination of XPath and ID-based selectors for reliable element identification.
         */
        private static final By loc_lblBanner = By.xpath("//h1");
        private static final By loc_txtSearch = By.id("searchInput");
        private static final By loc_icnAscArrow = By.xpath("//a[@href=\"?sort=EmployeeName&dir=asc\"]");
        private static final By loc_icnDescArrow = By.xpath("//a[@href=\"?sort=Status&dir=desc\"]");
        private static final By loc_tblLeaveRequest = By.xpath("//table");
        private static final By loc_btnApproveRequest = By.xpath("//*[contains(@onclick, 'ButtonEdit(this.id)')]");
        private static final By loc_btnRejectRequest = By.xpath("//*[contains(@onclick, 'ButtonEdit(this.id)')]");
        private static final By loc_btnCancelRequest = By.xpath("//*[contains(@onclick, 'ButtonEdit(this.id)')]");
        private static final By loc_lblCancelStatus = By.xpath("//*[@id=\"form2\"]/div[3]/div[3]/div/table/tbody/tr[46]/td[6]");
        private static final By loc_lblApproveStatus = By.xpath("//*[@id=\"form2\"]/div[3]/div[3]/div/table/tbody/tr[49]/td[6]");
        private static final By loc_lblRejectStatus = By.xpath("//*[@id=\"form2\"]/div[3]/div[3]/div/table/tbody/tr[51]/td[6]");
        private static final By loc_tblSearch = By.xpath("//table");

        /**
         * Initializes the LeaveRequestListPage with the provided WebDriver instance.
         * @param driver WebDriver instance to use for browser automation
         */
        public LeaveRequestListPage(WebDriver driver) {
                super(driver);
        }

        //the methods that handle the possible web interactions done on the page
        /**
         * Searches for leave requests using the provided search value.
         * @param searchValue Text to search for in the leave request list
         */
        public void searchList(String searchValue) {
                sendKeys(loc_txtSearch, searchValue);
        }

        /**
         * Sorts the employee list in ascending alphabetical order.
         */
        public void clickEmployeeAscendingSortBtn() {
                clickElement(loc_icnAscArrow);
        }

        /**
         * Sorts the status list in descending alphabetical order.
         */
        public void clickStatusDescendingSortBtn() {
                clickElement(loc_icnDescArrow);
        }

        /**
         * Approves the selected leave request.
         */
        public void clickApproveRequest() {
                clickElement(loc_btnApproveRequest);
        }

        /**
         * Rejects the selected leave request.
         */
        public void clickRejectRequest() {
                clickElement(loc_btnRejectRequest);
        }

        /**
         * Cancels the selected leave request.
         */
        public void clickCancelRequest() {
                clickElement(loc_btnCancelRequest);
        }

        //all validation methods for assertion purposes
        /**
         * Verifies if the user is on the correct leave request list page by checking the banner text.
         * @param expectedBanner Expected banner text to verify
         * @return true if the banner matches expectations, false otherwise
         */
        public boolean isUserOnLeaveRequestListPage(String expectedBanner) {
                String bannerText = getPageElementContent(loc_lblBanner);
                return expectedBanner.equals(bannerText);
        }

        /**
         * Verifies if the leave request table has loaded successfully.
         * @return true if the table is visible, false otherwise
         */
        public boolean hasTableLoaded() {
                return waitForElementToBeVisible(loc_tblLeaveRequest);
        }

        /**
         * Validates the contents of the leave request table against expected data.
         * @return true if all table data matches expected values, false otherwise
         */
        public boolean isTableContentsCorrect() {
                List<List<String>> tableContent = tableContentRetriever(loc_tblLeaveRequest);

                List<String> employees = new ArrayList<>();
                List<String> leaveTypes = new ArrayList<>();
                List<String> statuses = new ArrayList<>();


                for (int row = 1; row < tableContent.size(); row++) {
                        for (int col = 0; col < tableContent.get(row).size(); col++) {
                                switch (col) {
                                        case 0:
                                                employees.add(tableContent.get(row).get(col));
                                                break;
                                        case 1:
                                                leaveTypes.add(tableContent.get(row).get(col));
                                                break;
                                        case 5:
                                                statuses.add(tableContent.get(row).get(col));

                                }
                        }
                }


                return validateTableData(employees, leaveTypes, statuses);
        }

        /**
         * Validates table data against configuration values.
         * @param employees List of employee names from the table
         * @param leaveTypes List of leave types from the table
         * @param status List of statuses from the table
         * @return true if all data matches expected values, false otherwise
         */
        public boolean validateTableData(List<String> employees, List<String> leaveTypes, List<String> status) {
                String[] expectedEmployees = ConfigReader.getProperty("LeaveEmployees").split(",");
                String[] expectedLeaveTypes = ConfigReader.getProperty("LeaveTypes").split(",");
                String[] expectedStatues = ConfigReader.getProperty("LeaveStatus").split(",");

                boolean areEmployeesIncorrect = checkForIncorrectValues(employees, expectedEmployees);
                boolean areLeaveTypesIncorrect = checkForIncorrectValues(leaveTypes, expectedLeaveTypes);
                boolean areStatusesIncorrect = checkForIncorrectValues(status, expectedStatues);

                return areEmployeesIncorrect && areLeaveTypesIncorrect && areStatusesIncorrect;
        }

        /**
         * Verifies if the leave request status has changed to the expected value after cancellation.
         * @param ExpectedStatus Expected status text to verify
         * @return true if status matches expectations, false otherwise
         */
        public boolean didStatusChange(String ExpectedStatus) {
                WebElement status = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(getElement(loc_lblCancelStatus))));
                String statusText = status.getText();

                return ExpectedStatus.equals(statusText);
        }

        /**
         * Verifies if the leave request status has changed to the expected value after approval.
         * @param ExpectedStatus Expected status text to verify
         * @return true if status matches expectations, false otherwise
         */
        public boolean isRequestApproved(String ExpectedStatus) {
                WebElement status = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(getElement(loc_lblApproveStatus))));
                String statusText = status.getText();

                return ExpectedStatus.equals(statusText);
        }

        /**
         * Verifies if the leave request status has changed to the expected value after rejection.
         * @param ExpectedStatus Expected status text to verify
         * @return true if status matches expectations, false otherwise
         */
        public boolean isRequestRejected(String ExpectedStatus) {
                WebElement status = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(getElement(loc_lblRejectStatus))));
                String statusText = status.getText();

                return ExpectedStatus.equals(statusText);
        }

        /**
         * Verifies if there are any leave requests for the specified employee.
         * @param employee Employee name to search for
         * @return true if requests found for the employee, false otherwise
         */
        public boolean areRequestForEmployee(String employee) {
                waitForElementToBeVisible(loc_tblSearch);
                List<List<String>> tableContent = tableContentRetriever(loc_tblSearch);
                List<String> employees = getLeaveEmployeeDataType(tableContent, 0);

                for (String emp : employees) {
                        if (emp.equals(employee)) {
                                return true;
                        }
                }
                return false;
        }

        /**
         * Verifies if there are any leave requests of the specified type.
         * @param leave Type of leave to search for
         * @return true if requests found of the specified type, false otherwise
         */
        public boolean areRequestForLeaveType(String leave){
                waitForElementToBeVisible(loc_tblSearch);
                List<List<String>> tableContent = tableContentRetriever(loc_tblSearch);
                List<String> leaveTypes = getLeaveEmployeeDataType(tableContent, 1);

                for(String leaveType: leaveTypes){
                        if(leaveType.equals(leave)){
                                return true;
                        }
                }
                return false;
        }

        /**
         * Verifies if the employee list is sorted alphabetically.
         * @return true if employees are sorted alphabetically, false otherwise
         */
        public boolean isListOfEmployeesOnRequestSortedAlphabetically(){
                waitForElementToBeVisible(loc_tblSearch);
                List<List<String>> tableContent = tableContentRetriever(loc_tblSearch);
                List<String> actualEmployees = getLeaveEmployeeDataType(tableContent, 0);
                String expectedEmployees = ConfigReader.getProperty("LeaveEmployees");

                String[] employees = expectedEmployees.split(",");
                List<String> expectedEmployeesList = Arrays.stream(employees).
                        distinct().
                        sorted().
                        toList();

                List<String> nonDuplicateTestEmployees = actualEmployees.stream().
                        filter(e -> !e.contentEquals("Select Employee")).
                        distinct().
                        toList();

                return nonDuplicateTestEmployees.equals(expectedEmployeesList);
        }

        /**
         * Verifies if the status list is sorted in reverse alphabetical order.
         * @return true if statuses are sorted in reverse alphabetical order, false otherwise
         */
        public boolean isListOfLeaveStatuesSortedReverseAlphabetically(){
                waitForElementToBeVisible(loc_tblSearch);
                List<List<String>> tableContent = tableContentRetriever(loc_tblSearch);
                List<String> actualStatues = getLeaveEmployeeDataType(tableContent, 5);

                String expectedValues = ConfigReader.getProperty("LeaveStatus");
                String[] expectedValuesArray = expectedValues.split(",");
                List<String> expectedStatuses = Arrays.stream(expectedValuesArray).
                        sorted().
                        collect(Collectors.toList());

                Collections.reverse(expectedStatuses);

                List<String> nonDuplicateStatusList = actualStatues.
                        stream().
                        distinct().
                        toList();

                return expectedStatuses.equals(nonDuplicateStatusList);
        }
}
