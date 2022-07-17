Feature: Validating place api

  @AddPlace @Regression
  Scenario Outline: Verify if place is added successfully
    Given Add place payload with "<name>" "<language>" "<address>"
    When User calls "ADD_PLACE_API" with "POST" http request
    Then The API call is success with status code 200
    And "status" in response body should be "OK"
    And "scope" in response body should be "APP"
    And Verify place_id created map to "<name>" using "GET_PLACE_API"

    Examples:
      | name          | language | address |
      | Amit Sharma   | English  | Haryana |
      | Harman Sharma | Hindi    | Kaithal |


    @DeletePlace @Regression
    Scenario: Verify delete place functionally
      Given DeletePlace payload
      When User calls "DELETE_PLACE_API" with "DELETE" http request
      Then The API call is success with status code 200
      And "status" in response body should be "OK"