package adityaRathi.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Aditya.Rathi.pageobjects.CartPage;
import Aditya.Rathi.pageobjects.CheckoutPage;
import Aditya.Rathi.pageobjects.ConfirmationPage;
import Aditya.Rathi.pageobjects.LandingPage;
import Aditya.Rathi.pageobjects.OrderPage;
import Aditya.Rathi.pageobjects.ProductCatalogue;
import adityaRathi.TestComponent.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"})
	// data provider- picks 1st data set & run, then 2nd & run
	
	public void submitOrder(HashMap<String,String> input) throws Exception, InterruptedException 
	
	{
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,400)");

		Thread.sleep(2000);

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		Thread.sleep(2000);

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	// To verify ZARA Coat 3 is displaying in orders page

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		// "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("john.smith123@gmail.com", "Johnsmith@123");
		OrderPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	

	@DataProvider
	public Object[][] getData() throws Exception 
	{

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\adityaRathi\\data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)},{ data.get(1)} };
	}
	
}

 //using HashMap inserting data

//HashMap<String, String> map = new HashMap<String, String>();
//map.put("email", "john.smith123@gmail.com");
//map.put("password", "Johnsmith@123");
//map.put("product", "ZARA COAT 3");
//
//HashMap<String, String> map1 = new HashMap<String, String>();
//map1.put("email", "jim.harden@gmail.com");
//map1.put("password", "mary.Harden@123");
//map1.put("product", "ADIDAS ORIGINAL");


// using 2-D array inserting data

//@DataProvider
//public Object [] [] getData ()
//{
//   return new Object[] []	{{"john.smith123@gmail.com","Johnsmith@123", "ZARA COAT 3"}};
//}
