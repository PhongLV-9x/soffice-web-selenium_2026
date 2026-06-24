package com.soffice.projects.pages.objects;

import com.soffice.projects.pages.locator.DraftDocumentListLocator;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

@Getter
@Setter
public class DraftDocumentListObjects extends BaseObjects {
    private DraftDocumentListLocator locator;

    public DraftDocumentListObjects(WebDriver webDriver) {
        this.locator = new DraftDocumentListLocator();
        this.setWebDriver(webDriver);
    }

    //Find search input element
    public WebElement findSearchInput() {
        return findWebElement(By.xpath(locator.getSearchInput()));
    }

    //Find "Tìm kiếm" search button element
    public WebElement findSearchBtn() {
        return findWebElement(By.xpath(locator.getSearchBtn()));
    }

    //Find "Thêm mới" button element
    public WebElement findAddNewBtn() {
        return findWebElement(By.xpath(locator.getAddNewBtn()));
    }

    //Find "Trích yếu" link of the first row in the result table
    public WebElement findFirstRowSummaryLink() {
        return findWebElement(By.xpath(locator.getFirstRowSummaryLink()));
    }

    //Get all td cells of the first row -> used to read column values for verification
    public static String summaryColumn, loaiVBColumn, phanLoaiColumn, loaiBHColumn, ngayVBColumn, nguoiLapColumn;

    //Find the first cell of the first row, waiting for it to be visible. Used to make sure the
    //DataGrid has at least one row rendered before reading all cells - avoids a race condition
    //where getListWebElement() runs while the grid is still empty right after a page reload.
    //waitForElementVisible() swallows timeouts and returns null instead of throwing, so we
    //re-throw explicitly here to let the outer FluentWait retry correctly.
    public WebElement findFirstRowFirstCell() {
        WebElement cell = findWebElement(By.xpath(locator.getFirstRowCells() + "[1]"));
        if (cell == null) {
            throw new org.openqa.selenium.NoSuchElementException("First row of the result table is not rendered yet: " + locator.getFirstRowCells());
        }
        return cell;
    }

    public void getInfoFirstRow() {
        findFirstRowFirstCell(); // wait until at least the first cell is visible
        List<WebElement> cells = getListWebElement(By.xpath(locator.getFirstRowCells()));
        summaryColumn = cells.get(0).getText();
        loaiVBColumn = cells.get(1).getText();
        phanLoaiColumn = cells.get(2).getText();
        loaiBHColumn = cells.get(3).getText();
        ngayVBColumn = cells.get(4).getText();
        nguoiLapColumn = cells.get(5).getText();
    }

    //Actions
    public void inputSearchKeyword(String keyword) {
        inputText(findSearchInput(), "Tu khoa tim kiem", keyword);
    }

    public void clickSearchBtn() {
        clickTo(findSearchBtn(), "Click Tim kiem button");
    }

    public void clickAddNewBtn() {
        clickTo(findAddNewBtn(), "Click to add new draft document");
    }

    //Verify list page is displayed
    public void verifyListPageIsDisplayed() {
        Assert.assertTrue(findSearchInput().isDisplayed(), "Draft document list page is not displayed");
    }
}
