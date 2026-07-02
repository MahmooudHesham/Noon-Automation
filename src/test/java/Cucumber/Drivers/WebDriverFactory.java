package Cucumber.Drivers;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    // Removed static to ensure a new driver is created for each call to getDriver()
    public WebDriver driver;

    public WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = ChromeDriverClass.getChromeDriver();
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