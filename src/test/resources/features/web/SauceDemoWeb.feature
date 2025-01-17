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

  Scenario: Add products based on price criteria
    Given the user is logged into Sauce Demo
    When the user adds the products with the lowest, highest, and any price to the cart
    Then the selected products should be in the cart


  Scenario: Remove one product from the cart
    Given the user has added products to the cart
    When the user removes a product from the cart
    Then the product should no longer be in the cart

  Scenario: Complete the checkout process
    Given the user proceeds to checkout
    And the user completes the checkout process with "Estebanano", "Castillo", and "230050"
    Then the total number of items in the cart should be 3
    And the total with tax should be correct based on 67.97
    And the success message should be visible

  @swagLabs
  Scenario: Validate handling of incorrect product price
    Given the user has added the following products to the cart:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    When the user completes the checkout process with "Jane", "Smith", and "54321"
    Then the price of the most expensive product should match the expected price
    #incorrect price
      | Sauce Labs Backpack  | 45.00         |
