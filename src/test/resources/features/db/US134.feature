# US_134 As a user, I should be able to see user informations in the user table.
#I should see user informations in the correct columns, eg : email.
#The emails should be unique."

Feature: us134

  Scenario: user table
    Given user connects to the database
    Then user verifies "user" table has the following columns
      | id              |
      | security_id     |
      | subscription_id |
      | availability_id |
      | email           |
    Then user verifies all data are unique in "email" column of "user" table
