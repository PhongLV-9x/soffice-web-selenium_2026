package com.soffice.projects.pages.objects;

import com.soffice.projects.pages.locator.HomePageLocator;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@Getter
@Setter
public class HomePageObjects extends BaseObjects {
    private HomePageLocator locator;

    public HomePageObjects(WebDriver webDriver) {
        this.locator = new HomePageLocator();
        this.setWebDriver(webDriver);
    }

    //Find topbar username element
    public WebElement findTopBarUsername() {
        return findWebElement(By.xpath(locator.getTopBarUsername()));
    }

    //Find welcome header element
    public WebElement findWelcomeHeader() {
        return findWebElement(By.xpath(locator.getWelcomeHeader()));
    }

    //Find sidebar menu items
    public WebElement findMenuTrangChu() {
        return findWebElement(By.xpath(locator.getMenuTrangChu()));
    }

    public WebElement findMenuQuanTriHeThong() {
        return findWebElement(By.xpath(locator.getMenuQuanTriHeThong()));
    }

    public WebElement findMenuQuanTriDanhMuc() {
        return findWebElement(By.xpath(locator.getMenuQuanTriDanhMuc()));
    }

    public WebElement findMenuVanBanDen() {
        return findWebElement(By.xpath(locator.getMenuVanBanDen()));
    }

    public WebElement findMenuVanBanDi() {
        return findWebElement(By.xpath(locator.getMenuVanBanDi()));
    }

    public WebElement findMenuVanBanLuuTru() {
        return findWebElement(By.xpath(locator.getMenuVanBanLuuTru()));
    }

    public WebElement findMenuVanBanDuThao() {
        return findWebElement(By.xpath(locator.getMenuVanBanDuThao()));
    }

    //Actions
    public void clickMenuQuanTriHeThong() {
        clickTo(findMenuQuanTriHeThong(), "Click to Quan tri He thong menu");
    }

    public void clickMenuQuanTriDanhMuc() {
        clickTo(findMenuQuanTriDanhMuc(), "Click to Quan tri Danh muc menu");
    }

    public void clickMenuVanBanDen() {
        clickTo(findMenuVanBanDen(), "Click to Van ban den menu");
    }

    public void clickMenuVanBanDi() {
        clickTo(findMenuVanBanDi(), "Click to Van ban di menu");
    }

    public void clickMenuVanBanLuuTru() {
        clickTo(findMenuVanBanLuuTru(), "Click to Van ban luu tru menu");
    }

    public void clickMenuVanBanDuThao() {
        clickTo(findMenuVanBanDuThao(), "Click to Van ban du thao menu");
    }

    //Verify home page is displayed (after login)
    public void verifyHomePageIsDisplayed() {
        Assert.assertTrue(findTopBarUsername().isDisplayed(), "Home page is not displayed - username not found in topbar");
    }
}
