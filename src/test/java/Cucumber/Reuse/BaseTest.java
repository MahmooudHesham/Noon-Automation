package Cucumber.Reuse;

import com.noon.CartPage;
import com.noon.ElectronicsPage;
import com.noon.HomePage;
import com.noon.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected static WebDriver driver;
    public static SoftAssert softAssert;
    public static HomePage homePage;
    public static ElectronicsPage electronicsPage;
    public static SearchPage searchPage;
    public static CartPage cartPage;



    public BaseTest() {
        logger.info("Creating BaseTest objects");
    }

    public static WebDriver getDriver() {
        return driver;
    }
}