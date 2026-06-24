package com.soffice.projects.pages.objects;

import com.soffice.projects.pages.locator.LoginLocator;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@Getter
@Setter
public class LoginObjects extends BaseObjects {
    private LoginLocator locator;

    public LoginObjects() {
        super();
        this.locator = new LoginLocator();
    }

    //Find username input element
    public WebElement findUsernameInput() {
        return findWebElement(By.xpath(locator.getUsernameInput()));
    }

    //Find password input element
    public WebElement findPasswordInput() {
        return findWebElement(By.xpath(locator.getPasswordInput()));
    }

    //Find login button element
    public WebElement findLoginBtn() {
        return findWebElement(By.xpath(locator.getLoginBtn()));
    }

    //Find login error message element (hiển thị khi đăng nhập sai)
    public WebElement findLoginErrorMsg() {
        return findWebElement(By.xpath(locator.getLoginErrorMsg()));
    }

    //Actions
    public void inputUsername(String username) {
        inputText(findUsernameInput(), "Username", username);
    }

    public void inputPassword(String password) {
        inputText(findPasswordInput(), "Password", password);
    }

    public void clickLoginBtn() {
        clickTo(findLoginBtn(), "Click login button");
    }

    //Verify login page is displayed
    public void verifyLoginPageIsDisplayed() {
        Assert.assertTrue(findUsernameInput().isDisplayed(), "Login page is not displayed");
    }

    //Verify login error message is displayed
    public void verifyLoginErrorMsgIsDisplayed() {
        Assert.assertTrue(findLoginErrorMsg().isDisplayed(), "Login error message is not displayed");
    }
}
