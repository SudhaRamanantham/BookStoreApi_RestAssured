package stepDefinition;

import java.io.IOException;

import io.cucumber.java.en.*;
import utils.StepImplementation;

public class PostCreateAccountUser_SD extends StepImplementation {

	@Given("User launch baseUri and sets valid username and password in the request body")
	public void user_launch_base_uri_and_sets_valid_username_and_password_in_the_request_body() throws IOException {
	    System.out.println("Insie Given() method");
		// Call the GivenMethod from StepImplementation
	    httpNoAuthWBody();
		System.out.println("Inside Given() method end");
	}

	@When("User enters valid endpoint {string} with requestType {string} request")
	public void user_enters_valid_endpoint_with_request_type_request(String endPoint, String requestType) {
		System.out.println("Inside When() method");
		response = whenWEndPReqT(endPoint, requestType);
		System.out.println("Inside When() method end");
	}

	@Then("User should get successful {int} status code and {string} status line")
	public void user_should_get_successful_status_code_and_status_line(Integer statusCode, String statusLine) {
		System.out.println("Inside Then() method");
		thenMethod(statusCode, statusLine);
		System.out.println("Inside Then() method end");
		System.out.println("--------------------------------------------------------");
	}

}
