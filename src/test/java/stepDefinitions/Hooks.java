package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.CommonPage;
import utilities.BrowserUtilities;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;

public class Hooks {
    public static WebDriver driver;
    public static CommonPage commonPage;
    public static String userSessionID;

    @Before
    public void setUp() {
        driver = Driver.getDriver();
        commonPage = new CommonPage() {
        };
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshots");
        }
        Driver.closeDriver();
    }

    @Before("@ezra_login")
    public void ezra_login(){
        login_qa(ConfigReader.getProperty("email"), ConfigReader.getProperty("password"));
    }

    public String login_qa(String email, String password) {
        List<String> windowHandles = new ArrayList<>(Driver.getDriver().getWindowHandles());
        if (windowHandles.size() > 1) {
            driver.close();
            BrowserUtilities.switchToWindowWithIndex(0);
        }

        Driver.getDriver().get("https://test.hypnotes.net/login");
        BrowserUtilities.waitFor(3);
        BrowserUtilities.waitForClickability(commonPage.homePage().hypnotesLogo, 10);

        try {
            commonPage.login().login.click();
        } catch (Exception e) {
            commonPage.homePage().hypnotesLogo.click();
            driver.manage().deleteAllCookies();
            BrowserUtilities.refreshPage();
            BrowserUtilities.waitForClickability(commonPage.login().login, 30);
            commonPage.login().login.click();
        }

        BrowserUtilities.waitFor(3);
        commonPage.login().loginUsername.sendKeys(email);
        commonPage.login().loginPassword.sendKeys(password);
        BrowserUtilities.clickWithJS(commonPage.login().loginButton);
        BrowserUtilities.waitFor(3);

        try {

            BrowserUtilities.waitForClickability(commonPage.login().logOut, 10);
        } catch (Exception e) {
            BrowserUtilities.clearLocalSessionCookies();
            BrowserUtilities.refreshPage();
            BrowserUtilities.waitForClickability(commonPage.login().login, 30);
            BrowserUtilities.waitFor(3);

            BrowserUtilities.cleanTextInBox(commonPage.login().loginUsername);
            BrowserUtilities.cleanTextInBox(commonPage.login().loginPassword);

            commonPage.login().loginUsername.sendKeys(email);
            commonPage.login().loginPassword.sendKeys(password);
            BrowserUtilities.clickWithJS(commonPage.login().loginButton);
            BrowserUtilities.waitFor(3);
        }
        userSessionID = BrowserUtilities.getSessionId().split("=")[1];
        return userSessionID;
    }
}
