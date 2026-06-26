package com.soffice.projects.testscript;

import com.soffice.annotations.FrameAnnotation;
import com.soffice.consts.AuthorType;
import com.soffice.consts.FrameConst;
import com.soffice.projects.common.TestBase;
import com.soffice.projects.dataprovider.model.CreateDraftDocumentModel;
import com.soffice.projects.dataprovider.providers.CreateDraftDocumentProvider;
import com.soffice.projects.pages.PageManagement;
import com.soffice.projects.pages.pages.CreateDraftDocumentPage;
import com.soffice.projects.pages.pages.DraftDocumentListPage;
import com.soffice.projects.pages.pages.HomePage;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Objects;

@Getter
@Setter
public class CreateDraftDocument extends TestBase {
    HomePage homePage;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        super.beforeClass();
        homePage = PageManagement.accessWebPage()
                .login("thaonv", "123");
        homePage.verifyHomePageIsDisplayed();
    }

    /*
     * Lấy đường dẫn tuyệt đối thật trên đĩa của file đính kèm mẫu, nằm trong
     * src/test/resources/data/files. sendKeys() cần một path thật, không phải InputStream,
     * nên không dùng ResourceReader (chỉ đọc nội dung) mà lấy trực tiếp qua ClassLoader.
     *
     * Dùng Paths.get(URI) thay vì new File(URL.getFile()) vì getFile() trả về dạng
     * URL-encoded (dấu cách -> "%20"), gây lỗi "File not found" khi đường dẫn project
     * chứa khoảng trắng (ví dụ "D:\AUTO TEST\..."). URI tự decode đúng các ký tự đặc biệt.
     */
    private String getAttachmentFilePath() {
        try {
            URL resourceUrl = getClass().getClassLoader().getResource("data/files/Ky_test_note_cho_dai.pdf");
            return java.nio.file.Paths.get(Objects.requireNonNull(resourceUrl).toURI()).toFile().getAbsolutePath();
        } catch (java.net.URISyntaxException e) {
            throw new RuntimeException("Cannot resolve attachment file path", e);
        }
    }

    /*
     * Step 1: Đăng nhập (đã xử lý ở beforeClass với account thaonv/123)
     * Step 2: Vào trang "Văn bản dự thảo" -> "Danh sách văn bản dự thảo"
     * Step 3: Bấm "Thêm mới"
     * Step 4: Điền đầy đủ thông tin hợp lệ, upload file đính kèm, bấm "Thêm mới" để lưu
     * Step 5: Bấm "Cập nhật cấp ký" -> popup "Chuyển tiếp" -> chọn người ký cho từng cấp
     *         (Cấp 1 ký: phonglv, Cấp 2 ký: thaonv) -> "Lưu và chuyển duyệt"
     * Step 6: Quay lại danh sách, tìm theo Trích yếu vừa tạo, verify hiển thị đúng
     */
    @FrameAnnotation(category = {FrameConst.CategoryType.REGRESSION}, author = {AuthorType.ADMIN}, reviewer = {AuthorType.ADMIN})
    @Test(description = "Verify creating a new draft document with an attachment file",
            dataProvider = "TK_CreateDraftDocument_001_Valid", dataProviderClass = CreateDraftDocumentProvider.class)
    public void TK_CreateDraftDocument_001_Valid(CreateDraftDocumentModel data) {
        DraftDocumentListPage listPage = DraftDocumentListPage.goToDraftDocumentListPage(homePage.getWebDriver());

        CreateDraftDocumentPage createPage = listPage.verifyListPageIsDisplayed()
                .clickAddNewBtn()
                .verifyCreateFormIsDisplayed()
                .fillDraftDocumentData(data, getAttachmentFilePath())
                .clickAddNewSubmitBtn();

        createPage.clickUpdateSignLevelBtn()
                .verifyPopupIsDisplayed()
                .chooseUserForSignLevel("Cấp 1 ký", "phonglv")
                .chooseUserForSignLevel("cáp 2 ký", "thaonv")
                .clickSaveAndForward();

        DraftDocumentListPage.goToDraftDocumentListPage(homePage.getWebDriver())
                .getInfo()
                .searchBySummary(CreateDraftDocumentPage.currentSummary)
                .verifySummaryInListEquals();
    }
}
