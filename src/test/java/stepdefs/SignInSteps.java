package stepdefs;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import ApplicationPages.Checkoutpage;
import ApplicationPages.Homepage;
import ApplicationPages.Orderhistorypage;
import ApplicationPages.Signinpage;
import WebConnector.webconnector;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInSteps extends webconnector{
	private Signinpage signInPage;
	private Homepage homepage;
	private Checkoutpage checkoutpage;
	private Orderhistorypage orderhistorypage;
	private String scenDesc;
	String totalItemPrice= null;
	String orderReferenceNumber = null;

	public SignInSteps(){
		this.signInPage = new Signinpage();
		this.homepage = new Homepage();
		this.checkoutpage = new Checkoutpage();
		this.orderhistorypage = new Orderhistorypage();
	}

	@Before
	public void before(Scenario scenario) {
		//this.scenDesc = scenario.getName();
		setUpDriver();
	}

	@After
	public void after(Scenario scenario){
		closeDriver(scenario);
	}

	//	@BeforeStep
	//	public void beforeStep() throws InterruptedException {
	//		Thread.sleep(2000);
	//	}


	@Given("I am on Website")
	public void applicationIsLoaded() throws Exception {
		this.signInPage.appIsLoaded();
	}
	
	@When("I click sign in button")
	public void clickOnSignInButton() throws Exception {
		this.signInPage.clickSignin();
	}

	@When("I enter username and password")
	public void LoginTest() throws Exception {
		this.signInPage.enterUsername();
		this.signInPage.enterPassword();
		this.signInPage.clickSignInButton();

		//		driver.findElement(By.id("email")).sendKeys("SeleniumTesting@gmail.com");
		//		driver.findElement(By.id("passwd")).sendKeys("Selenium123.");
		//		driver.findElement(By.id("SubmitLogin")).click();

	}

	@Then("I am successfully logged in")
	public void validateLogin() throws Exception {
		this.signInPage.loggedIn();
		//Assert.assertTrue(driver.getCurrentUrl().contains("my-account"));
	}

	@Given("I am logged in to application")
	public void verifyLoggedIntoApplication() throws Exception {
		this.signInPage.loggedIn();
	}

	@When("I select the T-shirt")
	public void clickOnTshirtLinkOnHomePage() throws Exception {
		this.homepage.clickOnTshirtLink();

	}

	@When("I add T-shirt to cart")
	public void addTshirtToCart() throws Exception {
		this.homepage.clickOnAddToCartButton();
		totalItemPrice = this.checkoutpage.getTotalItemPriceOnTshirtPage();
	}

	@When("I place the order")
	public void clickOnProceedToCheckoutButtonOnPopup() throws Exception {
		//totalItemPrice = this.checkoutpage.getTotalItemPriceOnTshirtPage();
		this.homepage.clickOnProceedToCheckoutButton();
		this.checkoutpage.verifySelectedShoppingCartHeader("Your shopping cart");
		this.checkoutpage.clickOnProceedToCheckoutButtonOnCheckoutPage();
		this.checkoutpage.verifySelectedShoppingCartHeader("Addresses");
		this.checkoutpage.clickOnProceedToCheckoutButtonOnAddressPage();
		this.checkoutpage.verifySelectedShoppingCartHeader("Shipping");
		this.checkoutpage.clickOnTermsOfServiceCheckbox();
		this.checkoutpage.clickOnProceedToCheckoutButtonOnAddressPage();
		this.checkoutpage.verifySelectedShoppingCartHeader("Your payment method");
		this.checkoutpage.clickOnPayByBankWireOption();
		this.checkoutpage.verifySelectedShoppingCartHeader("Bank-wire payment.");
		this.checkoutpage.clickOnConfirmMyOrderButton();
		this.checkoutpage.verifySelectedShoppingCartHeader("Order confirmation");
		orderReferenceNumber = this.checkoutpage.getOrderReferenceNumberFromPaymentPage();
		this.checkoutpage.clickOnBackToOrdersButton();
	}


	@Then("I can see ordered T-shirt in Order History")
	public void i_can_see_ordered_T_shirt_in_Order_History() throws Exception {
		this.orderhistorypage.verifySelectedShoppingCartHeader("Order history");
		this.orderhistorypage.verifyCurrentOrderDetailsUnderOrderHistoryPage(orderReferenceNumber,totalItemPrice);
	}
}
