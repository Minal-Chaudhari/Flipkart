package com.flipkart.util;

import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ActionUtils {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ActionUtils.class);
    private WebDriver driver;
    private WaitUtils waitUtils;
    private Actions actions;
    private Logger logger;
    private Properties properties;

    //assign driver to call from test class
    public ActionUtils(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.actions = new Actions(driver);
        this.logger = logger;

    }

    //=====================================common methods============================================

    //get and check the url linked with hyperlink (actual and expected)
    public boolean checkHyperlink(By linkLocator, String expectedUrl) {
        try {

            waitUtils.waitForElementToBeVisible(linkLocator,10);
            WebElement link = driver.findElement(linkLocator);

            //get attribute will fetch "href(link)"
            String actualUrl = link.getAttribute("href");

            logger.info("Fetched URL: {}", actualUrl);

            //checking if actual url is fetched
            assert actualUrl != null;
            return actualUrl.equals(expectedUrl);

        } catch (NoSuchElementException e) {
            logger.info("Element not found: {}", linkLocator);
            return false;
        }
    }

    //navigate to URL
    public void navigateToURL(String URL){
        logger.info("Navigating to URL: {}",URL);
        driver.get(URL);

    }

    //waits until locator is visible
    public void waitUntilFieldIsVisible(By locator){
        logger.info("Waiting until Element is visible...");
        waitUtils.waitForElementToBeVisible(locator,10);
    }

    //gets text
    public String getText(By locator){
        WebElement element = driver.findElement(locator);
        logger.info("Fetching text...");
        return element.getText();
    }

    //gets title of page
    public String getTitle() {
        logger.info("Fetching title of page...");
        return driver.getTitle();
    }

    //checks if any button is displayed
    public boolean isButtonDisplayed(By buttonXpath) {
        WebElement button = driver.findElement(buttonXpath);
        logger.info("Checking button display...");
        return button.isDisplayed();
    }

    //click button
    public void clickButton(By locator) {
        logger.info("Clicking button");
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
                logger.info("Switched to new window");
                break;
            }
        }
        logger.info("Waiting for element to be visible...");
        waitUtils.waitForElementToBeVisible(textToWaitBeforeTitleFetch,10);
        return getTitle(); //using the same method from this class
    }

    //this method will switch bask to main flipkart tab
    public void switchBackToMainTab() {
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        logger.info("Switched back to main frame");
    }

    //locate element and insert value
    public void insertValue(String value) {
        logger.info("Inserted value: {}", value);
        driver.switchTo().activeElement().sendKeys(value);
    }

    //for valid emailID and pass (since they are sensitive)
    public void insertSensitiveValue(String value)
    {
        logger.info("Inserted sensitive value", value);
        driver.switchTo().activeElement().sendKeys(value);
    }

    //get popup test
    public String getPopUpText(By locator) {
        String popUpText = null;
        waitUtils.waitForElementToBeVisible(locator, 10);

        try {
            WebElement popUp = driver.findElement(locator);
            popUpText = popUp.getText();
            logger.info("Fetched PopUp text: {}",popUpText);
        } catch (NoSuchElementException e) {
            logger.error("Popup element not found: {}" , e.getMessage());
        } catch (StaleElementReferenceException e) {
            logger.error("Popup element is stale: {}" , e.getMessage());
        }

        return popUpText;
    }

    //method will hover over element
    public void hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
        logger.info("Hovering over element...");
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
                logger.info("Loaded property file: {}",fileName);
                input.close();
            } catch (IOException e) {
                logger.error("Property file loading fails: {}",fileName);
                e.printStackTrace();
            }
        }
        return properties.getProperty(key);
    }

    //method will store cookies to file
    public void storeCookies(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Set<Cookie> cookies = driver.manage().getCookies();
            for (Cookie cookie : cookies) {
                StringBuilder sb = new StringBuilder();
                sb.append(cookie.getName()).append(";")
                        .append(cookie.getValue()).append(";")
                        .append(cookie.getDomain()).append(";")
                        .append(cookie.getPath()).append(";")
                        .append(cookie.getExpiry()).append(";")
                        .append(cookie.isSecure() ? "true" : "false");
                writer.write(sb.toString());
                writer.newLine();
            }
            logger.info("Cookies stored to file: {}" , fileName);
        } catch (IOException e) {
            logger.info("Error storing cookies: {}" , e.getMessage());
        }
    }

    //method will load cookies from file
    public void loadCookies(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cookieValues = line.split(";");
                Cookie cookie = new Cookie(cookieValues[0], cookieValues[1], cookieValues[2],
                        cookieValues[3], cookieValues[4].isEmpty() ? null : new java.util.Date(cookieValues[4]),
                        Boolean.parseBoolean(cookieValues[5]));
                driver.manage().addCookie(cookie);
            }
            logger.info("Cookies loaded from file: {}" , fileName);
        } catch (IOException e) {
            logger.info("Error loading cookies: {}" , e.getMessage());
        }
    }

}
