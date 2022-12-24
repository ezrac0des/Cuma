package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ClientsPage extends CommonPage {
    @FindBy(xpath = "//a[@href='/dashboard/add-client']")                       public WebElement addClientSB;
    @FindBy(css = ".ant-form-item-control-input-content")                       public List<WebElement> inputs;
    @FindBy(css = ".ant-select-item-option-content")                            public List<WebElement> genders;

    @FindBy(css = "#register_gender")                                           public WebElement genderDropdown;
    @FindBy(css = "#register_firstName")                                        public WebElement firstName;
    @FindBy(css = "#register_lastName")                                         public WebElement lastName;
    @FindBy(css = "#register_occupation")                                       public WebElement occupation;
    @FindBy(css = "#register_email")                                            public WebElement email;

    @FindBy(xpath = "//button[.='Add Client']")                                 public WebElement addClient;
    @FindBy(css = ".ant-popover-message-title")                                 public WebElement timezoneMessage;
    @FindBy(css = ".ant-btn-primary.ant-btn-sm")                                public WebElement yes;
    @FindBy(css = ".ant-message-custom-content.ant-message-success")            public WebElement successMessage;

    @FindBy(xpath = "//a[@href='/dashboard/clients']")                          public WebElement clients;
    @FindBy(xpath = "//h5")                                                     public List<WebElement> clientsNames;
    @FindBy(css = "[aria-label='delete']")                                      public WebElement delete;
    @FindBy(css = ".ant-btn-default.ant-btn-dangerous")                         public WebElement deleteYes;
    @FindBy(css = ".ant-form-item-explain-error")                               public List<WebElement> alerts;
}
