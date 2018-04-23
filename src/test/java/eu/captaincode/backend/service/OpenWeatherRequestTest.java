package eu.captaincode.backend.service;

import eu.captaincode.backend.dto.CityWeather;
import eu.captaincode.backend.dto.MainWeather;
import eu.captaincode.backend.spring.SpringConfiguration;
import eu.captaincode.backend.util.OpenWeatherJsonParser;
import eu.captaincode.backend.util.OpenWeatherNetworkUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class OpenWeatherRequestTest {
    private static final double TEMPERATURE = 20.0;
    private static final String CITY = "Budapest";
    private static final String JSON_STRING = "{\"coord\":{\"lon\":19.04,\"lat\":47.5}}";
    private static final String EMPTY_STRING = "";

    @Mock
    private OpenWeatherJsonParser jsonParser;

    @Mock
    private OpenWeatherNetworkUtils networkUtils;

    @Mock
    private CityWeather cityWeather;


    private OpenWeatherRequest testRequest;

    @Before
    public void setUp() {
        cityWeather = mock(CityWeather.class);
        jsonParser = mock(OpenWeatherJsonParser.class);
        networkUtils = mock(OpenWeatherNetworkUtils.class);
        testRequest = new OpenWeatherRequest(networkUtils, jsonParser);
    }

    @Test
    public void whenCalledWithCity_thenRequestReturnsTemperature() {
        // given
        MainWeather weather = new MainWeather();
        weather.setTemp(TEMPERATURE);
        when(cityWeather.getCod()).thenReturn(200);
        when(cityWeather.getMain()).thenReturn(weather);
        when(networkUtils.getCityWeatherJson(CITY)).thenReturn(JSON_STRING);
        when(jsonParser.parseCityWeatherFrom(anyString())).thenReturn(cityWeather);

        // when
        Double result = testRequest.getTemperatureByCity(CITY);

        // then
        assertEquals("Temperature mismatch", TEMPERATURE, result, 0.1);
    }

    @Test
    public void whenCalledWithEmptyString_thenRequestReturnsNull() {
        // given
        when(networkUtils.getCityWeatherJson(EMPTY_STRING)).thenReturn(null);
        when(jsonParser.parseCityWeatherFrom(EMPTY_STRING)).thenReturn(null);

        // when
        Double result = testRequest.getTemperatureByCity(EMPTY_STRING);

        // then
        assertNull("Empty string invocation problem", result);
    }
}