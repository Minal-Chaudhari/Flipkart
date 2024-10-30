package com.flipkart.base;

import com.flipkart.util.ActionUtils;
import com.flipkart.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//0-89import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeClass;

public class BaseClass {

    protected WebDriver driver; // encapsulation
    public Logger logger;
    protected ActionUtils action;

    @BeforeClass(groups = {"sanity","smoke","regression","allTestSuite"})
    public void setUp() {

        logger= LogManager.getLogger(this.getClass());
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
        }else {
            logger.info("Flipkart website opened successfully");
        }
        action = new ActionUtils(driver);
    }



/*
    @AfterClass(groups = {"sanity","smoke","regression","allTestSuite"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver tear down complete ...");
        }
    }

 */




    //here screenshot method is present
    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }

    //static block for timestamp
    static {
        //seetting up the log file with a timestamp
        String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String logFileName = "./log/FlipkartTestExecution-" + timestamp + ".log";

        //dynamically update the log configuration
        LoggerContext context = (LoggerContext) org.apache.logging.log4j.LogManager.getContext(false);
        Configuration config = context.getConfiguration();

        //create a new file appender with the TIMESTAMPED log file
        FileAppender appender = FileAppender.newBuilder()
                .setName("FileLogger")
                .withFileName(logFileName)
                .withLayout(PatternLayout.newBuilder().withPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n").build())
                .build();

        appender.start();
        config.addAppender(appender);

        AppenderRef ref = AppenderRef.createAppenderRef("FileLogger", null, null);
        LoggerConfig loggerConfig = config.getRootLogger();
        loggerConfig.addAppender(appender, null, null);

        context.updateLoggers();
    }

}
