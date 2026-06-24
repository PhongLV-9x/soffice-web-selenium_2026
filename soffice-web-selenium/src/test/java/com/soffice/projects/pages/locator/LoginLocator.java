package com.soffice.projects.pages.locator;

import lombok.Getter;

@Getter
public class LoginLocator extends BaseLocator {
    String usernameInput = "//input[@name='userNameOrEmailAddress']";
    String passwordInput = "//input[@name='password']";
    String loginBtn = "//button[@type='submit' and contains(@class,'kt-login__btn-primary')]";
    //Validation message element (hiển thị dưới input khi sai/để trống)
    String validationMsg = "//validation-messages[contains(@class,'ng-tns')]/descendant::div";
    //Error message khi login sai (toast/alert, cần xác nhận lại với hệ thống thật)
    String loginErrorMsg = "//div[contains(@class,'alert-danger')]";
}
