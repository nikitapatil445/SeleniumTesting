package guru99bank;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BusinessLogic extends utilities {
	
	public WebDriver driver;
	
	public void LogIn(String UserName ,String Password) throws IOException, IOException {
		
		setBrowser("chrome");
		Navigate("http://demo.guru99.com/V4");
		DsendKeys(getObject("LogInPage_Username"),"mngr26593");
		DsendKeys(getObject("LogInPage_Password"),"abc@123");
	
	}

}
