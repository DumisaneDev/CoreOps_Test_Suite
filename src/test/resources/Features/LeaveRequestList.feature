@LeaveRequest
Feature: Leave Request List Feature

  Background:
    Given i am logged into the system as a valid user.
    When I click the leave dropdown
    And i click the leave request list tab

  @DashboardDisplay
  Scenario: Leave list displays the correct information
    Given i am on the leave Request list
    When I see the table
    Then the table's entries should clearly display the employee’s name,type of Leave start and end dates, days remaining, reason, status. and action.

@CancelRequest
Scenario: employee can cancel their leave request
  Given i am on the leave Request list
  When i click the review button on a pending request
  And land on the edit leave request page
  And i click on the Cancel button
  Then the leave request's status should change to Cancelled;

  @ApproveRequest
  Scenario: manager can approve a leave request
    Given i am on the leave Request list
    When i click the review button on a pending request waiting to be approved.
    And land on the edit leave request page
    And i click on the Approve button
    And I confirm the approval of the leave request
    Then the leave request's status should change to Approved;

    @RejectedRequest
  Scenario: manager can reject a leave request
    Given i am on the leave Request list
    When i click the review button on a pending request to be rejected.
    And land on the edit leave request page
    And i click on the Reject button
    And I confirm the Rejection of the leave request
    Then the leave request's status should change to Rejected;


   @SearchWithEmployeeName
   Scenario Outline: verify search functionality using employee name
     Given i am on the leave Request list
     When i enter employees name <employee> in the search bar
     Then  i should only see request that are for the employee<test_data>

     Examples:
       | test_data       |
       | Dumisane Mbhele |

     @SearchWithLeaveType
  Scenario Outline: verify search functionality using leave type
    Given i am on the leave Request list
    When in the search bar, i enter leave Type <type>
    Then  i should only receive request that are for the leave type <type>

    Examples:
      | type   |
      | Annual |


Scenario: verify sorting feature, specifically sorting in ascending alphabetical using employee name
  Given i am on the leave Request list
  When i click the up arrow underneath the employee Name
  Then the list of employee names in the requests should be sorted alphabetically

@DescendingOrderUsingLeaveStatus
Scenario: verify sorting feature, specifically sorting in reverse-alphabetical order using the leave statuses of each request
  Given i am on the leave Request list
  When i click the down arrow underneath the Status header on the table
  Then the list of leave statuses should be sorted in reverse alphabetical order.