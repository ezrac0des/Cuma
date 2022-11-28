Feature: HomePage

  @009
  @day1
  Scenario: As a user, I should be able to see the Testimonials section on the homepage.
    Given go to website "https://test.hypnotes.net/"
    When go to testimonials
    When click on first dot

    Then assert previous button is not enabled
    Then assert next button is enabled
    Then first testimonials is displayed

    When click on the last dot
    Then assert previous button is enabled
    Then assert next button is not enabled
    Then last testimonials is displayed
