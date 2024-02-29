package adityaRathi.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Aditya.Rathi.pageobjects.CartPage;
import Aditya.Rathi.pageobjects.ProductCatalogue;
import adityaRathi.TestComponent.BaseTest;
import adityaRathi.TestComponent.Retry;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void submitOrder() throws Exception, InterruptedException
    {
    	
    	landingPage.loginApplication("john.smith123@gmail.com", "Johnh@123");

		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
    
    @Test
    public void ProductErrorValidation(){
    	
    
    String productName = "ZARA COAT 3";
	ProductCatalogue productCatalogue= landingPage.loginApplication( "jim.harden@gmail.com", "mary.Harden@123");
List<WebElement>products = productCatalogue.getProductList();
	productCatalogue.addProductToCart(productName);
	CartPage cartPage = productCatalogue.goToCartPage();
	
	Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");
	Assert.assertFalse(match);
    }
}
