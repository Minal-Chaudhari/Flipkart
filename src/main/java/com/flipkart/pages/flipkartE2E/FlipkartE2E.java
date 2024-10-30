package com.flipkart.pages.flipkartE2E;

import com.flipkart.pages.checkoutPage.CheckOutPage;
import com.flipkart.pages.checkoutPage.CheckOutPageLocators;
import com.flipkart.pages.productDetailsPage.ProductDetailsPageLocators;
import com.flipkart.util.ActionUtils;
import com.flipkart.util.Constants;
import com.flipkart.util.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

public class FlipkartE2E {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private ActionUtils action;
    private CheckOutPage checkOut;

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(FlipkartE2E.class);

    //assign driver to call from test class
    public FlipkartE2E(WebDriver driver) {
        this.driver = driver;
        this.action = new ActionUtils(driver);
        this.waitUtils = new WaitUtils(driver);
        this.checkOut = new CheckOutPage(driver);
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

    //method will select your UPI id radio button option
    public void selectYourUPIIDAndVerify(){
        action.waitUntilElementIsClickable(CheckOutPageLocators.selectYourUPIId);
        action.clickButton(CheckOutPageLocators.selectYourUPIId);
        action.insertValue(Constants.validUPIId);
        action.waitUntilElementIsClickable(CheckOutPageLocators.verifyButton);
        action.clickButton(CheckOutPageLocators.verifyButton);
    }

    //method will click on continue button
    public void clickContinueButton(){
        action.waitUntilElementIsClickable(CheckOutPageLocators.continueButton);
        action.clickButton(CheckOutPageLocators.continueButton);
        checkOut.acceptAndContinuePopUpClick();
    }

    //method will perform buynow functinality
    public void clickBuyNow(){
        action.switchToNewTab();
        action.waitForPageToLoad();
        action.clickButton(ProductDetailsPageLocators.BuyNowButton);
    }


}
