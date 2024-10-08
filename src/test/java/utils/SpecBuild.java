package utils;

import java.io.FileOutputStream;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuild {

	public RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
	public ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();

	// Setting baseUri without Auth for reusable method
	public RequestSpecification ReqBuilder() {

		try {
            // Open log file in append mode (true ensures logs are appended)
			PrintStream logFile = new PrintStream(new FileOutputStream("log.txt",true));
			
            // Setup request specification with baseUri, logging, and content type
			reqBuilder.setBaseUri(PropertyReader.getStringProperty("baseUri"))
					.addFilter(RequestLoggingFilter.logRequestTo(logFile))
					.addFilter(ResponseLoggingFilter.logResponseTo(logFile))
					.setContentType("application/json");
			
			// Create the request specification
			return reqBuilder.build();
		} catch (Exception e) {
			e.printStackTrace(); // Print stack trace for debugging
			throw new RuntimeException(e);
		}
		
	}

	public ResponseSpecification ResBuilder() {

        // ResponseSpecBuilder for reusable response setup
		resBuilder.expectContentType(ContentType.JSON);

		return resBuilder.build();

	}

}
