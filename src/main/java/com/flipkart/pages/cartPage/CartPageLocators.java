package com.flipkart.pages.cartPage;

import org.openqa.selenium.By;

public class CartPageLocators {

    public static final By verifyButton = By.xpath("//a[@title='Logout']");
    public static final By placeOrderButton = By.xpath("//span[contains(text(),'Place Order')]");
    //price details right side table (to fetch all table keys)
    public static final By fetchPriceDetailsKeys = By.xpath("//div[@class='k9WPjB']");
    //price table right side (to fetch all table values )
    public static final By fetchPriceDetailsValues = By.xpath("//span[@class='b5rp0W']");
    public static final By cartItems = By.xpath("");

}
