package automatonTest.TestComponent;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automationTest.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LoginPage lp;
	
	public WebDriver initializeDriver() throws IOException{
			Properties pr = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\automationTest\\resources\\GlobalData.properties");
			pr.load(fis);
			
	String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") :pr.getProperty("browser");
			//String browserName = pr.getProperty("browser");
			if(browserName.equalsIgnoreCase("chrome"))
			{
				//WebDriverManager.chromedriver().setup();
				 driver = new ChromeDriver();
				 driver.manage().window().setSize(new Dimension(1440, 900));  // it helps you to run in fullscreen
					// import dimension from org.openqa.selenium package
				
			}
			else if(browserName.equalsIgnoreCase("firefox"))
			{
				
				 //driver = new FirefoxDriver();
			}
			else if (browserName.equalsIgnoreCase("edge"))
			{
				 driver = new EdgeDriver();
			}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				driver.manage().window().maximize();
				return driver;			
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json file and convert it into string
		String jsonContent = FileUtils.readFileToString(new File(
				filePath),
				StandardCharsets.UTF_8);

		// convert String into HashMap -- there is no inbuilt method from Java
		// hence we have to use the Jackson databind API
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts =  (TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}
	
	@BeforeMethod (alwaysRun=true)
	public LoginPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		lp = new LoginPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod (alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	
	

}
