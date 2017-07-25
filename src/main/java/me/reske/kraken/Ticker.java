package me.reske.kraken;

import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class Ticker implements Serializable {

    private String name;
    private TradeData askData;
    private TradeData bidData;
    private LastTrade lastTrade;
    private PriceData volume;
    private PriceData volumeWeightedAverage;
    private PriceData trades;
    private PriceData low;
    private PriceData high;
    private BigDecimal todayOpening;

    public Ticker() {
    }

    Ticker(String name, JSONObject jsonObject) {
        this.name = name;
        todayOpening = jsonObject.getBigDecimal("o");
        askData = new TradeData(jsonObject.getJSONArray("a"));
        bidData = new TradeData(jsonObject.getJSONArray("b"));
        lastTrade = new LastTrade(jsonObject.getJSONArray("c"));
        volume = new PriceData(jsonObject.getJSONArray("v"));
        volumeWeightedAverage = new PriceData(jsonObject.getJSONArray("p"));
        trades = new PriceData(jsonObject.getJSONArray("t"));
        low = new PriceData(jsonObject.getJSONArray("l"));
        high = new PriceData(jsonObject.getJSONArray("h"));
    }

    public String getName() {
        return name;
    }

    public TradeData getAskData() {
        return askData;
    }

    public TradeData getBidData() {
        return bidData;
    }

    public LastTrade getLastTrade() {
        return lastTrade;
    }

    public PriceData getVolume() {
        return volume;
    }

    public PriceData getVolumeWeightedAverage() {
        return volumeWeightedAverage;
    }

    public PriceData getTrades() {
        return trades;
    }

    public PriceData getLow() {
        return low;
    }

    public PriceData getHigh() {
        return high;
    }

    public BigDecimal getTodayOpening() {
        return todayOpening;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ticker{");
        sb.append("name='").append(name).append('\'');
        sb.append(", askData=").append(askData);
        sb.append(", bidData=").append(bidData);
        sb.append(", lastTrade=").append(lastTrade);
        sb.append(", volume=").append(volume);
        sb.append(", volumeWeightedAverage=").append(volumeWeightedAverage);
        sb.append(", trades=").append(trades);
        sb.append(", low=").append(low);
        sb.append(", high=").append(high);
        sb.append(", todayOpening=").append(todayOpening);
        sb.append('}');
        return sb.toString();
    }
}
