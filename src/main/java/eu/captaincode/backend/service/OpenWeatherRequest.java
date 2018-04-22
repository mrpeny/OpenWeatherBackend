package eu.captaincode.backend.service;

import eu.captaincode.backend.dto.CityWeather;
import eu.captaincode.backend.util.OpenWeatherJsonParser;
import eu.captaincode.backend.util.OpenWeatherNetworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenWeatherRequest implements WeatherRequestService {
    private static final int COD_OK = 200;
    private final Logger logger = LoggerFactory.getLogger(OpenWeatherRequest.class);

    private final OpenWeatherNetworkUtils openWeatherNetworkUtils;
    private final OpenWeatherJsonParser openWeatherJsonParser;

    public OpenWeatherRequest(OpenWeatherNetworkUtils openWeatherNetworkUtils,
                              OpenWeatherJsonParser openWeatherJsonParser) {
        this.openWeatherNetworkUtils = openWeatherNetworkUtils;
        this.openWeatherJsonParser = openWeatherJsonParser;
    }

    @Override
    public Double getTemperatureByCity(String cityName) {
        String jsonString = openWeatherNetworkUtils.getCityWeatherJson(cityName);
        CityWeather cityWeather = openWeatherJsonParser.parseCityWeatherFrom(jsonString);

        if (cityWeather == null) {
            logger.error("CityWeather is null");
            System.out.println("Problem occurred while retrieving data, please try again later!");
            return null;
        }
        if (cityWeather.getCod() != COD_OK) {
            logger.error("Problem while retrieving data: {}", cityWeather.getMessage());
            System.out.printf("Problem occurred while retrieving data: %s", cityWeather.getMessage());
            return null;
        }

        return cityWeather.getMain().getTemp();
    }
}