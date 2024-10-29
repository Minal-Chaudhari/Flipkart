package com.flipkart;

import com.flipkart.base.BaseClass;
import com.flipkart.pages.checkoutPage.CheckOutPage;
import com.flipkart.pages.checkoutPage.CheckOutPageLocators;
import com.flipkart.pages.homePage.HomePageLocators;
import com.flipkart.pages.loginPage.LoginPage;
import com.flipkart.pages.productDetailsPage.ProductDetailsPageLocators;
import com.flipkart.pages.searchResultsPage.SearchResultsPage;
import com.flipkart.pages.searchResultsPage.SearchResultsPageLocators;
import com.flipkart.util.Constants;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FlipkartE2ETests extends BaseClass {

    LoginPage login;
    SearchResultsPage search;
    CheckOutPage checkOut;

    @BeforeClass(groups = {"sanity", "smoke", "regression", "allTestSuite"})
    public void initializeE2ETest() {
        if (driver == null) {
            setUp();
        }
        login = new LoginPage(driver);
        search = new SearchResultsPage(driver);
        checkOut = new CheckOutPage(driver);

        //adding an assert to check if cookies are working and flipkart is logged in
        login.loadCookies();
        action.navigateToURL(Constants.FLIPKART_URL);
        action.hoverOverElement(HomePageLocators.accountButton);
        action.waitUntilFieldIsVisible(HomePageLocators.logoutButton);
        Assert.assertTrue(action.isButtonDisplayed(HomePageLocators.logoutButton));
        logger.info("Before class setup complete");
    }

    @Test(priority = 1, description = "ETE_001: Validate that when users search for product, user should be able to buy the first product displayed using 'Buy Now'")
    public void verifyBuyNowE2EPurchaseFlowForFirstProductDisplayed() {

        logger.info("Test Start: ETE_001: Validate that when users search for product, user should be able to buy the first product displayed using 'Buy Now'");
        search.searchProduct("laptop");
        List<String> allProductsDisplayedNames = action.fetchAllTextValuesUsingLocator(SearchResultsPageLocators.getAllProductNamesOnPage);
        String firstDisplayedProduct = search.getProductNameAtXPosition(allProductsDisplayedNames, 3);
        logger.info("First product displayed in search result: {}", firstDisplayedProduct);
        search.selectProdByProductName(firstDisplayedProduct);
        action.switchToNewTab();
        action.waitForPageToLoad();
        action.clickButton(ProductDetailsPageLocators.BuyNowButton);
        action.waitUntilElementIsClickable(CheckOutPageLocators.continueButton);
        action.clickButton(CheckOutPageLocators.continueButton);
        checkOut.acceptAndContinuePopUpClick();
        action.waitUntilElementIsClickable(CheckOutPageLocators.selectYourUPIId);
        action.clickButton(CheckOutPageLocators.selectYourUPIId);
        action.insertValue(Constants.validUPIId);
        action.waitUntilElementIsClickable(CheckOutPageLocators.verifyButton);
        action.clickButton(CheckOutPageLocators.verifyButton);
        logger.info("Test End: ETE_001: Validate that when users search for product, user should be able to buy the first product displayed using 'Buy Now'");

    }

    @Test(priority = 2, description = "ETE_002: Validate that when users search for product, user should be able to buy the first product displayed using  'Add to Cart'")
    public void verifyAddToCartE2EPurchaseFlowForFirstProductDisplayed(){

        logger.info("Test Start: ETE_002: Validate that when users search for product, user should be able to buy the first product displayed using  'Add to Cart'");
        search.searchProduct("laptop");
        List<String> allProductsDisplayedNames = action.fetchAllTextValuesUsingLocator(SearchResultsPageLocators.getAllProductNamesOnPage);
        String firstDisplayedProduct = search.getProductNameAtXPosition(allProductsDisplayedNames, 1);
        logger.info("First product displayed in search result: {}", firstDisplayedProduct);
        search.selectProdByProductName(firstDisplayedProduct);
        action.switchToNewTab();
        action.waitForPageToLoad();
        action.clickButton(ProductDetailsPageLocators.addToCartButton);
        logger.info("Test End: ETE_002: Validate that when users search for product, user should be able to buy the first product displayed using  'Add to Cart'");

    }


}



