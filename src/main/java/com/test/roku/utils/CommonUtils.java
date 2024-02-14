package com.test.roku.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import com.test.roku.steps.Hooks;

public class CommonUtils {
    static Long timeout = Long.parseLong("30");
    static WebDriverWait wait = new WebDriverWait(new Hooks().getWebDriver(), Duration.ofSeconds(timeout));

    public static void clickOnElement(WebElement element) {
        waitForVisible(element);
        waitForElementToBeClickable(element);
        element.click();
    }
    public static void sendKeysTo(WebElement element, String text) {
        waitForVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public static void waitForVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitForNotVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitForElementToBeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
