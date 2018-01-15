package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class CustomSearchPage {
	
	private WebDriver driver;
	private By sortBy = By.name("searchSortBy");
	private By firstElement = By.className("item-img");
	
	
	public CustomSearchPage (WebDriver driver) {
		this.driver = driver;
	}

	public void iSortTheSearch (String sortOrderBy){
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Select select = new Select (driver.findElements(sortBy).get(1));
		select.selectByValue(sortOrderBy);	
	}
	
	public void iClickOnTheFirstElement () {
		
		WebElement anElement = (new WebDriverWait(driver,5)).until(ExpectedConditions.elementToBeClickable(firstElement));
		anElement.click();
	}
	
	public void assertSearch (String sortOrder) {
		
		Select select = new Select (driver.findElements(sortBy).get(1));
		Assert.assertEquals(select.getFirstSelectedOption().getAttribute("value"), sortOrder);
	}
	
}
