package eu.captaincode.backend.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.captaincode.backend.dto.CityWeather;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class OpenWeatherJsonParser {
    private static Logger logger = LoggerFactory.getLogger(OpenWeatherJsonParser.class);

    public CityWeather parseCityWeatherFrom(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            logger.error("Empty jsonString entered for parsing");
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();

        //TODO: Handle errors
        CityWeather cityWeather = null;
        try {
            cityWeather = mapper.readValue(jsonString, CityWeather.class);
        } catch (JsonParseException | JsonMappingException e) {
            logger.error("JsonException while parsing jsonString", e);
        } catch (IOException e) {
            logger.error("IOException while parsing jsonString", e);
        }

        return cityWeather;
    }
}
