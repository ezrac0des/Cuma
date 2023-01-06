Feature: US135

  @ezra_login
  Scenario: negative scenario
    Given user does not create a certificate
    Then user verifies the status code is 200
    Then user verifies that the response is as expected
      | success | false            |
      | descr   | Date is required |

  @ezra_login
  Scenario: positive scenario
    Given user creates a certificate
    Then user verifies the status code is 200
    Then user verifies that the response is as expected
      | success | true |
#      | certificate.title        | faker |
#      | certificate.organization | faker |
    When user gets all user information
    Then user verifies the status code is 200
    Then user verifies certificate id is present

    When user updates a certificate
    Then user verifies the status code is 200
    Then user verifies that the response is as expected
      | success | true |

    When user deletes a certificate
    Then user verifies the status code is 200
    Then user verifies that the response is as expected
      | success | true |
    When user gets all user information with active
    Then user verifies the status code is 200
    Then user verifies certificate id is not present
#    Then user verifies that the response is as expected
#      | profile.certificates[last()].isActive | false |
