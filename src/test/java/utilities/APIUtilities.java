package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static stepDefinitions.Hooks.*;

public class APIUtilities {
    public static Response response;
    public static RequestSpecification specification = new RequestSpecBuilder().
            addCookie(new Cookie.Builder("PHPSESSID", userSessionID).build()).
            setBaseUri(ConfigReader.getProperty("baseURI")).setRelaxedHTTPSValidation().build();

    static Map<String, Object> payload = new HashMap<>();

    public static int addCoupon(String promoCode, int startDate, int endDate, int usersLimit, int discountRate, String discountType, int category) {
        payload.put("promoCode", promoCode);
        payload.put("startedAt", BrowserUtilities.getDay_day_month_year_time(startDate));
        payload.put("enddedAt", BrowserUtilities.getDay_day_month_year_time(endDate));
        payload.put("usersLimit", usersLimit);
        payload.put("discountRate", discountRate);
        payload.put("discountType", discountType);
        payload.put("category", category);

        response = given()
                .spec(specification)
                .contentType(ContentType.JSON)
                .headers("content-type", "application/x-www-form-urlencoded")
                .formParams(payload)
                .post("/promoCode/add");

        response.prettyPrint();
        return response.jsonPath().get("promoCode.id");
    }

    public static void deleteCoupon(int couponId) {
        payload.put("couponId", couponId);

        response = given()
                .spec(specification)
                .headers("content-type", "application/x-www-form-urlencoded")
                .formParams(payload)
                .post("/promoCode/deleteCoupon");

        response.prettyPrint();
    }

    public static List<Integer> getCoupons() {
        response = given()
                .spec(specification)
                .headers("content-type", "application/x-www-form-urlencoded")
                .post("/promoCode/getCoupons");

        response.prettyPrint();
        return response.jsonPath().getList("id");
    }

    public static int createTimeoff(String specificDate, String startAt, String finishAt, String title, boolean isAll) {
        payload.put("specificDate", specificDate);
        payload.put("startAt", startAt);
        payload.put("finishAt", finishAt);
        payload.put("title", title);
        payload.put("isAll", isAll);

        response = given()
                .spec(specification)
                .headers("content-type", "application/x-www-form-urlencoded")
                .formParams(payload)
                .post("/hypnotherapist/timeoff/create");

        response.prettyPrint();
        return response.jsonPath().getInt("data[0].id");
    }

    public static void notCreateCertificate(String title, String organization) {
        payload.put("title", title);
        payload.put("organization", organization);

        response = given()
                .spec(specification)
                .formParams(payload)
                .post("/profile/addCertificate");

        response.prettyPrint();

    }

    public static int createCertificate(String title, String organization, String date) {
        payload.put("title", title);
        payload.put("organization", organization);
        payload.put("date", date);

        response = given()
                .spec(specification)
                .formParams(payload)
                .post("/profile/addCertificate");

        response.prettyPrint();

        return response.jsonPath().getInt("certificate.id");
    }

    public static void updateCertificate(String title, String organization, String date, int id) {
        payload.put("title", title);
        payload.put("organization", organization);
        payload.put("date", date);
        payload.put("certificateId", id);

        response = given()
                .spec(specification)
                .formParams(payload)
                .post("/profile/updateCertificate");

        response.prettyPrint();
    }

    public static void deleteCertificate(int id) {
        payload.put("certificateId", id);

        response = given()
                .spec(specification)
                .formParams(payload)
                .post("/profile/removeCertificate");

        response.prettyPrint();
    }

    public static List<Integer> getUser() {
        response = given()
                .spec(specification)
                .post("/dashboard/getUser");

//        response.prettyPrint();

        return response.jsonPath().getList("profile.certificates.id");
    }

    public static List<Boolean> getUserActive() {
        response = given()
                .spec(specification)
                .post("/dashboard/getUser");

//        response.prettyPrint();

        return response.jsonPath().getList("profile.certificates.isActive");
    }


    //Cookie ekleme
    public static void addCookie(String key, String value) {
        specification.cookie(key, value);
    }

    //Cookie'ler ekleme
    public static void addCookies(Map<String, String> map) {
        specification.cookies(map);
    }

    // Status code doğrulaması yapar
    public static void verifyStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    // Bağlantı için kabul edilen content-type ifadesini ekler
    public static void addContentType(String contentType) {
        specification.contentType(contentType);
    }

    // Bağlantıya query param ekler
    public static void addQueryParam(String key, String value) {
        specification.queryParam(key, value);
    }

    // Bağlantıya query params ekler
    public static void addQueryParams(Map<String, String> params) {
        specification.queryParams(params);
    }

    // Bağlantıya form param ekler
    public static void addFormParam(String key, String value) {
        specification.formParam(key, value);
    }

    // Bağlantıya form params ekler
    public static void addFormParams(Map<String, String> params) {
        specification.formParams(params);
    }

    // Bağlantıya body ekler
    public static void addBody(String s) {
        specification.body(s);
    }

    // Kullanıcı istediği endpoint'e post tipinde ve daları form params içerisinde göndererek bağlanabilecek
    public static void connectWithPostMethodFormParams(String endPoint, Map<String, String> data) {
        response = given().
                contentType(ContentType.URLENC.withCharset("UTF-8")).
                spec(specification).
                formParams(data).
                post(endPoint);
    }

    // Kullanıcı istediği endpoint'e post tipinde ve daları body bölümünde göndererek bağlanabilecek
    public static void connectWithPostMethodBody(String endPoint, Map<String, String> data) {
        JSONObject object = new JSONObject();

        for (String s : data.keySet()) {
            object.put(s, data.get(s));
        }

        response = given().
                contentType(ContentType.URLENC.withCharset("UTF-8")).
                spec(specification).
                body(data.toString()).
                post(endPoint)
        ;
    }

    // Kullanıcı istediği endpoint'e post tipinde bağlanabilecek
    public static void connectWithPostMethod(String endPoint) {
        response = given().
                contentType(ContentType.URLENC.withCharset("UTF-8")).
                spec(specification).
                post(endPoint);

        response.prettyPrint();
    }

    // Kullanıcı istediği endpoint'e get tipinde bağlanabilecek
    public static void connectWithGetMethod(String endPoint) {
        response = given().
                contentType(ContentType.URLENC.withCharset("UTF-8")).
                spec(specification).
                get(endPoint);
    }

    // Kullanıcı istediği endpoint'e delete tipinde bağlanabilecek
    public static void connectWithDeleteMethod(String endPoint) {
        response = given().
                contentType(ContentType.URLENC.withCharset("UTF-8")).
                spec(specification).
                delete(endPoint);
    }

    // Kullanıcı istediği endpoint'e delete tipinde bağlanabilecek
    public static void connectWithDeleteMethod(String endPoint, Map<String, String> queryMap) {
        response = given().
                queryParams(queryMap).
                contentType(ContentType.URLENC.withCharset("UTF-8")).
                spec(specification).
                delete(endPoint);
    }

    // Kullanıcı istediği endpoint'e get tipinde query verilerini göndererek bağlanabilecek
    public static void connectWithGetMethodQuery(String endPoint, Map<String, String> queryMap) {
        response = given().
                queryParams(queryMap).
                contentType(ContentType.URLENC.withCharset("UTF-8")).
                spec(specification).
                get(endPoint);
    }

    // Gelen response içerisindeki array'e ait verilerin sahip olduğu field'ları kntrol eder
    public static void checkFieldsInArray(List<String> fields) {

        List<LinkedHashMap> list = APIUtilities.response.jsonPath().get("$");

        for (LinkedHashMap o : list) {

            for (String field : fields) {
                Assert.assertTrue(o.get(field) != null);
            }
        }
    }

    // Gelen response içerisindeki objelerin field'ları kntrol eder
    public static void checkFieldsInObject(List<String> fields) {
        for (String field : fields) {
            Assert.assertTrue(APIUtilities.response.jsonPath().get(field) != null);
        }
    }

}
