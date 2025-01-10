package com.flipkart;

import com.flipkart.base.BaseClass;
import com.flipkart.dataProvider.TestData;
import com.flipkart.pages.checkoutPage.CheckOutPage;
import com.flipkart.pages.checkoutPage.CheckOutPageLocators;
import com.flipkart.pages.flipkartE2E.FlipkartE2E;
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
import java.util.Map;

public class FlipkartE2ETests extends BaseClass {

    LoginPage login;
    SearchResultsPage search;
    CheckOutPage checkOut;
    FlipkartE2E e2e;

    @BeforeClass(groups = {"sanity", "smoke", "regression", "allTestSuite"})
    public void initializeE2ETest() {
        if (driver == null) {
            setUp();
        }
        login = new LoginPage(driver);
        search = new SearchResultsPage(driver);
        checkOut = new CheckOutPage(driver);
        e2e = new FlipkartE2E(driver);

        //adding an assert to check if cookies are working and flipkart is logged in
        login.loadCookies();
        action.navigateToURL(Constants.FLIPKART_URL);
        action.hoverOverElement(HomePageLocators.accountButton);
        action.waitUntilFieldIsVisible(HomePageLocators.logoutButton);
        Assert.assertTrue(action.isButtonDisplayed(HomePageLocators.logoutButton));
        logger.info("Before class setup complete");
    }

    @Test(priority = 1, description = "ETE_001: Validate that when users search for product, user should be able to buy the first product displayed using 'Buy Now'",dataProvider = "fetchFirstProductBuyNow", dataProviderClass = TestData.class, groups = {"smoke","regression","sanity","allTestSuite"})
    public void verifyBuyNowE2EPurchaseFlowForFirstProductDisplayed(Map<String, String> productData) {

        logger.info("Test Start: ETE_001: Validate that when users search for product, user should be able to buy the first product displayed using 'Buy Now'");
        action.navigateToURL(Constants.FLIPKART_URL);
        search.clearSearchFiledIfHasValue();
        action.waitForPageToLoad();

        String productName = productData.get("ProductName");
        String expectedBrandName = productData.get("Brand");

        logger.info("Product name: {}", productName);
        logger.info("Expected brand name: {}", expectedBrandName);

        search.searchProduct(productName);
        action.waitForPageToLoad();

        List<String> allBrandNamesInFilter = search.getAllDisplayedBrandNames();

        boolean isBrandNamePresent = search.isBrandNamePresent(expectedBrandName,allBrandNamesInFilter);
        Assert.assertTrue(isBrandNamePresent,"Brand name is not present in the filter section");

        List<String> allProductsDisplayedNames = action.fetchAllTextValuesUsingLocator(SearchResultsPageLocators.getAllProductNamesOnPage);
        action.printListItems(allProductsDisplayedNames);

        String firstDisplayedProduct = search.getProductNameAtXPosition(allProductsDisplayedNames, 1);
        logger.info("First product displayed in search result: {}", firstDisplayedProduct);

        search.selectProdByProductName(firstDisplayedProduct);
        //search.verifyOpenedProductIsCorrect(firstDisplayedProduct);

        e2e.clickBuyNow();
        e2e.clickContinueButton();
        e2e.selectYourUPIIDAndVerify();
        action.waitUntilElementIsClickable(CheckOutPageLocators.payButton);

        Assert.assertTrue(action.isButtonEnabled(CheckOutPageLocators.payButton));

        action.switchBackToMainTab();
        action.waitUntilFieldIsVisible(HomePageLocators.searchButton);
        search.clearSearchField();
        action.waitForPageToLoad();
        logger.info("Test End: ETE_001: Validate that when users search for product, user should be able to buy the first product displayed using 'Buy Now'");

    }

    /*

    @Test(priority = 2, description = "ETE_002: Validate that when users search for product, user should be able to buy the first product displayed using  'Add to Cart'")
    public void verifyAddToCartE2EPurchaseFlowForFirstProductDisplayed(){

        logger.info("Test Start: ETE_002: Validate that when users search for product, user should be able to buy the first product displayed using  'Add to Cart'");
        search.searchProduct("SAMSUNG Galaxy A15 5G (Blue, 128 GB)  (8 GB RAM)");
        List<String> allProductsDisplayedNames = action.fetchAllTextValuesUsingLocator(SearchResultsPageLocators.getAllProductNamesOnPage);
        String firstDisplayedProduct = search.getProductNameAtXPosition(allProductsDisplayedNames, 1);
        logger.info("First product displayed in search result: {}", firstDisplayedProduct);
        search.selectProdByProductName(firstDisplayedProduct);
        action.switchToNewTab();
        action.waitForPageToLoad();
        //add method in search to make sure the new tab opened is same as the one selected .. first product displayed
        //add assert
        action.clickButton(ProductDetailsPageLocators.addToCartButton);
        action.waitUntilElementIsClickable(CheckOutPageLocators.placeOrderButton);
        action.clickButton(CheckOutPageLocators.placeOrderButton);
        action.waitUntilElementIsClickable(CheckOutPageLocators.continueButton);
        action.clickButton(CheckOutPageLocators.continueButton);
        checkOut.acceptAndContinuePopUpClick();
        action.waitUntilElementIsClickable(CheckOutPageLocators.selectYourUPIId);
        action.clickButton(CheckOutPageLocators.selectYourUPIId);
        action.insertValue(Constants.validUPIId);
        action.waitUntilElementIsClickable(CheckOutPageLocators.verifyButton);
        action.clickButton(CheckOutPageLocators.verifyButton);
        logger.info("Test End: ETE_002: Validate that when users search for product, user should be able to buy the first product displayed using  'Add to Cart'");

    }

    @Test(priority = 2, description = "ETE_003: Validate that when users search for product, user should be able to buy the first product displayed using  'Add to Cart' for specific brand of mobile")
    public void verifyAddToCartE2EPurchaseFlowForSpecificBrandMobile(){

        logger.info("Test Start: ETE_003: Validate that when users search for product, user should be able to buy the first product displayed using  'Add to Cart' for specific brand of mobile");
        search.searchProduct("SAMSUNG Galaxy A15 5G (Blue, 128 GB)");
        //String mobileBrand = "POCO";
        List<String> allProductsDisplayedNames = action.fetchAllTextValuesUsingLocator(SearchResultsPageLocators.getAllProductNamesOnPage);
        String firstDisplayedProduct = search.getProductNameAtXPosition(allProductsDisplayedNames, 1);
        logger.info("First product displayed in search result: {}", firstDisplayedProduct);
        search.selectProdByProductName(firstDisplayedProduct);
        action.switchToNewTab();
        action.waitForPageToLoad();
        action.clickButton(ProductDetailsPageLocators.addToCartButton);
        logger.info("Test End: ETE_003: Validate that when users search for product, user should be able to buy the first product displayed using  'Add to Cart' for specific brand of mobile");

    }

     */


}



