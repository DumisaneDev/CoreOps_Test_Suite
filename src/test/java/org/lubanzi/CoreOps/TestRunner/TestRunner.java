package org.lubanzi.CoreOps.TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "org.lubanzi.CoreOps.StepDefinitions",
        plugin = {"pretty",
                  "html:target/CucumberReport.html",
                  "json:target/CucumberReport.json"
        },
        publish = true,
       tags = "@Dashboard or @DashboardDisplay"
)

public class TestRunner {
}
