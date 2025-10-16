/**
 * This is the page Object Repository for the locators as well as web interactions for the login page of CoreOps.
 * */
package org.lubanzi.Core_Ops.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * LoginPage represents the login page of the application and provides methods for login functionality.
 * Extends BasePage to inherit common page operations.
 */
public class LoginPage extends BasePage{
        /**
         * All locators below are used to identify elements on the Login page.
         * They use a combination of XPath and ID-based selectors for reliable element identification.
         */
        private final static By loc_spnLogo = By.xpath("//span[@style=\"font-size: 38px; font-weight: bold;\"]");
        private final static By loc_txtEmail = By.id("txtEmail");
        private final static By loc_txtPassword = By.id("txtPassword");
        private final static By loc_btnLogin = By.name("Button1");
        private final static By loc_lnkForgotPass = By.id("lnkForgotPass");
        private final static By loc_lnkRegister = By.id("lnkRegister");

        /**
         * Initializes the LoginPage with the provided WebDriver instance.
         * @param driver WebDriver instance to use for browser automation
         */
        public LoginPage(WebDriver driver){
                super(driver);
        }

        //the methods that handle the possible web interactions done on the page
        /**
         * Navigates to the login page using the provided URL.
         * @param URL Target URL to navigate to
         */
        public void navigateToLoginPage(String URL){
                navigateToTestSite(URL);
        }

        /**
         * Enters email address in the email input field.
         * @param email Email address to enter
         */
        public void enterEmail(String email){
                sendKeys(loc_txtEmail, email);
        }

        /**
         * Enters password in the password input field.
         * @param password Password to enter
         */
        public void enterPassword(String password){
                sendKeys(loc_txtPassword, password);
        }

        /**
         * Clicks the login button to submit the login form.
         */
        public void clickLoginBtn(){
                clickElement(loc_btnLogin);
        }

        /**
         * Clicks the forgot password link to access password recovery.
         */
        public void clickForgotPassLnk(){
                clickElement(loc_lnkForgotPass);
        }

        /**
         * Clicks the register link to access registration form.
         */
        public void clickRegisterLnk(){
                clickElement(loc_lnkRegister);
        }

        /**
         * Performs complete login operation with provided credentials.
         * @param email Email address to use for login
         * @param password Password to use for login
         */
        public void loginToCoreOps(String email, String password){
                enterEmail(email);
                enterPassword(password);
                clickLoginBtn();
        }

        //The validation methods for assertion purposes.
        /**
         * Verifies if the user is on the correct login page by checking title and logo text.
         * @param expectedTitle Expected page title to verify
         * @param expectedLogo Expected logo text to verify
         * @return true if both title and logo match expectations, false otherwise
         */
        public boolean isUserOnLoginPage(String expectedTitle, String expectedLogo){
                String logoText = getPageElementContent(loc_spnLogo);
                String pageTitle = getPageTitle();

                return expectedTitle.equals(pageTitle)
                        && expectedLogo.equals(logoText);
        }
}
