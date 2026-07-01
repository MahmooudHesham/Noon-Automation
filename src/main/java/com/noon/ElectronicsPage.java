package com.noon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ElectronicsPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(ElectronicsPage.class);
    private final By sortBy = By.xpath("//span[text()='Sort By']");
    private final By topRated = By.xpath("//a[text()='Best Rated']");
    private final By priceButton = By.xpath("//h3[text()='Price']");
    private final By maxPrice = By.xpath("//input[@data-qa='filter-max-input']");
    private final By minPrice = By.xpath("//input[@data-qa='filter-min-input']");
    private final By priceSubmit = By.xpath("//button[text()='Go']");
    private final By productTitles = By.xpath("//h2[@data-qa='plp-product-box-name']");
    private final By productRatings = By.cssSelector("div[class^='_textCtr_']");
    private final By productRatingsCount = By.cssSelector("div[class^='_countCtr_'] > span");
    private final By productPrices =By.cssSelector("strong[class^='_amount_']");

    public ElectronicsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnBrand(String brand) {
        logger.info("Clicking on brand: " + brand);
        By brandLocator = By.xpath("//img[contains(@alt,'" + brand.toLowerCase() + "?sort')]");
        // Wait for the element to be present in the DOM
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(brandLocator));
        // Scroll the element into view
        scrollToElement(element);
        // Use JavaScript click to bypass the intercepting header
        jsClick(element);
    }

    public void sortByTopRated() {
        logger.info("Sorting by top rated");
        findElement(sortBy).click();
        findElement(topRated).click();
    }

    public void clickOnPriceFilter() {
        logger.info("Clicking on price filter");
        findElement(priceButton).click();
    }

    public void setMaxPrice(int maxPrice) {
        findElement(this.maxPrice).clear();
        logger.info("Setting max price to: " + maxPrice);
        findElement(this.maxPrice).sendKeys(String.valueOf(maxPrice));
    }

    public void setMinPrice(int minPrice) {
        findElement(this.minPrice).clear();
        logger.info("Setting min price to: " + minPrice);
        findElement(this.minPrice).sendKeys(String.valueOf(minPrice));
    }

    public void clickOnPriceSubmit() {
        logger.info("Clicking on price submit");
        findElement(priceSubmit).click();
    }

    public boolean allProductsContainBrand(String brand) {
        logger.info("Verifying all products contain brand: " + brand);
        List<WebElement> titles = driver.findElements(productTitles);
        for (int i = 0; i < titles.size(); i++) {
            try {
                // Relocate sequentially if the page is unstable
                WebElement title = driver.findElements(productTitles).get(i);
                String titleText = title.getText().toLowerCase();
                if (!titleText.contains(brand.toLowerCase())) {
                    logger.warn("Product title does not contain brand: " + titleText);
                    return false;
                }
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                // Retry the single index element if it went stale mid-loop
                i--;
            }
        }
        return true;
    }

//    public boolean isSortedByTopRated() {
//        logger.info("Verifying products are sorted by top rated using score = rate * count");
//        List<WebElement> ratingsElements = driver.findElements(productRatings);
//        List<WebElement> countsElements = driver.findElements(productRatingsCount);
//
//        if (ratingsElements.isEmpty()) {
//            logger.warn("Could not verify sorting: ratings list is empty.");
//            return true; // Or false, depending on desired behavior for empty lists
//        }
//
//        double previousScore = Double.MAX_VALUE;
//
//        for (int i = 0; i < ratingsElements.size(); i++) {
//            double currentRating = 0.0;
//            double currentCount = 0.0;
//
//            try {
//                // Safely get rating
//                String ratingText = ratingsElements.get(i).getText().trim();
//                if (!ratingText.isEmpty()) {
//                    currentRating = Double.parseDouble(ratingText);
//                }
//
//                // Safely get count
//                if (i < countsElements.size()) {
//                    String countText = countsElements.get(i).getText().trim().replaceAll("[^0-9.Kk]", "");
//                    if (countText.toLowerCase().contains("k")) {
//                        currentCount = Double.parseDouble(countText.toLowerCase().replace("k", "")) * 1000;
//                    } else if (!countText.isEmpty()) {
//                        currentCount = Double.parseDouble(countText);
//                    }
//                }
//
//                double currentScore = currentRating * currentCount;
//                logger.info(String.format("Product %d: Rating=%.1f, Count=%.0f, Score=%.2f", i, currentRating, currentCount, currentScore));
//
//                if (currentScore > previousScore) {
//                    logger.warn(String.format("Sort order failed at index %d. Current Score (%.2f) > Previous Score (%.2f)", i, currentScore, previousScore));
//                    return false;
//                }
//                previousScore = currentScore;
//
//            } catch (Exception e) {
//                logger.error("Error parsing rating/count for product at index " + i, e);
//                return false; // Fail validation if data can't be parsed
//            }
//        }
//        return true;
//    }

    public boolean isInPriceRange(int minPrice, int maxPrice){
        logger.info("Verifying products are within price range");
        List<WebElement> prices = driver.findElements(productPrices);
        for (WebElement price : prices) {
            // Remove commas before parsing to integer
            String priceText = price.getText().trim().replace(",", "");
            int productPrice = Integer.parseInt(priceText);
            if (productPrice > maxPrice || productPrice < minPrice){
                return false;
            }
        }
        return true;
    }
}