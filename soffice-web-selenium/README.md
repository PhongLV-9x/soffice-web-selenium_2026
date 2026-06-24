# soffice-web-selenium

Project automation test Selenium cho hệ thống **SAGS e.Office** (`https://soffice-sg.aits.vn/`),
xây dựng theo cùng kiến trúc Page Object Model với project `labit-web-selenium-0326` (Testek),
package gốc đổi từ `com.testek` → `com.soffice`.

## Đã hoàn thành
- Core framework: `driver`, `controller/WebUI`, `datadriven`, `consts`, `report` (ExtentReports only),
  `utils`, `exceptions`, `annotations`, `listeners` — đã bỏ `database`, Cucumber, Allure theo yêu cầu.
- `TestBase`: **không auto-login**. Mỗi test script tự gọi `PageManagement.accessWebPage()` rồi
  `loginPage.login(username, password)` với account tự chọn.
- `LoginPage` / `LoginLocator` / `LoginObjects` — locator lấy từ outerHTML thật (name attribute,
  ổn định hơn class Angular hash).
- `HomePage` / `HomePageLocator` / `HomePageObjects` — verify dựa trên username hiển thị ở topbar.
- 1 test case mẫu: `LoginTest.TK_Login_001_Valid` — login với account `thaonv/123`, verify Home hiển thị.
- `ExecutionSuite.xml` chạy sẵn test này.

## ⚠️ Các điểm CẦN BẠN XÁC NHẬN LẠI trước khi tin tưởng 100%

### 1. `LoginLocator.loginErrorMsg`
Tôi **đoán** class `alert-danger` (Bootstrap phổ biến) cho thông báo lỗi khi đăng nhập sai.
**Chưa có outerHTML thật.** Nếu cần viết test "login fail", hãy thử đăng nhập sai 1 lần,
F12 lấy outerHTML của thông báo lỗi rồi gửi lại để tôi sửa.

### 2. `HomePageLocator` - các menu sidebar
Các locator sau chỉ dựa trên **text nhìn thấy qua ảnh UI**, KHÔNG có outerHTML xác nhận:
- `menuQuanTriHeThong`, `menuQuanTriDanhMuc`, `menuVanBanDen`, `menuVanBanDi`,
  `menuVanBanLuuTru`, `menuVanBanDuThao`
Tag dùng là `<span>` — có thể sai (có thể là `<div>`, `<a>`, hoặc nằm trong cấu trúc khác).
**Cần F12 xác nhận lại từng cái khi bắt đầu viết test cho module đó.**

### 3. `ProjectConst.ModuleURL` - đường dẫn URL từng module
Tôi đoán theo pattern đã thấy (`app/main/home`):
```
QUAN_TRI_HE_THONG -> app/main/system
QUAN_TRI_DANH_MUC -> app/main/category
VAN_BAN_DEN       -> app/main/incoming-document
VAN_BAN_DI        -> app/main/outgoing-document
VAN_BAN_LUU_TRU   -> app/main/archived-document
VAN_BAN_DU_THAO   -> app/main/draft-document
```
**Đây chỉ là placeholder/đoán — gần như chắc chắn sai.** Cách lấy đúng: click vào từng menu
trên web thật, copy URL trên thanh địa chỉ, sửa lại enum tương ứng.

### 4. `javax.annotation.Nullable` (dùng trong `WebUI.java`, `FrameAnnotation.java`)
Không khai báo dependency riêng trong `pom.xml` (giống bản gốc) — dựa vào transitive dependency.
Nếu build lỗi `package javax.annotation does not exist`, thêm dependency:
```xml
<dependency>
    <groupId>com.google.code.findbugs</groupId>
    <artifactId>jsr305</artifactId>
    <version>3.0.2</version>
</dependency>
```

## Cách chạy thử
```bash
mvn clean test
```
Mặc định chạy `ExecutionSuite.xml` → `LoginTest` với account `thaonv/123`.

## Cấu trúc package (giữ nguyên gốc, chỉ đổi `testek` → `soffice`)
```
com.soffice                          (core framework, src/main)
com.soffice.projects.common          (TestBase, BasePage, PropertiesUtils, TestListener)
com.soffice.projects.pages           (PageManagement)
com.soffice.projects.pages.locator   (xpath locators)
com.soffice.projects.pages.objects   (WebElement actions)
com.soffice.projects.pages.pages     (Page Object - fluent interface)
com.soffice.projects.dataprovider    (Model + Provider + DataPath)
com.soffice.projects.testscript      (test case thật)
```

## Bước tiếp theo gợi ý
1. Xác nhận lại các điểm ⚠️ ở trên (đặc biệt ProjectConst.ModuleURL).
2. Chọn 1 module cụ thể (ví dụ "Văn bản đến") để viết test case đầu tiên — cần outerHTML
   của menu, nút "Thêm mới"/"Tạo văn bản", và các field trong form, giống cách đã làm với
   module SaleEmployee ở project Testek.
3. Có thể bổ sung lại Cucumber/Allure/Database sau nếu nhu cầu thay đổi — kiến trúc hiện tại
   không khoá cứng việc mở rộng.
