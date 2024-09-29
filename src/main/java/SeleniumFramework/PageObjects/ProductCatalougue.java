package SeleniumFramework.PageObjects;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class ProductCatalougue extends AbstractComponent{

	WebDriver driver;
	
	public ProductCatalougue(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="mb-3")
	List<WebElement> products; 
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.className("mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	/*public WebElement getProductByName(String productName)	
	{
		WebElement prod = getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
	}
		
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}*/
	public WebElement getProductByName(String productName) {
	    // Correct the string comparison to use the variable productName
	    WebElement prod = getProductList().stream()
	        .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)) 
	        .findFirst()
	        .orElse(null);
	    return prod;
	}

	public void addProductToCart(String productName) {
	    WebElement prod = getProductByName(productName);
	    
	    // Check if prod is null before attempting to find addToCart button
	    if (prod != null) {
	        prod.findElement(addToCart).click();
	        waitForElementToAppear(toastMessage);
	        waitForElementToDisappear(spinner);
	    } else {
	        // Handle the case where the product is not found
	        throw new NoSuchElementException("Product with name '" + productName + "' not found.");
	    }
	}

	
}
