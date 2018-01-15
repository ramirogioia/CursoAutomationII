package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	private WebDriver driver;
	
	private By searchField = By.name("searchTerm");
	private By searchButton = By.className("header-search-btn");
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void enterSearch (String searchItem) {
		
		this.driver.findElement(searchField).sendKeys(searchItem);;
	}
	
	public void searchButton () {
		
		this.driver.findElement(searchButton).click();
	}

}
