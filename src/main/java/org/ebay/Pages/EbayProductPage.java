package org.ebay.Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EbayProductPage {
    private static final Logger log = Logger.getLogger(EbayProductPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    //Page Objects Ebay Product page
    @FindBy(css = "#isCartBtn_btn")
    private
    WebElement addToCartButton;

    @FindBy(id = "vi_oly_ADDON_0")
    private
    WebElement popUpProtectionPlan;

    @FindBy(xpath = "//button[text()='No thanks']")
    private
    WebElement noThanksButton;

    @FindBy(id = "itemTitle")
    private
    WebElement productName;


    public EbayProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 30);
    }

    public boolean verifySelectedProductIsOpen() {
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        return addToCartButton.isDisplayed();
    }

    public void clickOnAddToCartButton() {
        log.info("Clicking on the Add To Cart Button");
        addToCartButton.click();
    }

    public void clickOnNoThanksButton() {
        log.info("Clicking on No Thanks Button for protection plan");
        Assert.assertTrue(noThanksButton.isDisplayed());
        noThanksButton.click();
    }

    public String getProductName() {
        log.info("Retrieving the product name");
        Assert.assertTrue(productName.isDisplayed());
        return productName.getText();
    }
}

