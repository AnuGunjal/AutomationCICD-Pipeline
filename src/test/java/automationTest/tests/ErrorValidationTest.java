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
import org.testng.annotations.Test;

import automationTest.pageobjects.CartPage;
import automationTest.pageobjects.CheckoutPage;
import automationTest.pageobjects.ConfirmationPage;
import automationTest.pageobjects.LoginPage;
import automationTest.pageobjects.ProductCatlog;
import automatonTest.TestComponent.BaseTest;
import automatonTest.TestComponent.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest  extends BaseTest{
	
//removing public static void main and converting it into testng @test annotation method
	
	@Test (retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException
	{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ProductCatlog pc=lp.loginToapplication("anshika@gmail.com", "Iamking@00");
		lp.getErrorMessage();
		System.out.println(lp.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		
	}
	
	@Test (groups= {"ErrorHandling"})
	public void productErrorValidation() throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ProductCatlog pc=lp.loginToapplication("rahulshetty@gmail.com", "Iamking@000");
		
		List<WebElement> productList=pc.getProducts();
		pc.addProductinCart(productName);
		CartPage cp = pc.goToCart();
		//pc.verifyCartItems(productName);
	
		boolean match =cp.verifyCartItems("Zraa Coar 3");
		Assert.assertFalse(match);
		System.out.println(match);
		
	}
	
		
}


	

