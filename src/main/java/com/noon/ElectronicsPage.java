package com.noon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElectronicsPage extends BasePage {
//    private final By samsungBrand = By.xpath("//img[contains(@alt,'"+"samsung?sort')]");
    private final By sortBy = By.xpath("//button[contains(@class,'DesktopSortDropdown')]");
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
        By brandLocator = By.xpath("//img[contains(@alt,'" + brand.toLowerCase() + "?sort')]");
        findElement(brandLocator).click();
    }

    public void sortByTopRated() {
        findElement(sortBy).click();
        findElement(topRated).click();
    }

    public void clickOnPriceFilter() {
        findElement(priceButton).click();
    }

    public void setMaxPrice(int maxPrice) {
        findElement(this.maxPrice).sendKeys(String.valueOf(maxPrice));
    }

    public void setMinPrice(int minPrice) {
        findElement(this.minPrice).sendKeys(String.valueOf(minPrice));
    }

    public void clickOnPriceSubmit() {
        findElement(priceSubmit).click();
    }

    public boolean allProductsContainBrand(String brand) {
        List<WebElement> titles = driver.findElements(productTitles);
        for (WebElement title : titles) {
            if (!title.getText().toLowerCase().contains(brand.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedByTopRated() {
        List<WebElement> ratings = driver.findElements(productRatings);
        List<WebElement> counts = driver.findElements(productRatingsCount);

        double previousScore = Double.MAX_VALUE;

        for (int i = 0; i < ratings.size(); i++) {
            double rating = Double.parseDouble(ratings.get(i).getText().trim());

            String countText = counts.get(i).getText().trim()
                    .replaceAll("[^0-9.K]", "");
            double count;
            if (countText.contains("K")) {
                count = Double.parseDouble(countText.replace("K", "")) * 1000;
            } else {
                count = Double.parseDouble(countText);
            }

            double currentScore = rating * count;

            if (currentScore > previousScore) {
                return false;
            }
            previousScore = currentScore;
        }
        return true;
    }

    public boolean isInPriceRange(int minPrice, int maxPrice){
        List<WebElement> prices = driver.findElements(productPrices);
        for (WebElement price : prices) {
            int priceText = Integer.parseInt(price.getText().trim());
            if (priceText > maxPrice || priceText < minPrice){
                return false;
            }
        }
        return true;
    }

}