package eu.captaincode.backend.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.captaincode.backend.dto.CityWeather;

import java.io.IOException;

public class OpenWeatherJsonParser {
    public CityWeather parseCityWeatherFrom(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();

        //TODO: Handle errors
        CityWeather cityWeather = null;
        try {
            cityWeather = mapper.readValue(jsonString, CityWeather.class);
        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityWeather;
    }
}
