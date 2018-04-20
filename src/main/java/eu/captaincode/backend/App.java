package eu.captaincode.backend;

import eu.captaincode.backend.dto.CityWeather;
import eu.captaincode.backend.util.JsonParser;
import eu.captaincode.backend.util.NetworkUtils;

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
        Scanner scanner = new Scanner(System.in);
        //TODO: Handle city names with space
        String city = scanner.next();
        String jsonString = NetworkUtils.getCityWeatherJson(city);
        CityWeather cityWeather = JsonParser.parseCityWeatherFrom(jsonString);
        System.out.printf("The temperature in %s is %f °C: ", cityWeather.getName(), cityWeather.getMain().getTemp());
    }
}
