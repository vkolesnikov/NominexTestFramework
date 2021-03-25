package org.nominex.frontend;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BaseTest extends AbstractTestNGSpringContextTests {

    @BeforeSuite(alwaysRun = true)
    private void prepareDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications").addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        setWebDriver(new ChromeDriver(chromeOptions));
    }

    @AfterSuite(alwaysRun = true)
    private void closeDriver() {
        Selenide.closeWebDriver();
    }

}
