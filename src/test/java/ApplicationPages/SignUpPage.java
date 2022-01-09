package ApplicationPages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import WebConnector.webconnector;
import static WebConnector.webconnector.driver;
import java.io.IOException;

public class SignUpPage {
	webconnector wc=new webconnector();

	public void enterTextInEmailAddressField() throws Exception {
		String userEmail=wc.getSpecificColumnData("./src/test/testdata/signupData.xlsx","sheet1", "Email Address", "First User");
		wc.PerformActionOnElement("EmailAddressField_SignUpPage", "Type", userEmail);
	}

	public void clickOnCreateAnAccountButton() throws Exception {
		wc.PerformActionOnElement("CreateAccountButton_SignUpPage", "Click", "");
		wc.PerformActionOnElement("PageHeader_SignUpPage", "WaitForElementDisplay", "");
	}
	
	/*
	 * public void enterPersonalInformationOnCreateAnAccountButton(String fieldName)
	 * throws Exception { String
	 * fieldValue=wc.getSpecificColumnData("./src/test/testdata/data2.xls","sheet3",
	 * "User Email"); switch (fieldName) { case "First Name Personal Information":
	 * wc.PerformActionOnElement("FirstName_PersonalInformationSection", "Type",
	 * fieldValue); break;
	 * 
	 * case "Last Name Personal Information":
	 * wc.PerformActionOnElement("LastName_PersonalInformationSection", "Type",
	 * fieldValue); break;
	 * 
	 * default: break; }
	 */
		
		public void enterPersonalInformationOnCreateAnAccountButton(String rowNum, String fieldName) throws Exception {
			String element;
			switch (fieldName) {
			case "First Name":
				element="FirstName_PersonalInformationSection";
				break;
			case "Last Name":
				element="LastName_PersonalInformationSection";
				break;
			case "Email Address":
				element="email_PersonalInformationSection ";
				break;
			case "Password":
				element="password_PersonalInformationSection";
				break;
			case "Frist Name Address":
				element="FirstName_YourAddressSection";
				break;
			case "Last Name Address":
				element="LastName_YourAddressSection";
				break;
			case "Address":
				element="Address_YourAddressSection";
				break;
			case "City":
				element="City_YourAddressSection";
				break;
			case "State":
				element="State_YourAddressSection";
				break;
			case "Postal Code":
				element="PostalCode_YourAddressSection";
				break;
			case "Country":
				element="Country_YourAddressSection";
				break;
			case "Mobile phone":
				element="MobilePhone_YourAddressSection";
				break;
			case "Address Alias":
				element="AddressAlias_YourAddressSection";
				break;
			default:
				throw new IllegalArgumentException("Feild \"" + fieldName + "\" doesn't exist.");
			}
			if(fieldName== "State" || fieldName== "Country") {

				String fieldValue=wc.getSpecificColumnData("./src/test/testdata/signupData.xlsx","sheet1", fieldName, rowNum);
				Select dropdown = new Select(wc.FindAnElement(element)); 
			      dropdown.selectByValue("21");
				
			//	wc.PerformActionOnElement(element, "Type", fieldValue);
			}
			else if(fieldName== "Postal Code" || fieldName== "Mobile phone") {
				String fieldValue=wc.getSpecificColumnData("./src/test/testdata/signupData.xlsx","sheet1", fieldName, rowNum);
				fieldValue=fieldValue.replaceAll("\"", "");
				wc.PerformActionOnElement(element, "Type", fieldValue);
			}
			else {
				String fieldValue=wc.getSpecificColumnData("./src/test/testdata/signupData.xlsx","sheet1", fieldName, rowNum);
				wc.PerformActionOnElement(element, "Type", fieldValue);
			}
	}
		
		public void clickOnSignOutButton() throws Exception{
			wc.PerformActionOnElement("SignOut_HomePage", "Click", "");
		}
		public void clickRegisterButton() throws Exception {
			wc.PerformActionOnElement("SubmitButton_SignUpPage", "Click", "");
			
		}
		
		
		public void verifyWarningMessage() throws Exception {
			wc.PerformActionOnElement("WarningForInvalidPhoneNumber_SignUpPage", "WaitForElementDisplay", null);
		}
}