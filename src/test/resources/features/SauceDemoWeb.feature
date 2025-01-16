@web @agent3
Feature: Test Scenarios for SauceDemo Application

  @id:1 @swagLabs @login @swagLabsStaticData @smokeTest
  Scenario Outline: Login with valid credentials, static data
    Given customer admin navigates to sauceDemo page
    When login with credentials
    Then should login and see available products
    Examples:
    |user           |password    |
    | standard_user |secret_sauce|

  Scenario Outline:  Login with valid credentials
    Given customer admin navigates to sauceDemo page
    When login with credentials
    |user   | password   |
    |<user> | <password> |
    Then should login and see available products
    Examples:
    |@externaldata@demo/WebDataSauceDemo.csv|

