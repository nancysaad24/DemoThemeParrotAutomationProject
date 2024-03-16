package pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//div//form[@class='form-inline']//input[@type='text']")
	private WebElement searchProductsBox;

	@FindBy(xpath = "//div//form[@class='form-inline']//input[@value='Go']")
	private WebElement goSearchBtn;

	@FindBy(xpath = "//div[contains(@class,'j2store-content')]")
	private List<WebElement> searchResults;

	@FindBy(xpath = "//i[@class='fa fa-shopping-cart']")
	private WebElement cartBtn;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void searchProducts(String searchText) {
		searchProductsBox.click();
		searchProductsBox.sendKeys(searchText);
		goSearchBtn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preLoader")));
	}

	public boolean isSearchResultRelevant(String searchValue) {
		boolean areResultsRelevant = false;
		for (WebElement result : searchResults) {
			String title = result.getText().toLowerCase();

			if (title.contains(searchValue)) {
				areResultsRelevant = true;
				break;
			}
		}
		return areResultsRelevant;
	}

	public void addAnyProductToCart(int productIndex) {
		if (!searchResults.isEmpty()) {
			WebElement firstProduct = searchResults.get(productIndex);
			WebElement addToCartButton = firstProduct.findElement(By.xpath("//button[contains(@title,'Add to cart')]"));
			addToCartButton.click();
		} else {
			System.out.println("No products found on the page.");
		}

	}

	public String getPriceOfAnyProduct(int index) {
		String priceValue = null;
		if (!searchResults.isEmpty() && index >= 0 && index < searchResults.size()) {
			WebElement firstProduct = searchResults.get(index);
			WebElement price = firstProduct.findElement(By.xpath("//span[contains(@class,'sale-price')]"));
			priceValue = price.getText();
		} else {
			System.out.println("No products found on the page.");
		}

		return priceValue;

	}

	public boolean isItemAddedToCartMsgShown() {
		boolean message = false;

		try {
			driver.findElement(By.xpath("//p[contains(text(),'Item added to cart.')]"));
			message = true;
		} catch (NoSuchElementException e) {
			message = false;
		}

		return message;
	}

	public void goToShoppingCart() {
		cartBtn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preLoader")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(), 'Cart totals')]")));
	}

}