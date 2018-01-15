package steps;


import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Context;
import pageObjects.CustomSearchPage;
import pageObjects.DescriptionPage;
import pageObjects.HomePage;

public class SearchBook {
	private HomePage homePage;
	private Context context;
	private WebDriver driver;	
	private CustomSearchPage customPage;
	private DescriptionPage descriptionPage;
	
	public SearchBook (Context context) {
		this.context = context;
		driver = context.getDriver();
		homePage = new HomePage(driver);
		customPage = new CustomSearchPage(driver);
		descriptionPage = new DescriptionPage(driver);
	}

	
	@Given("^I enter my search (.*)")
	public void i_enter_search (String bookName) {
		
	    homePage.enterSearch(bookName);
	}

	@Given("^I click the search button")
	public void i_click_Search_button() {
		
	    homePage.searchButton();
	}
	
	@Given("^I sort the list of results by (.*)$")
	public void i_sort_the_list_result_by (String orderType) throws InterruptedException{
		
		customPage.iSortTheSearch(orderType);
	}
	
	@When("^I click the first product")
	public void i_click_on_the_first_element() {
		
		customPage.iClickOnTheFirstElement();
	}

	@Then("^The corresponding desctiption screen should be displayed")
	public void the_desctiption_screen_should_be_displayed () throws InterruptedException {
		
		descriptionPage.AssertDescriptionPage();
	}
}
