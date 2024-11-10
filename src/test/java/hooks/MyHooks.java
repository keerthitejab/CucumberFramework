package hooks;

import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class MyHooks {

    public Logger logger;
    WebDriver driver;

    @Before
    public void setup() {

        logger= LogManager.getLogger(this.getClass());

        Properties prop = new ConfigReader().intializeProperties();
        driver = DriverFactory.initializeBrowser(prop.getProperty("browser"));
        driver.get(prop.getProperty("url"));

    }

    @After
    public void tearDown(Scenario scenario) {

        String scenarioName = scenario.getName().replaceAll(" ","_");

        if(scenario.isFailed()) {

            byte[] srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(srcScreenshot,"image/png", scenarioName);
        }

        driver.quit();


    }

}
