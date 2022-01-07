Feature: Create Users

  Scenario: Create users
    Given I am on Website
    When I click sign in button
    And I enter user personal information
    |First User |
    |Second User|
    And I click register button
    Then I am successfully logged in
   
 @signup  
  Scenario: User creation fails with invalid data
    Given I am on Website
    When I click sign in button
    And I enter user personal information
    |Invalid Details|
    And I click register button
    Then I get Warning message