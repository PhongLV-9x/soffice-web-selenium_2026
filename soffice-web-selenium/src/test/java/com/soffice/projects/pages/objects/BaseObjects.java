package com.soffice.projects.pages.objects;

import com.soffice.driver.DriverManager;
import com.soffice.projects.common.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class BaseObjects extends BasePage {

    protected WebDriver webDriver;

    public BaseObjects() {
        this.webDriver = DriverManager.getDriver();
    }
}
