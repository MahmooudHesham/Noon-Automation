package Cucumber.Reuse;

import com.noon.ElectronicsPage;
import com.noon.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    public static SoftAssert softAssert;
    public static HomePage homePage;
    public static ElectronicsPage electronicsPage;

    public BaseTest() {
        logger.info("Creating BaseTest objects");
    }
}