package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Address;
import pojo.Location;
import pojo.LoginBody;
import pojo.Posts;
import utilities.APIConstant;
import utilities.RestAssuredExtension;
import utilities.RestAssuredExtensionv2;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetPostsSteps {

    public static ResponseOptions<Response> response;
    public static String token;

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws Throwable {
         //given().contentType(ContentType.JSON);
        //response = RestAssuredExtension.getOps(url);
        response = RestAssuredExtension.GetOpsWithToken(url, token);
    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {
        //with Builder pattern
        //Posts posts =  Posts.builder().build();
        //Posts post = response.getBody().as(Posts.class);
        var posts = response.getBody().as(Posts.class);
        assertThat(posts.getAuthor(), equalTo(authorName));
        //Without Builder pattern
//        var posts = response.getBody().as(Posts.class);
//        assertThat(posts.getAuthor(), equalTo(authorName));
//        assertThat(response.getBody().jsonPath().get("author"), hasItem("Kartik Bhatt"));
    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() throws Throwable {
        BDDStyleMethod.performContainsCollection();
    }

    @Then("^I should see verify GET Parameter$")
    public void iShouldVerifyGETParameter() throws Throwable {
        BDDStyleMethod.performPathParameter();
    }

    @Then("^I should see verify Query GET Parameter$")
    public void iShouldSeeVerifyQueryGETParameter() {
        BDDStyleMethod.performQueryParameter();
    }

    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
    public void iPerformAuthenticationOperationForWithBody(String uri, DataTable table) throws Throwable {
        var data = table.raw();

//        HashMap<String, String> body = new HashMap<>();
//        body.put("email", data.get(1).get(0));
//        body.put("password", data.get(1).get(1));

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(data.get(1).get(0));
        loginBody.setPassword(data.get(1).get(1));

        RestAssuredExtensionv2 restAssuredExtensionv2 = new RestAssuredExtensionv2(uri, APIConstant.ApiMethods.POST, null);
        token = restAssuredExtensionv2.Authenticate(loginBody);

        //response = RestAssuredExtension.PostOpsWithBody(url, body);
    }

    @And("^I perform GET operation with path parameter for address \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterForAddress(String uri, DataTable table) throws Throwable {
        var data = table.raw();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("id", data.get(1).get(0));

        //response
        //response = RestAssuredExtension.GetWithQueryParamsWithToken(url, queryParams, response.getBody().jsonPath().get("access_token"));
        RestAssuredExtensionv2 restAssuredExtensionv2 = new RestAssuredExtensionv2(uri, "GET", token);
        response = restAssuredExtensionv2.ExecuteWithQueryParamas(queryParams);
    }

    @Then("^I should see the street name as \"([^\"]*)\" for the \"([^\"]*)\" address$")
    public void iShouldSeeTheStreetNameAsForTheAddress(String streetName, String type) throws Throwable {
        var location = response.getBody().as(Location[].class);

        //filter the address based on the type addresses
        Address address = location[0].getAddress().stream().filter(x -> x.getType().equalsIgnoreCase(type))
                .findFirst().orElse(null);
        assertThat(address.getStreet(), equalTo(streetName));
    }

    @Then("^I should see the author name as \"([^\"]*)\" with json validation$")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String arg0) throws Throwable {
        //returns the body as string
        var responseBody = response.getBody().asString();
        assertThat(responseBody, matchesJsonSchemaInClasspath("post.json"));
    }
}
