package me.reske.kraken;

/**
 * @author Dirk Reske
 */
public enum TradeType {
    Buy("b"),
    Sell("s");

    private String value;

    TradeType(String value) {
        this.value = value;
    }

    public static TradeType byValue(String value) {
        for (TradeType tradeType : values()) {
            if (tradeType.value.equals(value)) {
                return tradeType;
            }
        }
        return null;
    }
}
