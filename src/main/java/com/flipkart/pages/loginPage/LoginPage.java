package com.flipkart.pages.loginPage;

import com.flipkart.util.Constants;
import com.flipkart.util.EmailUtils;
import com.flipkart.util.WaitUtils;
import org.openqa.selenium.*;

import java.util.Set;


public class LoginPage {

    //local driver
    private WebDriver driver;
    private WaitUtils waitUtils;

    //assign driver to call from test class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    //=======================testcase specific methods================================

    //testcase specific methods

    //this will enter otp in the field
    public void enterOtp(By locator, String otp) {
        driver.findElement(locator).sendKeys(otp);
    }

    //this method will fetch otp from email
    public String fetchOtpFromEmail() {
        String host = "imap.gmail.com"; // For Gmail
        String storeType = "imap";
        String user = Constants.validEmail; // Your email
        String password = Constants.emailPassword; // Your email password

        // Fetch OTP using EmailUtils (Utility class for fetching OTP from email)
        return EmailUtils.fetchOtpFromEmail(host, storeType, user, password);
    }
}
