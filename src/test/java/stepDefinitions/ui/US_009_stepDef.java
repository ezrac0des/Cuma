package stepDefinitions.ui;


import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.LoginPage;
import utilities.BrowserUtilities;

import java.util.List;

import static stepDefinitions.Hooks.driver;


public class US_009_stepDef {

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Given("go to website {string}")
    public void go_to_website(String url) {

        driver.get(url);
        BrowserUtilities.waitFor(5);
    }

    @When("go to testimonials")
    public void go_to_testimonials() {

        BrowserUtilities.scrollToElement(homePage.button_next);
        BrowserUtilities.waitFor(5);
    }

    @When("click on first dot")
    public void click_on_first_dot() {
        homePage.dots.get(0).click();


    }

    @Then("assert previous button is not enabled")
    public void assert_previous_button_is_not_enabled() {

        Assert.assertFalse("previous button gosterildi", homePage.button_previous.isEnabled());
    }

    @Then("assert next button is enabled")
    public void assert_next_button_is_enabled() {
        Assert.assertTrue(homePage.button_next.isEnabled());
    }

    @Then("first testimonials is displayed")
    public void first_testimonials_is_displayed() {
        Assert.assertTrue(homePage.testimonials.get(0).isDisplayed());
    }

    @When("click on the last dot")
    public void click_on_the_last_dot() {

        BrowserUtilities.waitFor(2);

        homePage.dots.get(homePage.dots.size() - 1).click();
        BrowserUtilities.waitFor(2);
    }

    @Then("assert previous button is enabled")
    public void assert_previous_button_is_enabled() {
        Assert.assertTrue(homePage.button_previous.isEnabled());

    }

    @Then("assert next button is not enabled")
    public void assert_next_button_is_not_enabled() {
        Assert.assertFalse(homePage.button_next.isEnabled());

    }

    @Then("last testimonials is displayed")
    public void last_testimonials_is_displayed() {
        List<WebElement> testimonials = homePage.testimonials;
        Assert.assertTrue(testimonials.get(testimonials.size() - 1).isDisplayed());

    }

}