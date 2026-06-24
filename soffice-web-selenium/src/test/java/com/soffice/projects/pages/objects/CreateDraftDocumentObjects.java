package com.soffice.projects.pages.objects;

import com.soffice.projects.pages.locator.CreateDraftDocumentLocator;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@Getter
@Setter
public class CreateDraftDocumentObjects extends BaseObjects {
    private CreateDraftDocumentLocator locator;

    public CreateDraftDocumentObjects(WebDriver webDriver) {
        this.locator = new CreateDraftDocumentLocator();
        this.setWebDriver(webDriver);
    }

    //Find elements - fields with stable name attribute
    public WebElement findSummaryTextarea() { return findWebElement(By.xpath(locator.getSummaryTextarea())); }
    public WebElement findExpireDateInput() { return findWebElement(By.xpath(locator.getExpireDateInput())); }
    public WebElement findDocumentDateInput() { return findWebElement(By.xpath(locator.getDocumentDateInput())); }
    public WebElement findRemarkTextarea() { return findWebElement(By.xpath(locator.getRemarkTextarea())); }

    //Find dropdown input by label text (opens the dropdown when clicked)
    public WebElement findDropdownInputByLabel(String labelText) {
        return findWebElement(By.xpath(String.format(locator.getDropdownInputByLabel(), labelText)));
    }

    //Find a dropdown option by its visible text (after the dropdown has been opened)
    public WebElement findDropdownOptionByText(String optionText) {
        return findWebElement(By.xpath(String.format(locator.getDropdownOptionByText(), optionText)));
    }

    //Find "Nơi ban hành" radio button by label (Phòng / Nhóm)
    public WebElement findRadioNoiBanHanhByLabel(String radioLabel) {
        return findWebElement(By.xpath(String.format(locator.getRadioNoiBanHanhByLabel(), radioLabel)));
    }

    //Find the real <input type="file"> element to upload a file directly via sendKeys.
    //PrimeNG p-fileupload can render more than one hidden <input type="file"> on the page
    //(e.g. drag-drop zone + button), so wait for at least one to be present, then take the first
    //match instead of relying on the locator being unique.
    public WebElement findFileUploadInput() {
        getWaitDriver().until(d -> !d.findElements(By.xpath(locator.getFileUploadInput())).isEmpty());
        java.util.List<WebElement> inputs = getListWebElement(By.xpath(locator.getFileUploadInput()));
        return inputs.get(0);
    }

    //Find the checkbox that marks a specific uploaded file as the one to be signed
    public WebElement findFileCheckboxByFileName(String fileName) {
        return findWebElement(By.xpath(String.format(locator.getFileCheckboxByFileName(), fileName)));
    }

    public WebElement findAddNewSubmitBtn() { return findWebElement(By.xpath(locator.getAddNewSubmitBtn())); }
    public WebElement findCancelBtn() { return findWebElement(By.xpath(locator.getCancelBtn())); }
    public WebElement findFormTitle() { return findWebElement(By.xpath(locator.getFormTitle())); }
    //Nút "Cập nhật cấp ký" xuất hiện trên toolbar sau khi Thêm mới thành công
    public WebElement findUpdateSignLevelBtn() { return findWebElement(By.xpath(locator.getUpdateSignLevelBtn())); }

    //Actions
    public void inputSummary(String summary) {
        inputText(findSummaryTextarea(), "Trich yeu", summary);
    }

    public void inputRemark(String remark) {
        inputText(findRemarkTextarea(), "Ghi chu", remark);
    }

    //Select a dropdown value by label text (e.g. "Loại VB:") and option text (e.g. "Công văn")
    public void selectDropdownByLabel(String labelText, String optionText) {
        clickTo(findDropdownInputByLabel(labelText), "Open dropdown: " + labelText);
        clickTo(findDropdownOptionByText(optionText), "Select option: " + optionText);
    }

    //Select "Nơi ban hành" radio (Phòng / Nhóm)
    public void selectNoiBanHanh(String radioLabel) {
        clickTo(findRadioNoiBanHanhByLabel(radioLabel), "Select Noi ban hanh: " + radioLabel);
    }

    //Upload a file by sending its absolute path directly to the hidden <input type="file">
    public void uploadFile(String absoluteFilePath) {
        findFileUploadInput().sendKeys(absoluteFilePath);
    }

    //Tích chọn checkbox để đánh dấu file PDF này dùng để ký (không tự động tích sau upload,
    //phải chủ động click)
    public void checkFileForSigning(String fileName) {
        clickTo(findFileCheckboxByFileName(fileName), "Check file for signing: " + fileName);
    }

    public void clickAddNewSubmitBtn() {
        clickTo(findAddNewSubmitBtn(), "Click Them moi (submit) button");
    }

    public void clickCancelBtn() {
        clickTo(findCancelBtn(), "Click Huy bo button");
    }

    //Click "Cập nhật cấp ký" button to open the "Chuyển tiếp" sign-level popup
    public void clickUpdateSignLevelBtn() {
        clickTo(findUpdateSignLevelBtn(), "Click 'Cập nhật cấp ký' button");
    }

    //Verify create form is displayed
    public void verifyCreateFormIsDisplayed() {
        Assert.assertTrue(findFormTitle().isDisplayed(), "Create draft document form is not displayed");
    }
}
