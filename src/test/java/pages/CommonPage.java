package pages;

import org.openqa.selenium.support.PageFactory;

import static stepDefinitions.Hooks.driver;

public abstract class CommonPage {
    public CommonPage() {
        PageFactory.initElements(driver, this);
    }

    private ClientsPage clients;
    private HomePage homePage;
    private LoginPage login;

    public ClientsPage clients() {
        if (clients == null) clients = new ClientsPage();
        return clients;
    }

    public HomePage homePage() {
        if (homePage == null) homePage = new HomePage();
        return homePage;
    }

    public LoginPage login() {
        if (login == null) login = new LoginPage();
        return login;
    }
}
