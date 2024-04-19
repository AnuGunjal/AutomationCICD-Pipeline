package automationTest.tests;

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

import automationTest.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		// WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");

		driver.findElement(By.id("login")).click();
		LoginPage lp = new LoginPage(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));

		WebElement desireProd = productList.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		System.out.println(desireProd);
		// desireProd.findElement(By.xpath("//button[2]/i")).click();
		// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".card-body
		// button:last-of-type")));

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.cssSelector(".card-body button:last-of-type"))));
		Thread.sleep(2000);
		desireProd.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// wait.until(ExpectedConditions.invisibilityOfEledriver.findElement(By.className(".ng-animating"))));

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[routerlink*='cart']"))));
		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		Thread.sleep(2000);
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartItems.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		System.out.println(match);
		int maxAttempts = 3; // Maximum number of times you want to retry

		int attempt = 1;
		boolean elementClickable = false;

		while (attempt <= maxAttempts)

			try {
				// Thread.sleep(2000);
				wait.until(ExpectedConditions
						.elementToBeClickable(driver.findElement(By.cssSelector(".totalRow button"))));
				Thread.sleep(4000);
				driver.findElement(By.cssSelector(".totalRow button")).click();
				elementClickable = true;
			} catch (Exception e) {
				attempt++;
			}
		if (!elementClickable) {
			System.out
					.println("Element was not clickable after " + maxAttempts + " attempts. Try a different strategy?");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".payment")));
/*
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");

		List<WebElement> searchResult = driver
				.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']"));
		Thread.sleep(2000);
		for (WebElement search : searchResult)
			if (search.getText().equalsIgnoreCase("India")) {
				System.out.println(search.getText());
				search.click();
				break;
			}

	}
	*/
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		Thread.sleep(2000);
		elementClickable = false;
		try
		{
		//wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Place Order ")));
		//driver.findElement(By.cssSelector(".action__submit")).click();
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		elementClickable = true;
		}
		catch(Exception e)
		{
			attempt++;
		}
		if (!elementClickable) {
			System.out
					.println("Element was not clickable after " + maxAttempts + " attempts. Try a different strategy?");
		}
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();

	}
}
