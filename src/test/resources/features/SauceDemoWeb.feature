@web @agent3
Feature: Test Scenarios for SauceDemo Application

  Background:
    Given customer admin navigates to sauceDemo page

  @id:1  @login @swagLabsStaticData @smokeTest
  Scenario Outline: Login with valid credentials, static data
    When login with credentials
    | user  | password   |
    |<user> | <password> |
    Then should login and see available products
    Examples:
    | user          | password   |
    | standard_user |secret_sauce|


  Scenario: Sort products by price (low to high)
    Given the user is logged into Sauce Demo
    When the user sorts the products by "Price (low to high)"
    Then the products should be displayed in ascending order of price

  @swagLabs
  Scenario: Add products based on price criteria
    Given the user is logged into Sauce Demo
    When the user adds the products with the lowest, highest, and any price to the cart
    Then the selected products should be in the cart
