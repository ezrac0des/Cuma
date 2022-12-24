Feature:

  @ezra_login
  Scenario:
    When user creates a coupon
    Then user verifies the status code is 200
    Then user verifies that the response is as expected
      | success                     | true |
      | promoCode.startsAt.timezone | UTC  |

    When user gets all coupon info
    Then user verifies the status code is 200
    Then user verifies created coupon is in the list

    When user deletes the created coupon
    Then user verifies the status code is 200
    Then user verifies that the response is as expected
      | success | true |

    When user gets all coupon info
    Then user verifies the status code is 200
    Then user verifies created coupon is not in the list