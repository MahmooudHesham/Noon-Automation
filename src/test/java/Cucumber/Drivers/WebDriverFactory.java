package Cucumber.Drivers;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    public static WebDriver driver;
    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver=ChromeDriverClass.getChromeDriver();
                break;

            case "edge":
                driver = EdgeDriverClass.getEdgeDriver();
                break;

            case  "firefox":
                driver = FirefoxDriverClass.getFirefoxDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported");
        }
        return driver;
    }
}
