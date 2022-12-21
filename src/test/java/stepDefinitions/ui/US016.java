package stepDefinitions.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;

import java.util.List;

public class US016 {
    HomePage hp = new HomePage();

    @When("user enters {string} to name input")
    public void userEntersToNameInput(String name) {
        hp.name.sendKeys(name);
    }

    @And("user enters {string} to surname input")
    public void userEntersToSurnameInput(String surname) {
        hp.surname.sendKeys(surname);
    }

    @And("user enters {string} to email input")
    public void userEntersToEmailInput(String email) {
        hp.email.sendKeys(email);
    }

    @When("user enters {string}")
    public void userEnters(String password) {
        hp.password.sendKeys(password);
    }

    @Then("user verifies green list's size is {string}")
    public void userVerifiesGreenListSSizeIs(String green) {
        int greenInt = Integer.parseInt(green);
        Assert.assertEquals(greenInt, hp.greenList.size());
    }

    @And("user verifies red list's size is {string}")
    public void userVerifiesRedListSSizeIs(String red) {
        int redInt = Integer.parseInt(red);
        Assert.assertEquals(redInt, hp.redList.size());
    }

    @And("user verifies sign up button is not clickable")
    public void userVerifiesSignUpButtonIsNotClickable() {
        Assert.assertFalse(hp.invalidSignup.isEnabled());
//        Assert.assertTrue(!hp.signupButton.isEnabled());
    }

    @And("user verifies sign up button is clickable")
    public void userVerifiesSignUpButtonIsClickable() {
        Assert.assertTrue(hp.validSignup.isEnabled());
    }

    @When("user enters password, then user verifies sizes of the lists")
    public void userEntersPasswordThenUserVerifiesSizesOfTheLists(DataTable dt) {
        List<String> passwords = dt.column(0);
        List<String> greenList = dt.column(1);
        List<String> redList = dt.column(2);

        for (int i = 0; i < passwords.size(); i++) {
            hp.password.sendKeys(passwords.get(i));
            Assert.assertEquals(Integer.parseInt(greenList.get(i)), hp.greenList.size());
            Assert.assertEquals(Integer.parseInt(redList.get(i)), hp.redList.size());
            Assert.assertFalse(hp.invalidSignup.isEnabled());
            hp.password.clear();
        }
    }
}
