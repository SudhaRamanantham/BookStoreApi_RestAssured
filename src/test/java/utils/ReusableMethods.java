package utils;

import io.restassured.response.Response;

public class ReusableMethods {

	// Reusable code for extracting particular given string value from response
	public String extractStringFromResponse(Response response, String key) {
		String resBodyValue = response.jsonPath().getString(key);
		return resBodyValue;
	}

	// Set userID value in collection variables
	public String setUserID(Response response) {
		String userIdValue = extractStringFromResponse(response, "userID");
		System.out.println("UserId value from response body: " + userIdValue);
		return userIdValue;
	}

	// Set token value in collection variables
	public String setToken(Response response) {
		String tokenValue = extractStringFromResponse(response, "token");
		System.out.println("Token value from response body: " + tokenValue);
		return tokenValue;
	}

}
