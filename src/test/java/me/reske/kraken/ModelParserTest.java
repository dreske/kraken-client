package me.reske.kraken;

import org.json.JSONObject;

import static org.junit.Assert.*;

/**
 * @author Dirk Reske
 */
public class ModelParserTest {

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
