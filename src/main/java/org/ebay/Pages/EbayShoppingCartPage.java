package org.ebay.Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EbayShoppingCartPage {
    private static final Logger log = Logger.getLogger(EbayShoppingCartPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    //Page Objects for Shopping Cart Page
    @FindBy(xpath = "//button[text()='Go to checkout']")
    private
    WebElement checkoutButton;

    @FindBy(css = ".page-alerts")
    private
    WebElement productRemovalConfirmation;

    public EbayShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 30);
    }

    public boolean verifyOnShoppingCartPage() {
        log.info("Verify the navigation to shopping cart page");
        wait.until(ExpectedConditions.visibilityOf(checkoutButton));
        return checkoutButton.isDisplayed();
    }

    public boolean verifyPrductShownInShoppingCartPage(String prodName) {
        log.info("Verify product added is the same as selected");
        String xpathElem = "//span[contains(text(),'" + prodName + "')]";
        return driver.findElement(By.xpath(xpathElem)).isDisplayed();
    }

    public void removeTheProductFromShoopingCart(String prodName) {
        log.info("Verify product added is the same as selected");
        String xpathElem = "//button[contains(@aria-label,'Remove – " + prodName + "')]";
        WebElement removeProduct = driver.findElement(By.xpath(xpathElem));
        Assert.assertTrue(removeProduct.isDisplayed());
        removeProduct.click();
    }

    public boolean veridyProductRemovedFromCart() {
        log.info("Verify product Removed from the cart");
        return productRemovalConfirmation.isDisplayed();
    }
}
