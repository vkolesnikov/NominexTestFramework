package org.nominex.frontend.steps;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.nominex.frontend.models.CoinHistoricalYieldRow;
import org.nominex.frontend.pages.TRXPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Component
public class TRXPageSteps {

    @Value("${trx.income.coefficient.daily}")
    private BigDecimal dailyCoefficient;

    @Value("${trx.income.coefficient.monthly}")
    private BigDecimal monthlyCoefficient;

    @Value("${trx.income.coefficient.yearly}")
    private BigDecimal yearlyCoefficient;

    @Autowired
    private TRXPage trxPage;

    @Autowired
    private BaseSteps baseSteps;

    @Step("Clicking on \"Sign in\" button")
    public void clickSignInButton() {
        trxPage.clickSignInButton();
    }

    @Step("Waiting for the TRX page to load")
    public void loadingTRXPage() {
        trxPage.loadingTRXPage();
    }

    @Step("Checking that \"Stacking history\" functionality availability - {0} ")
    public void checkVisibilityOfStackingHistoryButton(Boolean visibility) {
        assertThat(trxPage.stackingHistoryButtonDisplayedOption()).isEqualTo(visibility);
    }

    @Step("Searching income values for stake = {0}")
    public Map<TRXPage.INCOME_PERIOD, BigDecimal> getIncomeValues(String trxStake) {
        trxPage.setTRXStake(trxStake);
        Map<TRXPage.INCOME_PERIOD, BigDecimal> answer = new HashMap<>();
        answer.put(TRXPage.INCOME_PERIOD.DAILY_INCOME, new BigDecimal(trxPage.getIncomeValue(TRXPage.INCOME_PERIOD.DAILY_INCOME)));
        answer.put(TRXPage.INCOME_PERIOD.MONTHLY_INCOME, new BigDecimal(trxPage.getIncomeValue(TRXPage.INCOME_PERIOD.MONTHLY_INCOME)));
        answer.put(TRXPage.INCOME_PERIOD.YEARLY_INCOME, new BigDecimal(trxPage.getIncomeValue(TRXPage.INCOME_PERIOD.YEARLY_INCOME)));
        return answer;
    }

    @Step("Calculating expected income values for stake = {0}")
    public Map<TRXPage.INCOME_PERIOD, BigDecimal> calculateIncomeValues(String trxStake) {
        Map<TRXPage.INCOME_PERIOD, BigDecimal> answer = new HashMap<>();
        answer.put(TRXPage.INCOME_PERIOD.DAILY_INCOME, calculateIncomeValue(trxStake, dailyCoefficient));
        answer.put(TRXPage.INCOME_PERIOD.MONTHLY_INCOME, calculateIncomeValue(trxStake, monthlyCoefficient));
        answer.put(TRXPage.INCOME_PERIOD.YEARLY_INCOME, calculateIncomeValue(trxStake, yearlyCoefficient));
        return answer;
    }

    private BigDecimal calculateIncomeValue(String trxStake, BigDecimal periodCoefficient) {
        return new BigDecimal(trxStake).multiply(periodCoefficient).setScale(4, RoundingMode.HALF_UP);
    }

    @Step("Opening \"Coin Historical Yield\" table")
    public void clickCoinHistoricalYieldButton() {
        trxPage.scrollIntoViewCoinHistoricalYieldTable().clickCoinHistoricalYieldButton();
    }

    @Step("Checking yield in {0.tableIndex} table in {0.rowIndex} row")
    public void checkCoinHistoricalYieldRow(CoinHistoricalYieldRow coinHistoricalYieldRow) {
        Map<TRXPage.COIN_HISTORICAL_YIELD_COLUMN, String> answer = trxPage.getCoinHistoricalYieldRowByIndex(coinHistoricalYieldRow.getRowIndex());
        SoftAssertions softly = new SoftAssertions();

        for (TRXPage.COIN_HISTORICAL_YIELD_COLUMN coinHistoricalYieldColumn: TRXPage.COIN_HISTORICAL_YIELD_COLUMN.values()) {
            softly.assertThat(coinHistoricalYieldRow.getRowValue().get(coinHistoricalYieldColumn))
                    .isEqualTo(answer.get(coinHistoricalYieldColumn));
        }
        softly.assertAll();
    }

    public void checkURL() {
        baseSteps.checkURL(trxPage);
    }

    @Step("Clicking on \"next table ->\" button on the navigation bar")
    public void clickCoinHistoricalYieldTableNextButton() {
        trxPage.clickNextTableButton();
    }

    @Step("Clicking on \"previous table <-\" button on the navigation bar")
    public void clickCoinHistoricalYieldTablePreviousButton() {
        trxPage.clickPreviousTableButton();
    }

    @Step("Clicking on {0} button on the navigation bar")
    public void clickCoinHistoricalYieldTableCountButton(Integer index) {
        trxPage.clickNavigationButton(index);
    }

}
