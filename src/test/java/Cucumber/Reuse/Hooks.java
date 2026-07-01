package Cucumber.Reuse;

import Cucumber.Drivers.WebDriverFactory;
import com.noon.HomePage;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class Hooks extends BaseTest{
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setup(){
        logger.info("Setting up the test");
        driver = WebDriverFactory.getDriver("chrome");
        homePage = new HomePage(driver);
        softAssert = new SoftAssert();
    }

//    @After
//    public void tearDown(){
//        logger.info("Tearing down the test");
//        if(driver != null){
//            driver.quit();
//        }
//    }

    public static WebDriver getDriver() {
        return driver;
    }
}