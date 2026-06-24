package com.soffice.projects.pages.pages;

import com.soffice.projects.common.BasePage;
import com.soffice.projects.pages.objects.LoginObjects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginPage extends BasePage {
    private LoginObjects loginObjects;

    public LoginPage() {
        this.loginObjects = new LoginObjects();
    }

    //Verify login page is displayed
    public LoginPage verifyLoginPageIsDisplayed() {
        loginObjects.verifyLoginPageIsDisplayed();
        return this;
    }

    //Fill username/password and click login button (does not assume the result)
    public LoginPage fillLoginForm(String username, String password) {
        loginObjects.inputUsername(username);
        loginObjects.inputPassword(password);
        return this;
    }

    public LoginPage clickLoginBtn() {
        loginObjects.clickLoginBtn();
        return this;
    }

    //Login with valid account, returns HomePage for fluent chaining (use for happy-case tests)
    public HomePage login(String username, String password) {
        fillLoginForm(username, password);
        loginObjects.clickLoginBtn();
        return new HomePage();
    }

    //Verify login failed (wrong credentials) - stays on LoginPage
    public LoginPage verifyLoginErrorMsgIsDisplayed() {
        loginObjects.verifyLoginErrorMsgIsDisplayed();
        return this;
    }
}
