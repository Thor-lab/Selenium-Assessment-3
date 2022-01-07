package ApplicationPages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import WebConnector.webconnector;
import static WebConnector.webconnector.driver;
import java.io.IOException;

public class Signinpage {
	webconnector wc=new webconnector();

	public void appIsLoaded() throws Exception {
		String URL=wc.getSpecificColumnData("./src/test/testdata/data.xlsx","sheet1", "URL", "");
		driver.get(URL);
		wc.waitForCondition("PageLoad","",60);
		wc.verify("Equals", "My Store", wc.driverCommand("GetPageTitle"));
		//Assert.assertEquals("My Store", driver.getTitle());
	}

	public void clickSignin() throws Exception {
		wc.PerformActionOnElement("SignInLink_HomePage", "WaitForElementDisplay","");
		wc.PerformActionOnElement("SignInLink_HomePage", "Click","");
		//driver.findElement(By.className("login")).click();
		wc.PerformActionOnElement("SignInButton_SignInPage", "WaitForElementDisplay","");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
	}

	public void enterUsername() throws Exception {
		wc.PerformActionOnElement("Usernam_SignInPage", "Type", "SeleniumTesting@gmail.com");
		//driver.findElement(By.id("email")).sendKeys("SeleniumTesting@gmail.com");
	}

	public void enterPassword() throws Exception {
		wc.PerformActionOnElement("Password_SignInPage", "Type", "Selenium123.");
	}


	public void clickSignInButton() throws Exception {
		wc.PerformActionOnElement("SignInButton_SignInPage", "Click", "");
		wc.PerformActionOnElement("OrderHistory_MyAcntPage", "WaitForElementDisplay", "");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icon-list-ol")));
	}

	public void loggedIn() throws Exception {
		wc.PerformActionOnElement("OrderHistory_MyAcntPage", "WaitForElementDisplay", "");
	}

}