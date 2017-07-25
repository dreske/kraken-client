package me.reske.kraken;

import org.json.JSONArray;

import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class LastTradeExtended extends LastTrade {

    private BigDecimal time;
    private TradeType tradeType;
    private OrderType orderType;


    public LastTradeExtended(JSONArray jsonArray) {
        super(jsonArray);
        time = jsonArray.getBigDecimal(2);
        tradeType = TradeType.byValue(jsonArray.getString(3));
        orderType = OrderType.byValue(jsonArray.getString(4));
    }

    public BigDecimal getTime() {
        return time;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LastTradeExtended{");
        sb.append("price=").append(getPrice());
        sb.append(", volume=").append(getVolume());
        sb.append(", time=").append(time);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", orderType=").append(orderType);
        sb.append('}');
        return sb.toString();
    }
}
