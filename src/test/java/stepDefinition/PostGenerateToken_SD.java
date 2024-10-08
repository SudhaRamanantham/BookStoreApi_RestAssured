package stepDefinition;

import io.cucumber.java.en.Then;
import utils.StepImplementation;

public class PostGenerateToken_SD extends StepImplementation {

	@Then("User get successful {int} status code and {string} status line")
	public void user_get_successful_status_code_and_status_line(Integer statusCode, String statusLine) {
		System.out.println("Inside Then() method from PostGenerateToken_SD");
		thenMethod(statusCode, statusLine);
		System.out.println("Inside Then() method from PostGenerateToken_SD end");
		System.out.println("--------------------------------------------------------");
	}

}
