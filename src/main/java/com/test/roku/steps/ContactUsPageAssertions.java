package com.test.roku.steps;

import com.test.roku.pages.ContactUsPage;
import com.test.roku.utils.AssertionUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.test.roku.utils.ConfigUtils.getPropertyByKey;

public class ContactUsPageAssertions {

    public ContactUsPageAssertions(WebDriver driver) {
        contactUspage = new ContactUsPage(driver);
        thanksMessage = contactUspage.getThanksMessage();
        errorMessageContainer = contactUspage.getErrorMessageContainer();
        nameIdCardIcon = contactUspage.getNameIdCardIcon();
        emailIcon = contactUspage.getEmailIcon();
        phoneIcon = contactUspage.getPhoneIcon();
        subjectEnvelopeIcon = contactUspage.getSubjectEnvelopeIcon();
        nameTextField = contactUspage.getNameTextField();
        emailTextField = contactUspage.getEmailTextField();
        phoneTextField = contactUspage.getPhoneTextField();
        subjectTextField = contactUspage.getSubjectTextField();
        messageCaption = contactUspage.getMessageCaption();
    }

    private final ContactUsPage contactUspage;
    protected WebElement thanksMessage;
    private final WebElement errorMessageContainer;
    private final WebElement nameIdCardIcon;
    private final WebElement emailIcon;
    private final WebElement phoneIcon;
    private final WebElement subjectEnvelopeIcon;
    private final WebElement nameTextField;
    private final WebElement emailTextField;
    private final WebElement phoneTextField;
    private final WebElement subjectTextField;
    private final WebElement messageCaption;

    public ContactUsPage getContactUsPage() {
        return contactUspage;
    }

    public void assertContactUsSubmitSuccessMessage() {
        AssertionUtils.assertPresent(thanksMessage);
    }

    public void assertNameDisplayedInSubmitSuccessMessage() {
        AssertionUtils.assertEquals(thanksMessage, getPropertyByKey("contactUsPage.validName"));
    }

    public void assertSubjectDisplayedInSubmitSuccessMessage() {
        AssertionUtils.assertEquals(thanksMessage, getPropertyByKey("contactUsPage.validSubject"));
    }

    public void assertErrorMsgContainerDisplayed() {
        AssertionUtils.assertPresent(errorMessageContainer);
    }
    public void assertErrorMessageDisplayedWhenBlankNameTextEntered() {
        AssertionUtils.assertEquals(errorMessageContainer, getPropertyByKey("contactUsPage.blankNameErrMsg"));
    }

    public void assertErrorMessageDisplayedWhenBlankEmailTextEntered() {
        AssertionUtils.assertEquals(errorMessageContainer, getPropertyByKey("contactUsPage.blankEmailErrMsg"));
    }

    public void assertErrorMessageDisplayedWhenBlankPhoneTextEntered() {
        AssertionUtils.assertEquals(errorMessageContainer, getPropertyByKey("contactUsPage.blankPhoneErrMsg"));
    }

    public void assertErrorMessageDisplayedWhenOutOfRangeTextEntered(String fieldName) {
        String errMsg = "";
        switch (fieldName) {
            case "Phone":
                errMsg = getPropertyByKey("contactUsPage.outOfRangePhoneErrMsg");
                break;
            case "Subject":
                errMsg = getPropertyByKey("contactUsPage.outOfRangeSubjectErrMsg");
                break;
            case "Message":
                errMsg = getPropertyByKey("contactUsPage.outOfRangeMessageErrMsg");
                break;
            default:
                break;
        }
        AssertionUtils.assertEquals(errorMessageContainer, errMsg);
    }

    public void assertErrorMessageDisplayedWhenOutOfRangeSubjectTextEntered() {
        AssertionUtils.assertEquals(errorMessageContainer, getPropertyByKey("contactUsPage.outOfRangeSubjectErrMsg"));
    }

    public void assertErrorMessageDisplayedWhenBlankSubjectTextEntered() {
        AssertionUtils.assertEquals(errorMessageContainer, getPropertyByKey("contactUsPage.blankSubjectErrMsg"));
    }

    public void assertErrorMessageDisplayedWhenBlankMessageTextEntered() {
        AssertionUtils.assertEquals(errorMessageContainer, getPropertyByKey("contactUsPage.blankMessageErrMsg"));
    }
    public void assertErrorMessageDisplayedWhenOutOfRangeMessageTextEntered() {
        AssertionUtils.assertEquals(errorMessageContainer, getPropertyByKey("contactUsPage.outOfRangeMessageErrMsg"));
    }

    public void assertTextBoxIconDisplayed(String textBoxIcon) {
        switch (textBoxIcon) {
            case "idCard":
                AssertionUtils.assertPresent(nameIdCardIcon);
                break;
            case "emailEnvelope":
                AssertionUtils.assertPresent(emailIcon);
                break;
            case "phone":
                AssertionUtils.assertPresent(phoneIcon);
                break;
            case "subjectEnvelope":
                AssertionUtils.assertPresent(subjectEnvelopeIcon);
                break;
            default:
                break;
        }
    }

    public void assertTextBoxLabelDisplayed(String textBoxLabel) {
        String label;
        switch (textBoxLabel) {
            case "Name":
                label=nameTextField.getAttribute("aria-label");
                AssertionUtils.assertEquals(label, "Name");
                break;
            case "Email":
                label=emailTextField.getAttribute("aria-label");
                AssertionUtils.assertEquals(label, "Email");
                break;
            case "Phone":
                label=phoneTextField.getAttribute("aria-label");
                AssertionUtils.assertEquals(label, "Phone");
                break;
            case "Subject":
                label=subjectTextField.getAttribute("aria-label");
                AssertionUtils.assertEquals(label, "Subject");
                break;
            default:
                break;
        }
    }

    public void assertMessageBoxCaptionDisplayed() {
        AssertionUtils.assertPresent(messageCaption);
    }

    public void assertErrorDisplayedForInvalidFormat(String fieldName) {
        String errMsg="";
        switch(fieldName) {
            case "Name":
                errMsg = getPropertyByKey("contactUsPage.nameFormatErrMsg");
                break;
            case "Email":
                errMsg = getPropertyByKey("contactUsPage.emailFormatErrMsg");
                break;
            case "Phone":
                errMsg = getPropertyByKey("contactUsPage.phoneNoFormatErrMsg");
                break;
            default:
                break;
        }
        try {
            AssertionUtils.assertEquals(errorMessageContainer, errMsg);
        } catch(NoSuchElementException nse) {
            AssertionUtils.assertFail(String.format("User Should be displayed invalid %s format error message.", fieldName));
        }
    }
}
