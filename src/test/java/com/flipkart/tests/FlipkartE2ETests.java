package com.flipkart.tests;

import com.flipkart.base.BaseClass;
import com.flipkart.pages.loginPage.LoginPage;
import com.flipkart.util.ActionUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartE2ETests extends BaseClass {

    LoginPage login;

    @BeforeClass (groups = {"sanity","smoke","regression","allTestSuite"})
    public void initializeLoginPage() {
        System.out.println("setting up driver");
        if (driver == null) {
            setUp();
        }
        login = new LoginPage(driver);
        System.out.println("driver setup complete");
    }

    @Test
    public void endToEndFlow()
    {
        /*
        login
        search for product
        select specific product
        click on buy now
        verify same product is being displayed
        click on continue
        handle popup (popup needs to be accepted)
        select radio button (UPI)
        select radio button (YOUR UPI ID)
        click on verify
        verify if upi id is verified
         */
        


    }
}
