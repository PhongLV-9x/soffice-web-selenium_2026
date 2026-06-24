package com.soffice.projects.pages.locator;

import lombok.Getter;

@Getter
public class DraftDocumentListLocator extends BaseLocator {
    //Ô tìm kiếm nhanh theo Số VB hoặc Trích yếu
    String searchInput = "//input[@placeholder='Tìm kiếm nhanh theo Số VB hoặc Trích yếu']";
    //Nút "Tìm kiếm" để thực hiện search sau khi đã gõ từ khóa
    String searchBtn = "//button[text()=' Tìm kiếm ']";
    //Nút "Thêm mới" trên trang danh sách (button thường, không phải dx-button)
    String addNewBtn = "//button[contains(@class,'btn-primary') and contains(.,'Thêm mới')]";
    //Link "Trích yếu" của dòng đầu tiên trong bảng kết quả (cột 1, dạng <a class="hyperlink">)
    //Loại trừ table có class dx-pointer-events-none vì đó là bản header/scroll-sync, không chứa data thật
    String firstRowSummaryLink = "//table[contains(@class,'dx-datagrid-table') and not(contains(@class,'dx-pointer-events-none'))]/tbody/tr[contains(@class,'dx-data-row')][1]/td[1]//a[contains(@class,'hyperlink')]";
    //Toàn bộ ô (td) của dòng đầu tiên, dùng để lấy giá trị từng cột khi verify
    String firstRowCells = "//table[contains(@class,'dx-datagrid-table') and not(contains(@class,'dx-pointer-events-none'))]/tbody/tr[contains(@class,'dx-data-row')][1]/td";
}
