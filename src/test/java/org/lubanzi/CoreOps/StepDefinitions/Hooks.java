package org.lubanzi.CoreOps.StepDefinitions;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.lubanzi.Core_Ops.Utils.ConfigReader;
import org.lubanzi.Core_Ops.Utils.ScreenshotTaker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hooks class manages the lifecycle of test execution, including WebDriver setup and screenshot handling.
 * Provides setup and teardown functionality for Cucumber test scenarios.
 */
public class Hooks {
        public static WebDriver driver;
        public static String TEST_URL = ConfigReader.getProperty("testURL");
        private final String srcPath = ConfigReader.getProperty("Screenshot_Path");

        /**
         * Setup hook that runs before each scenario.
         * Initializes and configures the Firefox WebDriver.
         */
        @Before
        public void webdriverSetup(){
                String browser = ConfigReader.getProperty("Browser");
                driver = BrowserConfig(browser.toLowerCase());
                driver.manage().window().maximize();
        }

        private WebDriver BrowserConfig(String browser){
                WebDriverManager.chromedriver().setup();
                WebDriverManager.firefoxdriver().setup();
                WebDriverManager.edgedriver().setup();
                return switch (browser) {
                        case "firefox" -> new FirefoxDriver();
                        case "chrome" ->new ChromeDriver();
                        case "edge" -> new EdgeDriver();
                        default -> throw new IllegalStateException("Unexpected value: " + browser);
                };
        }

        /**
         * Teardown hook that runs after each scenario.
         * Ensures proper cleanup of WebDriver resources.
         */
        @After
        public void teardown(){
                driver.quit();
        }

        /**
         * AfterStep hook that captures screenshots when steps fail.
         * Takes a screenshot and logs the failure when a step fails during execution.
         * @param StepInfo Current scenario information
         */
        @AfterStep
        public void ScreenShotUtil(Scenario StepInfo){
                ScreenshotTaker SrcShotTaker = new ScreenshotTaker(srcPath,(StepInfo.getName() + " " + StepInfo.getId()) , driver);
                if(StepInfo.isFailed()){
                        System.out.printf("Step Failed: %s", StepInfo.getName());
                        SrcShotTaker.takeScreenShot();
                }
        }

}