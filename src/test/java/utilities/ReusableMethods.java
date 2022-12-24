package utilities;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static stepDefinitions.Hooks.driver;

public class ReusableMethods {

    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    //========Switching Window=====//
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    //========Hover Over=====//
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    //==========Return a list of string given a list of Web Element====////
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //===============Thread.sleep Wait==============//
    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //===============Explicit Wait==============//
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }
//    //======Fluent Wait====//
//    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
//        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
//                .withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class);
//        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
//            public WebElement apply(WebDriver driver) {
//                return webElement;
//            }
//        });
//        return element;
//    }

    public static String getCurrentDate() {
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        System.out.println(date);
        String currentDate = date.substring(0, date.indexOf(" "));
        return currentDate;
    }

    public static String getCurrentTime() {
        String date = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(new Date());
        System.out.println(date);
        String currentTime = date.substring(date.indexOf(" ") + 1);
        return currentTime;
    }

    public static void verifyElementClickable(WebElement element) {
        try {
            Assert.assertTrue("Element not visible: " + element, element.isEnabled());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);

        }
    }

    public static void verifyElementNotClickable(WebElement element) {
        try {
            Assert.assertFalse("Element not visible: " + element, element.isEnabled());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


    public static void verifyElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue("Element is visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    public static void verifyElementNotDisplayed(WebElement element) {
        try {
            Assert.assertFalse("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element found: " + element);
        }
    }

    public static void verifyUrlContains(String string) {
        try {
            Assert.assertTrue("The URL contains: " + string, driver.getCurrentUrl().contains(string));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("The URL does not contain " + string);
        }
    }


    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }


    public static void
    Element(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ReusableMethods.waitFor(2);

    }

    // My method - get child title - my method -EsmaY
    public static String getChildTitle() {
        Set<String> windows = Driver.getDriver().getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parent = it.next();
        String child = it.next();
        driver.switchTo().window(child);
        String pageTitle = driver.getTitle();
        driver.close();
        driver.switchTo().window(parent);
        return pageTitle;
    }

    //it will scroll the screen until given element.
    public static void scrollToView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    //it will scroll the screen until end of the page.
    public static void scrollUntilEnd(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    //it checks whether given text matches element text value or not
    public static boolean isTextVisible(WebElement element, String text) {
        if (text.equals(element.getText())) {
            return true;
        }
        return false;
    }

    // My method - return all open windows urls except mainpage - my method -EsmaY
    public static ArrayList<String> getAllWindowsUrls() {
        Set<String> windows = Driver.getDriver().getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parent = it.next();
        ArrayList<String> urls = new ArrayList<>();
        for (int i = 1; i < windows.size(); i++) {
            String childId = it.next();
            Driver.getDriver().switchTo().window(childId);
            urls.add(Driver.getDriver().getCurrentUrl());
        }
        return urls;
    }

    // My method - getElementFromGetText() - new version - my method -EsmaY
    public static WebElement getElementFromGetText(String text) {
        By element = By.xpath("//*[contains(text(), '" + text + "')]");
        WebElement found = null;
        try {
            List<WebElement> elements = Driver.getDriver().findElements(element);
            found = Driver.getDriver().findElement(element);
            if (elements.size() != 1) {
                System.out.println(elements.size() + " element found. Check if the element is yours");
            }
            return found;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
            return null;
        }
    }

    // My method - verifyVideoPlays() - youtube video is play - my method -EsmaY
    public static void verifyVideoPlays(WebElement element) {
        //precond: user clicks on youtube video
        // can be added iframe section and clicks
        try {
            waitFor(1);
            String playTime = element.getAttribute("currentTime");
            if (Double.parseDouble(playTime) > 0) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        } catch (Exception ex) {
            Assert.fail("You didnt click on play button.");
        }
    }

    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    public static void scrollToElement(WebElement element) {
        //scrollIntoView() not working for horizontal scroll
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // My method - getChildWindowURL() - my method -EsmaY
    public static String getChildWindowURL() {
        Set<String> windows = Driver.getDriver().getWindowHandles();
        Iterator<String> it = windows.iterator();
        String main = Driver.getDriver().getWindowHandle();
        it.next();
        String child = it.next();
        Driver.getDriver().switchTo().window(child);
        String childUrl = Driver.getDriver().getCurrentUrl();
        Driver.getDriver().close();
        ReusableMethods.waitFor(3);
        Driver.getDriver().switchTo().window(main);
        //Driver.getDriver().switchTo().window(parent); -> hata
        return childUrl;
    }

    public static void mySendKeysMethod(By locator, String sendText) {

        WebElement element = Driver.getDriver().findElement(locator);

        Wait<WebDriver> wait = new FluentWait<>(Driver.getDriver()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");

        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(sendText);

    }

    //By Davut
    public static void mySendKeysMethod(WebElement webElement, String sendText) {
        Wait<WebDriver> wait = new FluentWait<>(Driver.getDriver()).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(2)).
                withMessage("My click method failed");

        wait.until(ExpectedConditions.visibilityOf(webElement)).sendKeys(sendText);
    }

}