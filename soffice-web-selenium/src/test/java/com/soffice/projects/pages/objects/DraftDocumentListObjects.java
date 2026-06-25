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
    //Type the search keyword directly: clear the field then sendKeys, bypassing inputText()'s
    //isSameValueOfElement() shortcut. After navigating back to the list page by URL, the
    //search input can retain a leftover value from a previous state; if it happens to match
    //the new keyword by the shortcut's own (possibly loose) comparison, inputText() would skip
    //typing entirely, silently searching with the wrong/old keyword.
    public void inputSearchKeyword(String keyword) {
        WebElement input = findSearchInput();
        input.clear();
        input.sendKeys(keyword);
        //Verify the field actually holds the new value before moving on, since Angular/DevExtreme
        //may need a brief moment to bind the typed value before a search submit will use it.
        getWaitDriver(5).until(d -> keyword.equals(findSearchInput().getAttribute("value")));
    }

    //Click via scroll + JavaScript, consistent with SignLevelObjects, since these elements can
    //be covered by a fixed header row or sit outside the viewport, causing
    //ElementClickInterceptedException with a native Selenium click.
    public void clickSearchBtn() {
        WebElement btn = findSearchBtn();
        scrollToElement(btn);
        clickElementViaJs(btn, "Click Tim kiem button");
    }

    public void clickAddNewBtn() {
        WebElement btn = findAddNewBtn();
        scrollToElement(btn);
        clickElementViaJs(btn, "Click to add new draft document");
    }

    //Verify list page is displayed
    public void verifyListPageIsDisplayed() {
        Assert.assertTrue(findSearchInput().isDisplayed(), "Draft document list page is not displayed");
    }
}
