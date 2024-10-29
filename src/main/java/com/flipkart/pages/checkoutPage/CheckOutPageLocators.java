package com.flipkart.pages.checkoutPage;

import org.openqa.selenium.By;

public class CheckOutPageLocators {

    public static final By continueButton = By.xpath("//button[contains(text(),'CONTINUE')]");
    public static final By acceptAndContinueButton = By.xpath("//button[contains(text(),'Accept & Continue')]");
    public static final By acceptAndContinueCancelButton = By.xpath("//button[contains(text(),'✕')]");
    public static final By upiPaymentOption = By.xpath("//input[@id='UPI']");
    //public static final By selectYourUPIId = By.xpath("//input[@id='UPI_COLLECT']");
    public static final By selectYourUPIId = By.xpath("//div[contains(text(),'Your UPI ID')]");
    public static final By enterUPIId = By.xpath("//input[@name = 'upi-id']");
    public static final By verifyButton = By.xpath("//div[contains(text(),'Verify')]");
    public static final By payButton = By.xpath("//button[@type='button']");

    public static final By loggedInUserName = By.xpath("//div[contains(text(),'Login')]//following::div//div//child::span");
    public static final By loggedInUserDeliveryAddress = By.xpath("");


    public static final By getProlductName = By.xpath("");
    public static final By BuyNowButto3n = By.xpath("");
    public static final By getPro1ldu1ctName = By.xpath("");

}
