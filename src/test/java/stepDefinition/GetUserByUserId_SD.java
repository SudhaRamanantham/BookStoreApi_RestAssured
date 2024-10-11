package stepDefinition;

import io.cucumber.java.en.*;
import utils.StepImplementation;

public class GetUserByUserId_SD extends StepImplementation {

	@Given("User launch baseUri and sets Authorization as bearer token {string}")
	public void user_launch_base_uri_and_sets_authorization_as_bearer_token(String envBearerToken) {
		System.out.println("Inside Given() method");
		httpWAuthNoBody(envBearerToken);
		System.out.println("Inside Given() method end");
	}

	@When("User enters valid endpoint {string} with UserId and requestType {string} request")
	public void user_enters_valid_endpoint_with_user_id_and_request_type_request(String endPoint, String requestType) {
		System.out.println("Inside When()method");
		response = whenWUserId(endPoint, requestType);
		System.out.println("Inside When() method end");
	}
}
