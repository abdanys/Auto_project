package com.homes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListingsPageObject extends BasePage {

    public ListingsPageObject(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getListingListOnPage() {
        return wait.until(x -> x.findElements(By.cssSelector("article[data-tl-object='SR-ListingCell']")));
    }

    public int getListingsOnPage() {
        int listSize = getListingListOnPage().size();
        return listSize;
    }

    public WebElement getSortDD() {
        return wait.until(x -> x.findElement(By.cssSelector("div[data-tl-object='SR-Sort']")));
    }

    public String getSortCritiria() {
        return getSortDD().getText();
    }

    public WebElement getListing(int index) {
        return getListingListOnPage().get(index);
    }

    public String getListingPrice(int index) {
        return getListing(index).findElement(By.cssSelector("span[data-testid='price']")).getText();
    }

    public String getListingAddress(int index) {
        return getListing(index).findElement(By.cssSelector("a[data-testid='addr-link'] h2")).getText().replace(",", "");
    }

}