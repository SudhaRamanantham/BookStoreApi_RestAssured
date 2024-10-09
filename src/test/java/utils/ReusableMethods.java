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

	// get isbn 0th element value from response body
	public String getIsbn1(Response response) {
		String isbnValue1 = extractStringFromResponse(response, "books[0].isbn");
		System.out.println("isbn 0th element value from response body: " + isbnValue1);
		return isbnValue1;
	}

	// get isbn 0th element value from response body
	public String getIsbn2(Response response) {
		String isbnValue2 = extractStringFromResponse(response, "books[1].isbn");
		System.out.println("isbn 1th element value from response body: " + isbnValue2);
		return isbnValue2;
	}

}
