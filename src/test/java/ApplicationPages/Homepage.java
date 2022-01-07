package ApplicationPages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import WebConnector.webconnector;
import static WebConnector.webconnector.driver;
import java.io.IOException;

public class Homepage {
	webconnector wc=new webconnector();

	public void clickOnTshirtLink() throws Exception {
		wc.PerformActionOnElement("TshirtLink_HomePage", "Click", "");
		//wc.PerformActionOnElement("TshirtLabel_TshirtPage", "WaitForElementDisplay", "");
	}

	public void clickOnAddToCartButton() throws Exception {
		wc.performMouseActions("Item_TshirtPage", "MouseHover");
		wc.PerformActionOnElement("AddToCartButton_TshirtPage", "Click", "");
	}

	public void clickOnProceedToCheckoutButton() throws Exception {
		wc.PerformActionOnElement("ProceedToCheckout_TshirtPage", "WaitForElementDisplay", "");
		wc.PerformActionOnElement("ProceedToCheckout_TshirtPage", "Click", "");
	}


}