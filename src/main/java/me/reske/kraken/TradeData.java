package me.reske.kraken;

import org.json.JSONArray;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class TradeData implements Serializable {

    private BigDecimal price;
    private BigDecimal wholeLotVolume;
    private BigDecimal lotVolume;

    public TradeData() {
    }

    TradeData(JSONArray jsonArray) {
        this.price = jsonArray.getBigDecimal(0);
        this.wholeLotVolume = jsonArray.getBigDecimal(1);
        this.lotVolume = jsonArray.getBigDecimal(2);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getWholeLotVolume() {
        return wholeLotVolume;
    }

    public BigDecimal getLotVolume() {
        return lotVolume;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastTradeExtended{");
        sb.append("price='").append(price).append('\'');
        sb.append(", wholeLotVolume='").append(wholeLotVolume).append('\'');
        sb.append(", lotVolume='").append(lotVolume).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
