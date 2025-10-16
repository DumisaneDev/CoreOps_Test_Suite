package org.lubanzi.Core_Ops.PageObjects;

import org.lubanzi.Core_Ops.Utils.ConfigReader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * BasePage provides fundamental functionality for interacting with web pages during automation testing.
 * This class serves as a foundation for implementing page object pattern in Selenium WebDriver tests.
 */
public class BasePage implements IPageObjects {

        protected WebDriver _driver;
        protected WebDriverWait wait;

        /**
         * Initializes the BasePage with the provided WebDriver instance
         * @param driver WebDriver instance to use for browser automation
         */
        public BasePage(WebDriver driver) {
                this._driver = driver;
        }

        /**
         * Navigates to the specified URL in the browser
         * @param URL Target URL to navigate to
         */
        public void navigateToTestSite(String URL){
                this._driver.get(URL);
        }

        /**
         * Configures the wait mechanism with specified duration
         * @param waitTime Duration object specifying how long to wait for elements
         */
        public void configureWaitMechanism(Duration waitTime) {
                this.wait = new WebDriverWait(this._driver, waitTime);
        }

        /**
         * Retrieves text content from an element located by the specified locator
         * @param locator Selenium By object identifying the element
         * @return Text content of the element
         */
        public String getPageElementContent(By locator) {
                return getElement(locator).getText();
        }

        /**
         * Locates and returns a WebElement using the specified locator
         * Implements explicit waiting based on configuration
         * @param locator Selenium By object identifying the element
         * @return Located WebElement
         */
        public WebElement getElement(By locator) {
                int waitTime = Integer.parseInt(ConfigReader.getProperty("WAIT_TIME"));
                configureWaitMechanism(Duration.ofSeconds(waitTime));

                return this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        /**
         * Simulates a mouse click on the element identified by the locator
         * @param locator Selenium By object identifying the element to click
         */
        public void clickElement(By locator) {
                this.getElement(locator).click();

        }

        /**
         * Enters text into the element identified by the locator
         * @param locator Selenium By object identifying the element
         * @param keys Text to enter into the element
         */
        public void sendKeys(By locator, String keys) {
                this.getElement(locator).sendKeys(keys);
        }

        /**
         * Accepts an alert dialog present in the browser
         */
        public void acceptAlert(){
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = this._driver.switchTo().alert();
                alert.accept();
        }

        /**
         * Retrieves the current page title
         * @return Current browser page title
         */
        public String getPageTitle(){
                return this._driver.getTitle();
        }

        /**
         * Gets the current URL of the browser
         * @return Current browser URL
         */
        public String getUrl(){
                return this._driver.getCurrentUrl();
        }

        /**
         * Waits for the URL to match the expected value
         * @param expectedURL Expected URL to wait for
         * @return true if URL matches within timeout period, false otherwise
         */
        public boolean waitForURLChange(String expectedURL){
                return wait.until(ExpectedConditions.urlToBe(expectedURL));
        }

        /**
         * Verifies if an element is visible on the page
         * @param locator Selenium By object identifying the element
         * @return true if element is visible, false otherwise
         */
        public boolean waitForElementToBeVisible(By locator){
                return getElement(locator).isDisplayed();
        }

        /**
         * Extracts data from a table element identified by the locator
         * Returns a 2D list containing all table cell values
         * @param locator Selenium By object identifying the table element
         * @return List of Lists containing table cell values
         */
        public List<List<String>> tableContentRetriever(By locator){
                //find the table
                WebElement Table = getElement(locator);
                By loc_row = By.xpath("//tbody//tr");


                //find the rows
                List<WebElement> rows = Table.findElements(loc_row);

                List<List<String>> tableData = new LinkedList<>();

                // Process each row
                for (WebElement row : rows) {
                        List<WebElement> cells = row.findElements(By.tagName("td"));

                        // Store cell values for this row
                        List<String> rowValues = new ArrayList<>();
                        for (WebElement cell : cells) {
                                rowValues.add(cell.getText());
                        }
                        tableData.add(rowValues);
                }
                return tableData;
        }

        /**
         * Validates if any element in the list matches values in the array
         * @param list List of strings to validate
         * @param array Array of expected values
         * @return true if any incorrect values found, false otherwise
         */
        public boolean checkForIncorrectValues(List<String> list, String[] array) {
                List<String> arrayList = Arrays.asList(array);
                for (String element : list) {
                        if (!arrayList.contains(element)) {
                                return true;
                        }
                }
                return false;
        }

        /**
         * Extracts specific column data from a table based on position
         * @param tableContent 2D list containing table data
         * @param pos Position of the column to extract (0-based index)
         * @return List of values from the specified column
         */
        public static List<String> getLeaveEmployeeDataType(List<List<String>> tableContent, int pos){
                List<String> EmployeeDataType = new ArrayList<>();
                for (int row = 1; row < tableContent.size(); row++) {
                        if (row < tableContent.size() && !tableContent.get(row).isEmpty()) {
                                EmployeeDataType.add(tableContent.get(row).get(pos));
                        }
                }
                return EmployeeDataType;
        }
}
