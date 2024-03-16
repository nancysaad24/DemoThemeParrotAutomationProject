package pageobjects;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//table[contains(@class,'cart-footer')]//th[contains(text(),'Total')]/following-sibling::td")
	private WebElement totalPrice;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}

	public String getTotalPRiceInCart() {
		return totalPrice.getText();
	}

}
