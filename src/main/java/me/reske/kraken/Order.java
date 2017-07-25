package me.reske.kraken;

import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static me.reske.kraken.KrakenUtils.getDateOrNull;

/**
 * @author Dirk Reske
 */
public class Order implements Serializable {
    private String txId;
    private String refId;
    private Date openedTime;
    private Date startTime;
    private Date expireTime;
    private BigDecimal volume;
    private BigDecimal volumeExecuted;
    private BigDecimal cost;
    private BigDecimal fee;
    private BigDecimal price;
    private OrderType orderType;
    private String assetPair;
    private BigDecimal primaryPrice;
    private BigDecimal secondaryPrice;
    private String description;

    public Order() {
    }

    Order(String txId, JSONObject jsonObject) {
        this.txId = txId;
        refId = jsonObject.optString("refid", null);
        openedTime = getDateOrNull(jsonObject, "opentm");
        startTime = getDateOrNull(jsonObject, "starttm");
        expireTime = getDateOrNull(jsonObject, "expiretm");
        volume = jsonObject.getBigDecimal("vol");
        volumeExecuted = jsonObject.getBigDecimal("vol_exec");
        cost = jsonObject.getBigDecimal("cost");
        fee = jsonObject.getBigDecimal("fee");
        price = jsonObject.getBigDecimal("price");

        JSONObject descr = jsonObject.getJSONObject("descr");
        orderType = OrderType.byLongValue(descr.getString("ordertype"));
        primaryPrice = descr.getBigDecimal("price");
        secondaryPrice = descr.getBigDecimal("price2");
        description = descr.getString("order");
        assetPair = descr.getString("pair");
    }

    public String getTxId() {
        return txId;
    }

    public String getRefId() {
        return refId;
    }

    public Date getOpenedTime() {
        return openedTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getVolumeExecuted() {
        return volumeExecuted;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public BigDecimal getPrimaryPrice() {
        return primaryPrice;
    }

    public BigDecimal getSecondaryPrice() {
        return secondaryPrice;
    }

    public String getDescription() {
        return description;
    }
}
