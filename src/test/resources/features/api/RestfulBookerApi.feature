Feature: Booking managment in Restful Booker Api

  @api
  Scenario: Create a new booking
    Given the "user" connect to API "https://restful-booker.herokuapp.com"
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