package eu.captaincode.backend.util;

import eu.captaincode.backend.dto.CityWeather;
import eu.captaincode.backend.spring.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class OpenWeatherJsonParserTest {
    private static final String VALID_JSON_STRING = "{\"coord\":{\"lon\":19.04,\"lat\":47.5},\"weather\":[{\"id\":" +
            "200,\"main\":" + "\"Thunderstorm\",\"description\":\"thunderstorm with light rain\",\"icon\":\"11d\"}]," +
            "\"base\":\"stations\"," + "\"main\":{\"temp\":21,\"pressure\":1012,\"humidity\":73,\"temp_min\":21," +
            "\"temp_max\":21},\"visibility\":" + "8000,\"wind\":{\"speed\":6.2,\"deg\":300,\"gust\":11.3},\"clouds\":" +
            "{\"all\":75},\"dt\":1524495600,\"sys\":" + "{\"type\":1,\"id\":5724,\"message\":0.0039,\"country\":" +
            "\"HU\",\"sunrise\":1524454793,\"sunset\":1524505508}," + "\"id\":3054643,\"name\":\"Budapest\",\"cod\":200}";
    private static final String ERROR_JSON_STRING = "{\"cod\":\"404\",\"message\":\"city not found\"}";
    private static final String CITY_EXPECTED = "Budapest";
    private static final int TEMPERATURE_EXPECTED = 21;
    private static final String EMPTY_STRING = "";

    @Autowired
    private OpenWeatherJsonParser testJsonParser;

    @Test
    public void whenCalledWithValidJson_thenParsedIntoValidCityWeather() {
        // when
        CityWeather cityWeather = testJsonParser.parseCityWeatherFrom(VALID_JSON_STRING);

        // then
        assertEquals("City name mismatch", CITY_EXPECTED, cityWeather.getName());
        assertEquals(TEMPERATURE_EXPECTED, cityWeather.getMain().getTemp(), 0.1);
    }

    @Test
    public void whenCalledWithErrorJsonString_thenMainWeatherIsNull() {
        // when
        CityWeather cityWeather = testJsonParser.parseCityWeatherFrom(ERROR_JSON_STRING);

        // then
        assertNull("MainWeather parsed from error JSON", cityWeather.getMain());
    }

    @Test
    public void whenCalledWithEmptyJsonString_thenCityWeatherIsNull() {
        // when
        CityWeather cityWeather = testJsonParser.parseCityWeatherFrom(EMPTY_STRING);

        // then
        assertNull("CityWeather parsed from empty JSON string", cityWeather);
    }
}