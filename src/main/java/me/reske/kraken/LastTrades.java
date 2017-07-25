package me.reske.kraken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class LastTrades {

    private BigInteger last;
    private List<LastTradeExtended> trades = new ArrayList<>();

    public LastTrades() {
    }

    LastTrades(String assetPair, JSONObject jsonObject) {
        last = jsonObject.getBigInteger("last");
        JSONArray trades = jsonObject.getJSONArray(assetPair);
        for (int i = 0; i < trades.length(); i++) {
            this.trades.add(new LastTradeExtended(trades.getJSONArray(i)));
        }
    }

    public BigInteger getLast() {
        return last;
    }

    public List<LastTradeExtended> getTrades() {
        return trades;
    }
}
