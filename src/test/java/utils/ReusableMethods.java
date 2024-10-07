package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public String getResBodyData(Response response, String key) {
		String resp = response.getBody().asString();
		System.out.println("Response body: " + resp);
		JsonPath js = new JsonPath(resp);
		System.out.println("Response body in json format: " + js.get(key).toString());
		return js.get(key).toString();
	}

	/* Reusable code for extracting particular given string value from response */

	public String extractStringFromResponse(Response response, String key) {
		String resBodyValue = response.jsonPath().getString(key);//.trim();
//		System.out.println(key+ " value is: "+ resBodyValue);
		return resBodyValue;

	}

	// Set userID value in environment variable
	public String setUserID(Response response) {
		String userIdValue = extractStringFromResponse(response, "userID");
		System.out.println("UserId value: " + userIdValue);
//		System.out.println("Setting userId Value in Env Variables");
//		EnvVariables.UserId = userIdValue;
//		System.out.println("UserId value from EnvVariables: " + EnvVariables.UserId);
		return userIdValue;
	}

	// Set token value in environment variable
	public String setToken(Response response) {
		String tokenValue = extractStringFromResponse(response, "token");
		System.out.println("Token value: " + tokenValue);
//		System.out.println("Setting token value in Env Variables");
//		EnvVariables.Token = tokenValue;
//		System.out.println("Token value from EnvVariables: " + EnvVariables.Token);
		return tokenValue;
	}

}
