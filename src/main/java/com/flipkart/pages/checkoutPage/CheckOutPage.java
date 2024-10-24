package com.flipkart.pages.checkoutPage;

import com.flipkart.util.WaitUtils;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    //assign driver to call from test class
    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }




}
