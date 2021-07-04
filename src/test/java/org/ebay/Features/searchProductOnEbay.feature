Feature: Search Product on Ebay Home
  As Customer verify, able to search for products on Ebay Home page

  Scenario Outline: Navigate to Ebay and Search for products
    Given Customer is on Ebay Web portal
    When Customer Searches for the "<Product>" under "<Category>"
    Then Verify the search yielded result
    And Select the first option listed
    And Add the item to the cart
    And Handle any additional popup
    And verify customer gets check out option
    And Remove the product and verify its successfull
    Examples:
      | Product   | Category                       |
      | Lenovo    | Computers/Tablets & Networking |
      | Iphone 12 |                                |