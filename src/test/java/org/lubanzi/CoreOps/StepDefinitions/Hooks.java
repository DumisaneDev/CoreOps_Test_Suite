package org.lubanzi.CoreOps.StepDefinitions;

import io.cucumber.java.*;
import org.lubanzi.Core_Ops.Utils.ConfigReader;
import org.lubanzi.Core_Ops.Utils.ScreenshotTaker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {
        public static WebDriver driver;
        public static String TEST_URL = ConfigReader.getProperty("testURL");
        private String srcPath = ConfigReader.getProperty("Screenshot_Path");

        @Before
        public void webdriverSetup(){
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
        }

        @After
        public void teardown(){
                if(driver == null){
                        driver = new FirefoxDriver();
                }
                driver.quit();
        }

        @AfterStep
        public void ScreenShotUtil(Scenario StepInfo){
                ScreenshotTaker SrcShotTaker = new ScreenshotTaker(srcPath,(StepInfo.getName() + " " + StepInfo.getId()) , this.driver);
                if(StepInfo.isFailed()){
                        System.out.printf("Step Failed: %s", StepInfo.getName());
                        SrcShotTaker.takeScreenShot();
                }
        }

}
