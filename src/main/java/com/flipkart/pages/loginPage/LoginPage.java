package com.flipkart.pages.loginPage;

import com.flipkart.util.Constants;
import com.flipkart.util.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LoginPage {

    //local driver
    private WebDriver driver;
    private WaitUtils waitUtils;

    //assign driver to call from test class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);

    }

    //common methods
    //checks if link id clickable

    //get and check the url linked with hyperlink (actual and expected)
    public boolean checkHyperlink(By linkLocator, String expectedUrl) {
        try {

            waitUtils.waitForElementToBeVisible(linkLocator,10);
            WebElement link = driver.findElement(linkLocator);

            //get attribute will fetch "href(link)"
            String actualUrl = link.getAttribute("href");
            System.out.println("Fetched URL: " + actualUrl);

            assert actualUrl != null;
            return actualUrl.equals(expectedUrl);

        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + linkLocator);
            return false;
        }
    }


    //gets title of page
    public String getTitle() {
        return driver.getTitle();
    }

    //checks if any button is displayed
    public boolean isButtonDisplayed(By buttonXpath) {
        WebElement button = driver.findElement(buttonXpath);
        return button.isDisplayed();
    }

    //click button
    public void clickButton(By locator) {
        driver.findElement(locator).click();
    }

    //testcase specific methods
    //method will add mobile number
    public void addMobileNumber(String mobileNumber) {
        WebElement addMobile = driver.findElement(LoginPageLocators.enterEmailOrMobile);
        addMobile.sendKeys(mobileNumber);
    }

    //method will add emailID
    public void addEmailID(String emailID){
        WebElement addEmail = driver.findElement(LoginPageLocators.enterEmailOrMobile);
        addEmail.sendKeys(emailID);
    }



}
