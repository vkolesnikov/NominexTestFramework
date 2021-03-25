package org.nominex.utils;

import org.nominex.frontend.models.CoinHistoricalYieldRow;

public interface TestConstants {

    String EMAIL_ADDRESS = "kolvictor64@mail.ru";
    String PASSWORD = "1P#C~CejwZ";

    Object[][] TRX_STAKES = new String[][]{{"51878,6"}, {"0"}, {"100000"}};

    Integer LAST_TABLE_BUTTON_INDEX = 7;

    // ROW 1
    CoinHistoricalYieldRow ROW1_1 = new CoinHistoricalYieldRow(1, 1, "01/05/2021 — 01/06/2021",
            "01/06/2021", "158.05501578", "38.00%");
    CoinHistoricalYieldRow ROW1_4 = new CoinHistoricalYieldRow(1, 4, "01/02/2021 — 01/03/2021",
            "01/03/2021", "56.73087109", "11.51%");
    CoinHistoricalYieldRow ROW1_10 = new CoinHistoricalYieldRow(1, 10, "12/27/2020 — 12/28/2020",
            "12/28/2020", "157.38174137", "26.06%");

    // ROW 2
    CoinHistoricalYieldRow ROW2_5 = new CoinHistoricalYieldRow(2, 5, "12/22/2020 — 12/23/2020",
            "12/23/2020", "175.0660564", "28.47%");
    CoinHistoricalYieldRow ROW2_10 = new CoinHistoricalYieldRow(2, 10, "12/17/2020 — 12/18/2020",
            "12/18/2020", "159.66367186", "19.68%");

    // ROW 4
    CoinHistoricalYieldRow ROW4_4 = new CoinHistoricalYieldRow(1, 4, "12/03/2020 — 12/04/2020",
            "12/04/2020", "82.9491692", "13.70%");

    // ROW 5
    CoinHistoricalYieldRow ROW5_3 = new CoinHistoricalYieldRow(5, 3, "11/24/2020 — 11/25/2020",
            "11/25/2020", "63.8212825", "8.79%");

    // ROW 9
    CoinHistoricalYieldRow ROW9_10 = new CoinHistoricalYieldRow(9, 10, "10/06/2020 — 10/07/2020",
            "10/07/2020", "33.9352853", "10.86%");

    // ROW 10
    CoinHistoricalYieldRow ROW10_1 = new CoinHistoricalYieldRow(10, 1, "10/05/2020 — 10/06/2020",
            "10/06/2020", "39.74990441", "13.04%");
    CoinHistoricalYieldRow ROW10_6 = new CoinHistoricalYieldRow(10, 6, "09/30/2020 — 10/01/2020",
            "10/01/2020", "33.83061617", "13.67%");
    CoinHistoricalYieldRow ROW10_10 = new CoinHistoricalYieldRow(10, 10, "09/26/2020 — 09/27/2020",
            "09/27/2020", "32.48403399", "21.49%");

    // ROW 11
    CoinHistoricalYieldRow ROW11_1 = new CoinHistoricalYieldRow(11, 1, "09/25/2020 — 09/26/2020",
            "09/26/2020", "31.36718331", "19.87%");
    CoinHistoricalYieldRow ROW11_4 = new CoinHistoricalYieldRow(11, 4, "09/22/2020 — 09/23/2020",
            "09/23/2020", "29.2238808", "16.97%");
    CoinHistoricalYieldRow ROW11_8 = new CoinHistoricalYieldRow(11, 8, "01/01/1970 — 09/18/2020",
            "09/18/2020", "28.37099447", "10.42%");

}
