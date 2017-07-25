package me.reske.kraken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class OrderBook implements Serializable {

    private List<OrderBookEntry> asks = new ArrayList<>();
    private List<OrderBookEntry> bids = new ArrayList<>();

    public OrderBook() {
    }

    OrderBook(JSONObject jsonObject) {
        JSONArray asks = jsonObject.getJSONArray("asks");
        for (int i = 0; i < asks.length(); i++) {
            this.asks.add(new OrderBookEntry(asks.getJSONArray(i)));
        }

        JSONArray bids = jsonObject.getJSONArray("bids");
        for (int i = 0; i < bids.length(); i++) {
            this.bids.add(new OrderBookEntry(bids.getJSONArray(i)));
        }
    }

    public List<OrderBookEntry> getAsks() {
        return asks;
    }

    public List<OrderBookEntry> getBids() {
        return bids;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderBook{");
        sb.append("asks=").append(asks);
        sb.append(", bids=").append(bids);
        sb.append('}');
        return sb.toString();
    }
}
