package adityaRathi.stepDefinitions;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Aditya.Rathi.pageobjects.CartPage;
import Aditya.Rathi.pageobjects.CheckoutPage;
import Aditya.Rathi.pageobjects.ConfirmationPage;
import Aditya.Rathi.pageobjects.LandingPage;
import Aditya.Rathi.pageobjects.ProductCatalogue;
import adityaRathi.TestComponent.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	// tying code to feature file-
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws Exception {
		landingPage = launchAppliction();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@And("Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws Exception {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,400)");

		Thread.sleep(2000);

		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));

	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) 
	{
		Assert.assertEquals(strArg1 , landingPage.getErrorMessage());
		driver.close();

	}
}
