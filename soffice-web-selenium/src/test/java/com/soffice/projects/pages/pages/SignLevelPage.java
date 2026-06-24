package com.soffice.projects.pages.pages;

import com.soffice.projects.common.BasePage;
import com.soffice.projects.pages.objects.SignLevelObjects;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class SignLevelPage extends BasePage {
    private SignLevelObjects signLevelObjects;

    public SignLevelPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.signLevelObjects = new SignLevelObjects(webDriver);
    }

    //Verify popup "Chuyển tiếp" is displayed
    public SignLevelPage verifyPopupIsDisplayed() {
        signLevelObjects.verifyPopupIsDisplayed();
        return this;
    }

    //Choose a user for a given sign level (e.g. "Cấp 1 ký", "Cấp 2 ký"):
    //1. Click "Chọn" for that sign level row -> opens "Danh sách người dùng" popup
    //2. Type the username into the filter input
    //3. Click the matching user row to select it
    //4. Click "Lưu" to confirm, closing the user popup and returning to "Chuyển tiếp"
    public SignLevelPage chooseUserForSignLevel(String signLevelName, String username) {
        signLevelObjects.clickChooseBtnBySignLevel(signLevelName);
        signLevelObjects.inputChooseUserSearchKeyword(username);
        signLevelObjects.clickUserRowByUsername(username);
        signLevelObjects.clickChooseUserSaveBtn();
        return this;
    }

    //Click "Lưu và chuyển duyệt" to finalize the whole sign-level update flow.
    //After this, the system navigates to "Văn bản dự thảo > Xem chi tiết" (not back to the
    //list page) - no further verification is needed here per requirement.
    public void clickSaveAndForward() {
        signLevelObjects.clickSaveAndForwardBtn();
    }
}
