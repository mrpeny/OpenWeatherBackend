package eu.captaincode.backend.service;

import eu.captaincode.backend.dto.CityWeather;
import eu.captaincode.backend.util.JsonParser;
import eu.captaincode.backend.util.NetworkUtils;

public class OpenWeatherRequest implements WeatherRequestService {
    @Override
    public Double getTemperatureByCity(String cityName) {
        String jsonString = NetworkUtils.getCityWeatherJson(cityName);
        CityWeather cityWeather = JsonParser.parseCityWeatherFrom(jsonString);
        return cityWeather.getMain().getTemp();
    }


}
