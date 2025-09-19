package org.lubanzi.Core_Ops.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public interface IPageObjects {
        public void configureWaitMechanism(Duration waitTime);

        public String getPageElementContent(By locator);

        public WebElement getElement(By locator);

        public void clickElement(By locator);

        public void sendKeys(By locator, String keys);

        public String getPageTitle();

        public void navigateToTestSite(String URL);

        public String getUrl();
}
