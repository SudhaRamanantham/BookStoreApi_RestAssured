package utils;

import io.restassured.response.Response;

public class ReusableMethods {

	/* Reusable code for extracting particular given string value from response */
	public static String extractStringFromResponse(Response response, String key) {
		String resBodyValue = response.jsonPath().getString(key);// .trim();
		return resBodyValue;

	}

	// get userID value from response body
	public String getUserID(Response response) {
		String userIdValue = extractStringFromResponse(response, "userID");
		System.out.println("UserId value from response body: " + userIdValue);
		return userIdValue;
	}

	// get token value from response body
	public String getToken(Response response) {
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
		String isbnValue2 = extractStringFromResponse(response, "books[2].isbn");
		System.out.println("isbn 3rd element value from response body: " + isbnValue2);
		return isbnValue2;
	}

	// get isbn value from response body of PostCreateBookForUser
	public String getIsbnFromBook(Response response) {
		String isbnValue = extractStringFromResponse(response, "books[0].isbn");
		System.out.println("isbn value from response body: " + isbnValue);
		return isbnValue;
	}

}
