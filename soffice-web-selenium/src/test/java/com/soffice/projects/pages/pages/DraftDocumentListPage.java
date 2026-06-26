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


    private String currentSummary, currentDocumentType, currentDocumentClassify, currentTypeOfIssuance, currentDocumentDate, currentCreator;

    //Search by summary keyword (the unique "Trích yếu" value used at creation time).
    //Also pulls the other expected column values (set during creation in CreateDraftDocumentPage)
    //so verifySummaryInListEquals() can compare every column, not just "Trích yếu".
    public DraftDocumentListPage searchBySummary(String summary) {
        currentSummary = summary;
        currentDocumentType = CreateDraftDocumentPage.currentDocumentType;
        currentDocumentClassify = CreateDraftDocumentPage.currentDocumentClassify;
        currentTypeOfIssuance = CreateDraftDocumentPage.currentTypeOfIssuance;
        currentDocumentDate = CreateDraftDocumentPage.currentDocumentDate;
        currentCreator = CreateDraftDocumentPage.currentCreator;
        draftDocumentListObjects.inputSearchKeyword(summary);
        draftDocumentListObjects.clickSearchBtn();
        DraftDocumentListPage.getWaitDriver().until(webDriver -> {
            draftDocumentListObjects.getInfoFirstRow();
            return currentSummary.equals(DraftDocumentListObjects.summaryColumn);
        });
        return this;
    }


    //Verify the newly created draft document appears correctly in the list
    public DraftDocumentListPage verifySummaryInListEquals() {
        assertEqualCondition(null,currentSummary, DraftDocumentListObjects.summaryColumn, FrameConst.FailureHandling.CONTINUE_ON_FAILURE,"Result does not match");
        assertEqualCondition(null,currentDocumentType,DraftDocumentListObjects.loaiVBColumn, FrameConst.FailureHandling.CONTINUE_ON_FAILURE,"Result does not match");
        assertEqualCondition(null,currentDocumentClassify,DraftDocumentListObjects.phanLoaiColumn, FrameConst.FailureHandling.CONTINUE_ON_FAILURE,"Result does not match");
        assertEqualCondition(null,currentTypeOfIssuance,DraftDocumentListObjects.loaiBHColumn, FrameConst.FailureHandling.CONTINUE_ON_FAILURE,"Result does not match");
        assertEqualCondition(null,currentDocumentDate,DraftDocumentListObjects.ngayVBColumn, FrameConst.FailureHandling.CONTINUE_ON_FAILURE,"Result does not match");
        //assertEqualCondition(null,currentCreator,DraftDocumentListObjects.nguoiLapColumn, FrameConst.FailureHandling.CONTINUE_ON_FAILURE,"Result does not match");
        return this;
    }
}
