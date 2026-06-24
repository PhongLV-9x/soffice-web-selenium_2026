package com.soffice.projects.pages.locator;

import lombok.Getter;

@Getter
public class HomePageLocator extends BaseLocator {
    //Username hiển thị ở góc phải header sau khi login thành công
    String topBarUsername = "//span[contains(@class,'kt-header__topbar-username')]";
    //Tiêu đề chào mừng trên trang chủ
    String welcomeHeader = "//*[contains(text(),'CHÀO MỪNG TỚI VĂN PHÒNG SAGS E.OFFICE')]";
    //Menu sidebar (theo text tiếng Việt hiển thị trên UI)
    String menuTrangChu = "//span[normalize-space()='Trang chủ']";
    String menuQuanTriHeThong = "//span[normalize-space()='Quản trị Hệ thống']";
    String menuQuanTriDanhMuc = "//span[normalize-space()='Quản trị Danh mục']";
    String menuVanBanDen = "//span[normalize-space()='Văn bản đến']";
    String menuVanBanDi = "//span[normalize-space()='Văn bản đi']";
    String menuVanBanLuuTru = "//span[normalize-space()='Văn bản lưu trữ']";
    String menuVanBanDuThao = "//span[normalize-space()='Văn bản dự thảo']";
}
