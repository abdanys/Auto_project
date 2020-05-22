package com.homes.tests;

import com.homes.domain.ListingProperty;
import com.homes.pages.ListingsPageObject;
import com.homes.pages.PropertyPageObject;
import com.homes.pages.SearchPageObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

public class SearchTest extends BaseTests  {

    @DataProvider(name = "location")
    public Object[][] locationDataProvider() {
        return new Object[][] { { "Miami, FL", 6 }, {"Los Angeles, CA", 6}, {"Seattle, WA", 6} };
    }

    @Test(description="Search Listings by Location", dataProvider = "location")
    public void searchListingsByLocation(String location, int listingsToCheck)   {

        SearchPageObject searchPage = new SearchPageObject(driver);
        ListingsPageObject listingsPage = new ListingsPageObject(driver);
        PropertyPageObject propertyPage = new PropertyPageObject(driver);

        // Search by location
        searchPage.open();
        searchPage.doSearch(location);

        searchPage.waitUtilCurtainGone();

        // Check there are results
        int numberOfListings = listingsPage.getListingsOnPage();
        Assert.assertTrue(numberOfListings > 0, "No listings found");
        helpers.registerLog("Listing page has results");

        // Check if the result is enough for the number of listings to check
        listingsToCheck = numberOfListings > listingsToCheck ? listingsToCheck : numberOfListings;
        helpers.registerLog("Listings to be checked: " + listingsToCheck);

        // Selecting SORT
        helpers.registerLog("Sort displayed: " + listingsPage.getSortCritiria());

        Set<Integer> randomListings = helpers.getRandomIndexes(listingsToCheck, numberOfListings);

        ListingProperty listingProperty = new ListingProperty();
        for(Integer index : randomListings){
            helpers.registerLog("");
            helpers.registerLog("Listing index to be checked: " + index);

            // Get Data From Listing to be checked
            listingProperty.setPrice(listingsPage.getListingPrice(index));
            helpers.registerLog("Listing Price to be checked: " + listingProperty.getPrice());

            listingProperty.setAddress(listingsPage.getListingAddress(index));
            helpers.registerLog("Listing Address to be checked: " + listingProperty.getAddress());

            // Navigate to Property Details
            listingsPage.getListing(index).click();
            searchPage.waitUtilCurtainGone();

            // Check property data with listing page
            helpers.registerLog("Property Price Found: " + propertyPage.getPropertyPrice());
            helpers.registerLog("Property Price Expected: " + listingProperty.getPrice());
            softAssert.assertEquals(listingProperty.getPrice(), propertyPage.getPropertyPrice(), "Property price failed on index: " + index);

            helpers.registerLog("Property Address Found: " + propertyPage.getPropertyAddress());
            helpers.registerLog("Property Address Expected: " + listingProperty.getAddress());
            softAssert.assertEquals(listingProperty.getAddress(), propertyPage.getPropertyAddress(), "Property address failed on index: " + index);

            driver.navigate().back();
            searchPage.waitUtilCurtainGone();
        }

        // Collect assertions
        softAssert.assertAll();
    }

}