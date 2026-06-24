package com.soffice.projects.pages.locator;

import lombok.Getter;

@Getter
public class CreateDraftDocumentLocator extends BaseLocator {
    //Các field có name attribute ổn định (không đổi giữa các lần load trang)
    String summaryTextarea = "//textarea[@name='summary']";
    String expireDateInput = "//input[@name='expireDate']";
    String documentDateInput = "//input[@name='documentDate']";
    String remarkTextarea = "//textarea[@name='remark']";

    //Dropdown (dx-select-box) không có name/id ổn định -> định vị qua label text.
    //%s = text label, ví dụ 'Sổ văn bản dự kiến:', 'Loại VB:', 'Phân loại:', 'Loại BH:',
    //'Độ mật:', 'Độ khẩn:', 'Lĩnh vực VB:', 'Đơn vị/Nhóm ban hành:'
    //Input hiển thị text bên trong dx-select-box, dùng để click mở dropdown
    String dropdownInputByLabel = "//span[@class='dx-field-item-label-text' and contains(text(),'%s')]/ancestor::div[contains(@class,'dx-field-item')][1]/descendant::div[contains(@class,'dx-selectbox')]/descendant::input[contains(@class,'dx-texteditor-input')]";
    //Option trong list khi dropdown đang mở (DevExtreme render overlay riêng, item text khớp %s)
    String dropdownOptionByText = "//div[contains(@class,'dx-list-item') or contains(@class,'dx-item')][contains(.,'%s')]";

    //Radio "Nơi ban hành" (Phòng / Nhóm) - %s = 'Phòng' hoặc 'Nhóm'
    String radioNoiBanHanhByLabel = "//span[@class='dx-field-item-label-text' and contains(text(),'Nơi ban hành')]/ancestor::div[contains(@class,'dx-field-item')][1]/descendant::div[contains(@class,'dx-radiobutton')][.//div[@class='dx-item-content' and normalize-space()='%s']]";

    //File upload input thật (PrimeNG p-fileupload) - sendKeys trực tiếp, không cần click "Chọn file"
    String fileUploadInput = "//p-fileupload//input[@type='file']";
    //Checkbox tích chọn file PDF dùng để ký, hiện ra trong danh sách file đã upload (file-list).
    //Cấu trúc thật: <ul class="file-list"><div class="file-info">...<span class="file-name">tên file</span>...
    //KHÔNG có <li>, mỗi file là 1 <div class="file-info"> con trực tiếp của <ul>.
    //%s = tên file (không cần đầy đủ, chỉ cần phần đặc trưng) để xác định đúng dòng file cần tích.
    String fileCheckboxByFileName = "//ul[contains(@class,'file-list')]/div[contains(@class,'file-info')][.//span[@class='file-name' and contains(text(),'%s')]]/descendant::dx-check-box";

    //Nút submit (dx-button text="Thêm mới") và nút hủy (text="Hủy bỏ")
    String addNewSubmitBtn = "//dx-button[@text='Thêm mới']";
    String cancelBtn = "//div[contains(@class,'dx-button') and @aria-label='Hủy bỏ']";

    //Tiêu đề form (caption "Thông tin văn bản") - dùng để verify form đã hiển thị
    String formTitle = "//span[@class='dx-form-group-caption' and text()='Thông tin văn bản']";

    //Nút "Cập nhật cấp ký" xuất hiện trên toolbar trang chi tiết sau khi Thêm mới thành công
    String updateSignLevelBtn = "//button[contains(@class,'btn-primary') and contains(.,'Cập nhật cấp ký')]";
}
