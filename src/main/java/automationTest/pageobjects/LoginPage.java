package automationTest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationTest.abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	

	//driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
	@FindBy(id ="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	
	@FindBy (xpath="//div[@id='toast-container']/div")
	//(css="[class*='flyInOut']")
	
	WebElement errorMessage;
	
	public ProductCatlog loginToapplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatlog pc=new ProductCatlog(driver);
		return pc;
		
		
	}
	
	public String getErrorMessage() throws InterruptedException
	{
		Thread.sleep(2000);
		waitForWebElementtoAppear(errorMessage);
		return errorMessage.getText();
		
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

}
