Feature: filter Samsung products only

  Scenario: Press on samsung logo
    Given site is opened
    When user click on category button
    And User clicks on Samsung logo
    Then url should contains samsung