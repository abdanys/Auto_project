package com.homes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPageObject extends BasePage {

    public static final String siteURL = "https://www.homes.com/";

    public SearchPageObject(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(siteURL);
    }

    private WebElement getSearchInput() {
        return wait.until(x -> x.findElement(By.cssSelector("input[data-testid='HP-SearchInput']")));
    }

    public WebElement getPlaceInList() {
        return wait.until(x -> x.findElement(By.cssSelector("li[data-testid='HP-AS-Places-0']")));
    }

    public void doSearch(String location) {
        if (waitUtilCurtainGone()) {
            getSearchInput().sendKeys(location);
            helpers.registerLog("Location to search has been enter: " + location);
            getPlaceInList().click();
            helpers.registerLog("Autosuggestion has been selected");
        }
    }

    public boolean waitUtilCurtainGone() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("disable-scroll")));
    }
}