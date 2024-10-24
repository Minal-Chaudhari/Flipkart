package com.flipkart.pages.searchResultsPage;

import com.flipkart.util.WaitUtils;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    //assign driver to call from test class
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }
}
