package stepDefinition;

import io.cucumber.java.en.*;
import utils.StepImplementation;

public class GetBookByIsbn_SD extends StepImplementation {

	@Given("User launch baseUri, sets Authorization as bearer token {string} and query parameter")
	public void user_launch_base_uri_sets_authorization_as_bearer_token_and_query_parameter(String bearerToken) {
	    System.out.println("Inside Given() method from GetBookByIsbn_SD");
	    httpWithAuthQueryParams();
	    System.out.println("Inside Given() method from GetBookByIsbn_SD");
	}

	@When("User enters valid endpoint {string} with query parameter and requestType {string} request")
	public void user_enters_valid_endpoint_with_query_parameter_and_request_type_request(String endPoint, String requestType) {
		System.out.println("Inside When() method from GetBookByIsbn_SD");
		response = whenMethodWithAuth(endPoint,requestType);
		System.out.println("Inside When() method from GetBookByIsbn_SD end");
	}
}
