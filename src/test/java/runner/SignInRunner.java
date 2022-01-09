package runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions( 
		//tags = {"@signinpage"},
		glue = {"stepdefs"}, 
		plugin = {"html:target/cucumber-reports/cucumber-pretty","json:target/json-cucumber-reports/default/cukejson.json",
		"testng:target/testng-cucumber-reports/cuketestng.xml" }, 
		features = {"src/test/resources/features/SignInPage"})

public class SignInRunner extends AbstractTestNGCucumberParallelTests{
	
}
