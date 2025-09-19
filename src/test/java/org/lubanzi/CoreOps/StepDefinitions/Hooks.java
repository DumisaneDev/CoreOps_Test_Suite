package org.lubanzi.CoreOps.StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.lubanzi.Core_Ops.Utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {

        public static WebDriver driver;
        public static String TEST_URL = ConfigReader.getProperty("testURL");

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
}
