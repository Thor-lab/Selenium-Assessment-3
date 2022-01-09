package ApplicationPages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import WebConnector.webconnector;
import static WebConnector.webconnector.driver;
import java.io.IOException;

public class Checkoutpage {
	webconnector wc=new webconnector();

	public void verifySelectedShoppingCartHeader(String requiredHeaderText) throws Exception {
		String returnedPageHeader = wc.PerformGetTextActionOnElement("checkoutPageHeader", "GetText").trim();
		wc.verify("Equals", returnedPageHeader, requiredHeaderText);
	}

	public void clickOnProceedToCheckoutButtonOnCheckoutPage() throws Exception {
		wc.PerformActionOnElement("proceedToCheckoutButton", "Click", "");
	}

	public void clickOnProceedToCheckoutButtonOnAddressPage() throws Exception {
		wc.PerformActionOnElement("proceedToCheckoutButton_AddressPage", "Click", "");
	}

	public void clickOnTermsOfServiceCheckbox() throws Exception {
		wc.PerformActionOnElement("termsOfServiceCheckbox", "Click", "");
	}

	public void clickOnPayByBankWireOption() throws Exception {
		wc.PerformActionOnElement("payByBankWireOption", "Click", "");
	}

	public void clickOnConfirmMyOrderButton() throws Exception {
		wc.PerformActionOnElement("confirmMyOrderButton", "Click", "");
	}

	public void clickOnBackToOrdersButton() throws Exception {
		wc.PerformActionOnElement("backToOrdersButton", "Click", "");
	}

	public String getTotalItemPriceOnTshirtPage() throws Exception {
		wc.PerformActionOnElement("TotalItemPrice_TshirtPage", "WaitForElementDisplay","");
		return wc.PerformGetTextActionOnElement("TotalItemPrice_TshirtPage", "GetText");
	}

	public String getOrderReferenceNumberFromPaymentPage() throws Exception {
		String output = wc.PerformGetTextActionOnElement("orderCompletionDetails", "GetText");
		String order= "order reference";
		int index= output.indexOf(order) + order.length(); 
		int index2= output.indexOf(" ", index+1);
		String orderReferenceNumber= output.substring(index, index2).trim();
		return orderReferenceNumber;
	}

}