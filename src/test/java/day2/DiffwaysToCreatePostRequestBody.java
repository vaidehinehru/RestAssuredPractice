package day2;

/*
Different ways to create post request body
1. Post Request body using Hashmap
2. Post Request body creation using Org.Json
3. Post Request body creation using POJO class
4. Post request using external json file data
 */

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DiffwaysToCreatePostRequestBody {

    /*
    1. Post Request body using Hashmap
     */

   // @Test(priority = 1)
    void postTestRequestusingHashmap(){
        HashMap data = new HashMap();
        data.put("name","Scott");
        data.put("location","France");
        data.put("phone", "123456");

        String courseArr[] = {"C","C++"};
        data.put("courses",courseArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                    .post("http://localhost:3000/students")

                .then()
                    .statusCode(201)
                    .body("name",equalTo("Scott"))
                    .body("location",equalTo("France"))
                    .body("phone",equalTo("123456"))
                    .body("courses[0]",equalTo("C"))
                    .body("courses[1]",equalTo("C++"))
                    .header("content-Type","application/json")
                    .log().all();
    }

   @Test(priority=2)
    void testDelete(){

    given()
            .when()
                .delete("http://localhost:3000/students/1f")
            .then()
            .statusCode(200);
    }

    //2.Post Request body creation using Org.Json

    //@Test(priority = 1)
    void postTestRequestusingOrgJson(){

        /*Add org.json dependency in pom.xml
         */
        JSONObject data = new JSONObject();
        data.put("name","scott");
        data.put("location","France");
        data.put("phone", "123456");

        String courseArr[] = {"C","C++"};
        data.put("courses",courseArr);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("content-Type","application/json")
                .log().all();
    }

    //3.Post Request body creation using POJO class

    //@Test(priority = 1)
    void postTestRequestusingPOJO(){

        /*Add org.json dependency in pom.xml
         */
     Pojo_postRequest data = new Pojo_postRequest();
     data.setName("scott");
     data.setLocation("France");
     data.setPhone("123456");

     String courseArr[] = {"C","C++"};
     data.setCourses(courseArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("content-Type","application/json")
                .log().all();
    }

    //4.Post Request body creation using external json file

   // @Test(priority = 1)
    void postTestRequestusingExternalFile() throws FileNotFoundException {

        File f = new File(".\\body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("John"))
                .body("location",equalTo("India"))
                .body("phone",equalTo("1234567890"))
                .body("courses[0]",equalTo("Java"))
                .body("courses[1]",equalTo("Selenium"))
                .header("content-Type","application/json")
                .log().all();
    }




}
