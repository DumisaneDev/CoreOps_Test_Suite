package org.lubanzi.Core_Ops.PageObjects;

import org.lubanzi.Core_Ops.Utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LeaveRequestListPage extends BasePage {
        //TODO: Find, Declare and initialize all locators found on the Leave Request Page.
        private static final By loc_lblBanner = By.xpath("//h1");
        private static final By loc_txtSearch = By.id("searchInput");
        private static final By loc_icnAscArrow = By.xpath("//a[@href=\"?sort=EmployeeName&dir=asc\"]");
        private static final By loc_icnDescArrow = By.xpath("//a[@href=\"?sort=Status&dir=desc\"]");
        private static final By loc_tblLeaveRequest = By.xpath("//table");
        private static final By loc_btnApproveRequest = By.xpath("//*[contains(@onclick, 'ButtonEdit(this.id)')");
        private static final By loc_btnRejectRequest = By.xpath("//*[contains(@onclick, 'ButtonEdit(this.id)')");
        private static final By loc_btnCancelRequest = By.xpath("//*[contains(@onclick, 'ButtonEdit(this.id)')");
        private static final By loc_lblCancelStatus = By.xpath("//*[@id=\"form2\"]/div[3]/div[3]/div/table/tbody/tr[46]/td[6]");
        private static final By loc_lblApproveStatus = By.xpath("//*[@id=\"form2\"]/div[3]/div[3]/div/table/tbody/tr[49]/td[6]");
        private static final By loc_lblRejectStatus = By.xpath("//*[@id=\"form2\"]/div[3]/div[3]/div/table/tbody/tr[51]/td[6]");
        private static final By loc_tblSearch = By.xpath("//table[@class=\"table custom-table mb-0 datatables\"]");

        public LeaveRequestListPage(WebDriver driver) {
                super(driver);
        }

        //TODO: Write up all the method that handle the possible web interactions done on the page
        public void searchList(String searchValue) {
                sendKeys(loc_txtSearch, searchValue);
        }

        public void clickEmployeeAscendingSortBtn() {
                clickElement(loc_icnAscArrow);
        }

        public void clickStatusDescendingSortBtn() {
                clickElement(loc_icnDescArrow);
        }

        public void clickApproveRequest() {
                clickElement(loc_btnApproveRequest);
        }

        public void clickRejectRequest() {
                clickElement(loc_btnRejectRequest);
        }

        public void clickCancelRequest() {
                clickElement(loc_btnCancelRequest);
        }

        //TODO: Write up all validation method for assertion purposes
        public boolean isUserOnLeaveRequestListPage(String expectedBanner) {
                String bannerText = getPageElementContent(loc_lblBanner);
                return expectedBanner.equals(bannerText);
        }

        public boolean hasTableLoaded() {
                return waitForElementToBeVisible(loc_tblLeaveRequest);
        }

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

        ;

        public boolean validateTableData(List<String> employees, List<String> leaveTypes, List<String> status) {
                String[] expectedEmployees = ConfigReader.getProperty("LeaveEmployees").split(",");
                String[] expectedLeaveTypes = ConfigReader.getProperty("LeaveTypes").split(",");
                String[] expectedStatues = ConfigReader.getProperty("LeaveStatus").split(",");

                boolean areEmployeesIncorrect = checkForIncorrectValues(employees, expectedEmployees);
                boolean areLeaveTypesIncorrect = checkForIncorrectValues(leaveTypes, expectedLeaveTypes);
                boolean areStatusesIncorrect = checkForIncorrectValues(status, expectedStatues);

                return areEmployeesIncorrect && areLeaveTypesIncorrect && areStatusesIncorrect;
        }

        ;

        public boolean didStatusChange(String ExpectedStatus) {
                WebElement status = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(getElement(loc_lblCancelStatus))));
                String statusText = status.getText();

                return ExpectedStatus.equals(statusText);
        }

        public boolean isRequestApproved(String ExpectedStatus) {
                WebElement status = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(getElement(loc_lblApproveStatus))));
                String statusText = status.getText();

                return ExpectedStatus.equals(statusText);
        }

        public boolean isRequestRejected(String ExpectedStatus) {
                WebElement status = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(getElement(loc_lblRejectStatus))));
                String statusText = status.getText();

                return ExpectedStatus.equals(statusText);
        }

        public boolean areRequestForEmployee(String employee) {
                int maxAttempts = 3;
                for (int attempt = 0; attempt < maxAttempts; attempt++) {
                        try {
                                waitForElementToBeVisible(loc_tblSearch);
                                List<List<String>> tableContent = tableContentRetriever(loc_tblSearch);
                                List<String> employees = getStrings(tableContent);

                                for (String emp : employees) {
                                        if (emp.equals(employee)) {
                                                return true;
                                        }
                                }
                                return false;
                        } catch (TimeoutException e) {
                                if (attempt == maxAttempts - 1) {
                                        throw e;
                                }
                                // Wait before retrying
                                try {
                                        Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                        Thread.currentThread().interrupt();
                                }
                        }
                }
                return true;
        }

        private static List<String> getStrings(List<List<String>> tableContent) {
                List<String> employees = new ArrayList<>();
                for (int row = 1; row < tableContent.size(); row++) {
                        if (row < tableContent.size() && tableContent.get(row).size() > 0) {
                                employees.add(tableContent.get(row).get(0));
                        }
                }
                return employees;
        }




}
