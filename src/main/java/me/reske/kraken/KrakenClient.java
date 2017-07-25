package me.reske.kraken;

import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Dirk Reske
 */
@SuppressWarnings("WeakerAccess")
public class KrakenClient extends KrakenClientBase {

    public KrakenClient(String apiKey, String privateKey) {
        super(apiKey, privateKey);
    }

    /**
     * Get server time.
     *
     * @return Server's time
     */
    public ServerTime getServerTime() {
        JSONObject result = publicRequest("/0/public/Time");
        return new ServerTime(result.getLong("unixtime"), result.getString("rfc1123"));
    }


    /**
     * Gets all asset infos.
     *
     * @return the asset infos
     */
    public List<AssetInfo> getAssetInfos() {
        return getAssetInfos((List<String>) null);
    }

    /**
     * Gets the infos for a single asset.
     *
     * @param asset the assets name
     * @return the asset infos
     */
    public AssetInfo getAssetInfos(String asset) {
        List<AssetInfo> assetInfos = getAssetInfos(Collections.singletonList(asset));
        if (!assetInfos.isEmpty()) {
            return assetInfos.get(0);
        }
        return null;
    }

    /**
     * Gets the asset infos for a list of assets.
     *
     * @param assets the assets to retreive informations for
     * @return the asset informations
     */
    public List<AssetInfo> getAssetInfos(List<String> assets) {
        Map<String, String> parameters = new HashMap<>();
        if (assets != null && !assets.isEmpty()) {
            String assetList = assets.stream()
                    .collect(Collectors.joining(","));
            parameters.put("asset", assetList);
        }
        JSONObject result = publicRequest("/0/public/Assets", parameters);

        List<AssetInfo> assetInfos = new ArrayList<>();
        for (String name : result.keySet()) {
            JSONObject asset = result.getJSONObject(name);
            assetInfos.add(new AssetInfo(
                    name,
                    asset.getString("altname"),
                    asset.getString("aclass"),
                    asset.getBigDecimal("decimals"),
                    asset.getBigDecimal("display_decimals")
            ));
        }
        return assetInfos;
    }

    /**
     * Get tradable asset pairs.
     *
     * @return List of pair names and their info
     */
    public List<AssetPair> getAssetPairs() {
        JSONObject result = publicRequest("/0/public/AssetPairs");
        List<AssetPair> assetPairs = new ArrayList<>();
        for (String name : result.keySet()) {
            JSONObject pair = result.getJSONObject(name);
            assetPairs.add(new AssetPair(
                    name,
                    pair.getString("altname"),
                    pair.getString("aclass_base"),
                    pair.getString("base"),
                    pair.getString("aclass_quote"),
                    pair.getString("quote"),
                    pair.getString("lot"),
                    pair.getBigDecimal("pair_decimals"),
                    pair.getBigDecimal("lot_decimals"),
                    pair.getBigDecimal("lot_multiplier")
            ));
        }
        return assetPairs;
    }


    /**
     * Get ticker information.
     *
     * @param assetPairs list of asset pairs to get info on
     * @return List of pair names and their ticker info
     */
    public List<Ticker> getTicker(List<String> assetPairs) {
        String pairs = assetPairs.stream()
                .collect(Collectors.joining(","));
        Map<String, String> paramters = new HashMap<>();
        paramters.put("pair", pairs);
        JSONObject result = publicRequest("/0/public/Ticker", paramters);
        List<Ticker> tickers = new ArrayList<>();
        for (String name : result.keySet()) {
            tickers.add(new Ticker(name, result.getJSONObject(name)));

        }
        return tickers;
    }

    public OrderBook getOrderBook(String assetPair) {
        return getOrderBook(assetPair, null);
    }

    public OrderBook getOrderBook(String assetPair, Integer count) {
        if (assetPair == null || assetPair.trim().isEmpty()) {
            throw new IllegalArgumentException("assetPair may not be empty");
        }

        Map<String, String> paramters = new HashMap<>();
        paramters.put("pair", assetPair);
        if (count != null) {
            paramters.put("count", String.valueOf(count));
        }

        JSONObject result = publicRequest("/0/public/Depth", paramters);
        return new OrderBook(result.getJSONObject(assetPair));
    }


    public LastTrades getLastTrades(String assetPair) {
        return getLastTrades(assetPair, null);
    }

    public LastTrades getLastTrades(String assetPair, String since) {
        if (assetPair == null || assetPair.trim().isEmpty()) {
            throw new IllegalArgumentException("assetPair may not be empty");
        }

        Map<String, String> parameters = new HashMap<>();
        parameters.put("pair", assetPair);
        if (since != null) {
            parameters.put("since", since);
        }
        JSONObject result = publicRequest("/0/public/Trades", parameters);
        return new LastTrades(assetPair, result);
    }

    /**
     * Get account balance.
     *
     * @return List of asset names and balance amount
     */
    public List<AccountBalance> getAccountBalance() {
        JSONObject result = privateRequest("/0/private/Balance");
        List<AccountBalance> accountBalances = new ArrayList<>();
        for (String asset : result.keySet()) {
            accountBalances.add(new AccountBalance(asset, result.getBigDecimal(asset)));
        }
        return accountBalances;
    }

    /**
     * Get trade balance.
     *
     * @param asset base asset used to determine balance (default = ZUSD)
     * @return trade balance info
     */
    public TradeBalance getTradeBalance(String asset) {
        Map<String, String> parameters = new HashMap<>();

        if (asset != null) {
            // Asset may be null, will return the default asset
            parameters.put("asset", asset);
        }
        JSONObject result = privateRequest("/0/private/TradeBalance", parameters);
        return new TradeBalance(result);
    }

    public List<Order> getOpenOrders() {
        JSONObject result = privateRequest("/0/private/OpenOrders", null);
        JSONObject open = result.getJSONObject("open");
        List<Order> orders = new ArrayList<>();
        for (String txId : open.keySet()) {
            orders.add(new Order(txId, open.getJSONObject(txId)));
        }
        return orders;
    }
}
