Feature: Booking managment in Restful Booker Api

  Background:
    Given the "user" connect to API "https://restful-booker.herokuapp.com"

  Scenario: Create a new booking
    When the user creates a booking with the following details:
        """
      {
        "firstname": "John",
        "lastname": "Doe",
        "totalprice": 120,
        "depositpaid": true,
        "bookingdates": {
          "checkin": "2023-01-01",
          "checkout": "2023-01-10"
        },
        "additionalneeds": "Breakfast"
      }
      """
    Then the response code should be 200
    And the response schema should match "schemas/create_booking_schema.json"

  @api
  Scenario: Get an existing booking
    When the user retrieves the booking with ID 1
    Then the response code should be 200
    And the response schema should match "schemas/get_booking_schema.json"

  Scenario: Get a non-existing booking
    Given a booking does not exist with ID 99999
    When the user retrieves the booking with ID 99999
    Then the response code should be 404