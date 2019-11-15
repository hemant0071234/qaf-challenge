package com.hellofresh.challenge.driver;

import com.hellofresh.challenge.constants.Browser;
import com.hellofresh.challenge.utils.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.log4testng.Logger;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static Logger log = Logger.getLogger(DriverManager.class);

    public static WebDriver getWebDriver() {
        Browser browser = PropertyUtils.getBrowser();
        WebDriver driver = getWebdriver(browser);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.setProperty("webdriver.timeouts.implicitlywait", "30");

        return driver;
    }

    private static WebDriver getWebdriver(Browser browser) {
        log.info(String.format("Starting '%s' browser", browser));

        switch (browser) {
            case FIREFOX:

                WebDriverManager.firefoxdriver().setup();

                return new FirefoxDriver();

            case OPERA:

                WebDriverManager.operadriver().setup();

                return new OperaDriver();

            case EDGE:

                if (System.getProperty("os.name").contains("Windows")) {
                    log.info("Client Machine is windows");
                    WebDriverManager.edgedriver().setup();

                    return new EdgeDriver();
                } else {
                    log.info("Client Machine is not windows");
                }

            case CHROME:

                WebDriverManager.chromedriver().setup();

                return new ChromeDriver();

            default:
                throw new UnsupportedOperationException("Attempt to start invalid browser " + browser);
        }
    }

}