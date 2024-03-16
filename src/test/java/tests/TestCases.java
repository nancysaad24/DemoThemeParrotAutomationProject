package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.HomePage;
import pageobjects.ProductsPage;
import pageobjects.CartPage;

public class TestCases extends BaseClass {
	public WebDriver driver;
	HomePage hp;
	CartPage cartPage;
	ProductsPage productsPage;

	@BeforeClass
	public void beforeClass() throws IOException {
		driver = initializeDriver();
		hp = new HomePage(driver);
		cartPage = new CartPage(driver);
		productsPage = new ProductsPage(driver);
		navigateToURL();

	}

	@Test(enabled = true)
	public void case01_checkSearchResultsAndPriceInCart()  {
		hp.chooseBracelets();
		Assert.assertTrue((driver.getCurrentUrl()).contains("/catalog/product-category/bracelets"));
		productsPage.searchProducts("fa");
		Assert.assertTrue(productsPage.isSearchResultRelevant("fa"));
		productsPage.addAnyProductToCart(0); //This method will add first product in page by adding its index
		Assert.assertTrue(productsPage.isItemAddedToCartMsgShown());
		final String pricePage = productsPage.getPriceOfAnyProduct(0); //This method will get the price of first product in page by adding its index
		productsPage.goToShoppingCart();
		Assert.assertEquals(pricePage, cartPage.getTotalPRiceInCart());
	}



	@AfterClass
	public void afterClass() {
		closeBrowser();

	}

}