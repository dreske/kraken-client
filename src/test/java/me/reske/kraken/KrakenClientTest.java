package me.reske.kraken;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Dirk Reske
 */
public class KrakenClientTest {

    private KrakenClient client;

    @Before
    public void setUp() throws Exception {
        String apiKey = System.getProperty("kraken.apiKey");
        String privateKey = System.getProperty("kraken.privateKey");
        client = new KrakenClient(apiKey, privateKey);
    }

    @Test
    public void testGetServerTime() throws IOException {
        ServerTime serverTime = client.getServerTime();
        assertNotNull(serverTime);
        assertNotNull(serverTime.getRfc1123());
        assertNotNull(serverTime.getUnixtime());
    }

    @Test
    public void testGetAssetInfos() throws IOException {
        List<AssetInfo> assetInfos = client.getAssetInfos();
        assertNotNull(assetInfos);
        assertFalse(assetInfos.isEmpty());
    }

    @Test
    public void testGetSingleAssetInfos() throws IOException {
        AssetInfo assetInfos = client.getAssetInfos("EUR");
        assertNotNull(assetInfos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInvalidAssetInfos() throws IOException {
        client.getAssetInfos("INVALID");
    }

    @Test
    public void testGetTicker() throws IOException {
        List<Ticker> tickers = client.getTicker(Collections.singletonList("XETHZEUR"));
        assertNotNull(tickers);
        assertEquals(1, tickers.size());

        Ticker ticker = tickers.get(0);
        assertNotNull(ticker.getAskData());
        assertNotNull(ticker.getBidData());
        assertNotNull(ticker.getHigh());
        assertNotNull(ticker.getLow());
        assertNotNull(ticker.getLastTrade());
        assertNotNull(ticker.getTodayOpening());
    }

    @Test
    public void testGetMultipleTickers() throws IOException {
        List<Ticker> tickers = client.getTicker(Arrays.asList("XETHZEUR", "DASHEUR"));
        assertNotNull(tickers);
        assertEquals(2, tickers.size());
    }

    @Test
    public void testGetAssetPairs() throws IOException {
        List<AssetPair> assetPairs = client.getAssetPairs();
        assertNotNull(assetPairs);
        assertFalse(assetPairs.isEmpty());
    }

    @Test
    public void testGetOrderBook() throws IOException {
        OrderBook orderBook = client.getOrderBook("XXBTZEUR", 50);
        assertNotNull(orderBook);
        assertEquals(50, orderBook.getAsks().size());
        assertEquals(50, orderBook.getBids().size());
    }

    @Test
    public void testGetLastTrades() {
        LastTrades lastTrades = client.getLastTrades("XXBTZEUR");
        assertNotNull(lastTrades);
        assertNotNull(lastTrades.getLast());
        assertNotNull(lastTrades.getTrades());
        assertFalse(lastTrades.getTrades().isEmpty());
        for (LastTradeExtended lastTrade : lastTrades.getTrades()) {
            assertNotNull(lastTrade.getPrice());
            assertNotNull(lastTrade.getVolume());
            assertNotNull(lastTrade.getTime());
            assertNotNull(lastTrade.getTradeType());
            assertNotNull(lastTrade.getOrderType());
        }
    }

    @Test
    public void testGetAccountBalance() throws IOException {
        List<AccountBalance> tradeBalances = client.getAccountBalance();
        assertNotNull(tradeBalances);
        assertFalse(tradeBalances.isEmpty());
    }

    @Test
    public void testGetTradeBalance() throws IOException {
        TradeBalance tradeBalance = client.getTradeBalance("ZEUR");
        assertNotNull(tradeBalance);
    }

    @Test
    public void testGetOpenOrders() {
        List<Order> orders = client.getOpenOrders();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }
}