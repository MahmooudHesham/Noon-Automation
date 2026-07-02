package Cucumber.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverClass {

    // No static/cached driver field. A new WebDriver instance must be created
    // every time this is called, otherwise a quit() session gets reused by the
    // next scenario and throws NoSuchSessionException.
    public static WebDriver getChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}