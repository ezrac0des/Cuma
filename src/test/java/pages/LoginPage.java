package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends CommonPage{
    @FindBy(css = "[name='username']")
    public WebElement loginUsername;

    @FindBy(css = "[name='password']")
    public WebElement loginPassword;

    @FindBy(css = ".LoginForm_loginBtn__1yNyd")
    public WebElement loginButton;
}
