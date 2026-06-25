package com.soffice.projects.pages.objects;

import com.soffice.projects.pages.locator.SignLevelLocator;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

@Getter
@Setter
public class SignLevelObjects extends BaseObjects {
    private SignLevelLocator locator;

    public SignLevelObjects(WebDriver webDriver) {
        this.locator = new SignLevelLocator();
        this.setWebDriver(webDriver);
    }

    //Find elements
    public WebElement findPopupTitle() {
        return findWebElement(By.xpath(locator.getPopupTitle()));
    }

    public WebElement findChooseBtnBySignLevel(String signLevelName) {
        return findWebElement(By.xpath(String.format(locator.getChooseBtnBySignLevel(), signLevelName)));
    }

    public WebElement findChooseUserSearchInput() {
        return findWebElement(By.xpath(locator.getChooseUserSearchInput()));
    }

    public WebElement findUserRowByUsername(String username) {
        return findWebElement(By.xpath(String.format(locator.getUserRowByUsername(), username)));
    }

    public WebElement findChooseUserSaveBtn() {
        return findWebElement(By.xpath(locator.getChooseUserSaveBtn()));
    }

    public WebElement findSaveAndForwardBtn() {
        return findWebElement(By.xpath(locator.getSaveAndForwardBtn()));
    }

    //Actions
    //Click an element with retry on Stale/ClickIntercepted exceptions. The element finder is
    //re-invoked on every attempt so a fresh WebElement is always used (DevExtreme can re-render
    //the DOM right between findElement() and click()). The actual click is done via JavaScript
    //(scrollIntoView + click()) rather than a native Selenium click, because elements in these
    //popups are frequently covered by a fixed header row or sit outside the current viewport -
    //both cause ElementClickInterceptedException with a native click, but not with a JS click.
    private void clickWithRetry(java.util.function.Supplier<WebElement> elementFinder, String title) {
        int maxAttempts = 5;
        RuntimeException lastException = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                WebElement element = elementFinder.get();
                scrollToElement(element);
                clickElementViaJs(element, title);
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                lastException = e;
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                lastException = e;
            }
        }
        throw lastException;
    }

    //Same retry pattern, for typing text into an element instead of clicking it. Typing still
    //uses the normal inputText() (which needs the element interactable for sendKeys to work),
    //but the click that focuses the field beforehand goes through clickWithRetry above.
    private void inputTextWithRetry(java.util.function.Supplier<WebElement> elementFinder, String title, String value) {
        int maxAttempts = 5;
        org.openqa.selenium.StaleElementReferenceException lastException = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                inputText(elementFinder.get(), title, value);
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                lastException = e;
            }
        }
        throw lastException;
    }

    //Verify popup "Chuyển tiếp" is displayed. Uses ExpectedConditions directly inside until()
    //rather than calling findPopupTitle() (which already wraps its own 20s wait) - nesting two
    //independent 20s waits would make each retry attempt take up to 20s, causing the whole
    //check to hang far longer than intended.
    public void verifyPopupIsDisplayed() {
        try {
            getWaitDriver().until(org.openqa.selenium.support.ui.ExpectedConditions
                    .visibilityOfElementLocated(By.xpath(locator.getPopupTitle())));
        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail("Popup 'Chuyển tiếp' is not displayed - title element not found with locator: " + locator.getPopupTitle());
        }
    }

    //Click "Chọn" button for a given sign level row (e.g. "Cấp 1 ký", "Cấp 2 ký")
    public void clickChooseBtnBySignLevel(String signLevelName) {
        clickWithRetry(() -> findChooseBtnBySignLevel(signLevelName), "Click 'Chọn' for sign level: " + signLevelName);
    }

    //Type the username into the "Danh sách người dùng" filter input.
    //The filter input is only interactable after clicking into it first (DevExtreme filter row
    //behavior). The popup can keep re-rendering or have its filter row covered by a fixed
    //header for a short moment right after it opens, so the click goes through clickWithRetry
    //(scroll + JS click + retry) instead of a single native click.
    public void inputChooseUserSearchKeyword(String username) {
        clickWithRetry(this::findChooseUserSearchInput, "Click into search input before typing");
        inputTextWithRetry(this::findChooseUserSearchInput, "Tu khoa tim nguoi ky", username);
    }

    //Click the matching user row to select it
    public void clickUserRowByUsername(String username) {
        clickWithRetry(() -> findUserRowByUsername(username), "Select user row: " + username);
    }

    //Click "Lưu" to confirm the chosen user, closing the "Danh sách người dùng" popup
    public void clickChooseUserSaveBtn() {
        clickWithRetry(this::findChooseUserSaveBtn, "Click 'Lưu' to confirm chosen user");
    }

    //Click "Lưu và chuyển duyệt" to finalize the whole sign-level update flow.
    //The "Chuyển tiếp" popup can be larger than the viewport, so the button may be covered by
    //another element or sit outside the visible area, causing a normal click to throw
    //ElementClickInterceptedException. clickWithRetry already scrolls + clicks via JavaScript,
    //which bypasses the pixel-visibility check that a native click relies on.
    public void clickSaveAndForwardBtn() {
        clickWithRetry(this::findSaveAndForwardBtn, "Click 'Lưu và chuyển duyệt'");
    }
}
