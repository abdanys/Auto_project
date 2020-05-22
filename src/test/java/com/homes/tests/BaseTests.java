package com.homes.tests;

import com.homes.share.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTests {

    public WebDriver driver;
    public Helpers helpers;
    public SoftAssert softAssert;

    @BeforeMethod(description = "Initializing Driver")
    public void startUp() {
        helpers = new Helpers();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        softAssert = new SoftAssert();
    }

    @AfterMethod(description = "Closing the driver")
    public void tearDown() throws InterruptedException {
        driver.quit();
    }

}
