package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class MainMenu {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(@title,'View my shopping cart')]")
    public WebElement shoppingCartButton;

    @FindBy(xpath = "//a[@id='button_order_cart']")
    public WebElement checkoutButton;

    @FindBy(xpath = "//a[contains(@title,'View my shopping cart')]//span[contains(@class,'quantity')]")
    public WebElement quantityInBasket;

    public MainMenu(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * move cursor to action on basket
     */
    public void moveCursorToActionOnBasket() {
        Actions builder = new Actions(driver);
        builder.moveToElement(this.shoppingCartButton).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
