package org.ebay.Steps;

import Utils.CSVUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.ebay.Base.BaseUtil;
import org.ebay.Pages.EbayHomePage;
import org.ebay.Pages.EbayProductPage;
import org.ebay.Pages.EbaySearchPage;
import org.ebay.Pages.EbayShoppingCartPage;
import org.junit.Assert;

public class searchProductOnEbay extends BaseUtil {
    private static final Logger log = Logger.getLogger(searchProductOnEbay.class);
    private final BaseUtil base;
    private EbayHomePage homePage;
    private EbaySearchPage searchPage;
    private EbayProductPage prodPage;
    private EbayShoppingCartPage shoppingCartPage;
    private String productSelected="";
    public searchProductOnEbay(BaseUtil base) {
        this.base = base;
    }

    @Given("^Customer is on Ebay Web portal$")
    public void customer_is_on_Ebay_Web_portal() throws Throwable {
        //String url=System.getProperty("env");
        String url = CSVUtil.getTestData("URL");
        log.info("Navigate to  : " + url);
        homePage = base.getEbayHomePage();
        boolean isNavSuccess = homePage.navigateToEbay(url);
        Assert.assertTrue(isNavSuccess);
    }

    @When("^Customer Searches for the \"([^\"]*)\" under \"([^\"]*)\"$")
    public void customer_Searches_for_the_under(String product, String category) throws Throwable {
        if(!category.isEmpty()){
            boolean isCategorySelSuccess=homePage.selectProductCategory(category);
            Assert.assertTrue(isCategorySelSuccess);
            log.info("Category is set as : "+category);
        }
        else{
            log.info("Category is set as Default");
        }
        boolean productEnteredSuccessfully=homePage.enterProductToBeSearched(product);
        Assert.assertTrue(productEnteredSuccessfully);
        homePage.clickOnSearchButton();
    }

    @Then("^Verify the search yielded result$")
    public void verify_the_search_yielded_result() throws Throwable {
        log.info("Verify the product search");
        searchPage = base.getEbaySearchPage();
        searchPage.verifyProductSearchResults();
    }

    @Then("^Select the first option listed$")
    public void select_the_first_option_listed() throws Throwable {
        log.info("Select the first product option");
        searchPage.selectingFirstProductInSearchResults();
        prodPage = base.getEbayProductPage();
        prodPage.verifySelectedProductIsOpen();
    }

    @Then("^Add the item to the cart$")
    public void add_the_item_to_the_cart() throws Throwable {
        log.info("Add Product to cart");
        productSelected=prodPage.getProductName().trim();
        prodPage.clickOnAddToCartButton();
    }

    @Then("^Handle any additional popup$")
    public void handle_any_additional_popup() throws Throwable {
        log.info("Verify and Handle Popup");
        prodPage.clickOnNoThanksButton();
    }

    @Then("^verify customer gets check out option$")
    public void verify_customer_gets_check_out_option() throws Throwable {
        log.info("Verify customer able to Checkout");
        shoppingCartPage= base.getEbayShoppingCartPage();
        boolean isSuccess=shoppingCartPage.verifyOnShoppingCartPage();
        Assert.assertTrue(isSuccess);
        shoppingCartPage.verifyPrductShownInShoppingCartPage(productSelected);
        isSuccess=shoppingCartPage.verifyPrductShownInShoppingCartPage(productSelected);
        Assert.assertTrue(isSuccess);
        log.info("product is shown on the shopping cart");
    }


    @Then("^Remove the product and verify its successfull$")
    public void remove_the_product_and_verify_its_successfull() throws Throwable {
        log.info("Remove the product from Cart");
        shoppingCartPage.removeTheProductFromShoopingCart(productSelected);
        log.info("Verify that the product successfully removed");
        boolean isSuccess=shoppingCartPage.veridyProductRemovedFromCart();
        Assert.assertTrue(isSuccess);
        log.info("product successfully from Cart");
    }
}
