package com.ks.study.meteo.runner;

import com.ks.study.meteo.city.model.CityGeographicData;
import com.ks.study.meteo.city.service.CityReaderService;
import com.ks.study.meteo.converter.WeatherConverterFacade;
import com.ks.study.meteo.model.WeatherDataDto;
import com.ks.study.meteo.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

    private final ConfigurableApplicationContext context;
    private final Map<String, CityGeographicData> cityGeographicDataMap;
    private final WeatherService weatherService;
    private final WeatherConverterFacade weatherConverterFacade;
    private final MultiValueMap<String, WeatherDataDto> weatherDataMap = new LinkedMultiValueMap<>();

    public AppRunner(CityReaderService cityReaderService, WeatherService weatherService,
                     WeatherConverterFacade weatherConverterFacade, ConfigurableApplicationContext context) throws IOException {
        this.cityGeographicDataMap = cityReaderService.readDataFromCityFile();
        this.weatherService = weatherService;
        this.weatherConverterFacade = weatherConverterFacade;
        this.context = context;
        log.info("Data with cities with geographic points loaded successfully");
    }

    @Override
    public void run(String... args) {
        Scanner in = new Scanner(System.in);
        readWeatherData(in);
        System.exit(SpringApplication.exit(context));
    }

    private void readWeatherData(Scanner in) {
        boolean enabled = true;
        while (enabled) {
            log.info("C - Give a city, E - close app");
            String startType = in.nextLine();
            if (shouldStop(startType)) {
                enabled = false;
            }
            if (!startType.equals("C")) {
                continue;
            }
            log.info("Type city name:");
            String city = in.nextLine();
            CityGeographicData cityGeographicData = cityGeographicDataMap.get(city);
            if (cityGeographicData == null) {
                log.error("City does not exists in our database");
                continue;
            }
            var weatherData = weatherService.getWeatherData(city, cityGeographicData);
            if(weatherData==null) {
                log.error("Could not get weather for city: " + city);
                continue;
            }
            weatherDataMap.add(city, weatherData);
            printToConsole(weatherData);

            log.info("""
                    If you want to print data to pdf type 'P'
                    If you want to print data to xml type 'X'
                    If you want to print data to json type 'J'
                    If you want to get weather for another city type 'C'
                    If you want to exit type 'E'.""");
            String exitString = in.nextLine();
            if (shouldStop(exitString)) {
                enabled = false;
            }

            if (exitString.equalsIgnoreCase("C")) {
                continue;
            }

            if (exitString.equalsIgnoreCase("P")) {
                printToPdf();
            }

            if (exitString.equalsIgnoreCase("X")) {
                printToXml();
            }

            if (exitString.equalsIgnoreCase("J")) {
                printToJson();
            }
        }
        weatherDataMap.clear();
        in.close();
    }

    private void printToConsole(WeatherDataDto weatherData) {
        log.info(weatherConverterFacade.getWeatherAsString(weatherData));
    }

    private void printToPdf() {
        log.info(weatherConverterFacade.printToPdf(weatherDataMap));
    }

    private void printToJson() {
        String jsonValue = weatherConverterFacade.printToJson(weatherDataMap);
        log.info("File saved as Json");
        log.info(jsonValue);
    }

    private void printToXml() {
        String xmlValue = weatherConverterFacade.printToXml(weatherDataMap);
        log.info("File saved as XML");
        log.info(xmlValue);
    }

    private static boolean shouldStop(String in) {
        return in.compareToIgnoreCase("E") == 0;
    }
}
