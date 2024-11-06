package com.flipkart.pages.productDetailsPage;

import com.flipkart.util.ActionUtils;
import com.flipkart.util.Constants;
import com.flipkart.util.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    //method to check pincode (CAN BE REMOVED IF BELOW METHOD STARTS WORKING)
    public void checkIfProductIsAvailableAtPincode(){
        WebElement pinCode = driver.findElement(ProductDetailsPageLocators.enterPincodeField);
        pinCode.click();
        pinCode.clear();
        action.insertValue(Constants.pinCode);
    }

    /*
    //TO BE COMPLETED
    //method to change pincode to the one where the product is available
    public void checkProductAvailabilityAtPincodeFromList(List<String> pincodeList) {
        for (String pincode : pincodeList) {
            WebElement pinCode = driver.findElement(ProductDetailsPageLocators.enterPincodeField);
            pinCode.click();
            pinCode.clear();
            action.insertValue(pincode);
            //waitUtils.waitForElementToBeClickable(ProductDetailsPageLocators.deliveryCodeCheckButton,10);
            action.clickButton(ProductDetailsPageLocators.deliveryCodeCheckButton);

            // Wait for the result to be visible
            WaitUtils.waitForElementToBeVisible(ProductDetailsPageLocators.availabilityMessage,10);

            // Check for the availability message
            String availabilityMessage = driver.findElement(ProductDetailsPageLocators.availabilityMessage).getText();
            if (availabilityMessage.contains("available")) {
                System.out.println("Product is available at pin code: " + pincode);
                return; // Exit the method if product is available at any pincode
            } else {
                System.out.println("Product is not available at pin code: " + pincode);
            }
        }

        System.out.println("Product is not available at any of the provided pin codes.");
    }

     */


}
