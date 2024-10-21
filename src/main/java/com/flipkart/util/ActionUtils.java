package com.flipkart.util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ActionUtils {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private Actions actions;
    private Properties properties;

    //assign driver to call from test class
    public ActionUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.actions = new Actions(driver);
    }

    //=====================================common methods============================================

    //get and check the url linked with hyperlink (actual and expected)
    public boolean checkHyperlink(By linkLocator, String expectedUrl) {
        try {

            waitUtils.waitForElementToBeVisible(linkLocator,10);
            WebElement link = driver.findElement(linkLocator);

            //get attribute will fetch "href(link)"
            String actualUrl = link.getAttribute("href");
            System.out.println("Fetched URL: " + actualUrl);

            //checking if actual url is fetched
            assert actualUrl != null;
            return actualUrl.equals(expectedUrl);

        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + linkLocator);
            return false;
        }
    }

    //navigate to URL
    public void navigateToURL(String URL){
        driver.get(URL);
    }

    //waits until locator is visible
    public void waitUntilFieldIsVisible(By locator){
        waitUtils.waitForElementToBeVisible(locator,10);
    }

    //gets text
    public String getText(By locator){
        WebElement element = driver.findElement(locator);
        return element.getText();
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

    //method will switch to new open tab and get nits title
    public String switchToNewTabAndGetTitle(By textToWaitBeforeTitleFetch) {

        //current window
        String mainWindowHandle = driver.getWindowHandle();
        //all windows
        Set<String> allWindowHandles = driver.getWindowHandles();

        //switching to the new tab opened by user
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle); //here new tab is switched
                break;
            }
        }
        waitUtils.waitForElementToBeVisible(textToWaitBeforeTitleFetch,10);
        return getTitle(); //using the same method from this class
    }

    //this method will switch bask to main flipkart tab
    public void switchBackToMainTab() {
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }

    //locate element and insert value
    public void insertValue(String value){
        driver.switchTo().activeElement().sendKeys(value);
    }

    //get popup test
    public String getPopUpText(By locator) {
        String popUpText = null;
        waitUtils.waitForElementToBeVisible(locator, 10);

        try {
            WebElement popUp = driver.findElement(locator);
            popUpText = popUp.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Popup element not found: " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            System.out.println("Popup element is stale: " + e.getMessage());
        }

        return popUpText;
    }

    //method will hover over element
    public void hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
    }


    //INCOMPLETE
    //method will fetch all the values in a list using locator (return list)
    public List<String> fetchAllTextValuesUsingLocator(By locator){
        List<String> allFetchedValues = new ArrayList<>();

        return allFetchedValues;
    }

    //method to load property file
    public String getPropertyValue(String key, String fileName) {
        if (properties == null) {
            properties = new Properties();
            try {
                FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"\\propertyFiles\\"+fileName+".properties");
                properties.load(input);
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(key);
    }

}
