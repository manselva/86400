package org.ebay.Base;

import org.ebay.Pages.EbayHomePage;
import org.ebay.Pages.EbayProductPage;
import org.ebay.Pages.EbaySearchPage;
import org.ebay.Pages.EbayShoppingCartPage;
import org.openqa.selenium.WebDriver;

public class BaseUtil {

    public WebDriver driver;
    private EbayHomePage ebayHomePage;
    private EbayProductPage ebayProductPage;
    private EbaySearchPage ebaySearchPage;
    private EbayShoppingCartPage ebayShoppingCartPage;

    public EbayHomePage getEbayHomePage() {
        return (ebayHomePage == null) ? ebayHomePage = new EbayHomePage(driver) : ebayHomePage;
    }

    public EbaySearchPage getEbaySearchPage() {
        return (ebaySearchPage == null) ? ebaySearchPage = new EbaySearchPage(driver) : ebaySearchPage;
    }

    public EbayProductPage getEbayProductPage() {
        return (ebayProductPage == null) ? ebayProductPage = new EbayProductPage(driver) : ebayProductPage;
    }

    public EbayShoppingCartPage getEbayShoppingCartPage() {
        return (ebayShoppingCartPage == null) ? ebayShoppingCartPage = new EbayShoppingCartPage(driver) : ebayShoppingCartPage;
    }
}
