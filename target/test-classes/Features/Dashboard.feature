@Dashboard
Feature: Dashboard Feature
  As a Lubanzi Employee/Manager,
  I want to view a high-level summary of key company data on a dashboard,
  So that I can quickly monitor project status, employee activity, and overall performance.

  Background:
    Given i am logged into the system as a valid user.

  Scenario: Verify that the dashboard correctly displays the correct key data values.
    Given i am on the dashboard page
    Then I should see a clear summary displaying the total count for projects, employees, submitted_timesheets and leave_Requests.

