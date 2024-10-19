package SeleniumFramework;

import java.io.IOException;
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
import org.testng.annotations.Test;

import SeleniumFramework.PageObjects.CartPage;
import SeleniumFramework.PageObjects.CheckOutPage;
import SeleniumFramework.PageObjects.ConfirmationPage;
import SeleniumFramework.PageObjects.LandingPage;
import SeleniumFramework.PageObjects.ProductCatalougue;
import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder extends BaseTest {
	
		@Test
		public void SubmitOrder() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";			
		LandingPage landingPage = launchApplication();
		ProductCatalougue productCatalougue = landingPage.loginApplication("kartikshah@yopmail.com","Admin@123");
		
		List<WebElement> products = productCatalougue.getProductList();
		productCatalougue.addProductToCart(productName);
		CartPage cartPage = productCatalougue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage .equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}
}
