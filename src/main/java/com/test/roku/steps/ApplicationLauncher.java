package com.test.roku.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

/**
 * @author Nitin Saini
 */
public class ApplicationLauncher {
    Hooks hooks = new Hooks();
    WebDriver driver = hooks.getWebDriver();

    @Given("^User launches the prime Video Application$")
    public void launchApplication() {
        System.out.println("launch app");
    }

    @Then("User should be displayed the apps home page")
    public void userShouldBeDisplayedTheAppsHomePage() {

    }
}