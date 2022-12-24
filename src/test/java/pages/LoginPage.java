package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends CommonPage {
    @FindBy(xpath = "//a[text() = 'Login']")                        public WebElement login;
    @FindBy(css = "[name='username']")                              public WebElement loginUsername;
    @FindBy(css = "[name='password']")                              public WebElement loginPassword;
    @FindBy(css = ".LoginForm_loginBtn__1yNyd")                     public WebElement loginButton;
    @FindBy(xpath = "//span[text()='Logout']")                      public WebElement logOut;
}
