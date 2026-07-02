package Cucumber.Reuse;

import Cucumber.Drivers.WebDriverFactory;
import com.noon.HomePage;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class Hooks extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setup() {
        logger.info("=========================================");
        logger.info("@BEFORE HOOK TRIGGERED - SETTING UP NEW SCENARIO");
        logger.info("=========================================");

        // CRITICAL: Quit previous driver if it exists
        if (driver != null) {
            logger.warn("WARNING: Driver still exists from previous scenario. Quitting it now...");
            try {
                driver.quit();
                logger.info("✓ Previous driver session terminated successfully.");
            } catch (Exception e) {
                logger.warn("Could not quit previous driver: ", e);
            }
        }

        // Create completely fresh driver
        logger.info("Creating fresh WebDriver instance...");
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.getDriver("chrome");
        logger.info("✓ Driver created with session ID: " + driver.hashCode());
        driver.manage().window().maximize();

        // Re-instantiate the landing page container with the clean driver instance
        homePage = new HomePage(driver);
        logger.info("✓ HomePage initialized with new driver");

        // CRITICAL: Reset all page objects to null so they get recreated with new driver
        electronicsPage = null;
        searchPage = null;
        cartPage = null;
        logger.info("✓ All page objects reset to null");

        softAssert = new SoftAssert();
        logger.info("✓ SoftAssert initialized");
        logger.info("=========================================");
        logger.info("SETUP COMPLETE - READY FOR SCENARIO");
        logger.info("=========================================");
    }

    @After
    public void tearDown() {
        logger.info("=========================================");
        logger.info("@AFTER HOOK TRIGGERED - TEARING DOWN SCENARIO");
        logger.info("=========================================");

        if (driver != null) {
            try {
                logger.info("Quitting driver session...");
                driver.quit();
                logger.info("✓ Driver session terminated successfully.");
            } catch (Exception e) {
                logger.error("✗ Exception occurred while quitting the driver: ", e);
            } finally {
                driver = null; // CRITICAL: Erase reference so the next scenario gets a clean init
                logger.info("✓ Driver reference set to null");

                // Also clear all page objects
                homePage = null;
                electronicsPage = null;
                searchPage = null;
                cartPage = null;
                softAssert = null;
                logger.info("✓ All page object references cleared.");
            }
        } else {
            logger.warn("WARNING: Driver was null in @After hook!");
        }
        logger.info("=========================================");
        logger.info("TEARDOWN COMPLETE");
        logger.info("=========================================");
    }

    public static WebDriver getDriver() {
        return driver;
    }
}