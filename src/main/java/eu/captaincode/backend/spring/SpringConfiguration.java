package eu.captaincode.backend.spring;

import eu.captaincode.backend.service.OpenWeatherRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public OpenWeatherRequest openWeatherRequest() {
        return new OpenWeatherRequest();
    }
}
