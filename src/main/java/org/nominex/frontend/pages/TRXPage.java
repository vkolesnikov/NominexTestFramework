package org.nominex.frontend.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

@Component
public class TRXPage extends BasePage {

    private static final String TRX_PAGE_URL = "/staking/TRX/";

    private final String TRX_PAGE_TITLE = "Staking is a great way to maximize your holdings. Activate it, " +
            "so coins and fiat that are held on your regular Nominex accounts can bring you much more!";

    private final String SIGN_IN_BUTTON_XPATH = "//sl-button[contains(text(), 'Sign in')]";
    private final String STACKING_HISTORY_BUTTON_XPATH = "//*[text() = 'Staking history']";
    private final String TRX_TITLE_FIELD_XPATH = "//*[contains(text(), '%s')]";

    // Earnings calculator fields
    private final String TRX_STAKE_INPUT_FIELD_XPATH = "//*[contains(@class, 'staking-calculator')]//input";
    private final String INCOME_FIELD_PATTERN_XPATH = "//*[contains(@class, 'staking-calculator')]//div[contains(text(), '%s')]/parent::div//span";

    // Coin historical yield fields
    private final String COIN_HISTORICAL_YIELD_BUTTON_XPATH = "//li[contains(text(), 'Coin historical yield')]";
    private final String COIN_HISTORICAL_YIELD_TABLE_XPATH = "//*[contains(@class, 'tab-sections')]";
    private final String COIN_HISTORICAL_YIELD_NAVIGATION_PANEL_PATTERN_XPATH = "//*[contains(@class, 'nx-pagination')]";

    private final String COIN_HISTORICAL_YIELD_ROW_COLUMN_PATTERN = "//*[contains(@class, 'tab-sections')]//tr[@class = 'row'][%s]";

    private SelenideElement findCoinHistoricalYieldRowByIndex(Integer i) {
        return $x(String.format(COIN_HISTORICAL_YIELD_ROW_COLUMN_PATTERN, i)).scrollIntoView(true);
    }

    public TRXPage scrollIntoViewCoinHistoricalYieldTable() {
        $x(COIN_HISTORICAL_YIELD_TABLE_XPATH).scrollIntoView(true);
        return this;
    }

    public Map<COIN_HISTORICAL_YIELD_COLUMN, String> getCoinHistoricalYieldRowByIndex(Integer rowIndex) {
        Map<COIN_HISTORICAL_YIELD_COLUMN, String> coinHistoricalYieldMap = new HashMap<>();
        ElementsCollection coinHistoricalYieldRow = findCoinHistoricalYieldRowByIndex(rowIndex).$$x("./child::td");
        for (COIN_HISTORICAL_YIELD_COLUMN coinHistoricalYieldColumn: COIN_HISTORICAL_YIELD_COLUMN.values()) {
            coinHistoricalYieldMap.put(coinHistoricalYieldColumn, coinHistoricalYieldRow.get(coinHistoricalYieldColumn.getIndex()).getText());
        }
        return coinHistoricalYieldMap;
    }

    private SelenideElement findNavigationPanelButton(String xpath) {
        return $x(COIN_HISTORICAL_YIELD_NAVIGATION_PANEL_PATTERN_XPATH.concat(xpath));
    }

    public TRXPage clickNavigationButton(Integer index) {
        findNavigationPanelButton(String.format("/sl-button[%s]", index)).click();
        return this;
    }

    public TRXPage clickNextTableButton() {
        $x("//sl-icon-button[@name = 'arrow-right']").click();
        return this;
    }

    public TRXPage clickPreviousTableButton() {
        $x( "//sl-icon-button[@name = 'arrow-left']").click();
        return this;
    }

    public TRXPage loadingTRXPage() {
        $x(String.format(TRX_TITLE_FIELD_XPATH, TRX_PAGE_TITLE)).shouldBe(Condition.visible, Duration.ofSeconds(5));
        return this;
    }

    public boolean stackingHistoryButtonDisplayedOption() {
        return $x(STACKING_HISTORY_BUTTON_XPATH).isDisplayed();
    }

    public TRXPage clickSignInButton() {
        $x(SIGN_IN_BUTTON_XPATH).click();
        return this;
    }

    public TRXPage setTRXStake(String trxStake) {
        $x(TRX_STAKE_INPUT_FIELD_XPATH).setValue(trxStake);
        return this;
    }

    public String getIncomeValue(INCOME_PERIOD incomePeriod) {
        return $x(String.format(INCOME_FIELD_PATTERN_XPATH, incomePeriod.getIncomeDescription())).getText().replaceAll(",","");
    }

    public TRXPage clickCoinHistoricalYieldButton() {
        $x(COIN_HISTORICAL_YIELD_BUTTON_XPATH).click();
        return this;
    }

    @Override
    public String getExpectedURL() {
        return TRX_PAGE_URL;
    }

    public enum INCOME_PERIOD {

        DAILY_INCOME("Daily income"),
        MONTHLY_INCOME("Monthly income"),
        YEARLY_INCOME("Yearly income");

        private final String incomeDescription;

        INCOME_PERIOD(String incomeDescription) {
            this.incomeDescription = incomeDescription;
        }

        public String getIncomeDescription() {
            return incomeDescription;
        }
    }

    public enum COIN_HISTORICAL_YIELD_COLUMN {
        PERIOD(0),
        DISTRIBUTION_DATE(1),
        DISTRIBUTION_TOTAL(2),
        ESTIMATED_ANNUAL_INTEREST(3);

        private final Integer index;

        COIN_HISTORICAL_YIELD_COLUMN(Integer columnIndex) {
            this.index = columnIndex;
        }

        public Integer getIndex() {
            return index;
        }
    }

}
