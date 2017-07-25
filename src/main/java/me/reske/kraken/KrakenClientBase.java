package me.reske.kraken;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Dirk Reske
 */
abstract class KrakenClientBase {
    private static final String CHARSET = "UTF-8";

    private static final String BASE_URL = "https://api.kraken.com";

    private final String apiKey;
    private final SecretKeySpec privateKeySpec;

    public KrakenClientBase(String apiKey, String privateKey) {
        this.apiKey = apiKey;
        if (privateKey != null) {
            byte[] key = Base64.getDecoder().decode(privateKey);
            privateKeySpec = new SecretKeySpec(key, "HmacSHA512");
        } else {
            privateKeySpec = null;
        }

    }

    /**
     * Calls a public api endpoint.
     *
     * @param path the path
     * @return the api response
     */
    JSONObject publicRequest(String path) {
        return publicRequest(path, null);
    }

    /**
     * Calls a public api endpoint.
     *
     * @param path       the path
     * @param parameters the parameters
     * @return the api response
     */
    JSONObject publicRequest(String path, Map<String, String> parameters) {
        return performRequest(path, parameters, false);
    }

    /**
     * Calls a private api endpoint.
     *
     * @param path the path
     * @return the api response
     */
    JSONObject privateRequest(String path) {
        return privateRequest(path, null);
    }

    /**
     * Calls a private api endpoint.
     *
     * @param path       the path
     * @param parameters the parameters
     * @return the api response
     */
    JSONObject privateRequest(String path, Map<String, String> parameters) {
        return performRequest(path, parameters, true);
    }

    /**
     * Performs an API request.
     * Errors from the api will be translated to exceptions.
     *
     * @param path       the path
     * @param parameters paremters
     * @param isPrivate  {@code true} if this is a private request; {@code false} if not
     * @return the api response
     */
    private JSONObject performRequest(String path, Map<String, String> parameters, boolean isPrivate) {
        JSONObject response = null;
        try {
            response = execute(path, parameters, isPrivate);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        if (response == null) {
            throw new IllegalArgumentException("Invalid response");
        }

        JSONArray error = response.getJSONArray("error");
        if (error != null && error.length() > 0) {
            throw new IllegalArgumentException(error.getString(0));
        }

        return response.getJSONObject("result");
    }

    /**
     * Performs an API request.
     *
     * @param path       the path
     * @param parameters paremters
     * @param isPrivate  {@code true} if this is a private request; {@code false} if not
     */
    private JSONObject execute(String path, Map<String, String> parameters, boolean isPrivate) throws IOException {
        if (parameters == null) {
            parameters = new HashMap<>();
        }

        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append(path);
        if (!isPrivate && !parameters.isEmpty()) {
            urlBuilder.append("?").append(formatParameters(parameters, true));
        }

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (isPrivate) {
            String nonce = String.valueOf(System.currentTimeMillis() * 1000);
            parameters.put("nonce", nonce);
            String body = formatParameters(parameters, false);

            connection.setRequestProperty("API-Key", apiKey);
            connection.setRequestProperty("API-Sign", signRequest(path, body, nonce));
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(body.getBytes(CHARSET));
            outputStream.flush();
            outputStream.close();
        }

        if (connection.getResponseCode() == 200) {
            InputStream inputStream = connection.getInputStream();
            String response = readResponse(inputStream);

            return new JSONObject(response);
        }

        //TODO error handling
        return null;
    }

    /**
     * Calculates the request signature.
     *
     * @param path  the request path
     * @param body  the request body
     * @param nonce the nonce used for the request
     * @return the signature
     */
    private String signRequest(String path, String body, String nonce) {
        byte[] sha256 = sha256(nonce + body);
        byte[] pathBytes;
        try {
            pathBytes = path.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        byte[] signData = new byte[sha256.length + pathBytes.length];
        System.arraycopy(pathBytes, 0, signData, 0, pathBytes.length);
        System.arraycopy(sha256, 0, signData, pathBytes.length, sha256.length);
        return hmacSha512(signData);
    }

    private String readResponse(InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        char[] buffer = new char[1024];
        int read;
        StringBuilder response = new StringBuilder();
        while ((read = reader.read(buffer)) > -1) {
            response.append(buffer, 0, read);
        }
        return response.toString();
    }

    /**
     * Formats the request paramters.
     *
     * @param parameters the current parameters
     * @param urlEncode  {@code true} if the parameters should be url encoded; {@code false} if not
     * @return the formatted parameter string
     */
    private String formatParameters(Map<String, String> parameters, boolean urlEncode) {
        StringBuilder urlBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> parameter = iterator.next();
            try {
                urlBuilder.append(urlEncode ? URLEncoder.encode(parameter.getKey(), CHARSET) : parameter.getKey())
                        .append("=")
                        .append(urlEncode ? URLEncoder.encode(parameter.getValue(), CHARSET) : parameter.getValue());
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }

            if (iterator.hasNext()) {
                urlBuilder.append("&");
            }
        }
        return urlBuilder.toString();
    }

    /**
     * Calculates the SHA-256 hash.
     *
     * @param input the input
     * @return the hash
     */
    private byte[] sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input.getBytes(CHARSET));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates the HMACSHA512 signature.
     *
     * @param input the input
     * @return the signature
     */
    private String hmacSha512(byte[] input) {
        if (privateKeySpec == null) {
            throw new IllegalStateException("Missing private key");
        }

        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(privateKeySpec);
            byte[] result = mac.doFinal(input);
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException(e);
        }
    }
}
