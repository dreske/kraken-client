package me.reske.kraken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

/**
 * @author Dirk Reske
 */
final class KrakenUtils {

    static Date getDateOrNull(JSONObject jsonObject, String field) {
        long timestamp = jsonObject.getBigDecimal(field).longValue() * 1000;
        return timestamp == 0 ? null : new Date(timestamp);
    }

    static JSONObject handleResponse(String response) {
        if (response == null) {
            throw new IllegalArgumentException("Invalid response");
        }

        JSONObject result = new JSONObject(response);
        JSONArray error = result.getJSONArray("error");
        if (error != null && error.length() > 0) {
            throw new IllegalArgumentException(error.getString(0));
        }

        return result.getJSONObject("result");
    }
}
