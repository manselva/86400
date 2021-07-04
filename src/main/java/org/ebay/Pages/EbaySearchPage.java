package org.ebay.Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EbaySearchPage {
    private static final Logger log = Logger.getLogger(EbaySearchPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    //Page Objects Ebay for Search page
    @FindBy(css = "#srp-river-results > ul > li")
    private
    List<WebElement> productSearchResults;

    @FindBy(css = "#srp-river-results > ul > li:nth-child(2)")
    private
    WebElement productSearchFirstItem;

    @FindBy(css = "#srp-river-results > ul > li:nth-child(2) > div > div.s-item__info.clearfix > a")
    private
    WebElement productSearchFirstItemLink;

    public EbaySearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 30);
    }

    public void verifyProductSearchResults() {
        log.info("Validating the Product Search Results");
        if (productSearchResults.size() > 0) {
            log.info("Product Search returned: " + productSearchResults.size() + " items");
        } else {
            log.info("Product Search returned: " + productSearchResults.size() + " items");
            Assert.fail("No Product Returned");
        }
    }

    public void selectingFirstProductInSearchResults() throws InterruptedException {
        log.info("Selecting the First product on the search list");
        Assert.assertTrue(productSearchFirstItemLink.isDisplayed());
        log.info("Selecting First product  # " + productSearchFirstItemLink.getText());
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", productSearchFirstItemLink);
    }

}

