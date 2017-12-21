Feature: Check login screen fields validation of the Trujet web site
   In order to test login screen fields
   As a user I should have Truejet site accessible

  Background: 
    Given user navigates to URL : "${application.url}" application

  @Login
  Scenario: Verify that user can login to truejet site
    Given that I am on Trujet landing screen
    When I click on "TruFliers, Login" button
    Then I should see "Login" screen opens
    When I enter username "${username}"
    When I enter password "${password}"
    When I click on Login button
    Then the user login should be successful
    
  @Login
  Scenario: Verify that user is not able to login when he provide valid username invalid password
 		Given that I am on Trujet landing screen
  	When I click on "TruFliers, Login" button
   	Then I should see "Login" screen opens
   	When I enter username "${username}"
   	When I enter password "invalidpwd"
   	When I click on Login button
   	Then the user login should fail