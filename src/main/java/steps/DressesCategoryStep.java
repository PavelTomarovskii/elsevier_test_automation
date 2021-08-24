package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.*;
import utils.ElementUtils;
import utils.NavigationUtils;
import utils.SetUpUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DressesCategoryStep {

    private WebDriver driver;
    private SetUpUtils setUpUtils = new SetUpUtils();
    private NavigationUtils navigationUtils = new NavigationUtils();
    private ElementUtils elementUtils = new ElementUtils();
    private pages.MainMenu mainMenu;
    private ProductCommonPage productCommonPage;
    private ShoppingCartPage shoppingCartPage;
    private SignInPage signInPage;

    private String productName;

    @Before
    public void setup() {
        driver = setUpUtils.driverSetUp();
        mainMenu = PageFactory.initElements(driver, MainMenu.class);
        productCommonPage = PageFactory.initElements(driver, ProductCommonPage.class);
        shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
        signInPage = PageFactory.initElements(driver, SignInPage.class);
    }

    @After
    public void onAfter() throws InterruptedException {
        driver.quit();
    }

    @Given("user is on Dresses category page {string}")
    public void navigate(String url) {
        navigationUtils.navigateToPage(url, driver);
        navigationUtils.slowDown(1000);
    }

    @When("^user click on button Add To Cart \"([^\"]*)\"$")
    public void clickOnAddToCartButton(String productName) {
        this.productName = productName;
        WebElement addToCartButton = null;
        elementUtils.implicitlyWait(10, driver);
        productCommonPage.moveCursorOverActionOnProduct(productName, driver);
        addToCartButton = productCommonPage.getButtonsFromProductInCategoryPage(productName, "Add to cart", driver);
        elementUtils.waitForElementToBeClickable(addToCartButton, driver);
        if (addToCartButton.isDisplayed() && addToCartButton.isEnabled()) {
            elementUtils.clickOnElement(driver, addToCartButton);
        }
        elementUtils.implicitlyWait(10, driver);

    }

    @Then("^products should be successfully added to the shopping cart$")
    public void isProductSuccessfullyAdded() {
        navigationUtils.slowDown(1000);
        boolean isAdded = false;
        elementUtils.waitForElementToBeClickable(productCommonPage.continueShoppingButton, driver);
        WebElement productTitle = productCommonPage.getProductInfo("title", driver);
        String prodName = productTitle.getText();
        if (prodName.equals(productName)) {
            isAdded = true;
        }
        assertThat(isAdded, is(true));
    }

    @And("^continue shopping")
    public void continueShopping() {
        elementUtils.implicitlyWait(10, driver);
        elementUtils.waitForElementToBeClickable(productCommonPage.continueShoppingButton, driver);
        productCommonPage.continueShoppingButton.click();
    }

    @And("there should be {int} products in the shopping cart")
    public void checkNumberOfProductsInBasket(int numberOfProductsInBasket) {
        elementUtils.waitForElementToBeClickable(mainMenu.quantityInBasket, driver);
        String numberOfProductsString = mainMenu.quantityInBasket.getText();
        int numberOfProductsInt = Integer.parseInt(numberOfProductsString);
        assertThat(numberOfProductsInBasket == numberOfProductsInt, is(true));
    }

    @When("^user click on button Check out$")
    public void clickOnCheckOutButton() {
        mainMenu.moveCursorToActionOnBasket();
        elementUtils.waitForElementToBeClickable(mainMenu.checkoutButton, driver);
        mainMenu.checkoutButton.click();
    }

    @When("^user click on button Proceed to checkout$")
    public void clickOnProceedToCheckOutButton() {
        elementUtils.waitForElementToBeClickable(shoppingCartPage.proceedToCheckoutButton, driver);
        shoppingCartPage.proceedToCheckoutButton.click();
    }

    @Then("^Authentication tab is opened$")
    public void checkAuthenticationTabIsOpened() {
        elementUtils.implicitlyWait(10, driver);
        elementUtils.waitForElementToBeClickable(signInPage.authenticationTabHeader, driver);
        String authenticationTabHeaderText = signInPage.authenticationTabHeader.getText();
        assertThat(authenticationTabHeaderText, equalToIgnoringCase("Authentication"));
    }

    @And("^Create an account button is available$")
    public void createAnAccountButtonIsAvailable() {
        elementUtils.waitForElementToBeClickable(signInPage.createAnAccountButton, driver);
    }

    @And("^Sign in button is available$")
    public void signInButtonIsAvailable() {
        elementUtils.waitForElementToBeClickable(signInPage.signInButton, driver);
    }
}
