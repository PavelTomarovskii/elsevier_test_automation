Feature: Testing possibility to add summer dresses to the shopping cart

  Scenario: Add 1 product from Summer Dresses category to the shopping cart
    Given user is on Dresses category page "http://automationpractice.com/index.php?id_category=11&controller=category"
    When user click on button Add To Cart "Printed Chiffon Dress"
    Then products should be successfully added to the shopping cart
    And continue shopping
    And there should be 1 products in the shopping cart

  Scenario: Proceed to the Sign in section from the shopping cart
    Given user is on Dresses category page "http://automationpractice.com/index.php?id_category=11&controller=category"
    Given user click on button Add To Cart "Printed Chiffon Dress"
    Given continue shopping
    Given there should be 1 products in the shopping cart
    Given user click on button Check out
    When user click on button Proceed to checkout
    Then Authentication tab is opened

