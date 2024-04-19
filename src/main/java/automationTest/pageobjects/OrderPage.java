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

public class OrderPage extends AbstractComponents {
	WebDriver driver;
	
	@FindBy (css =".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy (css ="tr td:nth-child(3)")
	List<WebElement> OrderedItems;
	
	
	
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyOrderItems(String productOrder)
	{
		
		boolean match = OrderedItems.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productOrder));
		System.out.println(match);
		return match;
	}
	
	

}
