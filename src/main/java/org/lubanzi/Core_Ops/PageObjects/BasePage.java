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

public class BasePage implements IPageObjects {

        protected WebDriver _driver;
        protected WebDriverWait wait;


        public BasePage(WebDriver driver) {
                this._driver = driver;
        }

        public void navigateToTestSite(String URL){
                this._driver.get(URL);
        }

        public void configureWaitMechanism(Duration waitTime) {
                this.wait = new WebDriverWait(this._driver, waitTime);
        }


        public String getPageElementContent(By locator) {
                return getElement(locator).getText();
        }

        public WebElement getElement(By locator) {
                int waitTime = Integer.parseInt(ConfigReader.getProperty("WAIT_TIME"));
                configureWaitMechanism(Duration.ofSeconds(waitTime));

                return this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        public void clickElement(By locator) {
                        this.getElement(locator).click();

        }

        public void sendKeys(By locator, String keys) {
                this.getElement(locator).sendKeys(keys);
        }

        public void acceptAlert(){
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = this._driver.switchTo().alert();
                alert.accept();
        }

        public String getPageTitle(){
                return this._driver.getTitle();
        }

        public String getUrl(){
                return this._driver.getCurrentUrl();
        }

        public boolean waitForURLChange(String expectedURL){
                return wait.until(ExpectedConditions.urlToBe(expectedURL));
        }

        public boolean waitForElementToBeVisible(By locator){
                return getElement(locator).isDisplayed();
        }

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

        public boolean checkForIncorrectValues(List<String> list, String[] array) {
                List<String> arrayList = Arrays.asList(array);
                for (String element : list) {
                        if (!arrayList.contains(element)) {
                                return true;
                        }
                }
                return false;
        }
}
