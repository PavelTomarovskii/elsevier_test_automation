package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.ElementUtils;

public class ProductCommonPage {

    WebDriver driver;
    ElementUtils elementUtils = new ElementUtils();

    @FindBy(xpath = "//div[@class='button-container']//span[.//span[contains(.,'Continue')]]//i")
    public WebElement continueShoppingButton;

    public ProductCommonPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * get information about product such as price, attributes, quantity, title, info.
     *
     * @param productElementID what needs to be found (e.g. title)
     * @param driver           webdriver entity
     * @return a {@link WebElement} with required information
     */
    public WebElement getProductInfo(String productElementID, WebDriver driver) {
        return driver.findElement(By
                .xpath("//div[contains(@class,'layer_cart_product')]//span[contains(@id,'" + productElementID + "')]"));
    }

    /**
     * get button from product card, e.g. Add to card, More
     *
     * @param productName the name of selected product
     * @param buttonTitle e.g. Add to card, More
     * @param driver      webdriver entity
     * @return a {@link WebElement}
     */
    public WebElement getButtonsFromProductInCategoryPage(String productName, String buttonTitle, WebDriver driver) {
        return driver.findElement(By.xpath("//div[@class='product-container'][.//a[contains(@title, '" + productName
                + "')]]//div[@class='button-container']//a[contains(@title, '" + buttonTitle + "')]"));
    }

    /**
     * move cursor over one of the available actions for the product
     *
     * @param productName the name of selected product
     * @param driver      webdriver entity
     */
    public void moveCursorOverActionOnProduct(String productName, WebDriver driver) {
        Actions builder = new Actions(driver);
        WebElement product = driver
                .findElement(By.xpath("//div[@id='center_column']//h5//a[@title='" + productName + "']"));
        builder.moveToElement(product).build().perform();
        elementUtils.implicitlyWait(10, driver);
    }
}
