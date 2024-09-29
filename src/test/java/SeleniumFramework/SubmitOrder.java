package SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.LandingPage;
import SeleniumFramework.PageObjects.ProductCatalougue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalougue productCatalougue = landingPage.loginApplication("kartikshah@yopmail.com","Admin@123");
		
		List<WebElement> products = productCatalougue.getProductList();
		productCatalougue.addProductToCart(productName);
		CartPage cartPage = productCatalougue.goToCartPage();
		

		
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartPage.goToCheckout();

	   
	  
	    
	 
	
	    
	    Actions a = new Actions(driver);
	    a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
	    
	  //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    
	    driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	    
	    driver.findElement(By.cssSelector(".action__submit")).click();
	    
	    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	     confirmMessage.equals("Thankyou for the order") ;
	    
	    driver.close();
	}

}
