
@tag
Feature: Title of your feature
  I want to use this template for my feature file
  
  Background:
Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Purchasing the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And  Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                    | password     |productName |
      | john.smith123@gmail.com |Johnsmith@123 |ZARA COAT 3 |
      
