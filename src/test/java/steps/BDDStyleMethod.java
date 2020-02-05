package steps;

import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BDDStyleMethod {

    public static void simpleGetPost(String postNumber) {
        given().contentType(ContentType.JSON).
                when().get(String.format("http://localhost:3000/posts/%s", postNumber)).
                then().body("author", is("Kartik Bhatt"));
    }

    public static void performContainsCollection() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/posts/")
        .then()
                .body("author", containsInAnyOrder("Kartik Bhatt", "Kartik Bhatt", null)).statusCode(200);

    }

    public static void performPathParameter() {
        given()
                .contentType(ContentType.JSON).
        with()
                .pathParams("post", 2).
        when()
                .get("http://localhost:3000/posts/{post}").
        then()
                .body("author", containsString("Kartik Bhatt"));
    }

    public static void performQueryParameter() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("id", 1).
        when()
                .get("http://localhost:3000/posts/").
        then()
                .body("author", hasItem("Kartik Bhatt"));
    }

    public static void performPostWithBodyParameter() {
        HashMap<String, String> postContent = new HashMap<>();
        postContent.put("id", "5");
        postContent.put("title", "Robotium");
        postContent.put("author", "Automation");

        given()
                .contentType(ContentType.JSON).
        with()
                .body(postContent).
        when()
                .post("http://localhost:3000/posts").
        then()
                .body("author", Is.is("Automation"));
    }
}
