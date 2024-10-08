package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.StepImplementation;

public class PostCreateAccountUser_SD extends StepImplementation {

//	ReusableMethods rm = new ReusableMethods();

	@Given("User launch baseUri and sets valid username and password in the request body")
	public void user_launch_base_uri_and_sets_valid_username_and_password_in_the_request_body() {
	    System.out.println("Insie Given() method from PostCreateAccountUser_SD");

		// Call the GivenMethod from StepImplementation
		httpWithoutAuth();

		System.out.println("Inside Given() method from PostCreateAccountUser_SD end");
	}

	@When("User enters valid endpoint {string} with requestType {string} request")
	public void user_enters_valid_endpoint_with_request_type_request(String endPoint, String requestType) {
		System.out.println("Inside When() method from PostCreateAccountUser_SD");
		response = createUserWhenM(endPoint, requestType);
		System.out.println("Inside When() method from PostCreateAccountUser_SD end");
	}

	@Then("User should get successful {int} status code and {string} status line")
	public void user_should_get_successful_status_code_and_status_line(Integer statusCode, String statusLine) {
		System.out.println("Inside Then() method from PostCreateAccountUser_SD");
		thenMethod(statusCode, statusLine);
		System.out.println("Inside Then() method from PostCreateAccountUser_SD end");
		System.out.println("--------------------------------------------------------");
	}

}
