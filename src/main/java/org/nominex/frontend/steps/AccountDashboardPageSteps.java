package org.nominex.frontend.steps;

import io.qameta.allure.Step;
import org.nominex.frontend.pages.AccountDashboardPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class AccountDashboardPageSteps {

    @Autowired
    AccountDashboardPage accountDashboardPage;

    @Autowired
    BaseSteps baseSteps;

    @Step("Authenticating login address: {0}")
    public void checkEmailAddressField(String emailAddress) {
        assertThat(accountDashboardPage.getEmailAddress()).isEqualTo(emailAddress);
    }

    @Step("Opening \"TRX staking\" page via pop-up header menu")
    public void openTRXPage() {
        accountDashboardPage.clickTradeAndEarnSelector().clickStakingButton();
    }

    public void checkURL() {
        baseSteps.checkURL(accountDashboardPage);
    }
}
