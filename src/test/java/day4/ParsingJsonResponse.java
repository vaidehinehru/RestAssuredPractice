package day4;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJsonResponse {

    @Test(priority = 1)
    void testJsonResponse() {

        //Approach 1
        given()
                .contentType("ContentType.json")

                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .log()
                .all()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8");
    }
}
