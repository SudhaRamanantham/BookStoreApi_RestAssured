package utils;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Isbn_CreateBookFUser;
import pojo.PostCreateAccountUser_Pojo;
import pojo.PostCreateBookForUser_Pojo;
import routes.EndPoints;

public class StepImplementation extends SpecBuild {

	public static Response response;
	public static RequestSpecification reqSpec, reqSpecWithAuth;

	public static ReusableMethods rm = new ReusableMethods();

	// In stepDefenition -> Given()-> baseUri,header,requestBody
	public void httpWithoutAuth() {
		PostCreateAccountUser_Pojo createAccountUserPojo = new PostCreateAccountUser_Pojo(
				PropertyReader.getStringProperty("username"), PropertyReader.getStringProperty("password"));
		reqSpec = RestAssured.given().spec(ReqBuilder()).body(createAccountUserPojo);

	}

	// In stepDefinition -> When()-> requestType(Post,Get,Put,Delete) and endPoint
	public Response createUserWhenM(String endPoint, String reqType) {

		// Retrieve the enum endPoint value
		EndPoints ep = EndPoints.valueOf(endPoint);
		System.out.println("BaseUri with endpoint of " + endPoint + ": " + ep.getPath().toString());

		if (reqType.equalsIgnoreCase("POST"))// ||reqType.equalsIgnoreCase("PUT"))
		{
			response = reqSpec.when().post(ep.getPath());

			if (CollectionVariables.UserId == null) {
				CollectionVariables.UserId = rm.setUserID(response);
				System.out.println("Setting userId Value in collection Variables: \n" + CollectionVariables.UserId);
			}
			if (CollectionVariables.Token == null) {
				CollectionVariables.Token = rm.setToken(response);
				System.out.println("Setting token Value in collection Variables: \n" + CollectionVariables.Token);
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

			System.out.println("Response Body: \n" + response.body().asPrettyString());

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

	// In stepDefenition -> Given()-> baseUri,Authorization as Bearer token,header
	public void httpWithAuth(String bearerToken) {

		bearerToken = CollectionVariables.Token;

		System.out.println("Token from collection variables: " + bearerToken);

		reqSpecWithAuth = RestAssured.given().spec(ReqBuilder()).header("Authorization", "Bearer " + bearerToken);

	}

	public Response getUserByUserIdWhenM(String endPoint, String reqType) {

		System.out.println("UserId saved from EnvVariables: " + CollectionVariables.UserId);

		// Retrieve the enum endPoint value
		EndPoints ep = EndPoints.valueOf(endPoint);
		System.out.println(" BaseUri with endpoint of " + endPoint + ": " + ep.getPath().toString());

		// Use the retrieved enum endpoint value and pass the userId from EnvVariables
		// to replace the placeholder
		String endpointWithUserId = ep.getPathWithUserId(CollectionVariables.UserId);
		System.out.println("BaseUri with endpoint and UserId: " + endpointWithUserId);

		if (reqType.equalsIgnoreCase("GET")) {
			response = reqSpecWithAuth.when().get(endpointWithUserId);
			if (CollectionVariables.Isbn1 == null) {
				CollectionVariables.Isbn1 = rm.getIsbn1(response);
				System.out
						.println("Setting isbn 0th element value in collection variables:" + CollectionVariables.Isbn1);
			}
			if (CollectionVariables.Isbn2 == null) {
				CollectionVariables.Isbn2 = rm.getIsbn2(response);
				System.out
						.println("Setting isbn 1th element value in collection variables:" + CollectionVariables.Isbn2);
			}
		}
		return response;

	}

	public void httpWithAuthBody() {
		Isbn_CreateBookFUser isbnCreateBookFUserPojo = new Isbn_CreateBookFUser(CollectionVariables.Isbn1);
		// Add Isbn_CreateBookFUser objects to the list
		List<Isbn_CreateBookFUser> isbnArray = new ArrayList<>();
		isbnArray.add(isbnCreateBookFUserPojo);

		PostCreateBookForUser_Pojo createBookForUserPojo = new PostCreateBookForUser_Pojo(CollectionVariables.UserId,
				isbnArray);
		// reqSpecWithAuth =
		// RestAssured.given().spec(ReqBuilder()).header("Authorization", "Bearer " +
		// bearerToken);
		reqSpecWithAuth = RestAssured.given().spec(ReqBuilder())
				.header("Authorization", "Bearer " + CollectionVariables.Token).body(createBookForUserPojo);
	}

	public Response whenMethodWithAuth(String endPoint, String reqType) {

		// Retrieve the enum endPoint value
		EndPoints ep = EndPoints.valueOf(endPoint);
		System.out.println(" BaseUri with endpoint of " + endPoint + ": " + ep.getPath().toString());

		if (reqType.equalsIgnoreCase("POST"))// ||reqType.equalsIgnoreCase("PUT"))
		{
			response = reqSpecWithAuth.when().post(ep.getPath());
		} else if (reqType.equalsIgnoreCase("GET"))
			response = reqSpec.when().get(ep.getPath());
		else if (reqType.equalsIgnoreCase("PUT"))
			response = reqSpec.when().basePath(ep.getPath()).put();
		else if (reqType.equalsIgnoreCase("DELETE"))
			response = reqSpec.when().basePath(ep.getPath()).delete();

		return response;

	}

}
