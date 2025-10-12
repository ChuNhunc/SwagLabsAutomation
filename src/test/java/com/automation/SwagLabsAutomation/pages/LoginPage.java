package com.automation.SwagLabsAutomation.pages;

import com.automation.commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        new WebUI(driver);
    }
    private By userName = By.id("user-name");
    private By password = By.id("password");
    private By errorMsg = By.xpath("//h3[@data-test='error']");
    private By errMsgCloseButton = By.className("error-button");
    private By usernameErrIcon = By.xpath("//div[@class='login-box']//div[1]//*[name()='svg']");
    private By passwordErrIcon = By.xpath("//div[@class='login_wrapper-inner']//div[2]//*[name()='svg']");
    private By loginBtn = By.className("submit-button");

    public void enterUsername(String username) {
        WebUI.waitElementTobeVisible(userName);
        WebUI.setText(userName, username);
    }

    public void enterPassword(String passwrd) {
        WebUI.waitElementTobeVisible(password);
        WebUI.setText(password, passwrd);
    }

    public void clickLoginBtn() {
        WebUI.waitElementToBeClickable(loginBtn);
        WebUI.clickElement(loginBtn);
    }

    public String getErrorMessageText() {
        WebUI.waitElementTobeVisible(errorMsg);
        return WebUI.getText(errorMsg);
    }

    public void clickErrorMsgCloseButton() {
        WebUI.waitElementToBeClickable(errMsgCloseButton);
        WebUI.clickElement(errMsgCloseButton);
    }

    public void verifyUsernameErrorIconIsDisplayed() {
        WebUI.waitElementTobeVisible(usernameErrIcon);
        WebUI.verifyElementIsDisplayed(usernameErrIcon);
    }

    public void verifyPasswordErrorIconIsDisplayed() {
        WebUI.waitElementTobeVisible(passwordErrIcon);
        WebUI.verifyElementIsDisplayed(passwordErrIcon);
    }

    public void verifyLoginBtnIsDisplayed() {
        WebUI.waitElementTobeVisible(loginBtn);
        WebUI.verifyElementIsDisplayed(loginBtn);
    }

    public void verifyErrorMessageTextIsDisplayed() {
        WebUI.waitElementTobeVisible(errorMsg);
        WebUI.verifyElementIsDisplayed(errorMsg);
    }

    public void verifyErrorMsgCloseButtonIsDisplayed() {
        WebUI.waitElementTobeVisible(errMsgCloseButton);
        WebUI.verifyElementIsDisplayed(errMsgCloseButton);
    }

    public void verifyUsernameFieldIsDisplayed() {
        WebUI.waitElementTobeVisible(userName);
        WebUI.verifyElementIsDisplayed(userName);
    }

    public void verifyPasswordFieldIsDisplayed() {
        WebUI.waitElementTobeVisible(password);
        WebUI.verifyElementIsDisplayed(password);
    }

    public void login(String username, String password) {
        WebUI.waitForPageLoaded();
        verifyUsernameFieldIsDisplayed();
        enterUsername(username);
        verifyPasswordFieldIsDisplayed();
        enterPassword(password);
        verifyLoginBtnIsDisplayed();
        clickLoginBtn();
    }

    public void checkLoginSuccessful() {
        WebUI.sleep(1000);
        try {
            WebUI.verifyElementIsDisplayed(By.className("app_logo"));
            System.out.println("Login successful");
        } catch (Exception e) {
            System.out.println("Login failed");
        }
    }

    public void verifyLoginWithBlankUsername() {
        verifyUsernameErrorIconIsDisplayed();
        verifyPasswordErrorIconIsDisplayed();
        verifyErrorMessageTextIsDisplayed();
        WebUI.assertEquals(getErrorMessageText(), "Epic sadface: Username is required","Username blank error message is not displayed as expected");
    }

    public void verifyLoginWithBlankPassword() {
        verifyUsernameErrorIconIsDisplayed();
        verifyPasswordErrorIconIsDisplayed();
        verifyErrorMessageTextIsDisplayed();
        WebUI.assertEquals(getErrorMessageText(), "Epic sadface: Password is required","Username blank error message is not displayed as expected");
    }

    public void verifyLoginWithBlankUsernameAndPassword() {
        verifyUsernameErrorIconIsDisplayed();
        verifyPasswordErrorIconIsDisplayed();
        verifyErrorMessageTextIsDisplayed();
        WebUI.assertEquals(getErrorMessageText(), "Epic sadface: Username is required","Username blank error message is not displayed as expected");
    }

    public void verifyLoginWithWrongUsernameOrPassword() {
        verifyUsernameErrorIconIsDisplayed();
        verifyPasswordErrorIconIsDisplayed();
        verifyErrorMessageTextIsDisplayed();
        WebUI.assertEquals(
                getErrorMessageText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Username blank error message is not displayed as expected"
        );
    }
}
