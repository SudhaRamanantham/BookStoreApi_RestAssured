package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/BookStoreApi_RestAssured_Sudha_Organized.html", "json:target/cucumber-report.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, // reporting purpose
		monochrome = false, // console output color
//		tags = "@FeatureFileTagName", // tags from feature file
		dryRun = !true, // To add new method give true
		features = { "src/test/resources/features" }, glue = { "stepDefinition" })

public class TestRunner {

}
