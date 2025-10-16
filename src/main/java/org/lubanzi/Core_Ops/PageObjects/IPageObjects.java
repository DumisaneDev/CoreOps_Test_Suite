package org.lubanzi.Core_Ops.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/**
 * Interface defining common operations for page object pattern implementation.
 * Provides standard methods for web element interaction and navigation.
 */
public interface IPageObjects {
        void configureWaitMechanism(Duration waitTime);

        String getPageElementContent(By locator);

        WebElement getElement(By locator);

        void clickElement(By locator);

        void sendKeys(By locator, String keys);

        String getPageTitle();

        void navigateToTestSite(String URL);

        String getUrl();
}
