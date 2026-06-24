package com.soffice.projects.pages.pages;

import com.soffice.projects.common.BasePage;
import com.soffice.projects.dataprovider.model.CreateDraftDocumentModel;
import com.soffice.projects.pages.objects.CreateDraftDocumentObjects;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class CreateDraftDocumentPage extends BasePage {
    private CreateDraftDocumentObjects createDraftDocumentObjects;
    public static String currentSummary;

    public CreateDraftDocumentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.createDraftDocumentObjects = new CreateDraftDocumentObjects(webDriver);
    }

    //Verify create form is displayed
    public CreateDraftDocumentPage verifyCreateFormIsDisplayed() {
        createDraftDocumentObjects.verifyCreateFormIsDisplayed();
        return this;
    }

    //Fill the mandatory fields and upload an attachment file, using a unique summary (Trích yếu)
    //so the record can be found and verified afterward in the list page.
    public CreateDraftDocumentPage fillDraftDocumentData(CreateDraftDocumentModel data, String attachmentAbsolutePath) {
        String time = String.valueOf(System.currentTimeMillis());
        String summary = data.getSummary().getValue() + time.substring(8);
        currentSummary = summary;

        createDraftDocumentObjects.inputSummary(summary);
        createDraftDocumentObjects.selectDropdownByLabel("Sổ văn bản dự kiến:", data.getDocumentBook().getValue());
        createDraftDocumentObjects.selectDropdownByLabel("Loại BH:", data.getDistributionType().getValue());
        createDraftDocumentObjects.selectNoiBanHanh(data.getIssuingPlace().getValue());
        createDraftDocumentObjects.selectDropdownByLabel("Đơn vị/Nhóm ban hành:", data.getIssuingUnit().getValue());
        createDraftDocumentObjects.selectDropdownByLabel("Loại VB:", data.getDocumentType().getValue());
        createDraftDocumentObjects.selectDropdownByLabel("Phân loại:", data.getClassification().getValue());
        createDraftDocumentObjects.inputRemark(data.getRemark().getValue() + time.substring(8));

        if (attachmentAbsolutePath != null && !attachmentAbsolutePath.isEmpty()) {
            createDraftDocumentObjects.uploadFile(attachmentAbsolutePath);
            //Sau khi upload, hệ thống KHÔNG tự tích checkbox -> phải tự click để chọn
            //file này dùng để ký, dựa theo tên file hiển thị trong danh sách file đã upload.
            String fileName = new java.io.File(attachmentAbsolutePath).getName();
            createDraftDocumentObjects.checkFileForSigning(fileName);
        }
        return this;
    }

    //Click "Thêm mới" (submit) button
    public CreateDraftDocumentPage clickAddNewSubmitBtn() {
        createDraftDocumentObjects.clickAddNewSubmitBtn();
        return this;
    }

    //Click "Hủy bỏ" button
    public DraftDocumentListPage clickCancelBtn() {
        createDraftDocumentObjects.clickCancelBtn();
        return new DraftDocumentListPage(webDriver);
    }

    //Click "Cập nhật cấp ký" button (appears on the toolbar after a successful submit)
    //to open the "Chuyển tiếp" sign-level popup.
    public SignLevelPage clickUpdateSignLevelBtn() {
        createDraftDocumentObjects.clickUpdateSignLevelBtn();
        return new SignLevelPage(webDriver);
    }
}
