package com.flipkart.pages.loginPage;

import com.flipkart.util.ActionUtils;
import com.flipkart.util.Constants;
import com.flipkart.util.EmailUtils;
import com.flipkart.util.WaitUtils;
//import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Set;


public class LoginPage {

    //local driver
    private WebDriver driver;
    private WaitUtils waitUtils;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LoginPage.class);

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

        //Fetch OTP using EmailUtils (Utility class for fetching OTP from email)
        return EmailUtils.fetchOtpFromEmail(host, storeType, user, password);
    }

    //store cookies
    public void storeCookies() {
        // Specify the file path
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\cookies.data");

        Set<Cookie> cookies = driver.manage().getCookies();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Cookie cookie : cookies) {
                writer.write(cookie.getName() + ";" + cookie.getValue() + ";" +
                        cookie.getDomain() + ";" + cookie.getPath() + ";" +
                        cookie.getExpiry() + ";" + cookie.isSecure());
                writer.newLine();
            }
            logger.info("Cookies stored successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load cookies
    public void loadCookies() {
        //file path
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\cookies.data");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //split the line using ";" as a delimiter
                String[] token = line.split(";");

                //extracting each part of the cookie
                String name = token[0];
                String value = token[1];
                String domain = token[2];
                String path = token[3];
                String expiry = token[4]; // Can be null
                boolean isSecure = Boolean.parseBoolean(token[5]);

                //creating a new Cookie object
                Cookie cookie = new Cookie.Builder(name, value)
                        .domain(domain)
                        .path(path)
                        .isSecure(isSecure)
                        .build();

                //adding cookie to the browser
                driver.manage().addCookie(cookie);
            }
            logger.info("Cookies loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //refresh the page to apply cookies
        driver.navigate().refresh();
    }


}
