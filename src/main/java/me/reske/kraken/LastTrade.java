package me.reske.kraken;

import org.json.JSONArray;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class LastTrade implements Serializable {
    private BigDecimal price;
    private BigDecimal volume;

    public LastTrade() {
    }

    LastTrade(JSONArray array) {
        price = array.getBigDecimal(0);
        volume = array.getBigDecimal(1);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastTrade{");
        sb.append("price='").append(price).append('\'');
        sb.append(", volume='").append(volume).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
