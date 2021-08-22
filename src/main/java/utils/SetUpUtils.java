package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class SetUpUtils {

    public WebDriver driverSetUp() {

        try {
            System.setProperty("webdriver.chrome.driver",
                    new File("./src/main/resources/drivers/chromedriver_92.exe").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ChromeDriver();
    }
}
