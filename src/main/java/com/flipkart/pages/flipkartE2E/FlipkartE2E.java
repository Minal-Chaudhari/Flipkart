package com.flipkart.pages.flipkartE2E;

import com.flipkart.util.WaitUtils;
import org.openqa.selenium.WebDriver;

public class FlipkartE2E {

    private WebDriver driver;
    private WaitUtils waitUtils;

    //assign driver to call from test class
    public FlipkartE2E(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

}
