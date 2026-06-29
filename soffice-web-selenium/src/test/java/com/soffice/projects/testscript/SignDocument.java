package com.soffice.projects.testscript;

import com.soffice.annotations.FrameAnnotation;
import com.soffice.consts.AuthorType;
import com.soffice.consts.FrameConst;
import com.soffice.projects.common.TestBase;
import com.soffice.projects.dataprovider.providers.CreateDraftDocumentProvider;
import com.soffice.projects.pages.PageManagement;
import com.soffice.projects.pages.pages.HomePage;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Getter
@Setter
public class SignDocument extends TestBase {
    HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        super.beforeClass();
        homePage = PageManagement.accessWebPage()
                .login("thaonv", "123");
        homePage.verifyHomePageIsDisplayed();
    }
//   @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.ADMIN}, reviewer = {AuthorType.ADMIN})
//   @Test(description = "Verify creating a new draft document with an attachment file",dataProvider = "TK_CreateDraftDocument_001_Valid",
//            dataProviderClass = CreateDraftDocumentProvider.class)

}

