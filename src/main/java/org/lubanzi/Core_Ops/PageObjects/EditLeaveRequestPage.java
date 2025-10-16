package org.lubanzi.Core_Ops.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * EditLeaveRequestPage represents the page for editing leave requests and provides methods for interaction.
 * Extends BasePage to inherit common page operations.
 */
public class EditLeaveRequestPage extends BasePage{
        /**
         * All locators below are used to identify elements on the Edit Leave Request page.
         * They use a combination of XPath and ID-based selectors for reliable element identification.
         */
        private static final By loc_lblHeader = By.xpath("//h1");
        private static final By loc_btnApprove = By.id("btnUpdate");
        private static final By loc_btnReject = By.id("btnReject");
        private static final By loc_btnCancel = By.id("btnCancel");

        /**
         * Initializes the EditLeaveRequestPage with the provided WebDriver instance.
         * @param driver WebDriver instance to use for browser automation
         */
        public EditLeaveRequestPage(WebDriver driver){
                super(driver);
        }

        //the methods that handle the possible web interactions done on the page
        /**
         * Clicks the Cancel button to exit the edit leave request page.
         */
        public void clickCancelBtn(){
                clickElement(loc_btnCancel);
        }

        /**
         * Clicks the Approve button to approve the leave request.
         */
        public void clickApproveBtn(){
                clickElement(loc_btnApprove);
        }

        /**
         * Clicks the Reject button to reject the leave request.
         */
        public void clickRejectBtn(){
                clickElement(loc_btnReject);
        }

        /**
         * Confirms any alert dialog that appears after an action.
         */
        public void confirmAction(){
                acceptAlert();
        }

        /**
         * Verifies if the user is on the correct edit leave request page by checking the header text.
         * @param expectedHeader Expected header text to verify
         * @return true if the header matches expectations, false otherwise
         */
        public boolean isUserOnTheRightRequest(String expectedHeader){
                String actualHeader = getPageElementContent(loc_lblHeader);
                return expectedHeader.equals(actualHeader);
        }
}
