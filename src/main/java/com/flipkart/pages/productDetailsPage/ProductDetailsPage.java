package com.flipkart.pages.productDetailsPage;

import com.flipkart.util.ActionUtils;
import com.flipkart.util.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private ActionUtils action;

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ProductDetailsPage.class);

    //assign driver to call from test class
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.action = new ActionUtils(driver);
    }

}
