package com.soffice.projects.pages.pages;

import com.soffice.consts.FrameConst;
import com.soffice.projects.common.BasePage;
import com.soffice.projects.pages.objects.DraftDocumentListObjects;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class DraftDocumentListPage extends BasePage {
    private DraftDocumentListObjects draftDocumentListObjects;

    public DraftDocumentListPage() {
        this.draftDocumentListObjects = new DraftDocumentListObjects(this.webDriver);
    }

    public DraftDocumentListPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.draftDocumentListObjects = new DraftDocumentListObjects(webDriver);
    }

    //Navigate directly to the draft document list page by URL (more reliable than
    //clicking through the 2-level sidebar submenu "Văn bản dự thảo" > "Danh sách văn bản dự thảo")
    public static DraftDocumentListPage goToDraftDocumentListPage(WebDriver webDriver) {
        goToURL(com.soffice.consts.FrameConst.AppConfig.APP_DOMAIN + "app/document/draft");
        return new DraftDocumentListPage(webDriver);
    }

    //Verify list page is displayed
    public DraftDocumentListPage verifyListPageIsDisplayed() {
        draftDocumentListObjects.verifyListPageIsDisplayed();
        return this;
    }

    //Click "Thêm mới" to go to the create form
    public CreateDraftDocumentPage clickAddNewBtn() {
        draftDocumentListObjects.clickAddNewBtn();
        return new CreateDraftDocumentPage(webDriver);
    }

    public DraftDocumentListPage getInfo() {
        draftDocumentListObjects.getInfoFirstRow();
        return this;
    }


    private String currentSummary;

    //Search by summary keyword (the unique "Trích yếu" value used at creation time).
    //Click refresh first to force the DataGrid to reload fresh data from the server before
    //typing the keyword - the grid can otherwise hold onto a stale state from before this
    //page navigation, causing the search to return no results even with the correct keyword.
    public DraftDocumentListPage searchBySummary(String summary) {
        currentSummary = summary;
        draftDocumentListObjects.inputSearchKeyword(summary);
        draftDocumentListObjects.clickSearchBtn();
        DraftDocumentListPage.getWaitDriver().until(webDriver -> {
            draftDocumentListObjects.getInfoFirstRow();
            return currentSummary.equals(DraftDocumentListObjects.summaryColumn);
        });
        return this;
    }

    //Wait until the search result shows the matching summary, then read the row info.
    //Uses a longer timeout (40s instead of the default 20s) because the real system can be
    //slow or flaky right after a "Lưu và chuyển duyệt" action.
//    public DraftDocumentListPage waitForSearchResultAndGetInfo() {
//        getWaitDriver(40).until(webDriver1 -> {
//            draftDocumentListObjects.getInfoFirstRow();
//            return DraftDocumentListObjects.summaryColumn.contains(currentSummary);
//        });
//        return this;
//    }

    //Verify the newly created draft document appears correctly in the list
    public DraftDocumentListPage verifySummaryInListEquals(String expectedSummary) {
        assertEqualCondition(null, expectedSummary, DraftDocumentListObjects.summaryColumn, FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "Verify Trich yeu column matches the newly created document");
        return this;
    }
}
