package stepDefinition;

import io.cucumber.java.en.*;
import utils.StepImplementation;

public class PutBookWAnotherIsbn_SD extends StepImplementation {

	@Given("User launch baseUri and sets Authorization as bearer token with request body of put request")
	public void user_launch_base_uri_and_sets_authorization_as_bearer_token_with_request_body_of_put_request() {
		System.out.println("Inside Given() method");
		httpWAuthPutBody();
		System.out.println("Inside Given() method end");
	}
	
	@When("User enters valid endpoint {string} with another Isbn and requestType {string} request")
	public void user_enters_valid_endpoint_with_another_isbn_and_request_type_request(String endPoint, String requestType) {
		System.out.println("Inside When() method");
		response = whenWAnotherIsbn(endPoint,requestType);
		System.out.println("Inside When() method end");
	}
	
}
