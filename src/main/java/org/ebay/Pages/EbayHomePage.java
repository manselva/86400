package org.ebay.Pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EbayHomePage {
    private static final Logger log = Logger.getLogger(EbayHomePage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;
    //Page Objects Ebay for Home page
    @FindBy(css = "#gh-ug > a")
    private
    WebElement signInLink;
    @FindBy(css = "#gh-ac")
    private
    WebElement inputProductToBeSearched;
    @FindBy(css = "#gh-cat")
    private
    WebElement selectProductCategory;
    @FindBy(css = "#gh-btn")
    private
    WebElement searchProductButton;

    public EbayHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(this.driver, 30);
    }

    public boolean navigateToEbay(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(signInLink));
        return signInLink.isDisplayed();
    }

    public boolean selectProductCategory(String category) {
        boolean foundCatgeory = false;
        int postion = 0;
        if (!selectProductCategory.isDisplayed()) {
            log.info("Category  dropdown is not available");
            return false;
        }
        Select selectElem = new Select(selectProductCategory);
        log.info("Verifying the " + category + " is available to select");
        List<WebElement> lst = selectElem.getOptions();
        for (WebElement options : lst) {
            if (options.getText().trim().equalsIgnoreCase(category)) {
                foundCatgeory = true;
                break;
            }
            postion = postion + 1;
        }
        if (foundCatgeory = false)
            return false;
        log.info("Selecting Product Category as: " + category + " at Position: " + postion);
        selectElem.selectByIndex(postion);
        return selectElem.getFirstSelectedOption().getText().trim().equalsIgnoreCase(category);
    }

    public boolean enterProductToBeSearched(String product) {
        if (!inputProductToBeSearched.isDisplayed()) {
            log.info("Search Text box not available");
            return false;
        }
        log.info("Entering the Product value as " + product + " to search");
        inputProductToBeSearched.click();
        inputProductToBeSearched.sendKeys(product);
        return true;
    }

    public void clickOnSearchButton() {
        if (!searchProductButton.isDisplayed()) {
            log.info("Search button not available");
            Assert.fail();
        }
        log.info("Clicking on Search button");
        searchProductButton.click();
    }
}
