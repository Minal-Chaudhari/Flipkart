package com.flipkart.pages.loginPage;

import org.openqa.selenium.By;

public class LoginPageLocators {

    //login page xpaths
    public static final By loginButton = By.xpath("//span[text()='Login']");
    public static final By enterEmailOrMobile = By.xpath("//span[text()=\"Enter Email/Mobile number\"]");
    public static final By termsOfUseLink = By.xpath("//a[contains(text(),'Terms of Use')]");
    public static final By createAccountLink = By.xpath("//a[contains(text(),'Create an account')]");
    public static final By privacyPolicyLink = By.xpath("//a[contains(text(),'Privacy Policy')]");
    public static final By loginText = By.xpath("//span[contains(text(),'Login')]");
    public static final By loginPageText = By.xpath("//span[contains(text(),'Login')]//following::span");

    //new user page xpaths
    public static final By newUserEnterMobileNumber = By.xpath("//span[contains(text(),'Enter Mobile number')]");
    public static final By newUserPageExistingUserButton = By.xpath("//span[contains(text(),'Existing User? Log in')]");
    public static final By newUserContinueButton = By.xpath("//span[contains(text(),'CONTINUE')]//parent::button");

    //to be added
    public static final By newUserPageLoginText = By.xpath("");
    public static final By newUserPageText = By.xpath("");
}
