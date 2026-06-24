package com.soffice.projects.pages;

import com.soffice.driver.DriverManager;
import com.soffice.projects.pages.pages.HomePage;
import com.soffice.projects.pages.pages.LoginPage;

import static com.soffice.consts.FrameConst.AppConfig.APP_DOMAIN;


/**
 * Page management
 */
public class PageManagement {

    /* Access to the web page */
    public static LoginPage accessWebPage() {
        DriverManager.getDriver().get(APP_DOMAIN);
        return new LoginPage();
    }


    /* Go to the home page */
    public static HomePage gotoHomePage() {
        return new HomePage();
    }
}