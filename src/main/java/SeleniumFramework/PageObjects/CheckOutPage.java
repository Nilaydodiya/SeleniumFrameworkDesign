package SeleniumFramework.PageObjects;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountry; 
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectSecondItem;
	
	@FindBy(css=".hero-primary")
	WebElement getMessage;
	
	By results = By.className("mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
	    a.sendKeys(selectCountry, countryName).build().perform();
	    waitForElementToAppear(By.cssSelector(".ta-results"));
	    selectSecondItem.click();
	   
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}

}
