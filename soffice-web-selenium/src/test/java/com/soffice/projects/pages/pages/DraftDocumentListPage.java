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

    private String currentSummary;

    //Search by summary keyword (the unique "Trích yếu" value used at creation time)
    public DraftDocumentListPage searchBySummary(String summary) {
        currentSummary = summary;
        draftDocumentListObjects.inputSearchKeyword(summary);
        draftDocumentListObjects.clickSearchBtn();
        return this;
    }

    //Wait until the search result shows the matching summary, then read the row info
    public DraftDocumentListPage waitForSearchResultAndGetInfo() {
        getWaitDriver().until(webDriver1 -> {
            draftDocumentListObjects.getInfoFirstRow();
            return DraftDocumentListObjects.summaryColumn.contains(currentSummary);
        });
        return this;
    }

    //Verify the newly created draft document appears correctly in the list
    public DraftDocumentListPage verifySummaryInListEquals(String expectedSummary) {
        assertEqualCondition(null, expectedSummary, DraftDocumentListObjects.summaryColumn,
                FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "Verify Trich yeu column matches the newly created document");
        return this;
    }
}
