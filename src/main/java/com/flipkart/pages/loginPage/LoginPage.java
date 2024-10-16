package com.flipkart.pages.loginPage;

import com.flipkart.util.WaitUtils;
import org.openqa.selenium.*;

import java.util.Set;


public class LoginPage {

    //local driver
    private WebDriver driver;
    private WaitUtils waitUtils;

    //assign driver to call from test class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    //=======================testcase specific methods================================

    //testcase specific methods



}
