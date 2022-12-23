package stepDefinitions.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static utilities.APIUtilities.*;

public class US089 {
    @Given("user connects to the {string} with payload")
    public void userConnectsToTheWithPayload(String endpoint, DataTable dataTable) {
        Map<String, String> payload = dataTable.asMaps().get(0);
/*
        response = given()
                .spec(specification)
                .formParams(payload)
                .post(endpoint);*/

        connectWithPostMethodFormParams(endpoint, payload);

        response.prettyPrint();
    }

    @Then("user verifies the status code is {int}")
    public void userVerifiesTheStatusCodeIs(int statusC) {
//        Assert.assertEquals(statusC, response.getStatusCode());
        verifyStatusCode(statusC);
    }

    @Then("user verifies that the response is as expected")
    public void userVerifiesThatTheResponseIsAsExpected(DataTable dataTable) {
        List<String> paths = dataTable.column(0);
        List<String> expectedData = dataTable.column(1);

        for (int i = 0; i < paths.size(); i++) {
            Assert.assertEquals(expectedData.get(i), response.jsonPath().getString(paths.get(i)));
            System.out.println("Expected data = " + expectedData.get(i) + " - Actual Data = " + response.jsonPath().getString(paths.get(i)));
        }

        /*
        Assert.assertEquals(expectedData.get(0), response.jsonPath().getString(paths.get(0)));
        Assert.assertEquals(expectedData.get(1), response.jsonPath().getString(paths.get(1)));
        Assert.assertEquals(expectedData.get(2), response.jsonPath().getString(paths.get(2)));
        Assert.assertEquals(expectedData.get(3), response.jsonPath().getString(paths.get(3)));
        Assert.assertEquals(expectedData.get(4), response.jsonPath().getString(paths.get(4)));
        */
    }

    @And("user connects to the {string}")
    public void userConnectsToThe(String endpoint) {
        response = given()
                .spec(specification)
                .post(endpoint);

        response.prettyPrint();
    }

    @Then("user verifies {int} is not in the list")
    public void userVerifiesIsNotInTheList(int notFound) {
        //[0].id ==349
//        Map<String, Object> parsed = response.jsonPath().get("find { it.id: { $ne: " + notFound + " } }");
//        int numberOfPair = parsed.keySet().size();
//        System.out.println(numberOfPair);
//
//        for (int i = 0; i < numberOfPair - 1; i++) {
//            Assert.assertNotEquals(String.valueOf(notFound), response.jsonPath().get("[" + i + "].id").toString());
//            System.out.println(notFound + " is not equals to " + response.jsonPath().getInt("[" + i + "].id"));
//        }

        ArrayList<Integer> allIds = response.path("id");
        int ids = allIds.size();


        for (int i = 0; i < ids; i++) {
            Assert.assertNotEquals(notFound+"", allIds.get(i)+"");
            System.out.println(notFound + " is equals to " + response.jsonPath().getInt("[" + i + "].id"));
        }

    }

    @Then("user verifies {int} is in the list")
    public void userVerifiesIsInTheList(int found) {
        //[0].id ==349
//        Map<String, Object> parsed = response.jsonPath().get("find {id = '" + found + "'}");
//        int numberOfPair = parsed.keySet().size();

        List<Integer> allIds = response.jsonPath().getList("id");
        int ids = allIds.size();
        boolean var = false;

        for (int i = 0; i < ids; i++) {
            if(allIds.get(i).equals(found)) var =true;
        }
        Assert.assertTrue(var);
    }
}
