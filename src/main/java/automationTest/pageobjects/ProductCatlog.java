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

public class ProductCatlog extends AbstractComponents{
	
	WebDriver driver;
	public ProductCatlog(WebDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
	

	//driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
	@FindBy(css =".mb-3")
	List<WebElement> productList;
	
	@FindBy(css =".card-body button:last-of-type")
	WebElement addbutton;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	
	
	@FindBy (css =".cartSection h3")
	List<WebElement> cartItems;

	
	By listOfProducts =By.cssSelector(".mb-3");
	By toastMessage = By.cssSelector("#toast-container");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	
	public  List<WebElement> getProducts()
	{
		waitForElementtoAppear(listOfProducts);
		return productList;
	}
	
	public WebElement getProductByName(String desiredprod)
	{
		WebElement desireProd = getProducts().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		return desireProd;
		//System.out.println(desireProd);
	}
	
	public void addProductinCart(String desiredprod) throws InterruptedException
	{
	
		WebElement prod =getProductByName(desiredprod);
		prod.findElement(addToCart).click();

		waitForElementtoAppear(toastMessage);
		waitForElementtoDisappear(spinner);
		Thread.sleep(2000);
		
		

	}
	
	
	
	
	
	
	

}
