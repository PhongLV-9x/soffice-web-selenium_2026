package com.soffice.projects.pages.pages;

import com.soffice.projects.common.BasePage;
import com.soffice.projects.pages.objects.HomePageObjects;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class HomePage extends BasePage {
    private HomePageObjects homePageObjects;

    public HomePage() {
        this.homePageObjects = new HomePageObjects(this.webDriver);
    }

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.homePageObjects = new HomePageObjects(webDriver);
    }

    //Verify home page is displayed
    public HomePage verifyHomePageIsDisplayed() {
        homePageObjects.verifyHomePageIsDisplayed();
        return this;
    }

    //Navigate to other modules from sidebar menu
    //(Placeholder pages - create dedicated Locator/Objects/Page for each module when implementing test cases for it)
}
