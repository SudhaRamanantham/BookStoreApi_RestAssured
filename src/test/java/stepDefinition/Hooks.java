package stepDefinition;

import io.cucumber.java.BeforeAll;
import utils.PropertyReader;
import utils.StepImplementation;

public class Hooks extends StepImplementation {

	@BeforeAll
	public static void getProperty() {
		PropertyReader.loadProperties();
	}

//	@SuppressWarnings("resource")
//	@BeforeClass
//    public static void clearLogFile() {
//        try {
//            // Clear the log file only once at the beginning of the test suite
//            new PrintStream(new FileOutputStream("log.txt", false));  // false = overwrite mode
//            System.out.println("Log file cleared before test suite execution.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//	}
	
//	@Before(order = 1)
//	public void generateUserId() {
//		if (EnvVariables.UserId == null) {
//
//			System.out.println("Inside hooks generateUserId method");
//			httpWithoutAuth();
//
//			// Make the API call to generate the UserId
//			response = whenMethodWithoutAuth("CreateAccountUserEP", "POST");
//			System.out.println("Post create account user response body: \n" + response.body().asPrettyString());
//
//			// Check that the response is not null
//			if (response != null) {
//				// Extract UserID from the response and save it to EnvVariables.UserId
//				EnvVariables.UserId = rm.setUserID(response);// response.path("userID");
//				System.out.println("Setting response body userID value in EnvVariables: \n EnvVariables.UserId: "
//						+ EnvVariables.UserId);
//			} else {
//				throw new RuntimeException("Failed to generate userID. Response is null.");
//			}
//
//		}
//	}
//
//	@Before(order = 2)
//	public void generateToken() {
//		if (EnvVariables.Token == null) {
//
//			System.out.println("Inside hooks generateToken method");
//			httpWithoutAuth();
//
//			// Make the API call to generate the token
//			response = whenMethodWithoutAuth("GenerateTokenEP", "POST");
//			System.out.println("Post generate token response body: \n" + response.body().asPrettyString());
//
//			// Check that the response is not null
//			if (response != null) {
//				// Extract token from the response and save it to EnvVariables.Token
//				EnvVariables.Token = rm.setToken(response); // response.path("token");
//				System.out.println("Setting response body token value in EnvVariables: \n EnvVariables.Token:"
//						+ EnvVariables.Token);
//				System.out.println("_________________________________________________________________________________");
//			} else {
//				throw new RuntimeException("Failed to generate token. Response is null.");
//			}
//
//		}
//	}

}
