package org.nominex.frontend.steps;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.nominex.frontend.pages.BasePage;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class BaseSteps {

    @Step("Opening page with URL: {0}")
    public void openPage(String urlAddress) {
        open(urlAddress);
    }

    @Step("Checking page URL")
    public void checkURL(BasePage basePage) {
        assertThat(url()).endsWith(basePage.getExpectedURL());
    }

    @Step("Deleting all cookies")
    public void deleteAllCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
    }
}
