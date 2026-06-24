package Cucumber.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverClass {
    public  static WebDriver driver = null;

    public static WebDriver getFirefoxDriver(){
        if (driver == null){
            driver= new FirefoxDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
