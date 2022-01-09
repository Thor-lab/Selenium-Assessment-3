package stepdefs;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import ApplicationPages.Checkoutpage;
import ApplicationPages.Homepage;
import ApplicationPages.Orderhistorypage;
import ApplicationPages.SignUpPage;
import ApplicationPages.Signinpage;
import WebConnector.webconnector;
import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpSteps extends webconnector{
	private SignUpPage signUpPage;
	private Homepage homepage;
	private Signinpage signInPage;
	private Checkoutpage checkoutpage;
	private Orderhistorypage orderhistorypage;
	private String scenDesc;
	String totalItemPrice= null;
	String orderReferenceNumber = null;

	public SignUpSteps(){
		this.signUpPage = new SignUpPage();
		this.signInPage= new Signinpage();
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

	@When("I enter user personal information")
	public void enterUserDetails(DataTable table) throws Exception {
		this.signUpPage.enterTextInEmailAddressField();
		this.signUpPage.clickOnCreateAnAccountButton();

		ArrayList<String> fields=new ArrayList<String>();
		fields.add("First Name");
		fields.add("Last Name");
		//fields.add("Email Address");
		fields.add("Password");
		fields.add("Frist Name Address");
		fields.add("Last Name Address");
		fields.add("Address");
		fields.add("City");
		fields.add("State");
		fields.add("Postal Code");
		fields.add("Country");
		fields.add("Mobile phone");
		fields.add("Address Alias");
		int userCount= table.cells().size();
		for(int i=0; i<userCount; i++ ) {
			/*
			 * for (String x:fields) {
			 * this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,
			 * 0), x); }
			 */

			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "First Name");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "Last Name");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "Password");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "Frist Name Address");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "Last Name Address");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "Address");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "City");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "State");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "Postal Code");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "Country");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "Mobile phone");
			this.signUpPage.enterPersonalInformationOnCreateAnAccountButton(table.cell(i,0), "Address Alias");
			this.signUpPage.clickRegisterButton();
			this.signInPage.loggedIn();
			this.signUpPage.clickOnSignOutButton();
		}
		/*this.signUpPage.enterTextInEmailAddressField();
		this.signUpPage.clickOnCreateAnAccountButton();
	//	this.signUpPage.enterPersonalInformationOnCreateAnAccountButton("First Name", data.get(1).get(1));
		 */	}

	@When("I click register button")
	public void clickOnRegister() throws Exception {
		this.signUpPage.clickRegisterButton();

	}

	/*
	 * @Then("I am successfully registered") public void
	 * i_am_successfully_registered() { // Write code here that turns the phrase
	 * above into concrete actions throw new cucumber.api.PendingException(); }
	 */

	@Then("I get Warning message")
	public void warningMessageIsDisplayed() throws Exception {
		this.signUpPage.verifyWarningMessage();
	}


}
