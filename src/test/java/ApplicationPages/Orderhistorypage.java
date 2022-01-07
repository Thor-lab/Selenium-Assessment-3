package ApplicationPages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import WebConnector.webconnector;
import static WebConnector.webconnector.driver;
import java.io.IOException;

public class Orderhistorypage extends Checkoutpage{
	webconnector wc=new webconnector();
	public String getCurrentOrderDetailsUnderOrderHistoryPage(String tableSection) throws Exception {
		if (tableSection.equals("Order reference")) {
			return wc.PerformGetTextActionOnElement("currentOrderDetails_OrderHistoryPage" + "1]", "GetText");
		}
		if (tableSection.equals("Date")) {
			return wc.PerformGetTextActionOnElement("currentOrderDetails_OrderHistoryPage" + "2]", "GetText");
		}
		if (tableSection.equals("Total price")) {
			return wc.PerformGetTextActionOnElement("currentOrderDetails_OrderHistoryPage" + "3]", "GetText");
		}
		else {
			return null;
		}
	}
	
/*	public void verifyCurrentOrderDetailsUnderOrderHistoryPage(String tableSection, String epectedValue) throws Exception {
		String actualValue = getCurrentOrderDetailsUnderOrderHistoryPage(tableSection);
		wc.verify("Equals", actualValue, epectedValue);
	}*/
	
	public void verifyCurrentOrderDetailsUnderOrderHistoryPage(String referenceId, String expectedItemPrice) throws Exception {
		
		 
//		String ExpectedOrderID=getOrderReferenceNumberFromPaymentPage();
//		String expectedItemPrice= getTotalItemPriceOnTshirtPage();
		String expectedOrderDate= wc.getSystemDate();
		String orderId = getCurrentOrderDetailsUnderOrderHistoryPage("Order reference");
		String itemPrice=getCurrentOrderDetailsUnderOrderHistoryPage("Total price");
		String orderPlacedDate=getCurrentOrderDetailsUnderOrderHistoryPage("Date");
		wc.verify("Equals",orderId, referenceId);
		wc.verify("Equals", itemPrice, expectedItemPrice);
		wc.verify("Equals", orderPlacedDate, expectedOrderDate);
	}
}