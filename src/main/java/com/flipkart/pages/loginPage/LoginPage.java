package com.flipkart.pages.loginPage;

import com.flipkart.util.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver; // Use a local driver for this class

    public LoginPage(WebDriver driver) {
        this.driver = driver; // Assign the driver to this class
    }

    // Method to open the Flipkart website
    public String openFlipkart() {
        String actualTitle = driver.getTitle();
        return actualTitle;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    // Method to click on the login button
    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(LoginPageLocators.loginButton));
        loginBtn.click();
    }

    // Method to click on enter mobile number field and add valid data
    public void addValidMobileNumber() {
        WebElement addMobile = driver.findElement(LoginPageLocators.enterEmailOrMobile);
        addMobile.sendKeys(Constants.validMobileNumber);
    }
}
