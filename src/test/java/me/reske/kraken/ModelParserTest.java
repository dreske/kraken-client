package me.reske.kraken;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Dirk Reske
 */
public class ModelParserTest {

    @Test
    public void testOpenOrders() throws IOException {
        JSONObject data = load("/orders.json");
        JSONObject open = data.getJSONObject("open");

        List<Order> orders = new ArrayList<>();
        for (String txId : open.keySet()) {
            orders.add(new Order(txId, open.getJSONObject(txId)));
        }

        assertEquals(1, orders.size());
        Order order = orders.get(0);
        assertEquals("O3RVAH-7JIF7-A5IVHQ", order.getTxId());
    }

    private JSONObject load(String file) throws IOException {
        InputStream inputStream = ModelParserTest.class.getResourceAsStream(file);
        Reader reader = new InputStreamReader(inputStream);
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[1024];
        int read = 0;
        while ((read = reader.read(buffer)) > -1) {
            builder.append(buffer, 0, read);
        }

        reader.close();
        return new JSONObject(builder.toString());
    }
//
//    @Test
//    public void testParseTicker() throws IOException {
//        GetTickerResponse response = parse("/ticker.json", GetTickerResponse.class);
//        assertNotNull(response);
//        assertEquals(2, response.getTickers().size());
//
//        Ticker etheur = response.getTickers().get(1);
//        assertNotNull(etheur);
//        assertEquals("XETHZEUR", etheur.getName());
//        assertEquals(new BigDecimal("172.58000"), etheur.getTodayOpening());
//    }
//
//    @Test
//    public void testParseAccountBalance() throws IOException {
//        GetAccountBalanceResponse accountBalance = parse("/accountBalance.json", GetAccountBalanceResponse.class);
//        assertNotNull(accountBalance);
//        assertEquals(5, accountBalance.getBalances().size());
//    }
//
//    @Test
//    public void testParseTradeBalance() throws IOException {
//        TradeBalance tradeBalance = parse("/tradeBalance.json", TradeBalance.class);
//        assertNotNull(tradeBalance);
//    }
//
//    @Test
//    public void testParseAssetPairs() throws IOException {
//        GetAssetPairsResponse response = parse("/assetPairs.json", GetAssetPairsResponse.class);
//        assertNotNull(response);
//        assertEquals(64, response.getAssetPairs().size());
//
//        AssetPair etheur = response.getAssetPairs().get(0);
//        assertNotNull(etheur);
//    }
//
//    @Test
//    public void testParseOrderBook() throws IOException {
//        GetOrderBookResponse orderBookResponse = parse("/orderBook.json", GetOrderBookResponse.class);
//        assertNotNull(orderBookResponse);
//        assertNotNull(orderBookResponse.getOrderBook());
//        assertEquals(100, orderBookResponse.getOrderBook().getAsks().size());
//        assertEquals(100, orderBookResponse.getOrderBook().getBids().size());
//    }
//
//    @Test
//    public void testParseLastTrades() throws IOException {
//        LastTrades lastTradesResponse = parse("/lastTrades.json", LastTrades.class);
//        assertNotNull(lastTradesResponse);
//        assertNotNull(lastTradesResponse.getTrades());
//        assertFalse(lastTradesResponse.getTrades().isEmpty());
//    }
//
//    // TODO: use KrakenClients parser
//    private <T> T parse(String file, Class<T> resultClass) throws IOException {
//        ObjectMapper objectMapper = objectMapper();
//        JavaType type = objectMapper.getTypeFactory()
//                .constructParametricType(KrakenApiResponse.class, (Class) resultClass);
//        KrakenApiResponse<T> krakenResponse = objectMapper
//                .readerFor(type)
//                .readValue(ModelParserTest.class.getResourceAsStream(file));
//        return krakenResponse.getResult();
//    }
//
//    private ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        return objectMapper;
//    }
}
