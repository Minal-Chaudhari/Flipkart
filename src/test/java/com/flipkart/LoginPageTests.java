package com.flipkart;

import com.flipkart.base.BaseClass;
import com.flipkart.pages.homePage.HomePageLocators;
import com.flipkart.pages.loginPage.LoginPage;
import com.flipkart.pages.loginPage.LoginPageLocators;
import com.flipkart.util.Constants;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

//@Test(groups = {"AllLoginPageTests"}) //this will add the group on class level
public class LoginPageTests extends BaseClass {

    LoginPage login ;

    @BeforeClass(groups = {"sanity","smoke","regression","allTestSuite"})
    public void initializeLoginPage() {
        if (driver == null) {
            setUp();
        }
        login = new LoginPage(driver);
        logger.info("Login Page is Initialised");
    }

    @Test(priority = 1,description = "LGN_001: Validate the login button presence")
    public void verifyLoginButtonIsDisplayed() {

        logger.info("Test Start: LGN_001: Validate the login button presence");
        Assert.assertTrue(action.isButtonDisplayed(LoginPageLocators.loginButton), "Login button is NOT displayed on the homepage.");
        logger.info("login button is displayed");
        action.clickButton(LoginPageLocators.loginButton);
        logger.info("Login Button is clicked");
        logger.info("Test End: LGN_001: Validate the login button presence");
    }

    @Test(priority = 1,description = "LGN_002: Verify the presence of the email/mobile number field")
    public void verifyPresenceOfEmailIdAndMobileNoField(){

        logger.info("Test Start: LGN_002: Verify the presence of the email/mobile number field");
        action.navigateToURL(Constants.flipkartLoginURL);
        Assert.assertTrue(action.isButtonDisplayed(LoginPageLocators.enterEmailOrMobile), "Enter Email Or Mobile Number button is NOT displayed on the homepage.");
        logger.info("Test End: LGN_002: Verify the presence of the email/mobile number field");
    }

    @Test(priority = 3,description = "LGN_006: Validate terms of use link is clickable")
    public void verifyTermsLinkIsClickable(){

        logger.info("Test Start: LGN_006: Validate terms of use link is clickable");
        action.navigateToURL(Constants.flipkartLoginURL);
        boolean link = action.checkHyperlink(LoginPageLocators.termsOfUseLink,Constants.expectedTermsUrl);
        logger.info("Expected URL: {}",Constants.expectedTermsUrl);
        Assert.assertTrue(link,"Terms of use link is NOT clickable");
        logger.info("Test End: LGN_006: Validate terms of use link is clickable");
    }

    @Test(priority = 3,description = "LGN_011: Validate privacy policy link is clickable")
    public void verifyPrivacyLinkIsClickable(){

        logger.info("Test Start: LGN_011: Validate privacy policy link is clickable");
        action.navigateToURL(Constants.flipkartLoginURL);
        boolean link = action.checkHyperlink(LoginPageLocators.privacyPolicyLink,Constants.expectedPrivacyUrl);
        logger.info("Expected URL: {}",Constants.expectedPrivacyUrl);
        Assert.assertTrue(link,"Privacy Policy link is NOT clickable");
        logger.info("Test End: LGN_011: Validate privacy policy link is clickable");
    }

    @Test(priority = 3,description = "LGN_004: Validate 'Create Account' link  is clickable")
    public void verifyCreateAccountLinkIsClickable(){

        logger.info("Test Start: LGN_004: Validate 'Create Account' link  is clickable");
        action.navigateToURL(Constants.flipkartLoginURL);
        boolean link = action.checkHyperlink(LoginPageLocators.createAccountLink,Constants.expectedCreateAccountUrl);
        logger.info("Expected URL: {}",Constants.expectedCreateAccountUrl);
        Assert.assertTrue(link,"'New to Flipkart? Create an account' Link is NOT clickable");
        logger.info("Test End: LGN_004: Validate 'Create Account' link  is clickable");
    }

    @Test(priority = 3,description = "LGN_007: Verify redirection to 'Terms of Use' page")
    public void verifyTermsRedirection() {

        logger.info("Test Start: LGN_007: Verify redirection to 'Terms of Use' page");
        action.navigateToURL(Constants.flipkartLoginURL);
        action.clickButton(LoginPageLocators.termsOfUseLink);
        action.switchToNewTab();
        action.waitForPageToLoad();
        //action.waitUntilFieldIsVisible(LoginPageLocators.termsOfUseText);
        String newTabTitle = action.getTitle();
        logger.info("New tab fetched title is: {}", newTabTitle);
        logger.info("New tab expected title is: {}",Constants.termsPageTitle);
        Assert.assertTrue(newTabTitle.contains(Constants.termsPageTitle), "New tab is NOT Terms Of Use page.");
        //Thread.sleep(2000);
        action.switchBackToMainTab();
        logger.info("Test End: LGN_007: Verify redirection to 'Terms of Use' page");
    }

    @Test(priority = 3,description = "LGN_012: Verify redirection to 'Privacy Policy' page")
    public void verifyPrivacyRedirection(){

        logger.info("Test Start: LGN_012: Verify redirection to 'Privacy Policy' page");
        action.navigateToURL(Constants.flipkartLoginURL);
        action.clickButton(LoginPageLocators.privacyPolicyLink);
        action.switchToNewTab();
        action.waitForPageToLoad();
        String newTabTitle = action.getTitle();
        logger.info("New tab fetched title is: {}", newTabTitle);
        logger.info("New tab expected title is: {}",Constants.privacyPageTitle);
        Assert.assertTrue(newTabTitle.contains(Constants.privacyPageTitle), "New tab is NOT Terms Of Use page.");
        action.switchBackToMainTab();
        logger.info("Test End: LGN_012: Verify redirection to 'Privacy Policy' page");

    }

    //=============login with data testcases==================================

    @Test(priority = 2,description = "LGN_008: Validate error message for invalid mobile number input", groups = {"sanity"})
    public void verifyInvalidMobileNo() {

        logger.info("Test Start: LGN_008: Validate error message for invalid mobile number input");
        action.navigateToURL(Constants.flipkartLoginURL);
        action.insertValue(Constants.invalidMobileNumber);
        action.clickButton(LoginPageLocators.requestOTPButton);
        String actualValue = action.getText(LoginPageLocators.invalidMobileNumberError);
        logger.info("Actual fetched value of error: {}", actualValue);
        String expectedValue = Constants.invalidMobileNumberError;
        logger.info("Expected Value: {}" ,expectedValue);
        Assert.assertEquals(actualValue,expectedValue,"Error message displayed is NOT correct");
        logger.info("Test End: LGN_008: Validate error message for invalid mobile number input");
    }

    @Test(priority = 2,description = "LGN_009: Validate error message for invalid email ID input",groups = "sanity")
    public void verifyInvalidEmailID() {

        logger.info("Test Start: LGN_009: Validate error message for invalid email ID input");
        action.navigateToURL(Constants.flipkartLoginURL);
        action.insertValue(Constants.invalidEmail);
        action.clickButton(LoginPageLocators.requestOTPButton);
        String actualPopUpText;
        actualPopUpText = action.getPopUpText(LoginPageLocators.invalidEmailIDPopUpText);
        logger.info("Actual fetched value of POP UP text: {}", actualPopUpText);
        String expectedPopUpText = Constants.invalidEmailIDPopUpText;
        logger.info("Expected PopUp Text: {}", expectedPopUpText);
        Assert.assertEquals(actualPopUpText,expectedPopUpText,"POP UP error message displayed is NOT correct");
        logger.info("Test End: LGN_009: Validate error message for invalid email ID input");
    }

    //==========new user account testcases =========================================

    @Test(priority = 3,description = "LGN_005: Verify redirection to 'Create Account' page")
    public void verifyCreateAccountLinkRedirection(){

        logger.info("Test Start: LGN_005: Verify redirection to 'Create Account' page");
        action.navigateToURL(Constants.flipkartLoginURL);
        action.clickButton(LoginPageLocators.createAccountLink);
        action.waitUntilFieldIsVisible(LoginPageLocators.newUserEnterMobileNumber);
        String fetchedText = action.getText(LoginPageLocators.newUserPageLoginText);
        logger.info("Fetched text: {}", fetchedText);
        String expectedText = Constants.createAccountPageLoginText;
        logger.info("Expected text: {}", expectedText);
        Assert.assertEquals(fetchedText,expectedText,"New User creation page is NOT opened");
        logger.info("Test End: LGN_005: Verify redirection to 'Create Account' page");

    }

    @Test(priority = 1,description = "LGN_013: Validate valid email login")
    public void verifyValidEmailLogin() throws InterruptedException {

        logger.info("Test Start: LGN_013: Validate valid email login");
        action.navigateToURL(Constants.flipkartLoginURL);
        action.insertSensitiveValue(Constants.validEmail);
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
        login.storeCookies();
        //logger.info("Cookies stored successfully");
        logger.info("Test End: LGN_013: Validate valid email login");
    }


    @Test(priority = 1,description = "LGN_018: Validate valid mobile number login")
    public void verifyValidMobileNoLogin() throws InterruptedException {

        logger.info("Test Start: LGN_018: Validate valid mobile number login");
        action.navigateToURL(Constants.flipkartLoginURL);
        action.insertSensitiveValue(Constants.validMobileNumber);
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
        login.storeCookies();
        //logger.info("Cookies stored successfully");
        logger.info("Test End: LGN_018: Validate valid mobile number login");

    }

    //test will check if the set cookies are working
    @Test(priority = 4, description = "LGN_014: Validate login using cookies")
    public void verifyLoginWithCookies() {
    /*
    Load cookies
    Navigate to Flipkart
    Check if logout button is displayed to verify login
    */
        logger.info("Test Start: LGN_019: Validate login using cookies");
        login.loadCookies();
        action.navigateToURL(Constants.FLIPKART_URL);
        action.hoverOverElement(HomePageLocators.accountButton);
        action.waitUntilFieldIsVisible(HomePageLocators.logoutButton);
        boolean isLogoutButtonDisplayed = action.isButtonDisplayed(HomePageLocators.logoutButton);
        Assert.assertTrue(isLogoutButtonDisplayed, "Login is successful using cookies");
        logger.info("Cookies loaded, and login verified via logout button visibility.");
        logger.info("Test End: LGN_019: Validate login using cookies");
    }




}
