package me.reske.kraken;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class Order implements Serializable {
    private String txId;
    private String refId;

    public Order() {
    }

    Order(String txId, JSONObject jsonObject) {
        this.txId = txId;
        refId = jsonObject.optString("refid", null);
    }
}
