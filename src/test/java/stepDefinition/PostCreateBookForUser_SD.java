package stepDefinition;

import io.cucumber.java.en.*;
import utils.StepImplementation;

public class PostCreateBookForUser_SD extends StepImplementation {

	@Given("User launch baseUri and sets Authorization as bearer token {string} with request body")
	public void user_launch_base_uri_and_sets_authorization_as_bearer_token_with_request_body(String string) {
		System.out.println("Inside Given() method");
		httpWAuthPostBody();
		System.out.println("Inside Given() method end");
	}

	@When("User enters valid endpoint {string} and requestType {string} request")
	public void user_enters_valid_endpoint_and_request_type_request(String endPoint, String requestType) {
		System.out.println("Inside When() method");
		response = whenWAuthPostBody(endPoint, requestType);
		System.out.println("Inside When() method end");
	}

}
