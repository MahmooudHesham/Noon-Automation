package Cucumber.Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverClass {

    public static WebDriver getEdgeDriver(){
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}