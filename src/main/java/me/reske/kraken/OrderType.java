package me.reske.kraken;

/**
 * @author Dirk Reske
 */
public enum OrderType {
    Market("m", "market"),
    StopLoss(null, "stop-loss"),
    TakeProfit(null, "take-profit"),
    StopLossProfit(null, "stop-loss-profit"),
    StopLossProfitLimit(null, "stop-loss-profit-limit"),
    StopLossLimit(null, "stop-loss-limit"),
    TakeProfitLimit(null, "take-profit-limit"),
    TrailingStop(null, "trailing-stop"),
    TrailingStopLimit(null, "trailing-stop-limit"),
    StopLossAndLimit(null, "stop-loss-and-limit"),
    SettlePosition(null, "settle-position"),
    Limit("l", "limit");

    private String shortValue;
    private String longValue;

    OrderType(String value, String longValue) {
        this.shortValue = value;
        this.longValue = longValue;
    }

    static OrderType byValue(String value) {
        for (OrderType tradeType : values()) {
            if (value.equals(tradeType.shortValue)) {
                return tradeType;
            }
        }
        return null;
    }

    static OrderType byLongValue(String value) {
        for (OrderType tradeType : values()) {
            if (value.equals(tradeType.longValue)) {
                return tradeType;
            }
        }
        return null;
    }
}
