package guru99bank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class utilities {
	
	public static String basepath = System.getProperty("user.dir");
	public static String driverpath = basepath+"\\Driver";
	public static String ConfgPath =basepath+"\\ConfgPath\\cofg.properties";
	public static String ObjectPath = basepath+"\\ObjectPath\\object.properties";
	public static String screenshot = basepath+"\\screenshot";
	public static String report = basepath+"\\report";
	public static String testdata = basepath+"\\testdata";
	public static String batFiles =basepath+"\\batFiles";
	public WebDriver driver;
	
	/**********************************************************************
	 * Discription: congif all data from confg.properties file before test
	 * 
	 ******************************************************************/
	
	@BeforeSuite
	public void loadConfg(String path) {
		Properties properties =new Properties();
		properties.load(readFile(path));
		
		
	}
	
	/**********************************************************************
	 * Discription: get file path and read file
	 * @throws FileNotFoundException 
	 * 
	 ******************************************************************/
	public FileInputStream readFile(String Filepath) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream("Filepath");
		return fis;
		
	}
	
	/**********************************************************************
	 * Discription: Takes browser (IE or Chrome) and clear cache by this method
	 * 
	 ******************************************************************/
	@BeforeTest()
	public void clearCache(String browser) {
		Process run;
		if(browser.equalsIgnoreCase("ie")|| browser.equalsIgnoreCase("Internetexplorer")) {
		     run = Runtime.getRuntime().exec("cmd.exe /c Start"+batFiles+"\\IECacheClear.bat");
		}
	   else if(browser.equalsIgnoreCase("chrome")|| browser.equalsIgnoreCase("Chrome")){
			run = Runtime.getRuntime().exec("cmd.exe /c Start"+batFiles+"\\ChromeClearCache.bat");
		}
	   else {
		   System.out.println("Error: Browser is not supporting");
		}
	}
	
	/**********************************************************************
	 * Discription: Takes Browser(ie or chrome) and set path 
	 * 
	 ******************************************************************/
	
	public void setBrowser(String browserName) {
	      
		if(browserName.equalsIgnoreCase("ie")|| browserName.equalsIgnoreCase("Internetexplorer")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\nikit\\Desktop\\Selenium\\chromedriver.exe");
			driver = new InternetExplorerDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome")|| browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\nikit\\Desktop\\Selenium\\chromedriver.exe");
		    driver = new ChromeDriver();
		}
		else {
			System.out.println("Error: Incorrect path for browser driver");
		}
	}
	
	/**********************************************************************
	 * Discription: Navigate to Application URL 
	 * 
	 ******************************************************************/
	
	public void Navigate(String url) {
		driver.manage().window().maximize();	
		driver.get(url);
		
	}
	
	public WebElement getObject(String Key) throws FileNotFoundException, IOException {
		WebElement element;
		Properties properties =new Properties();
		properties.load(readFile(ObjectPath));
		String LocatorType;
		String Locator;
		LocatorType = properties.getProperty(Key).split(":")[0];
		Locator = properties.getProperty(Key).split(":")[1];
		switch(LocatorType) {
		case"name":
			element = driver.findElement(By.name("Locator"));
			break;
			
		case"linktext":
			element = driver.findElement(By.linkText("Locator"));
			break;
			
		case"id":
			element = driver.findElement(By.id("Locator"));
			break;
			
		case"partiallinktext":
			element = driver.findElement(By.partialLinkText("Locator"));
			break;
			
		case"class":
			element = driver.findElement(By.className("Locator"));
			break;
			
		case"xpath":
			element = driver.findElement(By.xpath("Locator"));
			break;
		
		 default:
			 
			 return null;
		}
		
		return element;
		 
			
		}
		
	/************************************************************************
	 * Discription:Click function method
	 * 
	 ************************************************************************/
	public void Click(WebElement element) {
		
		element.click();
	}
	
	/************************************************************************
	 * Discription:Sendkey function method
	 * 
	 ************************************************************************/
	public void DsendKeys(WebElement element , String inputdata) {
		
		element.sendKeys(inputdata);
	}
	
		
	
	/************************************************************************
	 * Discription:close session after test
	 * 
	 ************************************************************************/
		
	@AfterTest()
	public void quiteSession() {
		driver.close();
		driver.quit();
		
	}
	
}