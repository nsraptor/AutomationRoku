package com.test.roku.steps;

import java.io.File;
import java.util.Date;
import java.util.Objects;

import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.test.roku.utils.DriverUtils;
import org.openqa.selenium.WebDriver;


public class Hooks {
    private DriverUtils driverUtils;
    public static WebDriver driver;

    public WebDriver getWebDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        driverUtils = new DriverUtils();
        driver = driverUtils.getDriver();
    }

    @After
    public void teardown() {
        driverUtils.tearDown();
    }

    @BeforeAll
    public static void beforeAll() {
        deleteOldReport();
    }

    @AfterStep
    /**************************************************************************************************
    Function to capture screenshots of each step
    **************************************************************************************************/

    public void afterStep(Scenario scenario) {
        Date currentDate = new Date();
        File screenshot = ((TakesScreenshot) driverUtils.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
            scenario.attach(fileContent, "image/png", "image");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**************************************************************************************************
     Function to delete existing test execution report and screenshots
     **************************************************************************************************/
    public static void deleteOldReport() {

        String directory = "./test-output";
        File file = new File(directory);
        String[] currentFiles;
        if (file.isDirectory()) {
            currentFiles = file.list();
            for (int i = 0; i < Objects.requireNonNull(currentFiles).length; i++) {
                File myFile = new File(file, currentFiles[i]);
                System.out.println("DelFile 12345" + myFile);
                myFile.delete();
            }
        }
    }
}
