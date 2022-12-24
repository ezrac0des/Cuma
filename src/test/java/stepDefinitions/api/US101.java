package stepDefinitions.api;

import io.cucumber.java.en.Given;
import utilities.APIUtilities;
import utilities.BrowserUtilities;

public class US101 {
    static int timeoffId;

    @Given("user creates a timeoff block")
    public void userCreatesATimeoffBlock() {
        timeoffId = APIUtilities.createTimeoff(
                //"2023-12-26"
                BrowserUtilities.createDate(0, 2, 3),
                BrowserUtilities.createTime(0, 0),
                BrowserUtilities.createTime(1, 10),
                "online",
                true
        );
        System.out.println(timeoffId);
    }
}
