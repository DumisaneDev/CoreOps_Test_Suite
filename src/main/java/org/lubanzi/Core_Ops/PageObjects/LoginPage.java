/**
* This is the page Object Repository for the locators as well as web interactions for the login page of CoreOps.
* */
package org.lubanzi.Core_Ops.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
        //TODO: Find, Declare and initialize all locators found on the Login Page.
        private final static By loc_spnLogo = By.xpath("//span[@style=\"font-size: 38px; font-weight: bold;\"]");
        private final static By loc_txtEmail = By.id("txtEmail");
        private final static By loc_txtPassword = By.id("txtPassword");
        private final static By loc_btnLogin = By.name("Button1");
        private final static By loc_lnkForgotPass = By.id("lnkForgotPass");
        private final static By loc_lnkRegister = By.id("lnkRegister");

        public LoginPage(WebDriver driver){
                super(driver);
        }

        //TODO: Write up all the method that handle the possible web interactions done on the page
        public void navigateToLoginPage(String URL){
                navigateToTestSite(URL);
        }

        public void enterEmail(String email){
                sendKeys(loc_txtEmail, email);
        }

        public void enterPassword(String password){
                sendKeys(loc_txtPassword, password);
        }

        public void clickLoginBtn(){
                clickElement(loc_btnLogin);
        }

        public void clickForgotPassLnk(){
                clickElement(loc_lnkForgotPass);
        }

        public void clickRegisterLnk(){
                clickElement(loc_lnkRegister);
        }

        public void loginToCoreOps(String email, String password){
          enterEmail(email);
          enterPassword(password);
          clickLoginBtn();
        }

        //TODO: Write up all validation method for assertion purposes.
        public boolean isUserOnLoginPage(String expectedTitle, String expectedLogo){
                String logoText = getPageElementContent(loc_spnLogo);
                String pageTitle = getPageTitle();

                return expectedTitle.equals(pageTitle)
                        && expectedLogo.equals(logoText);
        }
}
