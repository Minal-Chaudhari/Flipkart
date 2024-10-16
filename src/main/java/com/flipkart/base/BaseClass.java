package com.flipkart.base;

import com.flipkart.util.Constants;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

    protected WebDriver driver; // encapsulation
    //trying logger here
    protected static final Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeClass
    public void setUp() {
        logger.info("Setting up driver ...");
        System.setProperty("webdriver.chrome.driver", "C:\\Intellij Projects\\Flipkart\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Driver setup complete ...");
        driver.get(Constants.FLIPKART_URL);

        //adding assert to check if website is really opened
        String expectedTitle = Constants.flipkartHomePageTitle;
        String actualTitle = driver.getTitle();
        if (!actualTitle.contains(expectedTitle)) {
            logger.error("Flipkart website is not opened");
            throw new IllegalStateException("Flipkart is not opened, expected title: "
                    + expectedTitle + ", but got: " + actualTitle);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
