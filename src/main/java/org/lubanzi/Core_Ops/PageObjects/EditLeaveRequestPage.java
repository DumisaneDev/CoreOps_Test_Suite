package org.lubanzi.Core_Ops.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditLeaveRequestPage extends BasePage{
        //TODO: Find, Declare and initialize all locators found on the Edit leave page.
        private static final By loc_lblHeader = By.xpath("//h1");
        private static final By loc_txtEmployeeName = By.id("txtEmployeeName");
        private static final By loc_txtLeaveType = By.id("txtLeaveType");
        private static final By loc_btnApprove = By.id("btnUpdate");
        private static final By loc_btnReject = By.id("btnReject");
        private static final By loc_btnCancel = By.id("btnCancel");

        public EditLeaveRequestPage(WebDriver driver){
                super(driver);
        }

        //TODO: Write up all the method that handle the possible web interactions done on the page
        public void clickCancelBtn(){
                clickElement(loc_btnCancel);
        }

        public void clickApproveBtn(){
                clickElement(loc_btnApprove);
        }

        public void clickRejectBtn(){
                clickElement(loc_btnReject);
        }

        public void confirmAction(){
                acceptAlert();
        }


        //TODO: Write up all validation method for assertion purposes
        public boolean isUserOnTheRightRequest(String expectedHeader){
                String actualHeader = getPageElementContent(loc_lblHeader);

                return expectedHeader.equals(actualHeader);
        }
}
