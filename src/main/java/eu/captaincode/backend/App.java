package eu.captaincode.backend;

import eu.captaincode.backend.service.OpenWeatherRequest;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        System.out.println("Enter city name to show current temperature for:");
        Scanner scanner = new Scanner(System.in);
        //TODO: Handle city names with space
        String city = scanner.next();
        OpenWeatherRequest openWeatherRequest = new OpenWeatherRequest();
        Double cityTemperature = openWeatherRequest.getTemperatureByCity(city);
        System.out.printf("The temperature in %s is %f °C: ", city, cityTemperature);
    }
}
