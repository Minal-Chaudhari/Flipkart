package com.flipkart.base;

import com.flipkart.util.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass {

    protected WebDriver driver; // encapsulation
    //trying logger here
    protected static final Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeClass(groups = {"sanity"})
    public void setUp() {
        logger.info("Setting up driver ...");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe");
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


    @AfterClass(groups = {"sanity"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    //return driver here
    public WebDriver getDriver() {
        return driver;
    }

    //this method will capture screenshot of failed testcases
    public String captureScreen(String testCaseName) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + testCaseName + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }

}
