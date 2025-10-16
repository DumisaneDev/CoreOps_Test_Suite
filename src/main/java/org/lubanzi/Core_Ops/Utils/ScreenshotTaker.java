package org.lubanzi.Core_Ops.Utils;

import org.apache.commons.io.FileUtils;
import org.lubanzi.Core_Ops.PageObjects.BasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * ScreenshotTaker provides functionality for capturing and saving screenshots during test execution.
 * Extends BasePage to inherit common page operations.
 */
public class ScreenshotTaker extends BasePage {
        private String srcPath;
        private String srcName;

        /**
         * Sets the source path for screenshot storage
         * @param newPath New path where screenshots will be saved
         */
        public void set_srcPath(String newPath) {
                this.srcPath = newPath;
        }

        /**
         * Sets the source name for the screenshot file
         * @param newSrcName Name to use for the screenshot file
         */
        public void set_srcName(String newSrcName) {
                this.srcName = newSrcName;
        }

        /**
         * Initializes the ScreenshotTaker with the specified path, name, and WebDriver instance.
         * @param srcShotPath Path where screenshots will be saved
         * @param srcShotName Name to use for the screenshot file
         * @param srcDriver WebDriver instance to use for screenshot capture
         */
        public ScreenshotTaker(String srcShotPath, String srcShotName, WebDriver srcDriver) {
                super(srcDriver);
                set_srcPath(srcShotPath);
                set_srcName(srcShotName);
        }

        /**
         * Captures and saves a screenshot of the current browser window.
         * The screenshot is saved as a JPEG file using the configured path and name.
         */
        public void takeScreenShot() {
                File srcFile = ((TakesScreenshot) _driver).getScreenshotAs(OutputType.FILE);
                try {
                        FileUtils.copyFile(srcFile, new File(String.format("%s %s.jpeg", this.srcPath, this.srcName)));
                } catch (IOException e) {
                        e.printStackTrace();
                        System.out.printf("Couldn't take screenshot, Exception: %s", e);
                }
        }


}
