package org.nominex.frontend.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

@Component
public class AccountDashboardPage extends BasePage {


    private static final String ACCOUNT_DASHBOARD_PAGE_URL = "/account/";

    private SelenideElement emailAddressField = $x("//*[@class = 'mt-12']");

    private SelenideElement tradeAndEarnSelector = $x("//span[contains(text(), 'Trade & Earn')]");
    private SelenideElement stakingButton = $x("//*[@href = '/staking/']");

    public String getEmailAddress() {
        emailAddressField.shouldBe(Condition.appear, Duration.ofSeconds(5));
        return emailAddressField.getText();
    }

    public AccountDashboardPage clickTradeAndEarnSelector() {
        tradeAndEarnSelector.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();
        return this;
    }

    public AccountDashboardPage clickStakingButton() {
        stakingButton.shouldBe(Condition.enabled, Duration.ofSeconds(5));
        if (!stakingButton.isDisplayed()) {
            clickTradeAndEarnSelector();
        }
        stakingButton.click();
        return this;
    }

    @Override
    public String getExpectedURL() {
        return ACCOUNT_DASHBOARD_PAGE_URL;
    }
}
