package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.IsNot;
import utilities.RestAssuredExtension;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class POSTProfileSteps {

    private static ResponseOptions<Response> response;

    @Given("^I perform POST operation for \"([^\"]*)\"$")
    public void iPerformPOSTOperationFor(String arg0) throws Throwable {
        BDDStyleMethod.performPostWithBodyParameter();
    }

    @Given("^I perform POST operation for \"([^\"]*)\" with body$")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table) throws Throwable {
        //retrieve data
        var data = table.raw();

        //set body
        HashMap<String, String> body = new HashMap<>();
        body.put("name",data.get(1).get(0));

        //Path Parameters
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("profileNo", data.get(1).get(1));

        //Perform POST Operation
        response = RestAssuredExtension.PostOpsWithBodyAndPathParams(url, pathParams, body);
    }

    @Then("^I should see the body has name as \"([^\"]*)\"$")
    public void iShouldSeeTheBodyHasNameAs(String name) throws Throwable {
        assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
    }

    @Given("^I ensure to perform POST operation for \"([^\"]*)\" with body as$")
    public void iEnsureToPerformPOSTOperationForWithBodyAs(String url, DataTable table) throws Throwable {
       var data = table.raw();
        HashMap<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        //Perform POST operation
        RestAssuredExtension.PostOpsWithBody(url, body);
    }

    @And("^I perform DELETE operation for \"([^\"]*)\"$")
    public void iPerformDELETEOperationFor(String url, DataTable table) throws Throwable {
        var data = table.raw();
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        //Perform DELETE operation
        RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);
    }

    @Then("^I \"([^\"]*)\" see the body with title as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithTitleAs(String condition, String title) throws Throwable {
        if(condition.equalsIgnoreCase("should not"))
            assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
        else
            assertThat(response.getBody().jsonPath().get("title"), is(title));
    }

    @And("^I perform GET operation with path parameter for \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterFor(String url, DataTable table) throws Throwable {
        var data = table.raw();
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        //Perform DELETE operation
        response = RestAssuredExtension.GetWithPathParams(url, pathParams);
    }


    @And("^I perform PUT operation for \"([^\"]*)\"$")
    public void iPerformPUTOperationFor(String url, DataTable table) throws Throwable {
        var data = table.raw();
        HashMap<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        //Perform POST operation
        RestAssuredExtension.PUTOpsWithBodyAndPathParams(url, body, pathParams);
    }
}
