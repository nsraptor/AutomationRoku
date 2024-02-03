package com.test.roku.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import static com.test.roku.utils.ConfigUtils.*;

/**
 * @author Nitin Saini
 */
public class DriverUtils {
    static WebDriver driver;

    public static void initDriver() {
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
                driver.manage().window().maximize();
                break;
        }
        driver.get(getPropertyByKey("contactUsPage.URL"));
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void tearDown() {
        driver.quit();
        driver = null;
    }
}
