package automationTest.resources;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationTest.pageobjects.CartPage;
import automationTest.pageobjects.LoginPage;

public class AbstractComponents {
	WebDriver driver;
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	@FindBy (xpath ="//button[@routerlink='/dashboard/cart']")
	WebElement cartSection;

	public AbstractComponents(WebDriver driver) {
		this.driver =driver;
	}

	public void waitForElementtoAppear(By find) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(find));
	}

	public void waitForElementtoDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	
	public void waitForElementtoClickable(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));

	}
	
	public CartPage goToCart() throws InterruptedException
	{
		Thread.sleep(2000);
		//waitForElementtoClickable(cartSection);
		cartSection.click();
		Thread.sleep(2000);
		CartPage cp = new CartPage(driver);
		return cp;
	}

}
