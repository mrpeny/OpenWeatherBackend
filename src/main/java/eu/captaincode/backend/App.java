package eu.captaincode.backend;

import eu.captaincode.backend.service.OpenWeatherRequest;
import eu.captaincode.backend.service.WeatherRequestService;
import eu.captaincode.backend.spring.SpringConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        App app = new App();
        logger.info("App started");
        app.run();
    }

    private void run() {
        System.out.println("Enter city name to show current temperature for:");
        Scanner scanner = new Scanner(System.in);
        //TODO: Handle city names with space
        String city = scanner.next();

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        WeatherRequestService weatherRequestService = context.getBean(OpenWeatherRequest.class);

        Double cityTemperature = weatherRequestService.getTemperatureByCity(city);
        if (cityTemperature != null) {
            System.out.printf("The temperature in %s is %d °C: ", city, Math.round(cityTemperature));
        }
    }
}
