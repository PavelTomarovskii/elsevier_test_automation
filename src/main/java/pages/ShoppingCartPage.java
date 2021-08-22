package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(@class,'standard-checkout')]")
    public WebElement proceedToCheckoutButton;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }
}
