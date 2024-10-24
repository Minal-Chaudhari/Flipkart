package com.flipkart.pages.productDetailsPage;

import com.flipkart.util.WaitUtils;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    //assign driver to call from test class
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

}
