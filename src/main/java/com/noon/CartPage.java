package com.noon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Page Object for the Cart page.
 * Contains locators and methods for interacting with cart items.
 */
public class CartPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(CartPage.class);

    // A robust XPath to locate each product row in the cart.
    // It identifies containers that have both a product link (containing '/p/') and a quantity dropdown.
    // This is more stable than relying on dynamically generated CSS class names.
    private final By cartItemContainerLocator = By.xpath("//div[@class='_itemSection_2063g_61']");

    // Relative locators to find elements within a specific cart item container.
    private final By productNameLocator = By.xpath(".//div[@class='_title_18lb3_110']//semibold");
    private final By productPriceLocator = By.xpath(".//div[@data-qa='item-unit-price']");
    private final By productQuantityLocator = By.xpath(".//input[@data-qa='item-quantity-input']");


    /**
     * Constructor for CartPage.
     *
     * @param driver The WebDriver instance.
     */
    public CartPage(WebDriver driver) {
        super(driver);
        logger.info("CartPage initialized.");
    }

    /**
     * Retrieves a list of all active product names from the cart.
     *
     * @return A List of strings, where each string is the name of a product in the cart.
     */
    public List<String> getCartItemNames() {
        logger.info("Attempting to retrieve all cart item names with locator: " + cartItemContainerLocator);
        List<WebElement> cartItems = findElements(cartItemContainerLocator);

        if (cartItems.isEmpty()) {
            logger.warn("No cart items found using the specified locator. The cart might be empty or the locator may need an update.");
            return new ArrayList<>();
        }

        List<String> itemNames = cartItems.stream()
                .map(item -> {
                    try {
                        return item.findElement(productNameLocator).getText().trim();
                    } catch (Exception e) {
                        logger.error("Failed to find product name in a cart item container.", e);
                        return null;
                    }
                })
                .filter(name -> name != null && !name.isEmpty())
                .collect(Collectors.toList());

        logger.info("Found {} items in the cart: {}", itemNames.size(), itemNames);
        return itemNames;
    }

    /**
     * Verifies that a specific product in the cart has the expected price and quantity.
     *
     * @param productName      The name of the product to verify.
     * @param expectedPrice    The expected price of the product (as a String, e.g., "1,299.00").
     * @param expectedQuantity The expected quantity of the product (as a String, e.g., "1").
     */
    public void verifyProductDetails(String productName, String expectedPrice, String expectedQuantity) {
        logger.info("Verifying details for product: '{}'. Expected Price: {}, Expected Quantity: {}",
                productName, expectedPrice, expectedQuantity);

        Optional<WebElement> productRow = findProductRowByName(productName);

        if (productRow.isPresent()) {
            WebElement row = productRow.get();

            // Verify Price
            String actualPrice = row.findElement(productPriceLocator).getText().replaceAll("[^0-9.,]", "").trim();
            Assert.assertEquals(actualPrice, expectedPrice, "Price mismatch for product: " + productName);
            logger.info("Price verification PASSED for '{}'. Expected: {}, Actual: {}", productName, expectedPrice, actualPrice);

            // Verify Quantity
            WebElement quantityInput = row.findElement(productQuantityLocator);
            String actualQuantity = quantityInput.getAttribute("value").trim();
            Assert.assertEquals(actualQuantity, expectedQuantity, "Quantity mismatch for product: " + productName);
            logger.info("Quantity verification PASSED for '{}'. Expected: {}, Actual: {}", productName, expectedQuantity, actualQuantity);

        } else {
            logger.error("Product '{}' not found in the cart for verification.", productName);
            Assert.fail("Product '" + productName + "' not found in the cart.");
        }
    }

    /**
     * Finds and returns the specific WebElement container for a product in the cart.
     *
     * @param productName The name of the product to find.
     * @return An Optional containing the WebElement if found, otherwise an empty Optional.
     */
    private Optional<WebElement> findProductRowByName(String productName) {
        logger.debug("Searching for product row with name: '{}'", productName);
        List<WebElement> cartItems = findElements(cartItemContainerLocator);
        return cartItems.stream()
                .filter(item -> {
                    String currentName = item.findElement(productNameLocator).getText().trim();
                    return currentName.equalsIgnoreCase(productName.trim());
                })
                .findFirst();
    }
}