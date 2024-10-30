package com.flipkart.pages.homePage;

import com.flipkart.pages.productDetailsPage.ProductDetailsPage;
import com.flipkart.util.ActionUtils;
import com.flipkart.util.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private ActionUtils action;

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(HomePage.class);

    //assign driver to call from test class
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.action = new ActionUtils(driver);
    }





}
