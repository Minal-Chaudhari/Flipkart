package com.flipkart.pages.productDetailsPage;

import org.openqa.selenium.By;

public class ProductDetailsPageLocators {

    public static final By getProductName = By.xpath("//span[@class='VU-ZEz']"); //need to use getText here
    public static final By getProductPrice = By.xpath("(//div[@class='hl05eU']//child::div)[1]");//use get text
    public static final By addToCardButton = By.xpath("//button[contains(text(),'Add to cart')]");
    /*
    some special xpath
    //button will select all buttons
    .//text() will select all text nodes
    contains(.,) will check for all text nodes having the given text
    The text "Buy Now" wasn't directly inside the <button> tag but was a part of the descendants (most likely after a <span>)
     */
    public static final By BuyNowButton = By.xpath("//button[.//text()[contains(., 'Buy Now')]]");
    public static final By deliveryCodeTextField = By.xpath("//input[@placeholder='Enter Delivery Pincode']");
    public static final By deliveryCodeChangeButton = By.xpath("//span[contains(text(),'Change')]");
    public static final By deliveryCodeCheckButton = By.xpath("//span[contains(text(),'Check')]");
    public static final By productDescription = By.xpath("//div[contains(text(),'Description')]//parent::div//descendant::p");
    public static final By productSpecificationsTableKey = By.xpath("");
    public static final By productSpecificationsTableValue = By.xpath("");
    public static final By ratingsAndReview = By.xpath("");
    public static final By questionAndAnswers = By.xpath("");
    public static final By availableOffers = By.xpath("");

    //TO BE DONE

    public static final By BuyNowButto3n = By.xpath("");
    public static final By getProlductName = By.xpath("");

}
