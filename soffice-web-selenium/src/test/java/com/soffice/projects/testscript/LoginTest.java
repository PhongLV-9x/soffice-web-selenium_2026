package com.soffice.projects.testscript;

import com.soffice.annotations.FrameAnnotation;
import com.soffice.consts.AuthorType;
import com.soffice.consts.FrameConst;
import com.soffice.projects.common.TestBase;
import com.soffice.projects.dataprovider.model.LoginModel;
import com.soffice.projects.dataprovider.providers.LoginProvider;
import com.soffice.projects.pages.PageManagement;
import com.soffice.projects.pages.pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        super.beforeClass();
    }

    /*
     * Verify login thành công với account hợp lệ.
     * Mỗi test case tự quyết định account dùng để login (lấy từ JSON data),
     * không có auto-login mặc định ở TestBase.
     */
    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.ADMIN}, reviewer = {AuthorType.ADMIN})
    @Test(description = "Verify login successfully with valid account", dataProvider = "TK_Login_001_Valid", dataProviderClass = LoginProvider.class)
    public void TK_Login_001_Valid(LoginModel data) {
        loginPage = PageManagement.accessWebPage();
        loginPage.verifyLoginPageIsDisplayed()
                .login(data.getUserName().getValue(), data.getPassword().getValue())
                .verifyHomePageIsDisplayed();
    }
}
