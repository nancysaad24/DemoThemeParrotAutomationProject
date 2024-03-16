package pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//li[contains(@class,'dropdown mega')]/a[contains(text(), 'Catalog')]")
	private WebElement catalogBtn;

	@FindBy(xpath = "//a[contains(text(), 'Bracelets ')]")
	private WebElement braceletsOptn;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}

	public void navigateToCatalog() {
		catalogBtn.click();
	}

	public void chooseBracelets() {
		navigateToCatalog();
		braceletsOptn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preLoader")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(), 'Categories')]")));
	}

}
