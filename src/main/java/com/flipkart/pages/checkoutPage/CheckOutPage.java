package com.flipkart.pages.checkoutPage;

import com.flipkart.pages.loginPage.LoginPage;
import com.flipkart.util.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class CheckOutPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CheckOutPage.class);

    //assign driver to call from test class
    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    /*
    Checkout page contains 4 parts:
    1. Login
    2. Delivery Address
    3. Order Summary
    4. payment options
     */

    //INCOMPLETE
    //method to check if login and delivery address is correctly dispalyed
    public boolean isLoginAddressCorrect(String loginDetails, String addressDetails){

        return true;
    }

    //method to check if acceptAndContinuePopUp is clickable and accept it
    public void acceptAndContinuePopUpClick() {
        waitUtils.waitForElementToBeClickable(CheckOutPageLocators.acceptAndContinueButton,10);
        WebElement element = driver.findElement(CheckOutPageLocators.acceptAndContinueButton);
        element.click();
        logger.info("'Open box Delivery' Accept and Continue POPUP message is accepted");
    }
}
