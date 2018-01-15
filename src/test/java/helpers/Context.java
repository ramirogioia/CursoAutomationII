package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Context {
	
	private WebDriver driver;
	private XMLReader reader = new XMLReader();

	public void initializeDriver() {
		
		if(reader.readNode("browser").toUpperCase().contains("CHROME")) {
			
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}else {
			
			if(reader.readNode("browser").toUpperCase().contains("FIREFOX")) {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setJavascriptEnabled(true);
				System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}else {
				
				driver = null;
			}
		}
	}
	
	public WebDriver getDriver() {
		
		return driver;
	}

	public void setDriver(WebDriver driver) {
		
		this.driver = driver;
	}
}
