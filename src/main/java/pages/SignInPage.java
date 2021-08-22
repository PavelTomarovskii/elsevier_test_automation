package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage {

    WebDriver driver;

    @FindBy(xpath = "//button[@id='SubmitCreate']")
    public WebElement createAnAccountButton;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    public WebElement signInButton;

    @FindBy(xpath = "//div[@id='center_column']//h1[@class='page-heading']")
    public WebElement authenticationTabHeader;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }
}
