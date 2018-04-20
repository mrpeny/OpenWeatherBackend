package eu.captaincode.backend.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class NetworkUtils {
    private static final String PARAM_KEY_APP_ID = "APPID";
    //TODO: Insert your API key here
    private static final String PARAM_VALUE_APP_ID = "insert-your-api-key-here";

    private static final String PARAM_KEY_UNITS = "units";
    private static final String PARAM_VALUE_UNITS = "metric";
    private static final String PARAM_KEY_CITY = "q";

    private static final String BASE_API_URI_OWM = "http://api.openweathermap.org/data/2.5/weather";

    public static String getCityWeatherJson(String city) {
        if (TextUtils.isEmpty(city)) {
            return null;
        }

        String jsonString = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            URI cityUri = buildUriWithCity(city);
            HttpGet request = new HttpGet(cityUri);
            HttpResponse response = httpClient.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                stringBuilder.append(line);
            }
            jsonString = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    private static URI buildUriWithCity(String city) {
        URI uriWithCityName = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(BASE_API_URI_OWM);
            uriBuilder.addParameter(PARAM_KEY_CITY, city);
            uriBuilder.addParameter(PARAM_KEY_UNITS, PARAM_VALUE_UNITS);
            uriBuilder.addParameter(PARAM_KEY_APP_ID, PARAM_VALUE_APP_ID);
            uriWithCityName = uriBuilder.build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uriWithCityName;
    }
}
