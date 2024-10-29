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

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ActionUtils.class);

    //assign driver to call from test class CONSTRUCTOR
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
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

    //method will select product, to be passed: product title/name
    public void selectProdByProductName(String productName) {
        //creating dynamic xpath
        //String dynamicXPath = "//div[@class='KzDlHZ' and contains(text(),'Lenovo IdeaPad Slim 5 Qualcomm Snapdragon X Plus - (16 GB/1 TB SSD/Windows 11 Home) 14Q8X9 Thin and Li...')]";
        //String dynamicXPath = SearchResultsPageLocators.selectProdWithProdName +"'"+ productName + "')]";
        String dynamicXPath = SearchResultsPageLocators.prodNameXpath+"'"+productName+"')]";
        logger.info("Dynamic xpath: {}",dynamicXPath);
        //div[@class='KzDlHZ' and contains(text(),'prod name')]

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





}
