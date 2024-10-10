package com.flipkart.tests;

import com.flipkart.base.BaseClass;
import com.flipkart.pages.loginPage.LoginPage;
import com.flipkart.pages.loginPage.LoginPageLocators;
import com.flipkart.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseClass {

    LoginPage login;

    @BeforeClass
    public void initializeLoginPage() {
        login = new LoginPage(driver);
    }

    @Test(priority = 1,description = "LGN_010: Validate that flipkart is opened")
    public void verifyTitle() {
        String actualTitle = login.getTitle();
        System.out.println("Actual Title: " + actualTitle);
        String expectedTitle = Constants.flipkartHomePageTitle;
        System.out.println("Expected Title: " + expectedTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Website title did not match!");
    }

    @Test(priority = 2,description = "LGN_001: Validate the login button presence")
    public void verifyLoginButtonIsDisplayed() {
        Assert.assertTrue(login.isButtonDisplayed(LoginPageLocators.loginButton), "Login button is not displayed on the homepage.");
        System.out.println("login button is displayed");

        //click login button
        login.clickButton(LoginPageLocators.loginButton);
        System.out.println("Login Button is clicked");
    }

    @Test(priority = 3,description = "LGN_006: Validate terms of use link is clickable")
    public void verifyTermsLinkIsClickable(){
        boolean link = login.checkHyperlink(LoginPageLocators.termsOfUseLink,Constants.expectedTermsUrl);
        Assert.assertTrue(link,"Terms of use link is not clickable");
    }

    @Test(priority = 3,description = "LGN_011: Validate privacy policy link is clickable")
    public void verifyPrivacyLinkIsClickable(){
        boolean link = login.checkHyperlink(LoginPageLocators.privacyPolicyLink,Constants.expectedPrivacyUrl);
        Assert.assertTrue(link,"Privacy Policy link is not clickable");
    }

    @Test(priority = 3,description = "LGN_004: Validate 'Create Account' link  is clickable")
    public void verifyCreateAccountLinkIsClickable(){
        boolean link = login.checkHyperlink(LoginPageLocators.createAccountLink,Constants.expectedCreateAccountUrl);
        Assert.assertTrue(link,"'New to Flipkart? Create an account' Link is not clickable");
    }

    //NOT COMPLETE
    @Test(priority = 3,description = "LGN_005: Verify redirection to 'Create Account' page")
    public void verifyCreateAccountRedirection(){
        login.clickButton(LoginPageLocators.createAccountLink);

    }

    //NOT COMPLETE
    @Test(priority = 3,description = "LGN_007: Verify redirection to 'Terms of Use' page")
    public void verifyTermsRedirection(){
        login.clickButton(LoginPageLocators.termsOfUseLink);
        //String expectedTitle = Constants.;
        String actualTitle = login.getTitle();



    }

    //NOT COMPLETE
    @Test(priority = 3,description = "LGN_012: Verify redirection to 'Privacy Policy' page")
    public void verifyPrivacyRedirection(){

    }


    }
