package com.homes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PropertyPageObject extends BasePage {

    public PropertyPageObject(WebDriver driver) {
        super(driver);
    }

    public String getPropertyPrice() {
        return wait.until(x -> x.findElement(By.className("summary-price-display"))).getText();
    }

    public String getPropertyAddress() {
        return wait.until(x -> x.findElement(By.className("summary-address"))).getText().replace(",", "");
    }

}