package com.noon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    private final By cartButton = By.xpath("//a[@data-qa='btn_cartLink-Header-Desktop']");
    private final By productBoxes = By.xpath("//div[@data-qa='plp-product-box']");
    private final By productName = By.xpath(".//h2[@data-qa='plp-product-box-name']");
//    private final By productPrice = By.xpath("//div[@data-qa='plp-product-box-price']");
    private final By addToCartBtn = By.xpath(".//button[contains(@class, '_atcCta_')]");
//    private final By priceAmount = By.xpath("//strong[contains(@class, '_amount_')]");
    private static final Logger logger = LogManager.getLogger(SearchPage.class);

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void waitForProductsToLoad() {
        logger.info("Waiting for products to load...");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productBoxes));
        // Wait for at least the first product name to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@data-qa='plp-product-box']//h2[@data-qa='plp-product-box-name'])[1]")));
        logger.info("Products loaded successfully");
    }

    public List<String> addProductsToCart(int count){
        waitForProductsToLoad();
        List<WebElement> products = driver.findElements(productBoxes);
        List<String> addedProductNames = new ArrayList<>();

        if (products.size() < count){
            throw new IllegalStateException("Requested to add "+count+" products, but only found "+products.size());
        }
        for (int i = 0; i < count; i++){
            WebElement product = products.get(i);
            String name = product.findElement(productName).getText();
            addedProductNames.add(name);
            logger.info("Added product "+name+" to Cart");
            WebElement btn = product.findElement(addToCartBtn);
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        }
        return addedProductNames;
    }

    public CartPage clickOnCart(){
        logger.info("Clicking on cart");
        findElement(cartButton).click();
        return new CartPage(driver);
    }



}
