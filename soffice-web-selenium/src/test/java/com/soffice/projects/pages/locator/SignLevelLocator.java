package com.soffice.projects.pages.locator;

import lombok.Getter;

@Getter
public class SignLevelLocator extends BaseLocator {
    //Tiêu đề popup "Chuyển tiếp" (DevExtreme dx-popup-title). Chỉ cần match đúng <h3> chứa
    //text này - không cần ràng buộc thêm ancestor class, vì text "Chuyển tiếp" đã đủ unique
    //trên trang (không trùng với h3 rỗng của popup "Danh sách người dùng").
    String popupTitle = "//h4[text()='Người xử lý']";

    //Bảng "Người xử lý" là 1 dx-treelist (khác dx-datagrid của các bảng khác trên trang).
    //%s = tên cấp xử lý hiển thị trong cột "Tên cấp xử lý", ví dụ 'Cấp 1 ký', 'Cấp 2 ký'.
    //Loại trừ bản dx-pointer-events-none (header cố định, không chứa nút "Chọn" thật).
    String chooseBtnBySignLevel = "//table[contains(@class,'dx-treelist-table') and not(contains(@class,'dx-pointer-events-none'))]/tbody/tr[td[text()='%s']]/descendant::dx-button[@text='Chọn']";

    //Popup "Danh sách người dùng" (dx-datagrid) hiện ra sau khi bấm "Chọn". Trang có nhiều
    //table dx-datagrid-table cùng class (bảng văn bản, treelist cấp ký...), nên phải định vị
    //trong đúng dx-popup-wrapper chứa text "Danh sách người dùng" để không match nhầm.
    String chooseUserPopupWrapper = "//div[contains(@class,'dx-popup-wrapper')][.//*[contains(text(),'Danh sách người dùng')]]";
    //Ô input filter của cột đầu tiên "Danh sách người dùng" (DevExtreme filter row, không có id ổn định)
    String chooseUserSearchInput = chooseUserPopupWrapper + "//tr[contains(@class,'dx-datagrid-filter-row')]/td[1]//input[contains(@class,'dx-texteditor-input')]";
    //Dòng user trong kết quả filter, %s = username cần tìm (ví dụ 'phonglv', sẽ match dạng "phonglv - Tên đầy đủ")
    String userRowByUsername = chooseUserPopupWrapper + "//tr[contains(@class,'dx-data-row')][td[1][starts-with(normalize-space(),'%s')]]";
    //Nút "Lưu" để xác nhận chọn user (dx-button aria-label="Lưu"), giới hạn trong popup này
    String chooseUserSaveBtn = chooseUserPopupWrapper + "//div[contains(@class,'dx-button') and @aria-label='Lưu']";
    //Nút "Đóng" để đóng popup chọn user mà không lưu
    String chooseUserCloseBtn = chooseUserPopupWrapper + "//div[contains(@class,'dx-button') and @aria-label='Đóng']";

    //Nút "Lưu và chuyển duyệt" / "Hủy" ở popup "Chuyển tiếp"
    String saveAndForwardBtn = "//div[contains(@class,'dx-button') and contains(.,'Lưu và chuyển duyệt')]";
    String cancelForwardBtn = "//div[contains(@class,'dx-button') and contains(.,'Hủy')]";
}
