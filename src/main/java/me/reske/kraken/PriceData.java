package me.reske.kraken;

import org.json.JSONArray;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class PriceData implements Serializable {

    private BigDecimal today;
    private BigDecimal last24Hours;

    public PriceData() {
    }

    public PriceData(JSONArray jsonArray) {
        today = jsonArray.getBigDecimal(0);
        last24Hours = jsonArray.getBigDecimal(1);
    }

    public BigDecimal getToday() {
        return today;
    }

    public BigDecimal getLast24Hours() {
        return last24Hours;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PriceData{");
        sb.append("today=").append(today);
        sb.append(", last24Hours=").append(last24Hours);
        sb.append('}');
        return sb.toString();
    }
}

