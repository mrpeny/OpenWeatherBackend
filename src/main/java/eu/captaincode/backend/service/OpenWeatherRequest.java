package eu.captaincode.backend.service;

import eu.captaincode.backend.dto.CityWeather;
import eu.captaincode.backend.util.OpenWeatherJsonParser;
import eu.captaincode.backend.util.OpenWeatherNetworkUtils;

public class OpenWeatherRequest implements WeatherRequestService {
    private OpenWeatherNetworkUtils openWeatherNetworkUtils;
    private OpenWeatherJsonParser openWeatherJsonParser;

    public OpenWeatherRequest(OpenWeatherNetworkUtils openWeatherNetworkUtils,
                              OpenWeatherJsonParser openWeatherJsonParser) {
        this.openWeatherNetworkUtils = openWeatherNetworkUtils;
        this.openWeatherJsonParser = openWeatherJsonParser;
    }

    @Override
    public Double getTemperatureByCity(String cityName) {
        String jsonString = openWeatherNetworkUtils.getCityWeatherJson(cityName);
        CityWeather cityWeather = openWeatherJsonParser.parseCityWeatherFrom(jsonString);
        return cityWeather.getMain().getTemp();
    }
}
