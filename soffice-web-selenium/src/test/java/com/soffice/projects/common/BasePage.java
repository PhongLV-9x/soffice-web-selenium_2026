package com.soffice.projects.common;

import com.soffice.consts.FrameConst;
import com.soffice.consts.FrameConst.LogType;
import com.soffice.controller.WebUI;
import com.soffice.driver.DriverManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static com.soffice.report.ReportConfig.*;

/**
 * Create a base methods used for subpage to interact with elements
 */
@Getter
@Setter
@Slf4j
public class BasePage extends WebUI {
    public WebDriver webDriver;

    /**
     * Initial a new instance
     */
    public BasePage() {
        webDriver = DriverManager.getDriver();
    }

    //region Redirect to Page

    /**
     * Go to specific URL
     *
     * @param URL       : URL Page
     * @param pageTitle : Page title
     */
    protected void goToSpecificURL(String URL, String pageTitle) {
        goToURL(URL);
        assertTrueCondition(null, verifyPageUrl(URL), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, String.format("Verify the '%s' page", pageTitle));
        String msg = BOLD_START + Icon.ICON_NAVIGATE_RIGHT + " Go to URL : " + BOLD_END + DriverManager.getDriver().getCurrentUrl();
        addReportInfo(LogType.INFO, msg, null, null);
    }

    /* Add gotoXxxPage() helper methods here as new modules/pages are implemented,
     * e.g.:
     * public SomeModulePage gotoSomeModulePage() {
     *     ProjectConst.ModuleURL module = ProjectConst.ModuleURL.SOME_MODULE;
     *     goToSpecificURL(module.getPath(), module.getName());
     *     return new SomeModulePage();
     * }
     */
    //endregion
}

