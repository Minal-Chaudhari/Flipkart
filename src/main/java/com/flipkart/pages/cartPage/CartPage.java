package com.flipkart.pages.cartPage;

import com.flipkart.pages.flipkartE2E.FlipkartE2E;
import com.flipkart.util.ActionUtils;
import com.flipkart.util.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage {


    private WebDriver driver;
    private WaitUtils waitUtils;
    private ActionUtils action;

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CartPage.class);

    //assign driver to call from test class
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.action = new ActionUtils(driver);
    }

    //cart specific methods

    //method to print all values of list
    public void logAllCartElements(List<String> list) {
        for (String element : list) {
            logger.info(element);
        }
    }

    //method will give a list of name and price of all available products in cart
    public void logListOfAllCartProdAndPrice(List<String> keyList, List<String> valueList) {
        if (keyList.size() != valueList.size()) {
            logger.warn("Product name and Product price sizes do not match.");
            return;
        }

        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            String value = valueList.get(i);
            logger.info("Product Name: {} - Price: {}", key, value);
        }
    }


}
