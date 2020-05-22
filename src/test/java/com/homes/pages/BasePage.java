package com.homes.pages;

import com.homes.share.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    protected Helpers helpers;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
        helpers = new Helpers();
    }
}

