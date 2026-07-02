Feature: add products to cart

  Scenario Outline: Add Multiple Products to Cart
    Given site is opened
    When user search for a "<product>"
    And user adds <itemsNumber> items to the shopping cart
    And the user navigates to the cart page
    Then the cart page should display all selected items

    Examples:
      | product   | itemsNumber |
      | Headphones | 3     |
      | GamePad | 2     |
      | Tvs | 1     |

