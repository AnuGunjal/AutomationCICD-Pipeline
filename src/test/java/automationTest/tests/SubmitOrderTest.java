package automationTest.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationTest.pageobjects.CartPage;
import automationTest.pageobjects.CheckoutPage;
import automationTest.pageobjects.ConfirmationPage;
import automationTest.pageobjects.LoginPage;
import automationTest.pageobjects.OrderPage;
import automationTest.pageobjects.ProductCatlog;
import automatonTest.TestComponent.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest  extends BaseTest{
	String productName = "ZARA COAT 3";
	
//removing public static void main and converting it into testng @test annotation method
	
	@Test (dataProvider ="getData",groups= {"Purchase"})
	public void submitOrder(String email, String password) throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ProductCatlog pc=lp.loginToapplication(email, password);
		
		List<WebElement> productList=pc.getProducts();
		pc.addProductinCart(productName);
		CartPage cp = pc.goToCart();
		//pc.verifyCartItems(productName);
	
		boolean match =cp.verifyCartItems(productName);
		Assert.assertTrue(match);
		System.out.println(match);
		JavascriptExecutor jse6 = (JavascriptExecutor) driver;
		jse6.executeScript("window.scrollBy(0,250)", "");
		CheckoutPage chk =cp.gotoCheckout();
		chk.selectCountry("India");
		ConfirmationPage confirm =chk.submitOrder();
		String confirmMessage = confirm.displayOrder();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test (dependsOnMethods= {"submitOrder"},enabled= false)
	public void displayOrder()
	{
		//String productName = "ZARA COAT 3";
		ProductCatlog pc=lp.loginToapplication("anshika@gmail.com", "Iamking@000");
		OrderPage op= pc.goToOrderPage();
		op.verifyOrderItems(productName);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"anshika@gmail.com","Iamking@000"},{"anasha@gmail.com","Jack145@"}};
	}
}
	
