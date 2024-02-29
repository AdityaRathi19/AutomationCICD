package Aditya.Rathi.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Aditya.Rathi.pageobjects.CartPage;
import Aditya.Rathi.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	//constructor- calling driver from diff class
	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css ="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css ="[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) 
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) 
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public CartPage goToCartPage() 
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}
	
	public OrderPage goToOrderPage() 
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;

	}
	
	public void  waitForElementToDisappear(WebElement ele) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	

}
