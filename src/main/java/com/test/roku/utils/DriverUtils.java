package com.test.roku.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.util.Arrays;

import static com.test.roku.utils.ConfigUtils.*;

public class DriverUtils {
    WebDriver driver;
    AndroidDriver androidDriver;

    protected static final int PORT = 4723;
    private static AppiumDriverLocalService service;
    String deviceType;
    String deviceOSName;
    String browserName;

    public void initDriver() {
        //Load properties files
        loadProperties();
        //set browser name e.g. Chrome, Firefox or Safari
        setBrowserName();
        //set device type e.g. Mobile or Desktop
        setDeviceType();
        //set device OS name e.g. Android, iOS, Windows, macOS etc
        setDeviceOsName();
        //Initialise driver
        initializeDriver();
        // Window resolution setup for various viewports e.g. MobileViewportOnDesktop & TabletViewportOnDesktop.
        // or Desktop
        setWindowSize();
        // Launch URL
        launchUrl();
    }

    public WebDriver getDriver() {
        if (driver == null && androidDriver == null) {
            initDriver();
        }
        if(deviceType.equalsIgnoreCase("Mobile")) {
            return androidDriver;
        } else {
            return driver;
        }
    }

    public void tearDown() {
        if (driver != null) driver.quit();
        driver = null;

        if(service != null) {
            service.stop();
        }
    }

    public void setMobileDriverFor(String deviceOSName) throws IOException {
        if (deviceOSName.equals("Android")) {
            UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                    .setDeviceName("2A211FDH300FY5")
                    .setPlatformName("ANDROID")
                    .setPlatformVersion("16")
                    .setAutomationName("UiAutomator2")
                    .setApp("/app/Saucelabs_My_Demo.apk");
//                    .withBrowserName("Chrome");
//                    .setAppPackage(getPropertyByKey("primeVideoAppPackage"))
//                    .setAppActivity(getPropertyByKey("primeVideoAppMainActivity"));
            try {
                androidDriver = new AndroidDriver(service, uiAutomator2Options);
            } catch (org.openqa.selenium.SessionNotCreatedException e) {
                e.printStackTrace();
                service.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (deviceOSName.equals("iOS")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "ios");
            caps.setCapability("platformVersion", "14");
            caps.setCapability("deviceName", "Google Pixel 7 Pro");
            caps.setCapability("browserName", "Chrome");
//            caps.setCapability("deviceName", "Pixel_6_Pro_VD");
            caps.setCapability("automationName", "UiAutomator2");
            try {
                androidDriver = new AndroidDriver(service, caps);
            } catch (org.openqa.selenium.SessionNotCreatedException e) {
                System.out.println(e.getMessage());
                service.stop();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private void setDeviceType() {
        if ((System.getProperty("deviceType") != null)) {
            deviceType = System.getProperty("deviceType");
        } else {
            deviceType = getPropertyByKey("deviceType");
        }
    }

    private void setBrowserName() {
        if ((System.getProperty("browserName") != null)) {
            browserName = System.getProperty("browserName");
        } else {
            browserName = getPropertyByKey("browserName");
        }
    }

    private void setDeviceOsName() {
        if ((System.getProperty("deviceOS") != null)) {
            deviceOSName = System.getProperty("deviceOS");
        } else {
            deviceOSName = getPropertyByKey("deviceOS");
        }
    }

    private void initializeDriver() {
        if(Arrays.asList(new String[]{"Desktop", "TabletViewportOnDesktop", "MobileViewportOnDesktop"}).contains(deviceType)) {
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
        } else {
            service = new AppiumServiceBuilder()
                    .withIPAddress("127.0.0.1")
                    .usingPort(PORT)
                    .build();
            service.start();
            try {
                setMobileDriverFor(deviceOSName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setWindowSize() {
        if(Arrays.asList(new String[]{"Desktop", "TabletViewportOnDesktop", "MobileViewportOnDesktop"}).contains(deviceType)) {
            switch (deviceType) {
                case "MobileViewportOnDesktop": {
                    driver.manage().window().setSize(new Dimension(375, 667));
                    break;
                }
                case "TabletViewportOnDesktop": {
                    driver.manage().window().setSize(new Dimension(768, 1024));
                    break;
                }
                default: {
                    System.out.println("MAXIMISE THE WINDOW FOR DESKTOP");
                    driver.manage().window().maximize();
                    break;
                }
            }
        }
    }

    private void launchUrl() {
        if(deviceType.equals("Mobile")) {
            androidDriver.get(getPropertyByKey("primeVideoAppPackage")+"/"+getPropertyByKey("primeVideoAppMainActivity"));
        } else {
            driver.get(getPropertyByKey("contactUsPage.URL"));
        }
    }
}
