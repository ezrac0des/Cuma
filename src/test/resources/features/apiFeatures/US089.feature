@api
Feature:

  Scenario: TC001 - positive scenario
    Given user connects to the "/promoCode/add" with payload
      | promoCode | startedAt                     | enddedAt                      | usersLimit | discountType | discountRate | category | description            |
      | yuzde151  | Tue, 20 Dec 2022 16:17:06 GMT | Tue, 20 Dec 2022 16:17:06 GMT | 5          | percentage   | 15           | 2098     | yuzde on indirim yapar |
    Then user verifies the status code is 200
    Then user verifies that the response is as expected
      | success                     | true     |
      | promoCode.code              | yuzde151 |
      | promoCode.numberOfUsers     | 5        |
      | promoCode.startsAt.timezone | UTC      |
      | promoCode.categories[0]     | 2098     |

    And user connects to the "/promoCode/getCoupons"
    Then user verifies the status code is 200
    Then user verifies 349 is not in the list

    When user connects to the "/promoCode/deleteCoupon" with payload
      | couponId |
      | 349      |
    Then user verifies the status code is 200
    Then user verifies that the response is as expected
      | success | true |

    And user connects to the "/promoCode/getCoupons"
    Then user verifies the status code is 200
    Then user verifies 349 is not in the list
