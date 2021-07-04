package org.ebay.Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/org/ebay/Features"},
        glue = {"org/ebay/Steps"},
        plugin = {"json:target/cucumber.json", "pretty",
                "html:target/site/cucumber-reports"},
        monochrome = true)
public class TestRunner {
}
