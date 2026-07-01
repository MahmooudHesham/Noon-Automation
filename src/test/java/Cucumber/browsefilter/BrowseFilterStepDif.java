package Cucumber.browsefilter;

import Cucumber.Reuse.BaseTest;
import Cucumber.Reuse.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BrowseFilterStepDif extends BaseTest {

    @Given("site is opened")
    public void siteIsOpened(){
        Hooks.getDriver().get("https://www.noon.com/egypt-en/");
    }

    @When("user clicks on electronics category")
    public  void userClickOnCategoryButton(){
        electronicsPage= homePage.clickOnElectronicsCategory();
    }

    @When("user filters by {string}")
    public void userFilterByBrand(String brand ){
        electronicsPage.clickOnBrand(brand);
    }

    @When("user sets price range {int} to {int}")
    public void setPriceRange(int minPrice, int maxPrice){
        electronicsPage.clickOnPriceFilter();
        electronicsPage.setMaxPrice(maxPrice);
        electronicsPage.setMinPrice(minPrice);
        electronicsPage.clickOnPriceSubmit();
    }

    @When("user sorts by Top Rated")
    public void userSortsByTopRated() {
        electronicsPage.sortByTopRated();
    }


    @Then("verify that brand of listed items is {string}")
    public void verifyThatBrandOfListedItemsIs(String brand) {
        softAssert.assertTrue(electronicsPage.allProductsContainBrand(brand));
        softAssert.assertAll();
    }

    @Then("verify that price of items within the rang of {int} and {int}")
    public void verifyThatPriceOfItemsWithinTheRangOfMinPriceAndMaxPrice(int minPrice,int maxPrice) {
        softAssert.assertTrue(electronicsPage.isInPriceRange(minPrice,maxPrice));
        softAssert.assertAll();
    }

    @Then("items sorted by Top Rated")
    public void itemsSortedByTopRated() {
        softAssert.assertTrue(electronicsPage.isSortedByTopRated());
        softAssert.assertAll();
    }
}
