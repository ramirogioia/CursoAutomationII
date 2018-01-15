package pageObjects;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DescriptionPage {
	
	private WebDriver driver;	
	private By descriptionTitle = By.className("biblio-title");
	
	public DescriptionPage (WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void AssertDescriptionPage (){
		
		WebElement element = (new WebDriverWait(driver,5)).until(ExpectedConditions.presenceOfElementLocated(descriptionTitle));
		Assert.assertTrue(driver.findElement(descriptionTitle).getText().equals("Product details"));
	}

}
