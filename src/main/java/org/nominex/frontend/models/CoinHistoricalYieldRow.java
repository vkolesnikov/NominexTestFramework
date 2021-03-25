package org.nominex.frontend.models;

import org.nominex.frontend.pages.TRXPage.COIN_HISTORICAL_YIELD_COLUMN;

import java.util.HashMap;
import java.util.Map;

public class CoinHistoricalYieldRow {

    private Integer tableIndex;
    private Integer rowIndex;
    private Map<COIN_HISTORICAL_YIELD_COLUMN, String> rowValue;

    public CoinHistoricalYieldRow(Integer tableIndex, Integer rowIndex, String period,
                                  String distributionDate, String distributionTotal, String estimatedAnnualInterest) {
        this.tableIndex = tableIndex;
        this.rowIndex = rowIndex;
        this.rowValue = new HashMap<>();
        this.rowValue.put(COIN_HISTORICAL_YIELD_COLUMN.PERIOD, period);
        this.rowValue.put(COIN_HISTORICAL_YIELD_COLUMN.DISTRIBUTION_DATE, distributionDate);
        this.rowValue.put(COIN_HISTORICAL_YIELD_COLUMN.DISTRIBUTION_TOTAL, distributionTotal);
        this.rowValue.put(COIN_HISTORICAL_YIELD_COLUMN.ESTIMATED_ANNUAL_INTEREST, estimatedAnnualInterest);
    }

    public CoinHistoricalYieldRow(Integer tableIndex, Integer rowIndex, Map<COIN_HISTORICAL_YIELD_COLUMN, String> rowValue) {
        this.tableIndex = tableIndex;
        this.rowIndex = rowIndex;
        this.rowValue = rowValue;
    }

    public Integer getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(Integer tableIndex) {
        this.tableIndex = tableIndex;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Map<COIN_HISTORICAL_YIELD_COLUMN, String> getRowValue() {
        return rowValue;
    }

    public void setRowValue(Map<COIN_HISTORICAL_YIELD_COLUMN, String> rowValue) {
        this.rowValue = rowValue;
    }
}
