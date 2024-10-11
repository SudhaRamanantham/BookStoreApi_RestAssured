package utils;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Isbn_CreateBookFUser;
import pojo.PostCreateAccountUser_Pojo;
import pojo.PostCreateBookForUser_Pojo;
import pojo.PutBookWAnotherIsbn_Pojo;
import routes.EndPoints;

public class StepImplementation extends SpecBuild {

	public RequestSpecification reqSpec;

	public static Response response;

	public static ReusableMethods rm = new ReusableMethods();

	public RequestSpecification getBaseUriSpecBuild() {
		return RestAssured.given().spec(ReqBuilder());
	}

	// In stepDefenition -> Given()-> baseUri,header,requestBody
	public void httpNoAuthWBody() {
		PostCreateAccountUser_Pojo createAccountUserPojo = new PostCreateAccountUser_Pojo(
				PropertyReader.getStringProperty("username"), PropertyReader.getStringProperty("password"));
		reqSpec = getBaseUriSpecBuild().body(createAccountUserPojo);
	}

	// In stepDefinition -> When()-> requestType(Post,Get,Put,Delete) and endPoint
	public Response whenWEndPReqT(String endPoint, String reqType) {

		// Retrieve the enum endPoint value
		EndPoints ep = EndPoints.valueOf(endPoint);
		System.out.println(" BaseUri with endpoint of " + endPoint + ": " + ep.getPath().toString());

		if (reqType.equalsIgnoreCase("POST")) {
			response = reqSpec.when().post(ep.getPath());

			if (CollectionVariables.UserId == null) {
				CollectionVariables.UserId = rm.getUserID(response);
			}
			if (CollectionVariables.Token == null) {
				CollectionVariables.Token = rm.getToken(response);
			}
		} else if (reqType.equalsIgnoreCase("GET")) {
			System.out.println("Inside whenWEndReqT GET");
			httpWAuthNoBody(CollectionVariables.Token);
			response = reqSpec.when().get(ep.getPath());
			System.out.println("Request Spec Initialized: " + reqSpec);

			System.out.println("Response body inside whenWEndReqT GET: " + response.body().asPrettyString());
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

	// In stepDefinition -> Then()-> Verifying status code,status line
	public void thenMethod(Integer expectedStatusCode, String expectedStatusLine) {

		try {

			System.out.println("Response Body: \n" + response.body().asPrettyString());

			// Get the actual status message line from the response
			// Response message comes like -> "HTTP/1.1 200 OK"
			String resStatusMessage = response.getStatusLine();
			System.out.println("StatusMessage from response body: " + resStatusMessage);

			// Extract only the status code
			int resStatusCode = Integer.parseInt(resStatusMessage.split(" ")[1]);
			System.out.println("StatusCode from response body: " + resStatusCode);

			// Extract only the status message
			String resStatusLine = resStatusMessage.split(" ")[2];

			response.then().assertThat().spec(ResBuilder()).statusCode(expectedStatusCode)
					.statusLine("HTTP/1.1 " + expectedStatusCode + " " + expectedStatusLine);

			System.out.println("Test Passed: Status code and Status line matches. Expected status code: "
					+ expectedStatusCode + ", Response Status code: " + resStatusCode + ", Expected status line: "
					+ expectedStatusLine + ", Response Status line: " + resStatusLine);

		} catch (AssertionError e) {
			System.err.println("Test Failed: " + e.getMessage());
		}

	}

	/*
	 * In stepDefenition -> Given()-> baseUri,Authorization,header,requestBody and
	 * add Authorization as bearer token to
	 */
	public void httpWAuthNoBody(String bearerToken) {

		bearerToken = CollectionVariables.Token;

		System.out.println("Token from Collection variables: " + bearerToken);

		reqSpec = getBaseUriSpecBuild().header("Authorization", "Bearer " + bearerToken);

	}

	public Response whenWUserId(String endPoint, String reqType) {

		System.out.println("UserId saved from collection variables: " + CollectionVariables.UserId);

		// Retrieve the enum endPoint value
		EndPoints ep = EndPoints.valueOf(endPoint);
		System.out.println(" BaseUri with endpoint of " + endPoint + ": " + ep.getPath().toString());

		// Use the retrieved enum endpoint value and pass the userId from Collection
		// variables to replace the placeholder

		// Dynamically replace the {UserId} with the actual userId value
		String endpointWithUserId = ep.getPathWUserId(CollectionVariables.UserId);

		System.out.println("BaseUri with endpoint and UserId: " + endpointWithUserId);

		if (reqType.equalsIgnoreCase("GET")) {
			response = reqSpec.when().get(endpointWithUserId);
		} else if (reqType.equalsIgnoreCase("DELETE"))
			response = reqSpec.when().basePath(endpointWithUserId).delete();
		return response;

	}

	public void httpWAuthPostBody() {

		Isbn_CreateBookFUser isbnCreateBookFUserPojo = new Isbn_CreateBookFUser(CollectionVariables.Isbn1);
		// Add Isbn_CreateBookFUser objects to the list
		List<Isbn_CreateBookFUser> isbnArray = new ArrayList<>();
		isbnArray.add(isbnCreateBookFUserPojo);

		PostCreateBookForUser_Pojo createBookForUserPojo = new PostCreateBookForUser_Pojo(CollectionVariables.UserId,
				isbnArray);
		reqSpec = getBaseUriSpecBuild().header("Authorization", "Bearer " + CollectionVariables.Token)
				.body(createBookForUserPojo);
	}

	public Response whenWAuthPostBody(String endPoint, String reqType) {

		// Retrieve the enum endPoint value
		EndPoints ep = EndPoints.valueOf(endPoint);
		System.out.println(" BaseUri with endpoint of " + endPoint + ": " + ep.getPath().toString());

		if (reqType.equalsIgnoreCase("POST")) {
			response = reqSpec.when().post(ep.getPath());
			if (CollectionVariables.Isbn == null) {
				CollectionVariables.Isbn = rm.getIsbnFromBook(response);
				System.out.println("Setting isbn value in collection variables:" + CollectionVariables.Isbn);
			}
		} else if (reqType.equalsIgnoreCase("GET"))
			response = reqSpec.when().basePath(ep.getPath()).get();

		return response;

	}

	public void httpWAuthQueryParams() {

		reqSpec = getBaseUriSpecBuild().header("Authorization", "Bearer " + CollectionVariables.Token)
				.queryParam("ISBN", CollectionVariables.Isbn1);

	}

	public void httpWAuthPutBody() {

		System.out.println("Token value in collection variables: " + CollectionVariables.Token);

		System.out.println("UserId value in collection variables: " + CollectionVariables.UserId);

		System.out.println("Isbn2 value in collection variables: " + CollectionVariables.Isbn2);

		PutBookWAnotherIsbn_Pojo putBookWAnotherIsbnPojo = new PutBookWAnotherIsbn_Pojo(CollectionVariables.UserId,
				CollectionVariables.Isbn2);
		
		reqSpec = getBaseUriSpecBuild().header("Authorization", "Bearer " + CollectionVariables.Token)
				.body(putBookWAnotherIsbnPojo).log().body();

	}

	public Response whenWAnotherIsbn(String endPoint, String reqType) {

		System.out.println("Isbn value from collection variables: " + CollectionVariables.Isbn);

		// Retrieve the enum endPoint value
		EndPoints ep = EndPoints.valueOf(endPoint);
		System.out.println(" BaseUri with endpoint of " + endPoint + ": " + ep.getPath().toString());

		// Use the retrieved enum endpoint value and pass the Isbn1 from Collection
		// variables to replace the placeholder

		// Dynamically replace the {Isbn1} with the actual Isbn1 value
		String endpointWithIsbn = ep.getPathWIsbn(CollectionVariables.Isbn);

		System.out.println("BaseUri with endpoint and Isbn: " + endpointWithIsbn);

		if (reqType.equalsIgnoreCase("PUT"))
			response = reqSpec.when().put(endpointWithIsbn);

		return response;

	}

}
