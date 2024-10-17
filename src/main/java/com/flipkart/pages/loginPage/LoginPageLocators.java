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
    public static final By termsOfUseText = By.xpath("//h2[@id='flipkart-terms-of-use']");
    public static final By privacyPolicyText = By.xpath("//strong[contains(text(),'PRIVACY POLICY')]");
    public static final By requestOTPButton = By.xpath("//button[contains(text(),\"Request OTP\")]");
    public static final By invalidMobileNumberError = By.xpath("//span[contains(text(),\"Please enter valid Email ID/Mobile number\")]");
    public static final By invalidEmailIDPopUpText = By.xpath("//div[contains(text(),'You are not registered with us')]");
    //public static final By openTextBoxToEnterValue = By.xpath("//span[contains(text(),'Enter Email/Mobile number')]//parent::label//parent::div//input");
    public static final By verifyButton = By.xpath("//button[contains(text(),'Verify')]");




    //new user page xpaths
    public static final By newUserEnterMobileNumber = By.xpath("//span[contains(text(),'Enter Mobile number')]");
    public static final By newUserPageExistingUserButton = By.xpath("//span[contains(text(),'Existing User? Log in')]");
    public static final By newUserContinueButton = By.xpath("//span[contains(text(),'CONTINUE')]//parent::button");
    public static final By newUserPageLoginText = By.xpath("//span[contains(text(),\"Looks like you're new here!\")]");
    public static final By newUserPageText = By.xpath("//span[contains(text(),\"Looks like you're new here!\")]//following::span");


}
