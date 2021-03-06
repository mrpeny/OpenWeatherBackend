package eu.captaincode.backend;

import eu.captaincode.backend.service.OpenWeatherRequestServiceImpl;
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

    String run() {
        System.out.print("Enter city name to show current temperature for: ");
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine().trim();

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        WeatherRequestService weatherRequestService = context.getBean(OpenWeatherRequestServiceImpl.class);

        Double cityTemperature = weatherRequestService.getTemperatureByCity(city);
        String message = null;
        if (cityTemperature != null) {
            message = String.format("The temperature in %s is %d °C: ", city, Math.round(cityTemperature));
            System.out.println(message);
        }
        return message;
    }
}
