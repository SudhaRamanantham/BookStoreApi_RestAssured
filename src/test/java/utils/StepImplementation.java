package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.PostCreateAccountUser_Pojo;
import routes.EndPoints;

public class StepImplementation extends SpecBuild {

	public static Response response;
	public static RequestSpecification reqSpec, reqSpecWithAuth;

	public static ReusableMethods rm = new ReusableMethods();

	// In stepDefenition -> Given()-> baseUri,header,requestBody -> those things
	// going to call from httpWithoutAuth()
	public void httpWithoutAuth() {
		PostCreateAccountUser_Pojo createAccountUserPojo = new PostCreateAccountUser_Pojo(
				PropertyReader.getStringProperty("username"), PropertyReader.getStringProperty("password"));
		reqSpec = RestAssured.given().spec(ReqBuilder()).body(createAccountUserPojo);

	}

	// In stepDefinition -> When()-> requestType(Post,Get,Put,Delete) and endPoint
	public Response whenMethodWithoutAuth(String endPoint, String reqType) {

		// Retrieve the enum endPoint value
		EndPoints ep = EndPoints.valueOf(endPoint);
		System.out.println("BaseUri with endpoint of " + endPoint + ": " + ep.getPath().toString());

		if (reqType.equalsIgnoreCase("POST"))// ||reqType.equalsIgnoreCase("PUT"))
		{
			response = reqSpec.when().post(ep.getPath());

			if (EnvVariables.UserId == null) {
				EnvVariables.UserId = rm.setUserID(response);
			}
			if (EnvVariables.Token == null) {
				EnvVariables.Token = rm.setToken(response);
			}
		} else if (reqType.equalsIgnoreCase("GET"))
			response = reqSpec.when().get(ep.getPath());
		else if (reqType.equalsIgnoreCase("PUT"))
			response = reqSpec.when().basePath(ep.getPath()).put();
		else if (reqType.equalsIgnoreCase("DELETE"))
			response = reqSpec.when().basePath(ep.getPath()).delete();
		return response;

	}

	// In stepDefinition -> Then()-> Verifying status code,status line
	public void thenMethod(Integer expectedStatusCode, String expectedStatusLine) {

		try {

			// Get the actual status message line from the response -> "HTTP/1.1 200 OK"
			String resStatusMessage = response.getStatusLine();
			System.out.println("StatusMessage from response body: " + resStatusMessage);

			// Extract only the status code
			int resStatusCode = Integer.parseInt(resStatusMessage.split(" ")[1]);
			System.out.println("StatusCode from response body: " + resStatusCode);

			// Extract only the status message
			String resStatusLine = resStatusMessage.split(" ")[2];
			System.out.println("StatusLine from response body: " + resStatusLine);

			response.then().spec(ResBuilder()).statusCode(expectedStatusCode)
					.statusLine("HTTP/1.1 " + expectedStatusCode + " " + expectedStatusLine);

			System.out.println("Test Passed: Status code and Status line matches. Expected status code: "
					+ expectedStatusCode + ", Response Status code: " + resStatusCode + ", Expected status line: "
					+ expectedStatusLine + ", Response Status line: " + resStatusLine);

		} catch (AssertionError e) {
			System.err.println("Test Failed: " + e.getMessage());
		}

	}

	// In stepDefenition -> Given()-> baseUri,Authorization,header -> those things
	// going to call from httpWithAuth()
	public void httpWithAuth(String bearerToken) {

		bearerToken = EnvVariables.Token;

		System.out.println("Token from EnvVariables: " + bearerToken);

		reqSpecWithAuth = RestAssured.given().spec(ReqBuilder()).header("Authorization", "Bearer " + bearerToken);

	}

	public Response whenMethodWithUserId(String endPoint, String reqType) {

		// Retrieve the enum endPoint value
		EndPoints ep = EndPoints.valueOf(endPoint);
		System.out.println(" BaseUri with endpoint of " + endPoint + ": " + ep.getPath().toString());

		// Use the retrieved enum endpoint value and pass the userId from EnvVariables
		// to replace the placeholder
		String endpointWithUserId = ep.getPathWithUserId(EnvVariables.UserId);
		System.out.println("BaseUri with endpoint and UserId: " + endpointWithUserId);

		if (reqType.equalsIgnoreCase("GET"))
			response = reqSpecWithAuth.when().get(endpointWithUserId);
		else if (reqType.equalsIgnoreCase("DELETE"))
			response = reqSpecWithAuth.when().basePath(endpointWithUserId).delete();
		return response;

	}

}
