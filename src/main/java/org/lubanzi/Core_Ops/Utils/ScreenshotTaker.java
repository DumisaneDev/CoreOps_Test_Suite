package org.lubanzi.Core_Ops.Utils;

import org.apache.commons.io.FileUtils;
import org.lubanzi.Core_Ops.PageObjects.BasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotTaker extends BasePage {
        private String srcPath;
        private String srcName;

        public void set_srcPath(String newPath) {
                this.srcPath = newPath;
        }

        public void set_srcName(String newSrcName) {
                this.srcName = newSrcName;
        }

        public ScreenshotTaker(String srcShotPath, String srcShotName, WebDriver srcDriver) {
                super(srcDriver);
                set_srcPath(srcShotPath);
                set_srcName(srcShotName);
        }

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
