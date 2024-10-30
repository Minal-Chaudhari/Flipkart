package com.flipkart.pages.searchResultsPage;

import com.flipkart.pages.homePage.HomePageLocators;
import com.flipkart.util.ActionUtils;
import com.flipkart.util.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private ActionUtils action;

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ActionUtils.class);

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
        searchField.clear(); //just to make sure it is empty before sending new data
        searchField.sendKeys(prodName);
        searchField.sendKeys(Keys.ENTER);
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

    //method will select the product with given brand name
    public String getProductByBrandName(List<String> productList, String brandName) {

        if (productList.contains(brandName)) {
            //return the first element in the list
            return productList.get(0);
        } else {
            logger.warn("The product list does not contain the specified brand name.");
            return null;
        }
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
            System.out.println("An error occurred while clearing the search field: " + e.getMessage());
        }
    }
}
