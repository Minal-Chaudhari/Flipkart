package com.flipkart.tests;

import com.flipkart.base.BaseClass;
import com.flipkart.pages.homePage.HomePageLocators;
import com.flipkart.pages.loginPage.LoginPage;
import com.flipkart.pages.loginPage.LoginPageLocators;
import com.flipkart.util.ActionUtils;
import com.flipkart.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//@Test(groups = {"AllLoginPageTests"}) //this will add the group on class level
public class LoginPageTests extends BaseClass {

    ActionUtils action;
    LoginPage login;

    @BeforeClass
    public void initializeLoginPage() {
        System.out.println("setting up driver");
        if (driver == null) {
            setUp();
        }
        action = new ActionUtils(driver);
        login = new LoginPage(driver);
        System.out.println("driver setup complete");
    }

    @Test(priority = 1,description = "LGN_001: Validate the login button presence", groups = {"sanity"})
    public void verifyLoginButtonIsDisplayed() {
        Assert.assertTrue(action.isButtonDisplayed(LoginPageLocators.loginButton), "Login button is NOT displayed on the homepage.");
        System.out.println("login button is displayed");
        //click login button
        action.clickButton(LoginPageLocators.loginButton);
        System.out.println("Login Button is clicked");
    }

    @Test(priority = 2,description = "LGN_002: Verify the presence of the email/mobile number field")
    public void verifyPresenceOfEmailIdAndMobileNoField(){
        Assert.assertTrue(action.isButtonDisplayed(LoginPageLocators.enterEmailOrMobile), "Enter Email Or Mobile Number button is NOT displayed on the homepage.");
        System.out.println("Enter Email Or Mobile Number button is displayed");
    }

    @Test(priority = 2,description = "LGN_006: Validate terms of use link is clickable")
    public void verifyTermsLinkIsClickable(){
        boolean link = action.checkHyperlink(LoginPageLocators.termsOfUseLink,Constants.expectedTermsUrl);
        Assert.assertTrue(link,"Terms of use link is NOT clickable");
    }

    @Test(priority = 2,description = "LGN_011: Validate privacy policy link is clickable")
    public void verifyPrivacyLinkIsClickable(){
        boolean link = action.checkHyperlink(LoginPageLocators.privacyPolicyLink,Constants.expectedPrivacyUrl);
        Assert.assertTrue(link,"Privacy Policy link is NOT clickable");
    }

    @Test(priority = 2,description = "LGN_004: Validate 'Create Account' link  is clickable")
    public void verifyCreateAccountLinkIsClickable(){
        boolean link = action.checkHyperlink(LoginPageLocators.createAccountLink,Constants.expectedCreateAccountUrl);
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
        action.clickButton(LoginPageLocators.termsOfUseLink);
        String newTabTitle = action.switchToNewTabAndGetTitle(LoginPageLocators.termsOfUseText);
        System.out.println("New fetched tab title is: "+newTabTitle);
        Assert.assertTrue(newTabTitle.contains(Constants.termsPageTitle), "New tab is NOT Terms Of Use page.");
        //Thread.sleep(2000);
        action.switchBackToMainTab();
    }

    @Test(priority = 4,description = "LGN_012: Verify redirection to 'Privacy Policy' page")
    public void verifyPrivacyRedirection(){

        action.clickButton(LoginPageLocators.privacyPolicyLink);
        String newTabTitle = action.switchToNewTabAndGetTitle(LoginPageLocators.privacyPolicyText);
        System.out.println("New fetched tab title is: "+newTabTitle);
        Assert.assertTrue(newTabTitle.contains(Constants.privacyPageTitle), "New tab is NOT Terms Of Use page.");
        action.switchBackToMainTab();

    }

    //=============login with data testcases==================================

    @Test(priority = 5,description = "LGN_008: Validate error message for invalid mobile number input")
    public void verifyInvalidMobileNo() {

        action.navigateToURL(Constants.flipkartLoginURL);
        action.insertValue(Constants.invalidMobileNumber);
        action.clickButton(LoginPageLocators.requestOTPButton);
        String actualValue = action.getText(LoginPageLocators.invalidMobileNumberError);
        System.out.println("Actual fetched value of error: "+actualValue);
        String expectedValue = Constants.invalidMobileNumberError;
        Assert.assertEquals(actualValue,expectedValue,"Error message displayed is NOT correct");
    }

    @Test(priority = 6,description = "LGN_009: Validate error message for invalid email ID input")
    public void verifyInvalidEmailID() {

        action.navigateToURL(Constants.flipkartLoginURL);
        action.insertValue(Constants.invalidEmail);
        action.clickButton(LoginPageLocators.requestOTPButton);
        String actualPopUpText;
        actualPopUpText = action.getPopUpText(LoginPageLocators.invalidEmailIDPopUpText);
        System.out.println("Actual fetched value of POP UP text: "+actualPopUpText);
        String expectedPopUpText = Constants.invalidEmailIDPopUpText;
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
        action.clickButton(LoginPageLocators.createAccountLink);
        action.waitUntilFieldIsVisible(LoginPageLocators.newUserEnterMobileNumber);
        String fetchedText = action.getText(LoginPageLocators.newUserPageLoginText);
        System.out.println("Fetched text: "+fetchedText);
        String expectedText = Constants.createAccountPageLoginText;
        System.out.println("Expected text: "+expectedText);
        Assert.assertEquals(fetchedText,expectedText,"New User creation page is NOT opened");

    }

    //TO BE TESTED
    @Test(priority = 7,description = "LGN_013: Validate valid email login")
    public void verifyValidEmailLogin() throws InterruptedException {
        /*
        open url
        click on login button
        enter valid email id
        click on request otp
        get otp from mail
        click on verify
         */
        action.navigateToURL(Constants.flipkartLoginURL);
        action.insertValue(Constants.validEmail);
        action.clickButton(LoginPageLocators.requestOTPButton);
        Thread.sleep(20000);
        /*
        String fetchedOTP = login.fetchOtpFromEmail();
        System.out.println(fetchedOTP);
        Assert.assertNotNull(fetchedOTP);
         */
        action.clickButton(LoginPageLocators.verifyButton);
        boolean buttonIsPresent = action.isButtonDisplayed(HomePageLocators.logoutButton);
        Assert.assertTrue(buttonIsPresent,"Login is successful");
    }


    @Test(priority = 7,description = "LGN_018: Validate valid mobile number login")
    public void verifyValidMobileNoLogin() throws InterruptedException {
        /*
        open url
        click on login button
        enter valid mobile no
        click on request otp
        add otp
        click on verify
         */
        action.navigateToURL(Constants.flipkartLoginURL);
        action.insertValue(Constants.validMobileNumber);
        action.clickButton(LoginPageLocators.requestOTPButton);
        Thread.sleep(20000);
        /*
        String fetchedOTP = login.fetchOtpFromEmail();
        System.out.println(fetchedOTP);
        Assert.assertNotNull(fetchedOTP);
         */
        //action.clickButton(LoginPageLocators.verifyButton);
        action.hoverOverElement(HomePageLocators.accountButton);
        boolean buttonIsPresent = action.isButtonDisplayed(HomePageLocators.logoutButton);
        Assert.assertTrue(buttonIsPresent,"Login is successful");
    }

    }
