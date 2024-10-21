package com.flipkart.pages.searchResultsPage;

import org.openqa.selenium.By;


public class SearchResultsPageLocators {

    public static final By getAllProductNamesOnPage = By.xpath("//div[@class='KzDlHZ']");
    public static final By getGetAllProductPriceOnPage = By.xpath("//div[@class='KzDlHZ']//parent::div//parent::div//child::div[@class='Nx9bqj _4b5DiR']");
    public static final By clearAllFilterButton = By.xpath("//span[contains(text(),'Clear all')]");
    public static final By applyFilterButton = By.xpath("//span[contains(text(),'Apply Filters')]");
    public static final By getAllBrandNames = By.xpath("//div[@class='ewzVkT']");//use get attribute for title here to fetch the list of names of brands
    public static final By getAllFilters = By.xpath("//div[@class='fxf7w6 rgHxCQ']");//here use gettext



    //"//div[@class='ewzVkT']" this xpath will give all brands name .. getAttribute i will have to use here, the title is having the values of brands

}
