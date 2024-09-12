package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {

    //@Test(priority=1)
    void testCookies(){
        given()

                .when()
                    .get("https://www.google.com/")
                .then()
                    .cookie("AEC","AVYB7cpw5azlrlM0PwmfRSGINjJoYKeLonv7YYPtcr8RX6HYpCyW2MFROaQ")
                    .log().all();
    }

    @Test(priority=2)
    void getCookie(){

            //capturing response into a variable res
           Response res = given()

                    .when()
                        .get("https://www.google.com/");

                   //get single cookie info
                   //String cookie_value = res.getCookie("AEC");
                  // System.out.println("value of cookie ==>"+cookie_value);

                //get allcookies info
                Map<String,String> cookies_values = res.getCookies();

                //gets only keys of cookies & not values
                //System.out.println(cookies_values.keySet());

                //gets all cookie keys & values
                for(String i:cookies_values.keySet()){
                    String cookie_value = res.getCookie(i);
                    System.out.println(i+"            "+cookie_value);
                }

    }
}
