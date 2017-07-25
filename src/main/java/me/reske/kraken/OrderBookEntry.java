package me.reske.kraken;

import org.json.JSONArray;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class OrderBookEntry implements Serializable {
    private BigDecimal price;
    private BigDecimal volume;
    private Long timestamp;

    OrderBookEntry(JSONArray jsonArray) {
        this.price = jsonArray.getBigDecimal(0);
        this.volume = jsonArray.getBigDecimal(1);
        this.timestamp = jsonArray.getLong(2);
    }

    public OrderBookEntry() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderBookEntry{");
        sb.append("price=").append(price);
        sb.append(", volume=").append(volume);
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');
        return sb.toString();
    }
}
