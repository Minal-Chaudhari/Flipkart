package com.flipkart.tests;

import com.flipkart.base.BaseClass;
import com.flipkart.pages.loginPage.LoginPage;
import com.flipkart.util.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseClass {

    LoginPage login;

    @BeforeMethod
    public void initializePage() {
        login = new LoginPage(driver);
    }

    @Test(description = "LGN_001: Validate the login button presence", groups = {"LoginTests", "Smoke"})
    public void verifyLoginButton() {
        String actualTitle = login.getTitle();
        System.out.println("Actual Title: " + actualTitle);
        String expectedTitle = Constants.flipkartHomePageTitle;
        System.out.println("Expected Title: " + expectedTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Title did not match!");
    }
}
