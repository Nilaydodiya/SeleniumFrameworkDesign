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

import SeleniumFramework.PageObjects.LandingPage;
import SeleniumFramework.PageObjects.ProductCatalougue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("kartikshah@yopmail.com","Admin@123");
		ProductCatalougue productcatalouge = new ProductCatalougue(driver);
		List<WebElement> products = productcatalouge.getProductList();
		productcatalouge.addProductToCart(productName);
		
	

		

	   
	   
	    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	    
	   /* List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	    Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	    Assert.assertTrue(match);*/
	    
	    driver.findElement(By.cssSelector(".totalRow button")).click();
	    //Assert.assertTrue(match);
	    
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
