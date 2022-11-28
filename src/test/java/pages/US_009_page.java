package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class US_009_page extends CommonPage {

    @FindBy(css = ".dot")
    public List<WebElement> dots;

    @FindBy(css = ".details.LandingPage_textDark__EZpFS")
    public List<WebElement> testimonials;

    @FindBy(css = "[aria-label='Previous']")
    public WebElement button_previous;

    @FindBy(css = "[aria-label='Next']")
    public WebElement button_next;


}