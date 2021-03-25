package org.nominex.frontend;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.nominex.frontend.pages.TRXPage.INCOME_PERIOD;
import org.nominex.frontend.steps.AccountDashboardPageSteps;
import org.nominex.frontend.steps.BaseSteps;
import org.nominex.frontend.steps.SignInPageSteps;
import org.nominex.frontend.steps.TRXPageSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.Map;

import static org.nominex.utils.TestConstants.*;

@Epic("TRX page Tests")
@Test(groups = "FE")
@ContextConfiguration(classes = FrontEndConfiguration.class)
public class TRXPageTest extends BaseTest {

    @Value("${trx.url}")
    String trxPageAddress;

    @Autowired
    private SignInPageSteps signInPageSteps;

    @Autowired
    private TRXPageSteps trxPageSteps;

    @Autowired
    private AccountDashboardPageSteps accountDashboardPageSteps;

    @Autowired
    private BaseSteps baseSteps;

    @Story("Correct logging flow")
    @Test(description = "Correct logging from TRX staking page", priority = 0)
    public void correctLoginTest() {
        baseSteps.openPage(trxPageAddress);
        trxPageSteps.clickSignInButton();
        signInPageSteps.login(EMAIL_ADDRESS, PASSWORD);
        accountDashboardPageSteps.checkEmailAddressField(EMAIL_ADDRESS);
        accountDashboardPageSteps.checkURL();
    }

    @Story("Correct logging flow")
    @Test(description = "Checking availability of additional functioning on TRX staking page", dependsOnMethods = {"correctLoginTest"})
    public void checkTRXLoginOptionsTest() {
        accountDashboardPageSteps.openTRXPage();
        trxPageSteps.loadingTRXPage();
        trxPageSteps.checkURL();
        trxPageSteps.checkVisibilityOfStackingHistoryButton(true);
    }

    @Story("Earning calculator tests")
    @Test(description = "Checking earnings calculator", dependsOnMethods = {"checkTRXLoginOptionsTest"}, dataProvider = "trxStakes")
    public void checkCalculatorFunctioningTest(String[] trxStake) {
        Map<INCOME_PERIOD, BigDecimal> actualIncomeValues = trxPageSteps.getIncomeValues(trxStake[0]);
        Map<INCOME_PERIOD, BigDecimal> expectedIncomeValues = trxPageSteps.calculateIncomeValues(trxStake[0].replaceAll(",","."));
        SoftAssertions softly = new SoftAssertions();
        for (INCOME_PERIOD incomePeriod: INCOME_PERIOD.values()) {
            softly.assertThat(expectedIncomeValues.get(incomePeriod)).isEqualTo(actualIncomeValues.get(incomePeriod));
        }
        softly.assertAll();
    }

    @Story("Coin historical yield table tests")
    @BeforeGroups(description = "Opening coin historical yield table on TRX page", value = {"YieldTable"}, alwaysRun = true)
    public void openCoinHistoricalYieldTable() {
        baseSteps.openPage(trxPageAddress);
        trxPageSteps.loadingTRXPage();
        trxPageSteps.clickCoinHistoricalYieldButton();
    }

    @Story("Coin historical yield table tests")
    @Test(description = "Checking content on 1st table", priority = 1, groups = {"YieldTable"})
    public void checkCoinHistoricalYieldFirstTableTest() {
        trxPageSteps.checkCoinHistoricalYieldRow(ROW1_1);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW1_4);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW1_10);
    }

    @Story("Coin historical yield table tests")
    @Test(description = "Checking content on 2nd table after clicking on the next button",
            groups = {"YieldTable"}, dependsOnMethods = {"checkCoinHistoricalYieldFirstTableTest"})
    public void checkCoinHistoricalYieldNavigationPanelNextButtonTest() {
        trxPageSteps.clickCoinHistoricalYieldTableNextButton();
        trxPageSteps.checkCoinHistoricalYieldRow(ROW2_5);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW2_10);
    }

    @Story("Coin historical yield table tests")
    @Test(description = "Checking content on last 11th page",
            groups = {"YieldTable"}, dependsOnMethods = {"checkCoinHistoricalYieldNavigationPanelNextButtonTest"})
    public void checkCoinHistoricalYieldNavigationPanelLastTableButtonTest() {
        trxPageSteps.clickCoinHistoricalYieldTableCountButton(LAST_TABLE_BUTTON_INDEX);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW11_1);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW11_4);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW11_8);
    }

    @Story("Coin historical yield table tests")
    @Test(description = "Checking content on 10th page after clicking on the previous button",
            groups = {"YieldTable"}, dependsOnMethods = {"checkCoinHistoricalYieldNavigationPanelLastTableButtonTest"})
    public void checkCoinHistoricalYieldNavigationPanelPreviousButtonTest() {
        trxPageSteps.clickCoinHistoricalYieldTablePreviousButton();
        trxPageSteps.checkCoinHistoricalYieldRow(ROW10_1);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW10_6);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW10_10);
    }

    @Story("Coin historical yield table tests")
    @Test(description = "Checking content on 5th page after clicking on the fast come back button",
            groups = {"YieldTable"}, dependsOnMethods = {"checkCoinHistoricalYieldNavigationPanelPreviousButtonTest"})
    public void checkCoinHistoricalYieldNavigationPanelFastBackButtonTest() {
        trxPageSteps.clickCoinHistoricalYieldTableCountButton(2);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW5_3);
    }

    @Story("Coin historical yield table tests")
    @Test(description = "Checking content on 4th page after clicking on this in navigation panel",
            groups = {"YieldTable"}, dependsOnMethods = {"checkCoinHistoricalYieldNavigationPanelFastBackButtonTest"})
    public void checkCoinHistoricalYieldNavigationInnerButtonTest() {
        trxPageSteps.clickCoinHistoricalYieldTableCountButton(3);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW4_4);
    }

    @Story("Coin historical yield table tests")
    @Test(description = "Checking content on 9th page after clicking on the fast flip button",
            groups = {"YieldTable"}, dependsOnMethods = {"checkCoinHistoricalYieldNavigationInnerButtonTest"})
    public void checkConHistoricalYieldNavigationPanelFastFlipThroughButtonTest() {
        trxPageSteps.clickCoinHistoricalYieldTableCountButton(LAST_TABLE_BUTTON_INDEX - 1);
        trxPageSteps.checkCoinHistoricalYieldRow(ROW9_10);
    }

    @DataProvider(name = "trxStakes")
    public Object[][] trxStakes() {
        return TRX_STAKES;
    }

}
