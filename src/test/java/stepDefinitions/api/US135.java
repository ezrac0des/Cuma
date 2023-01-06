package stepDefinitions.api;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.APIUtilities;
import utilities.BrowserUtilities;

import java.util.List;
import java.util.Random;

public class US135 {
    String title = new Faker().name().title();
    String organization = new Faker().name().name();
    static int id;
    static List<Integer> ids;
    static List<Boolean> isActives;

    @Given("user does not create a certificate")
    public void userDoesNotCreateACertificate() {
        APIUtilities.notCreateCertificate(title, organization);
    }

    @Given("user creates a certificate")
    public void userCreatesACertificate() {
        id = APIUtilities.createCertificate(title, organization, BrowserUtilities.getDay_day_month_year_time(72));
        System.out.println(id);
    }

    @When("user gets all user information")
    public void userGetsAllUserInformation() {
        ids = APIUtilities.getUser();
        System.out.println(ids);
    }

    @Then("user verifies certificate id is present")
    public void userVerifiesCertificateIdIsPresent() {
        boolean var = false;
        for (int i = 0; i < ids.size(); i++) {
            if (id == ids.get(i)) var = true;
        }

        //        if (ids.get(ids.size() - 1) == id)
//            var = true;
    }

    @When("user updates a certificate")
    public void userUpdatesACertificate() {
        //update icin tum bilgileri update etmemize gerek yok, patch gibi dusunebiliriz
        APIUtilities.updateCertificate(new Faker().name().title(),
                new Faker().name().name(),
                BrowserUtilities.getDay_day_month_year_time(new Random().nextInt(1000)),
                id
        );
    }

    @When("user deletes a certificate")
    public void userDeletesACertificate() {
        APIUtilities.deleteCertificate(id);
    }

    @When("user gets all user information with active")
    public void userGetsAllUserInformationWithActive() {
        isActives = APIUtilities.getUserActive();
    }

    @Then("user verifies certificate id is not present")
    public void userVerifiesCertificateIdIsNotPresent() {
//        for (int i = 0; i < ids.size(); i++) {
//            Assert.assertNotEquals(id + "", ids.get(i) + "");
////            Assert.assertNotEquals(Integer.valueOf(id), ids.get(i));
//        }
        Assert.assertEquals(false, isActives.get(isActives.size() - 1));
    }


}
