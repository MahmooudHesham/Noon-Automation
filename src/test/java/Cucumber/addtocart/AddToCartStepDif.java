package Cucumber.addtocart;

import Cucumber.Reuse.BaseTest;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class AddToCartStepDif extends BaseTest {

    private static final Logger logger = LogManager.getLogger(AddToCartStepDif.class);
    private List<String> expectedProducts;

    @When("user search for a {string}")
    public void userSearchForA(String searchItem) {
        searchPage = homePage.searchOnItem(searchItem);
    }

    @When("user adds {int} items to the shopping cart")
    public void userAddsItemsNumberItemsToTheShoppingCart(int productsNumber) {
        expectedProducts = searchPage.addProductsToCart(productsNumber);
    }

    @When("the user navigates to the cart page")
    public void theUserNavigatesToTheCartPage() {
        cartPage = searchPage.clickOnCart();
    }

    @Then("the cart page should display all selected items")
    public void theCartPageShouldDisplayAllSelectedItems() {
        List<String> actualProducts = cartPage.getCartItemNames();

        // Normalize both lists to account for Noon adding " - " after brand names in cart
        List<String> normalizedExpected = expectedProducts.stream()
                .map(this::normalizeProductName)
                .collect(Collectors.toList());

        List<String> normalizedActual = actualProducts.stream()
                .map(this::normalizeProductName)
                .collect(Collectors.toList());

        logger.info("Expected products (normalized): {}", normalizedExpected);
        logger.info("Actual products (normalized): {}", normalizedActual);

        softAssert.assertEquals(normalizedActual.size(), normalizedExpected.size(),
                "Cart product count mismatch");

        for (String expectedName : normalizedExpected) {
            softAssert.assertTrue(normalizedActual.contains(expectedName),
                    "Product not found in cart: " + expectedName);
        }
        softAssert.assertAll();
    }

    /**
     * Normalizes product names to handle variations like extra dashes after brand names.
     * Example: "Samsung - Galaxy A17..." becomes "Samsung Galaxy A17..."
     *
     * @param productName The original product name
     * @return Normalized product name
     */
    private String normalizeProductName(String productName) {
        if (productName == null || productName.isEmpty()) {
            return productName;
        }

        // Remove extra " - " that appears after brand names in cart page
        // "Samsung - Galaxy A17" -> "Samsung Galaxy A17"
        String normalized = productName.replaceAll("\\s*-\\s*", " ")
                .replaceAll("\\s+", " ")  // Replace multiple spaces with single space
                .trim();

        logger.debug("Normalized: '{}' -> '{}'", productName, normalized);
        return normalized;
    }
}