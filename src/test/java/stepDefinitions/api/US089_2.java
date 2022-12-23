package stepDefinitions.api;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.APIUtilities;

import java.util.List;

public class US089_2 {
    static int couponId;
    static String name = new Faker().name().firstName();
    static List<Integer> ids;

    @When("user creates a coupon")
    public void userCreatesACoupon() {
        couponId = APIUtilities.addCoupon(
                name,
                0,
                72,
                20,
                5,
                "percentage",
                2098
        );
        System.out.println(couponId);
    }

    @When("user gets all coupon info")
    public void userGetsAllCouponInfo() {
        ids = APIUtilities.getCoupons();

    }

    @Then("user verifies created coupon is in the list")
    public void userVerifiesCreatedCouponIsInTheList() {
        boolean var = false;
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).equals(couponId)) {
                var = true;
                break;
            }
        }
        Assert.assertTrue(var);System.out.println(ids);
    }

    @When("user deletes the created coupon")
    public void userDeletesTheCreatedCoupon() {
        APIUtilities.deleteCoupon(couponId);
        System.out.println(couponId);
    }

    @Then("user verifies created coupon is not in the list")
    public void userVerifiesCreatedCouponIsNotInTheList() {
        for (int i = 0; i < ids.size(); i++) {
            Assert.assertNotEquals(couponId + "", ids.get(i) + "");
        }
        System.out.println(ids);
    }
}
