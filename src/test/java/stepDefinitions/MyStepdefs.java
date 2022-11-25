package stepDefinitions;

import io.cucumber.java.en.Given;
import utilities.ConfigReader;

import static stepDefinitions.Hooks.driver;

public class MyStepdefs {
    @Given("user goes to {string}")
    public void userGoesTo(String endpoint) {
        driver.get((ConfigReader.getProperty("url") + endpoint));
    }
}
