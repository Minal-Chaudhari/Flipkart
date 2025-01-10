package com.flipkart.pages.searchResultsPage;

import com.flipkart.pages.homePage.HomePageLocators;
import com.flipkart.pages.productDetailsPage.ProductDetailsPageLocators;
import com.flipkart.util.ActionUtils;
import com.flipkart.util.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private ActionUtils action;

    private static final Logger logger = LogManager.getLogger(SearchResultsPage.class);

    //assign driver to call from test class CONSTRUCTOR
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.action = new ActionUtils(driver);
    }

    //================================ page specific methods ================================

    //method will search for a given product name "prodName"
    public void searchProduct(String prodName) {
        WebElement searchField = driver.findElement(HomePageLocators.searchButton);
        searchField.click();
        if (!searchField.getAttribute("value").isEmpty()) {
            searchField.clear(); //just to make sure it is clear before entering new value
        }
        searchField.sendKeys(prodName);
        searchField.sendKeys(Keys.ENTER);
        waitUtils.waitForPageToLoad(10);
        //searchField = driver.findElement(HomePageLocators.searchButton); //refresh element
        logger.info("Searching for product: {}", prodName);
    }

    //method will fetch the prodname stored in list at given position "x"
    public String getProductNameAtXPosition(List<String> productList, int x) {
        if (productList.size() < x) {
            logger.warn("The product list does not contain enough items.");
            return null;
        }
        return productList.get(x-1); //(x-1) because of indexing since list startes from "0"
    }


    //method will select product, to be passed: product title/name
    public void selectProdByProductName(String productName) {
        //creating dynamic xpath
        String dynamicXPath = SearchResultsPageLocators.prodNameXpath+"'"+productName+"')]";
        logger.info("Dynamic xpath: {}",dynamicXPath);

        //locate elements
        List<WebElement> products = driver.findElements(By.xpath(dynamicXPath));

        //if product exists click it
        if (!products.isEmpty()) {
            products.get(0).click(); // Click the first match
            logger.info("Selected product: {}", productName);
        } else {
            logger.warn("Product not found: {}", productName);
        }
    }


    //method to clear search field if it has some value
    public void clearSearchFiledIfHasValue() {

        try {
            WebElement searchField = driver.findElement(HomePageLocators.searchButton);
            if (!searchField.getAttribute("value").isEmpty()) {
                searchField.clear();
            }
        } catch (Exception e) {
            logger.error("An error occurred while clearing the search field: {}" , e.getMessage());
        }
    }


    //method to check if the opened product tab is same as expected
    public void verifyOpenedProductIsCorrect(String ExpectedProductPageTitle){
        logger.info("Waiting for product name to be present");
        /*
        waitUtils.waitForElementToBePresent(ProductDetailsPageLocators.getProductName,10);
        WebElement element = driver.findElement(ProductDetailsPageLocators.getProductName);
        String fetchedProductName = element.getText();
        logger.info("Expected product title: {}", ExpectedProductTitle);
        logger.info("Fetched product title: {}", fetchedProductName);
         */
        waitUtils.waitForPageToLoad(10);
        String fetchedProductPageTitle = driver.getTitle();
        logger.info("Fetched title: {}",fetchedProductPageTitle);
        logger.info("Expected title: {}",ExpectedProductPageTitle);
        Assert.assertNotNull(fetchedProductPageTitle, "Fetched title is null");
        //assert fetchedProductPageTitle != null;
        Assert.assertTrue(fetchedProductPageTitle.equalsIgnoreCase(ExpectedProductPageTitle), "Opened product does not match the expected product title");

        logger.info("Opened product is the same as expected.");

    }

    //method to get all displayed brandnames in the filter section
    public List<String> getAllDisplayedBrandNames(){

        List<String> displayedBrandNames = new ArrayList<String>();

        logger.info("Fetching brand names from filter section");
        List<WebElement> elements = driver.findElements(SearchResultsPageLocators.getAllBrandNames);
        for(WebElement element : elements) {
            String brandName = element.getAttribute("title");
            displayedBrandNames.add(brandName);
        }
        return displayedBrandNames;
    }

    //method to check if the required brand name is present in the displayed brands list .. return true if present
    public boolean isBrandNamePresent(String brandName, List<String> displayedBrandNamesList){

        for(String element:displayedBrandNamesList)
        {
            if(element.equalsIgnoreCase(brandName)){
                logger.info("Expected brand name is present in filter section");
                return true;
            }
        }
        logger.info("Expected brand name is not present in filter section");
        return false;
    }

    //method to clear search field
    public void clearSearchField() {
        //adding wiat
        waitUtils.waitForElementToBeVisible(HomePageLocators.searchButton, 10);
        WebElement element = driver.findElement(HomePageLocators.searchButton);

        //clearing search field
        if (element.isDisplayed() && element.isEnabled()) {
            element.clear();
            logger.info("Search field cleared for new input");
        } else {
            logger.warn("Search field is not accessible to clear");
        }
    }


}
