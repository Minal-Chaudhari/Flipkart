package com.flipkart.tests;

import com.flipkart.base.BaseClass;
import com.flipkart.pages.loginPage.LoginPage;
import com.flipkart.pages.loginPage.LoginPageLocators;
import com.flipkart.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseClass {

    LoginPage login;

    @BeforeClass
    public void initializeLoginPage() {
        if (driver == null) {
            setUp();
        }
        login = new LoginPage(driver);
    }

    @Test(priority = 1,description = "LGN_001: Validate the login button presence")
    public void verifyLoginButtonIsDisplayed() {
        Assert.assertTrue(login.isButtonDisplayed(LoginPageLocators.loginButton), "Login button is NOT displayed on the homepage.");
        System.out.println("login button is displayed");
        //click login button
        login.clickButton(LoginPageLocators.loginButton);
        System.out.println("Login Button is clicked");
    }

    @Test(priority = 2,description = "LGN_002: Verify the presence of the email/mobile number field")
    public void verifyPresenceOfEmailIdAndMobileNoField(){
        Assert.assertTrue(login.isButtonDisplayed(LoginPageLocators.enterEmailOrMobile), "Enter Email Or Mobile Number button is NOT displayed on the homepage.");
        System.out.println("Enter Email Or Mobile Number button is displayed");
    }

    @Test(priority = 2,description = "LGN_006: Validate terms of use link is clickable")
    public void verifyTermsLinkIsClickable(){
        boolean link = login.checkHyperlink(LoginPageLocators.termsOfUseLink,Constants.expectedTermsUrl);
        Assert.assertTrue(link,"Terms of use link is NOT clickable");
    }

    @Test(priority = 2,description = "LGN_011: Validate privacy policy link is clickable")
    public void verifyPrivacyLinkIsClickable(){
        boolean link = login.checkHyperlink(LoginPageLocators.privacyPolicyLink,Constants.expectedPrivacyUrl);
        Assert.assertTrue(link,"Privacy Policy link is NOT clickable");
    }

    @Test(priority = 2,description = "LGN_004: Validate 'Create Account' link  is clickable")
    public void verifyCreateAccountLinkIsClickable(){
        boolean link = login.checkHyperlink(LoginPageLocators.createAccountLink,Constants.expectedCreateAccountUrl);
        Assert.assertTrue(link,"'New to Flipkart? Create an account' Link is NOT clickable");
    }

    @Test(priority = 4,description = "LGN_007: Verify redirection to 'Terms of Use' page")
    public void verifyTermsRedirection() {
        /*
        click button
        switch to new tab
        get title
        verify if title contains terms(fetch from constants)
         */
        login.clickButton(LoginPageLocators.termsOfUseLink);
        String newTabTitle = login.switchToNewTabAndGetTitle(LoginPageLocators.termsOfUseText);
        System.out.println("New fetched tab title is: "+newTabTitle);
        Assert.assertTrue(newTabTitle.contains(Constants.termsPageTitle), "New tab is NOT Terms Of Use page.");
        //Thread.sleep(2000);
        login.switchBackToMainTab();
    }

    @Test(priority = 4,description = "LGN_012: Verify redirection to 'Privacy Policy' page")
    public void verifyPrivacyRedirection(){

        login.clickButton(LoginPageLocators.privacyPolicyLink);
        String newTabTitle = login.switchToNewTabAndGetTitle(LoginPageLocators.privacyPolicyText);
        System.out.println("New fetched tab title is: "+newTabTitle);
        Assert.assertTrue(newTabTitle.contains(Constants.privacyPageTitle), "New tab is NOT Terms Of Use page.");
        login.switchBackToMainTab();

    }

    //=============login with data testcases==================================

    @Test(priority = 5,description = "LGN_008: Validate error message for invalid mobile number input")
    public void enterInvalidMobileNo() {

        login.navigateToURL(Constants.flipkartLoginURL);
        login.insertValue(Constants.invalidMobileNumber);
        login.clickButton(LoginPageLocators.requestOTPButton);
        String actualValue = login.getText(LoginPageLocators.invalidMobileNumberError);
        System.out.println("Actual fetched value of error: "+actualValue);
        String expectedValue = Constants.invalidMobileNumberError;
        Assert.assertEquals(actualValue,expectedValue,"Error message displayed is NOT correct");
    }

    @Test(priority = 6,description = "LGN_009: Validate error message for invalid email ID input")
    public void enterInvalidEmailID() {

        login.navigateToURL(Constants.flipkartLoginURL);
        login.insertValue(Constants.invalidEmail);
        login.clickButton(LoginPageLocators.requestOTPButton);
        String actualPopUpText = login.getPopUpText(LoginPageLocators.invalidEmailIDPopUpText);
        System.out.println("Actual fetched value of POP UP text: "+actualPopUpText);
        String expectedPopUpText = Constants.invalidEmailIDAlertText;
        Assert.assertEquals(actualPopUpText,expectedPopUpText,"POP UP error message displayed is NOT correct");
    }

    //==========new user account testcases priority=4=========================================

    @Test(priority = 3,description = "LGN_005: Verify redirection to 'Create Account' page")
    public void verifyCreateAccountLinkRedirection(){
        /*
        click create accoun button
        wait until enter mobile number xpath is visible
        if login text viible visible assert true
         */
        login.clickButton(LoginPageLocators.createAccountLink);
        login.waitUntilFieldIsVisible(LoginPageLocators.newUserEnterMobileNumber);
        String fetchedText = login.getText(LoginPageLocators.newUserPageLoginText);
        System.out.println("Fetched text: "+fetchedText);
        String expectedText = Constants.createAccountPageLoginText;
        System.out.println("Expected text: "+expectedText);
        Assert.assertEquals(fetchedText,expectedText,"New User creation page is NOT opened");

    }

    }
