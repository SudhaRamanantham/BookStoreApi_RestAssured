package stepDefinition;

import io.cucumber.java.BeforeAll;
import utils.PropertyReader;
import utils.StepImplementation;

public class Hooks extends StepImplementation {

	@BeforeAll
	public static void getProperty() {
		PropertyReader.loadProperties();
	}

}
