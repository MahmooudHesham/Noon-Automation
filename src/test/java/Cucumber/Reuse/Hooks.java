package Cucumber.Reuse;

import Cucumber.Drivers.WebDriverFactory;
import com.noon.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class Hooks extends BaseTest{
    private static WebDriver driver;

    @Before
    public void setup(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.get("https://www.noon.com/egypt-ar/");
        homePage = new HomePage(driver);
        softAssert = new SoftAssert();
    }

//    @After
//    public void tearDown(){
//        if(driver != null){
//            driver.quit();
//        }
//    }

    public static WebDriver getDriver() {
        return driver;
    }
}