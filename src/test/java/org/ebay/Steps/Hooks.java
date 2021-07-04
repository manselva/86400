package org.ebay.Steps;

import Utils.CSVUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.ebay.Base.BaseUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.petstore.RestApi.RestFunctionalLibrary;

import java.util.concurrent.TimeUnit;


public class Hooks extends BaseUtil {

    private static final Logger log = Logger.getLogger(Hooks.class);
    private static final RestFunctionalLibrary rApi = new RestFunctionalLibrary();
    private final BaseUtil base;

    public Hooks(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void initializeDriverAndApi() throws Exception {
        String browser = "Chrome";
        log.info("Setting up Web Driver: " + browser);
        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            base.driver = new ChromeDriver();
        }
        base.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        base.driver.manage().window().maximize();
        log.info("Loading the CSV File");
        CSVUtil.loadCSV();
        log.info("Setting up Rest Base URI and API Key");
        rApi.setBaseURI(CSVUtil.getTestData("uri"));
        rApi.setContentType("JSON");
    }

    @After
    public void tearDown(Scenario scenario) {
        log.info("Verify Scenario failed and take a screenshot");
        if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        log.info("Verify Driver exists and tear down the driver");
        if (base.driver != null) {
            base.driver.close();
            base.driver.quit();
        }
    }
}
