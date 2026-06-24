package Cucumber.Drivers;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    public static WebDriver driver;
    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver=ChromeDriverClass.getChromeDriver();
                break;
        }
    }
}
