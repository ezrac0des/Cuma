package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.ClientsPage;
import pages.LoginPage;
import utilities.BrowserUtilities;
import utilities.ConfigReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static stepDefinitions.Hooks.driver;

public class US041 {
    LoginPage lp = new LoginPage();
    ClientsPage cp = new ClientsPage();
    WebElement element;

    @When("user logins to the website")
    public void userLoginsToTheWebsite() {
        lp.loginUsername.sendKeys(ConfigReader.getProperty("email"));
        lp.loginPassword.sendKeys(ConfigReader.getProperty("password"));
        lp.loginButton.click();
    }

    @And("user clicks on Add Client")
    public void userClicksOnAddClient() {
        cp.addClientSB.click();
    }

    @Then("user verifies labels are visible")
    public void userVerifiesLabelsAreVisible(DataTable dataTable) {
        List<String> labels = dataTable.column(0);

        for (int i = 0; i < labels.size(); i++) {
            element = driver.findElement(By.xpath("//label[.='" + labels.get(i) + "']"));

            Assert.assertTrue(element.isDisplayed());
            System.out.println(element.getText());
        }
    }

    @Then("user verifies inputs are clickable")
    public void userVerifiesInputsAreClickable() {
        for (int i = 0; i < cp.inputs.size(); i++) {
            Assert.assertTrue(cp.inputs.get(i).isEnabled());
        }
    }

    @When("user opens on Gender dropdown")
    public void userOpensOnGenderDropdown() {
        cp.genderDropdown.click();
    }

    @Then("user verifies all three options are visible")
    public void userVerifiesAllThreeOptionsAreVisible(DataTable dataTable) {
        Assert.assertEquals(3, cp.genders.size());
//        Assert.assertTrue(cp.genders.stream().anyMatch(t -> t.getText().equals("Male")));
//        Assert.assertTrue(cp.genders.stream().anyMatch(t -> t.getText().equals("Female")));
//        Assert.assertTrue(cp.genders.stream().anyMatch(t -> t.getText().equals("Other")));

        List<String> genders = dataTable.column(0);
        for (int i = 0; i < genders.size(); i++) {
            element = driver.findElement(By.xpath("//div[.='" + genders.get(i) + "']"));
            BrowserUtilities.staleElementVisible(element);
        }
    }

    @When("user enters {string}, {string}, {string}, {string}")
    public void userEnters(String fn, String ln, String o, String e) {
        cp.firstName.sendKeys(fn);
        cp.lastName.sendKeys(ln);
        cp.occupation.sendKeys(o);
        cp.email.sendKeys(e);
    }

    @When("user clicks on Add Client button")
    public void userClicksOnAddClientButton() {
        BrowserUtilities.clickWithJS(cp.addClient);
    }

    @Then("user verifies timezone message is visible")
    public void userVerifiesTimezoneMessageIsVisible() {
        BrowserUtilities.waitFor(2);
        Assert.assertTrue(cp.timezoneMessage.isDisplayed());
        Assert.assertEquals("Are you sure to client timezone Eastern Standard Time", cp.timezoneMessage.getText());
    }

    @When("user clicks on Yes")
    public void userClicksOnYes() {
        cp.yes.click();
    }

    @Then("user verifies {string} is visible")
    public void userVerifiesIsVisible(String popup) {
//        BrowserUtilities.waitFor(4);
        BrowserUtilities.waitForVisibility(cp.successMessage, 25);
        BrowserUtilities.waitForPageToLoad(15);
        Assert.assertEquals(popup, cp.successMessage.getText());
    }

    @When("user clicks on Clients")
    public void userClicksOnClients() {
        BrowserUtilities.waitForClickablility(cp.clients, 25);
        BrowserUtilities.clickWithJS(cp.clients);
    }

    @Then("user verifies new client is visible on Clients page")
    public void userVerifiesNewClientIsVisibleOnClientsPage() {
        Assert.assertTrue(
                cp.clientsNames.stream().anyMatch(t -> t.getText().equals("Ali Murat"))
        );
    }

    @And("user deletes the client")
    public void userDeletesTheClient() {
        cp.delete.click();
        cp.deleteYes.click();
    }

    @Then("user verifies alert size is {string}")
    public void userVerifiesAlertSizeIs(String no) {
        Assert.assertEquals(Integer.parseInt(no), cp.alerts.size());
    }

    @And("user verifies alert messages are {string}")
    public void userVerifiesAlertMessagesAre(String messages) {
        BrowserUtilities.waitFor(3);
        String[] mes = messages.split(",");

        /*for (int i = 0; i < mes.length; i++) {
            int finalI = i;
            Assert.assertTrue(
                    cp.alerts.stream().anyMatch(t -> t.getText().equals(mes[finalI]))
            );
        }*/

        //2. yol
        List<String> alertsTexts = new ArrayList<>();
        for (int i = 0; i < cp.alerts.size(); i++) {
            alertsTexts.add(cp.alerts.get(i).getText());
        }

        boolean kontrol = false;
        for (int i = 0; i < mes.length; i++) { //first
            for (int j = 0; j < alertsTexts.size(); j++) {
                if (mes[i].equals(alertsTexts.get(j))) kontrol = true;
            }
        }
        Assert.assertTrue(kontrol);
    }
}
