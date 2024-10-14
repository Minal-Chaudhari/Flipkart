package com.flipkart.base;

import com.flipkart.util.Constants;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Objects;

public class BaseClass {

    protected WebDriver driver; // encapsulation
    //trying logger here
    protected static final Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Intellij Projects\\Flipkart\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Constants.FLIPKART_URL);

        //adding assert to check if website is really opened
        String expectedTitle = Constants.flipkartHomePageTitle;
        String actualTitle = driver.getTitle();
        if (!actualTitle.contains(expectedTitle)) {
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
