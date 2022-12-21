#Name and Surname are required (at least 1 letter character)
#Pacific Standard Time should appear in the Time Zone menu
#One of the Time Zone in the Time Zone drop-down menu must be select
#When you click the Add button, the warning "Are you sure to client timezone Pacific Standard Time" (ets) should appear.
#When you click the Add button, if the name field is empty, a warning will appear: ''Name is required''
#When you click the Add button, if the surname field is empty, a warning will appear: ''Surname is required''
#Any Country in the Country drop-down menu must be select
#Any State in the State dropdown menu must be select
#State must be related to Country
#City must be functional
#Any City in the City drop-down menu must be select
#City must be related to State
#Client should be created with the necessary information.
#When you create the client, the notification should appear: ''New Client is successfully added.''
#The added client should be seen at the top of the "Clients" page on the left panel.

Feature: US041

  Background:
    Given user goes to "/login"
    When user logins to the website
    And user clicks on Add Client

  Scenario: US041-TC001 -> visibility ve functionality
    Then user verifies labels are visible
      | First Name   |
      | Last Name    |
      | Middle Name  |
      | Gender       |
      | Phone Number |
      | Occupation   |
      | E-Mail       |
      | Zip Code     |
      | Country      |
      | State        |
      | City         |
      | Address      |
      | Time Zone    |
    Then user verifies inputs are clickable

  Scenario: US041-TC002 -> dropdown
    When user opens on Gender dropdown
    Then user verifies all three options are visible
      | Female |
      | Male   |
      | Other  |

  Scenario Outline: US041-TC003 -> positive
    When user enters "<name>", "<last name>", "<occupation>", "<email>"
    When user clicks on Add Client button
    Then user verifies timezone message is visible
    When user clicks on Yes
    Then user verifies "New Client is successfully added." is visible
    When user clicks on Clients
    Then user verifies new client is visible on Clients page
    And user deletes the client
    Examples:
      | name | last name | occupation | email            |
      | Ali  | Murat     | tester     | qwerty@gmail.com |


  Scenario Outline: US041-TC004 -> negative scenario
    When user enters "<name>", "<last name>", "<occupation>", "<email>"
    When user clicks on Add Client button
    Then user verifies timezone message is visible
    When user clicks on Yes
    Then user verifies alert size is "<alertNumber>"
    And user verifies alert messages are "<alertMessage>"
    Examples:
      | name | last name | occupation | email | alertNumber | alertMessage                                               |
      |      |           |            |       | 2           | Please input your First Name!,Please input your Last Name! |
      |      | hi        |            |       | 1           | Please input your First Name!                              |
      | hi   |           |            |       | 1           | Please input your Last Name!                               |

