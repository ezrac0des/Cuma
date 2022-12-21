package pages;

import org.openqa.selenium.support.PageFactory;

import static stepDefinitions.ui.Hooks.driver;

public abstract class CommonPage {
    public CommonPage() {
        PageFactory.initElements(driver, this);
    }

}
