package WebConnector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import io.cucumber.core.api.Scenario;
import org.testng.Assert;

public class webconnector<V> {

	public static  WebDriver driver = null;
	public  SessionId session=null;
	public static Properties prop = new Properties();

	public webconnector(){
		try {
			prop.load( new FileInputStream("./src/test/config/application.properties") );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setUpDriver(){

		String browser = prop.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver","./src/test/lib/chromedriver2.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("start-maximized");
			//session = ((ChromeDriver)driver).getSessionId();
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver","./src/test/lib/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			//session = ((FirefoxDriver)driver).getSessionId();
			break;
		default:
			throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
		}
	}

	public void closeDriver(Scenario scenario){
		if(scenario.isFailed()){
			saveScreenshotsForScenario(scenario);
		}
		driver.quit();
	}

	private void saveScreenshotsForScenario(final Scenario scenario) {
		final byte[] screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}

	public void waitForPageLoad(int timeout){
		ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
	}


	public String getSpecificColumnData(String FilePath, String SheetName, String ColumnName, String RowNum) throws InvalidFormatException, IOException {
		DataFormatter formatter = new DataFormatter();
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(SheetName);
		XSSFRow row1 = sheet.getRow(0);
		int col_num = -1;
		int row_num=1;
		for(int i=0; i < row1.getLastCellNum(); i++)
		{
			if(row1.getCell(i).getStringCellValue().trim().equals(ColumnName))
				col_num = i;
		}
		if (RowNum=="row1") {
			row_num=1;
		}
		else {
			for (Row row : sheet) {
				for (Cell cell : row) {

					// get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
					String text = formatter.formatCellValue(cell);
					// String text = row.cell().getStringCellValue()

					// is it an exact match?
					if (RowNum.equals(text)) {
						row_num= row.getRowNum();
					}

				}
			}
		}
		row1 = sheet.getRow(1);
		XSSFCell cell = row1.getCell(col_num);
		String value = cell.getStringCellValue();
		fis.close();
		System.out.println("Value of the Excel Cell is - "+ value);    	 
		return value;
	}

	public void setSpecificColumnData(String FilePath, String SheetName, String ColumnName) throws IOException{
		FileInputStream fis;
		fis = new FileInputStream(FilePath);
		FileOutputStream fos = null;
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(SheetName);
		XSSFRow row = null;
		XSSFCell cell = null;
		XSSFFont font = workbook.createFont();
		XSSFCellStyle style = workbook.createCellStyle();
		int col_Num = -1;
		row = sheet.getRow(0);
		for(int i = 0; i < row.getLastCellNum(); i++)
		{
			if(row.getCell(i).getStringCellValue().trim().equals(ColumnName))
			{
				col_Num = i;
			}
		}
		row = sheet.getRow(1);
		if(row == null)
			row = sheet.createRow(1);
		cell = row.getCell(col_Num);
		if(cell == null)
			cell = row.createCell(col_Num);
		font.setFontName("Comic Sans MS");
		font.setFontHeight(14.0);
		font.setBold(true);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		style.setFillForegroundColor(HSSFColor.GREEN.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		cell.setCellValue("PASS");
		fos = new FileOutputStream(FilePath);
		workbook.write(fos);
		fos.close();
	}

	public By getElementWithLocator(String WebElement) throws Exception {
		String locatorTypeAndValue = prop.getProperty(WebElement);
		String[] locatorTypeAndValueArray = locatorTypeAndValue.split(",", 2);
		String locatorType = locatorTypeAndValueArray[0].trim();
		String locatorValue = locatorTypeAndValueArray[1].trim();
		switch (locatorType.toUpperCase()) {
		case "ID":
			return By.id(locatorValue);
		case "NAME":
			return By.name(locatorValue);
		case "TAGNAME":
			return By.tagName(locatorValue);
		case "LINKTEXT":
			return By.linkText(locatorValue);
		case "PARTIALLINKTEXT":
			return By.partialLinkText(locatorValue);
		case "XPATH":
			return By.xpath(locatorValue);
		case "CSS":
			return By.cssSelector(locatorValue);
		case "CLASSNAME":
			return By.className(locatorValue);
		default:
			return null;
		}
	}

	public WebElement FindAnElement(String WebElement) throws Exception{
		return driver.findElement(getElementWithLocator(WebElement));
	}

	public void PerformActionOnElement(String WebElement, String Action, String Text) throws Exception {
		switch (Action) {
		case "Click":
			FindAnElement(WebElement).click();
			break;
		case "Type":
			FindAnElement(WebElement).sendKeys(Text);
			break;
		case "Clear":
			FindAnElement(WebElement).clear();
			break;
		case "WaitForElementDisplay":
			waitForCondition("Visibility",WebElement,60);
			break;
		case "WaitForElementClickable":
			waitForCondition("Clickable",WebElement,60);
			break;
		case "ElementNotDisplayed":
			waitForCondition("NotPresent",WebElement,60);
			break;
		default:
			throw new IllegalArgumentException("Action \"" + Action + "\" isn't supported.");
		}
	}

	public String PerformGetTextActionOnElement(String WebElement, String Action) throws Exception {
		String textValue = null;
		switch (Action) {
		case "GetText":
			textValue =	FindAnElement(WebElement).getText();
			return textValue;
		default:
			throw new IllegalArgumentException("Action \"" + Action + "\" isn't supported.");
		}
	}

	public void waitForCondition(String TypeOfWait, String WebElement, int Time){
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Time, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);
			switch (TypeOfWait)
			{
			case "PageLoad":
				wait.until( ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
				break;
			case "Clickable":
				wait.until(ExpectedConditions.elementToBeClickable(FindAnElement(WebElement)));
				break;
			case "Presence":
				wait.until(ExpectedConditions.presenceOfElementLocated(getElementWithLocator(WebElement)));
				break;
			case "Visibility":
				wait.until(ExpectedConditions.visibilityOfElementLocated(getElementWithLocator(WebElement)));
				break;
			case "NotPresent":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(getElementWithLocator(WebElement)));
				break;
			default:
				Thread.sleep(Time*1000);
			}
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("wait For Condition \"" + TypeOfWait + "\" isn't supported.");
		}
	}
	public String driverCommand(String Command) {
		switch (Command) {
		case "GetPageTitle":
			return driver.getTitle();
		default:
			throw new IllegalArgumentException("Command \"" + Command + "\" isn't supported.");
		}
	}

	public void verify(String AssertionType, String Text1, String Text2) {
		switch (AssertionType)
		{
		case "Equals":
			Assert.assertEquals(Text1, Text2);
			break;
		default:
			throw new IllegalArgumentException("AssertionType \"" + AssertionType + "\" isn't supported.");
		}
	}
	public void performMouseActions(String WebElement, String ActionToBePerformed) throws Exception {
		Actions act = new Actions(driver);
		switch (ActionToBePerformed)
		{
		case "MouseHover":
			act.moveToElement(FindAnElement(WebElement)).build().perform();
			break;
		default:
			throw new IllegalArgumentException("AssertionType \"" + ActionToBePerformed + "\" isn't supported.");
		}
	}

	public String getSystemDate() throws Exception {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
		String dateOnly = dateFormat.format(currentDate);
		return dateOnly;
	}
}