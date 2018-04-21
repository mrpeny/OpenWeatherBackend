package eu.captaincode.backend.spring;

import eu.captaincode.backend.service.OpenWeatherRequest;
import eu.captaincode.backend.util.OpenWeatherJsonParser;
import eu.captaincode.backend.util.OpenWeatherNetworkUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public OpenWeatherRequest openWeatherRequest() {
        return new OpenWeatherRequest(openWeatherNetworkUtils(), openWeatherJsonParser());
    }

    @Bean
    public OpenWeatherNetworkUtils openWeatherNetworkUtils() {
        return new OpenWeatherNetworkUtils();
    }

    @Bean
    public OpenWeatherJsonParser openWeatherJsonParser() {
        return new OpenWeatherJsonParser();
    }
}
