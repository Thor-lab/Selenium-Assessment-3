Feature: Order T-shirt

  Background: Login into application
    Given I am on Website
    When I click sign in button
    And I enter username and password
    Then I am successfully logged in
    
  Scenario: Verify Order History
    Given I am logged in to application
    When I select the T-shirt
    And I add T-shirt to cart
    And I place the order
    Then I can see ordered T-shirt in Order History
   