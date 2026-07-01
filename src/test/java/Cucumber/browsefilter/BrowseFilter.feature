Feature: navigate to category page

  Scenario Outline: Filter products by brand and price
    Given site is opened
    When user clicks on electronics category
    And user filters by "<brand>"
    And user sets price range <minPrice> to <maxPrice>
    And user sorts by Top Rated
    Then verify that brand of listed items is "<brand>"
    And verify that price of items within the rang of <minPrice> and <maxPrice>
    And items sorted by Top Rated

    Examples:
      | brand   | minPrice | maxPrice |
      | Samsung | 1000     | 3000     |