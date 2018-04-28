package eu.captaincode.backend.spring;

import eu.captaincode.backend.service.OpenWeatherRequestServiceImpl;
import eu.captaincode.backend.util.OpenWeatherJsonParser;
import eu.captaincode.backend.util.OpenWeatherNetworkUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public OpenWeatherRequestServiceImpl openWeatherRequest() {
        return new OpenWeatherRequestServiceImpl(openWeatherNetworkUtils(), openWeatherJsonParser());
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
