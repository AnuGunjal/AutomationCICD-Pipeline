package automationTest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import automationTest.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	
	@FindBy (css =".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy (css =".totalRow button")
	WebElement checkoutButton;
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyCartItems(String desiredprod)
	{
		
		boolean match = cartItems.stream().anyMatch(s -> s.getText().equalsIgnoreCase(desiredprod));
		return match;
		
	}
	
	public CheckoutPage gotoCheckout()
	{
		int maxAttempts = 3; // Maximum number of times you want to retry

		int attempt = 1;
		boolean elementClickable = false;

		while (attempt <= maxAttempts)

			try {
				// Thread.sleep(2000);
				waitForElementtoClickable(checkoutButton);
				Thread.sleep(4000);
				checkoutButton.click();
				elementClickable = true;
			} catch (Exception e) {
				attempt++;
			}
		if (!elementClickable) {
			System.out
					.println("Element was not clickable after " + maxAttempts + " attempts. Try a different strategy?");
		}
		return new CheckoutPage(driver);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".payment")));

		
	}

}
