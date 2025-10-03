package org.lubanzi.CoreOps.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.lubanzi.Core_Ops.PageObjects.DashboardPage;
import org.lubanzi.Core_Ops.PageObjects.EditLeaveRequestPage;
import org.lubanzi.Core_Ops.PageObjects.LeaveRequestListPage;
import org.lubanzi.Core_Ops.Utils.ConfigReader;

import static org.testng.Assert.assertTrue;


public class LeaveRequestListStepDef {
        private final LeaveRequestListPage leaveRequestCoreObj = new LeaveRequestListPage(Hooks.driver);
        private final DashboardPage dashboardCoreObj = new DashboardPage(Hooks.driver);
        private final EditLeaveRequestPage editRequestCoreObj = new EditLeaveRequestPage(Hooks.driver);

        @When("I click the leave dropdown")
        public void I_click_the_Leave_dropdown(){
                dashboardCoreObj.openLeaveDropdown();
        }

        @And("i click the leave request list tab")
        public void iClickTheLeaveRequestListTab() {
                dashboardCoreObj.clickLeaveRequestList();
        }

        @Given("i am on the leave Request list")
        public void iAmOnTheLeaveRequestList() {
                String expectedBannerMsg = ConfigReader.getProperty("leaveBanner");
                assertTrue(leaveRequestCoreObj.isUserOnLeaveRequestListPage(expectedBannerMsg));
        }

        @When("I see the table")
        public void iSeeTheTable() {
                assertTrue(leaveRequestCoreObj.hasTableLoaded());
        }

        @Then("the table's entries should clearly display the employee’s name,type of Leave start and end dates, days remaining, reason, status. and action.")
        public void theTableSEntriesShouldClearlyDisplayTheEmployeeSNameTypeOfLeaveStartAndEndDatesDaysRemainingReasonStatusAndAction() {
                assertTrue(leaveRequestCoreObj.isTableContentsCorrect(), "Incorrect value found in the table");
        }

        @When("i click the review button on a pending request")
        public void iClickTheReviewButtonOnAPendingRequest() {
                leaveRequestCoreObj.clickCancelRequest();
        }

        @And("land on the edit leave request page")
        public void landOnTheEditLeaveRequestPage() {
                String expectedHeader = ConfigReader.getProperty("EditRequestHeader");
                assertTrue(editRequestCoreObj.isUserOnTheRightRequest(expectedHeader));
        }

        @And("i click on the Cancel button")
        public void iClickOnTheCancelButton() {
                editRequestCoreObj.clickCancelBtn();
        }

        @Then("the leave request's status should change to Cancelled;")
        public void theLeaveRequestSStatusShouldChangeToCancelled() {
                String expectedStatus = ConfigReader.getProperty("leaveCancellationResult");
                assertTrue(leaveRequestCoreObj.didStatusChange(expectedStatus));
        }

        @When("i click the review button on a pending request waiting to be approved.")
        public void iClickTheReviewButtonOnAPendingRequestWaitingToBeApproved() {
                leaveRequestCoreObj.clickApproveRequest();
        }

        @And("i click on the Approve button")
        public void iClickOnTheApproveButton() {
                editRequestCoreObj.clickApproveBtn();
        }

        @And("I confirm the approval of the leave request")
        public void iConfirmTheApprovalOfTheLeaveRequest() {
                editRequestCoreObj.acceptAlert();
                editRequestCoreObj.clickCancelBtn();
        }

        @Then("the leave request's status should change to Approved;")
        public void theLeaveRequestSStatusShouldChangeToApproved() {
                String expectedStatus = ConfigReader.getProperty("leaveStatusForAApprovedRequest");
                assertTrue(leaveRequestCoreObj.isRequestApproved(expectedStatus));
        }

        @When("i click the review button on a pending request to be rejected.")
        public void iClickTheReviewButtonOnAPendingRequestToBeRejected() {
                leaveRequestCoreObj.clickRejectRequest();
        }

        @And("i click on the Reject button")
        public void iClickOnTheRejectButton() {
                editRequestCoreObj.clickRejectBtn();
        }

        @And("I confirm the Rejection of the leave request")
        public void iConfirmTheRejectionOfTheLeaveRequest() {
                editRequestCoreObj.acceptAlert();
                editRequestCoreObj.clickCancelBtn();
        }

        @Then("the leave request's status should change to Rejected;")
        public void theLeaveRequestSStatusShouldChangeToRejected() {
                String expectedStatus = ConfigReader.getProperty("leaveStatusForARejectedRequest");
                assertTrue(leaveRequestCoreObj.isRequestRejected(expectedStatus));
        }

        /**
        * Search functionality cases
        * */
        @When("i enter {} in the search bar")
        public void iEnterInTheSearchBar() {
                String searchValue = ConfigReader.getProperty("EmployeeNameSearch");
                leaveRequestCoreObj.searchList(searchValue);
        }

        @Then("i should only see request that are for the {}")
        public void iShouldOnlySeeRequestThatAreForThe() {
                String EmployeeName = ConfigReader.getProperty("EmployeeNameSearch");
                assertTrue(leaveRequestCoreObj.areRequestForEmployee(EmployeeName));
        }

        @When("in the search bar, i enter leave Type {}")
        public void inTheSearchBarIEnterLeaveType(String arg0) {
                String leaveTypes = ConfigReader.getProperty("LeaveTypeSearch");
                leaveRequestCoreObj.searchList(leaveTypes);
        }

        @Then("i should only receive request that are for the leave type {}")
        public void iShouldOnlyReceiveRequestThatAreForTheLeaveType(String arg0) {
                String leaveType = ConfigReader.getProperty("LeaveTypeSearch");
                assertTrue(leaveRequestCoreObj.areRequestForLeaveType(leaveType));
        }

        /**
         * Sorting Test cases
         */
        @When("i click the up arrow underneath the employee Name")
        public void iClickTheUpArrowUnderneathTheEmployeeName() {
                leaveRequestCoreObj.clickEmployeeAscendingSortBtn();
        }

        @Then("the list of employee names in the requests should be sorted alphabetically")
        public void theListOfEmployeeNamesInTheRequestsShouldBeSortedAlphabetically() {
                assertTrue(leaveRequestCoreObj.isListOfEmployeesOnRequestSortedAlphabetically());
        }


        @When("i click the down arrow underneath the Status header on the table")
        public void iClickTheDownArrowUnderneathTheStatusHeaderOnTheTable() {
                leaveRequestCoreObj.clickStatusDescendingSortBtn();
        }

        @Then("the list of leave statuses should be sorted in reverse alphabetical order.")
        public void theListOfLeaveStatusesShouldBeSortedInReverseAlphabeticalOrder() {
                assertTrue(leaveRequestCoreObj.isListOfLeaveStatuesSortedReverseAlphabetically());
        }
}
