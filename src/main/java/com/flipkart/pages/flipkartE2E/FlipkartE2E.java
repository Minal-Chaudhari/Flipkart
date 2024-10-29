package com.flipkart.pages.flipkartE2E;

import com.flipkart.pages.homePage.HomePage;
import com.flipkart.util.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;

public class FlipkartE2E {

    private WebDriver driver;
    private WaitUtils waitUtils;

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(FlipkartE2E.class);

    //assign driver to call from test class
    public FlipkartE2E(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }


    /*
    search prod
click on buy now
click on continue
"accept and continue" popup
make sure upi is selecetd
enter valid upi id
click on verify
verify pay button is enabled
     */




}
