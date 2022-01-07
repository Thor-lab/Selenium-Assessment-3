package runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions( 
		//tags = {"@signinpage"},
		glue = {"stepdefs"}, 
//		plugin = {"html:target/cucumber-reports/SignIn/cucumber-pretty",
//				"json:target/json-cucumber-reports/signin/cukejson.json",
//				"testng:target/testng-cucumber-reports/HomePage/cuketestng.xml" }, 
		features = {"src/test/resources/features/SignInPage"})

public class SignInRunner extends AbstractTestNGCucumberParallelTests{
	
}
