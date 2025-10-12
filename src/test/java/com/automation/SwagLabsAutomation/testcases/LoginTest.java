package com.automation.SwagLabsAutomation.testcases;

import com.automation.SwagLabsAutomation.pages.LoginPage;
import com.automation.commons.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    // Case Đăng nhập thành công
    @Test
    public void testLoginSuccess() {
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        loginPage.checkLoginSuccessful();
    }

    //Test case login with blank username and password
    @Test
    public void testLoginWithBlankUsernameAndPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login("", "");
        loginPage.verifyLoginWithBlankUsernameAndPassword();
    }

    //Testcase login with blank username
    @Test
    public void testLoginWithBlankUsername() {
        loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");
        loginPage.verifyLoginWithBlankUsername();
    }

    //Testcase login with blank password
    @Test
    public void testLoginWithBlankPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");
        loginPage.verifyLoginWithBlankPassword();
    }

    //Testcase login with wrong username
    @Test
    public void testLoginWithWrongUserName() {
        loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "secret_sauce");
        loginPage.verifyLoginWithWrongUsernameOrPassword();
    }

    //Testcase login with wrong password
    @Test
    public void testLoginWithWrongPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");
        loginPage.verifyLoginWithWrongUsernameOrPassword();
    }

    //Testcase login with wrong username and password
    @Test
    public void testLoginWithWrongUsernameAndPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "wrong_password");
        loginPage.verifyLoginWithWrongUsernameOrPassword();
    }
}
