package com.soffice.projects.pages.locator;

import lombok.Getter;

@Getter

public class SignDocLocator extends BaseLocator{
    //button xu ly
    String handleBtnEle = "//span[text()='Xử lý']";
    //Download
    String downloadBtnEle = "//dx-button[@class='ng-tns-c21-2 dx-button dx-button-default dx-button-mode-contained dx-widget dx-button-has-icon']";
    //Xem lich su
    String viewHistoryEle = "//dx-button[@aria-label='fas fa-history']";
    //xem vb
    String viewDocumentBtnEle = "//dx-button[@aria-label='fas fa-eye']";
    //Xem truoc mau ky VB
    String viewSignedBtnEle = "//span[text()='Xem trước']";
    //gop y
    String feedbackBtnEle = "//button[normalize-space()='Góp ý']";
    //Decline button
    String declineBtnEle = "//button[normalize-space()='Từ chối']";
    //Agree button
    String agreeBtnEle = "//button[normalize-space()='Đồng ý']";
    //Title xem truoc VB
    String viewDocumentTitleEle = "//span[text()='Xem văn bản']";
    //Van ban lien quan
    String relatedDocumentTitleEle = "//span[text()='VB liên quan']";
    //Noi dung VB lien quan
    String relatedDocumentAreaEle = "//textarea[@id=\"dx_dx-8bdb65d7-db8d-abae-d5a9-ada9a71b727b_24ae5774-75ac-496f-185b-ac6e1e28a67d\"]";
}
