package stepDefinitions;

import org.apache.http.HttpStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utilities.Enum;
import utilities.Utils;
import utilities.playload.Payload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StepDefinitions extends Utils {
    static RequestSpecification req;
    static ResponseSpecification res;
    static Response response;
    static Payload payload;
    static String place_id;

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) {
        payload = new Payload();
        req = given().log().all().spec(requestSpecification()).body(payload.addPlaceRequest(name,language,address));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String api, String method) {
        res = new ResponseSpecBuilder().expectStatusCode(HttpStatus.SC_OK).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST"))
            response = req.when().post(Enum.valueOf(api).getResource());
        else if(method.equalsIgnoreCase("GET"))
            response = req.when().get(Enum.valueOf(api).getResource());
        else if(method.equalsIgnoreCase("PUT"))
            response = req.when().put(Enum.valueOf(api).getResource());
        else if (method.equalsIgnoreCase("DELETE"))
            response = req.when().delete(Enum.valueOf(api).getResource());
        else
            System.out.println("Invalid method!!!");
    }

    @Then("The API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer statusCode) {
        assertThat(response.getStatusCode(),equalTo(statusCode));
    }

    @Then("{string} in response body should be {string}")
    public void in_response_body_should_be(String responseField, String responseValue) {
        assertThat(getValue(response,responseField),equalTo(responseValue));
    }

    @Then("Verify place_id created map to {string} using {string}")
    public void verify_place_id_created_map_to_using(String expectedName, String api) {
        place_id = getValue(response,"place_id");
        req = given().log().all().spec(requestSpecification())
                .queryParam("place_id",place_id);

        user_calls_with_http_request(api, "GET");
        String actualName = getValue(response,"name");
        assertThat(actualName,equalTo(expectedName));
    }


    @Given("DeletePlace payload")
    public void delete_place_payload() {
       req = given().spec(requestSpecification()).body(payload.deletePlaceRequest(place_id));
    }
}
