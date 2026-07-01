package Cucumber.browsefilter;

import Cucumber.Reuse.BaseTest;
import Cucumber.Reuse.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrowseFilterStepDif extends BaseTest {

    private static final Logger logger = LogManager.getLogger(BrowseFilterStepDif.class);

    @Given("site is opened")
    public void siteIsOpened(){
        logger.info("Opening site");
        Hooks.getDriver().get("https://www.noon.com/egypt-en/");
    }

    @When("user clicks on electronics category")
    public  void userClickOnCategoryButton(){
        logger.info("Clicking on electronics category");
        electronicsPage= homePage.clickOnElectronicsCategory();
    }

    @When("user filters by {string}")
    public void userFilterByBrand(String brand ){
        logger.info("Filtering by brand: " + brand);
        electronicsPage.clickOnBrand(brand);
    }

    @When("user sets price range {int} to {int}")
    public void setPriceRange(int minPrice, int maxPrice){
        logger.info("Setting price range from " + minPrice + " to " + maxPrice);
        electronicsPage.clickOnPriceFilter();
        electronicsPage.setMaxPrice(maxPrice);
        electronicsPage.setMinPrice(minPrice);
        electronicsPage.clickOnPriceSubmit();
    }

    @When("user sorts by Top Rated")
    public void userSortsByTopRated() {
        logger.info("Sorting by Top Rated");
        electronicsPage.sortByTopRated();
    }


    @Then("verify that brand of listed items is {string}")
    public void verifyThatBrandOfListedItemsIs(String brand) {
        logger.info("Verifying brand of listed items is " + brand);
        softAssert.assertTrue(electronicsPage.allProductsContainBrand(brand));
        softAssert.assertAll();
    }

    @Then("verify that price of items within the rang of {int} and {int}")
    public void verifyThatPriceOfItemsWithinTheRangOfMinPriceAndMaxPrice(int minPrice,int maxPrice) {
        logger.info("Verifying price of items is within range");
        softAssert.assertTrue(electronicsPage.isInPriceRange(minPrice,maxPrice));
        softAssert.assertAll();
    }

//    @Then("items sorted by Top Rated")
//    public void itemsSortedByTopRated() {
//        logger.info("Verifying items are sorted by Top Rated");
//        softAssert.assertTrue(electronicsPage.isSortedByTopRated());
//        softAssert.assertAll();
//    }
}