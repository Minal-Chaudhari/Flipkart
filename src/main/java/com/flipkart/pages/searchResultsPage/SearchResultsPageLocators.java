package com.flipkart.pages.searchResultsPage;

import org.openqa.selenium.By;


public class SearchResultsPageLocators {

    public static final String prodNameXpath = "//div[@class='KzDlHZ' and contains(text(),";
    public static final By getAllProductNamesOnPage = By.xpath("//div[@class='KzDlHZ']");
    public static final By getGetAllProductPriceOnPage = By.xpath("//div[@class='KzDlHZ']//parent::div//parent::div//child::div[@class='Nx9bqj _4b5DiR']");
    public static final By clearAllFilterButton = By.xpath("//span[contains(text(),'Clear all')]");
    public static final By applyFilterButton = By.xpath("//span[contains(text(),'Apply Filters')]");

    //use get attribute for title here to fetch the list of names of brands, the title is having the values of brands
    public static final By getAllBrandNames = By.xpath("//div[text()='Brand']//ancestor::section//child::div[@title]");
    public static final By getAllFilters = By.xpath("//div[@class='fxf7w6 rgHxCQ']");//here use gettext
    public static final By selectProdWithProdName = By.xpath("//div[@class='KzDlHZ' and contains(text(),");

    //for the below xpath to make it dynamic remove "Samsung" and make sure to add brand name during runtime
    //just check if this is visible.. or wait until it is visible
    //public static final By brandDropdownValue = By.xpath("//div[text()='Brand']//parent::div//following-sibling::div//following::div[text()='SAMSUNG']");


}
