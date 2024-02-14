package com.test.roku.utils;

import com.test.roku.pages.ContactUsPage;
import com.test.roku.steps.ContactUsPageAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import static com.test.roku.utils.ConfigUtils.*;

public class DriverUtils {
    WebDriver driver;

    public void initDriver() {
        loadProperties();
        String deviceType;
        String browserName;


        if ((System.getProperty("browserName") != null)) {
            browserName = System.getProperty("browserName");
        } else {
            browserName = getPropertyByKey("browserName");
        }

        if ((System.getProperty("deviceType") != null)) {
            deviceType = System.getProperty("deviceType");
        } else {
            deviceType = getPropertyByKey("deviceType");
        }

        switch (browserName) {
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        switch (deviceType) {
            case "Mobile":
                driver.manage().window().setSize(new Dimension(375, 667));
                break;
            case "Tablet":
                driver.manage().window().setSize(new Dimension(768, 1024));
                break;
            default:
                System.out.println("MAXIMISE THE WINDOW");

                driver.manage().window().maximize();
                break;
        }

        driver.get(getPropertyByKey("contactUsPage.URL"));
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void tearDown() {
        if (driver != null) driver.quit();
        driver = null;
    }
}
