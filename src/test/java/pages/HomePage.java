package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends CommonPage {
    @FindBy(css = "[name='name']")
    public WebElement name;

    @FindBy(css = "[name='surname']")
    public WebElement surname;

    @FindBy(css = "[name='email']")
    public WebElement email;

    @FindBy(css = "[name='password']")
    public WebElement password;

    @FindBy(css = ".RegisterForm_invalid__10sju")
    public List<WebElement> redList;

    @FindBy(css = ".RegisterForm_valid__sQYgm")
    public List<WebElement> greenList;

    @FindBy(css = ".RegisterForm_SignUpBtnInvalid__FtjCT")
    public WebElement invalidSignup;

    @FindBy(css = ".RegisterForm_SignUpBtn__DNymU")
    public WebElement validSignup;

    @FindBy(css = ".ytp-large-play-button-red-bg")
    public WebElement youtube_play;

    @FindBy(css = "[src*='youtube']")
    public WebElement youtube_frame;

    @FindBy(css = ".video-stream.html5-main-video")
    public WebElement youtube_attribute;

    @FindBy(css = ".dot")
    public List<WebElement> dots;



    @FindBy(css = ".details.LandingPage_textDark__EZpFS")
    public List<WebElement> testimonials;

    @FindBy(css = "[aria-label='Previous']")
    public WebElement button_previous;

    @FindBy(css = "[aria-label='Next']")
    public WebElement button_next;

    @FindBy(css = ".BaseFooter_footerCol__kUthZ ul li")
    public List<WebElement> footerLinks;
}
