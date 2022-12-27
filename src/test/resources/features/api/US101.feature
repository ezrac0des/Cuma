@ezra_login
Feature:

#  @ezra_login
  Scenario:
    Given user creates a timeoff block
    Then user verifies the status code is 200
    Then user verifies that the response is as expected
      | status                | true  |
      | data[0].isRecurring   | false |
      | data[0].isAllDay      | false |
      | data[0].locationTitle | home  |


