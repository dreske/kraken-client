package me.reske.kraken;

/**
 * @author Dirk Reske
 */
public enum OrderType {
    Market("m"),
    Limit("l");

    private String value;

    OrderType(String value) {
        this.value = value;
    }

    public static OrderType byValue(String value) {
        for (OrderType tradeType : values()) {
            if (tradeType.value.equals(value)) {
                return tradeType;
            }
        }
        return null;
    }
}
