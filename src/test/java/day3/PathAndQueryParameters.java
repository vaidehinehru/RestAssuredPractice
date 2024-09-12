package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

    //https://reqres.in/api/users?page=2&id=5

    @Test
    void testPathAndQueryParameters(){
        given()
                .pathParam("mypath", "users")
                .queryParam("page",2)
                .queryParam("id",5)

                .when()
                //No need to mention quer params in request as they will get passed automatically when
               // we proide them in given secton along with path param.
                    .get("https://reqres.in/api/{mypath}")
                .then()
                    .statusCode(200)
                    .log().all();
    }
}
