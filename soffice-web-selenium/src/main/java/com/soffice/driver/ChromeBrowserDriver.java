package com.soffice.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

import static com.soffice.consts.FrameConst.ExecuteConfig.HEADLESS_FLAG;

public class ChromeBrowserDriver extends BrowserDriver {
    @Override
    public WebDriver createDriver(boolean... isLoadings) {
        return new ChromeDriver((ChromeOptions) getOptions(isLoadings));
    }

    @Override
    public MutableCapabilities getOptions(boolean... isLoadings) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model, useful for CI
        chromeOptions.setAcceptInsecureCerts(true);

        if (HEADLESS_FLAG) chromeOptions.addArguments("--headless=new");

        LoggingPreferences logProfs = new LoggingPreferences();
        logProfs.enable(LogType.PERFORMANCE, Level.ALL);
        chromeOptions.setCapability("goog:loggingPrefs", logProfs);
        if (isLoadings.length > 0 && isLoadings[0])
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        return chromeOptions;
    }
}
