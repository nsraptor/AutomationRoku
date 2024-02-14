package com.test.roku.pages;

import com.test.roku.steps.Hooks;
import com.test.roku.utils.DriverUtils;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;

import static com.test.roku.utils.CommonUtils.clickOnElement;
import static com.test.roku.utils.CommonUtils.sendKeysTo;
import static com.test.roku.utils.CommonUtils.waitForNotVisible;

@Getter
public class ContactUsPage {
    WebDriver driver = new Hooks().getWebDriver();
    public ContactUsPage(WebDriver driver) {
        System.out.println("WEBELEMENTS INITIALISED!!!!");
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".fa-id-card")
    private WebElement nameIdCardIcon;
    @FindBy(xpath = "//input[@id='email']/preceding-sibling::div//span[@class='fa fa-envelope']")
    private WebElement emailIcon;
    @FindBy(xpath = "//span[@class='fa fa-phone']")
    private WebElement phoneIcon;
    @FindBy(xpath = "//input[@id='subject']/preceding-sibling::div//span[@class='fa fa-envelope']")
    private WebElement subjectEnvelopeIcon;
    @FindBy(xpath = "//span[@class='input-group-text']")
    private WebElement messageCaption;
    @FindBy(id = "name")
    private WebElement nameTextField;
    @FindBy(id = "email")
    private WebElement emailTextField;
    @FindBy(id = "phone")
    private WebElement phoneTextField;
    @FindBy(id = "subject")
    private WebElement subjectTextField;
    @FindBy(xpath = "//*[h2[contains(.,'Thanks for getting in touch')]]")
    private WebElement thanksMessage;
    @FindBy(id = "description")
    private WebElement messageTextArea;
    @FindBy(id = "submitContact")
    private WebElement submitButton;
    @FindBy(xpath = "//div[@class='alert alert-danger']")
    private WebElement errorMessageContainer;

    public void submitContactUsDetails(String name, String email, String phone,
                                            String subject, String message) {
         sendKeysTo(nameTextField, name);
         sendKeysTo(emailTextField, email);
         sendKeysTo(phoneTextField, phone);
         sendKeysTo(subjectTextField, subject);
         sendKeysTo(messageTextArea, message);
    }

    public void scrollToContactUsForm(WebDriver webDriver) {
        // Call the scrollIntoView method using JavascriptExecutor
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", nameTextField);
    }

    public void clickOnSubmitButton() {
         clickOnElement(submitButton);
         waitForNotVisible(submitButton);
}
}
