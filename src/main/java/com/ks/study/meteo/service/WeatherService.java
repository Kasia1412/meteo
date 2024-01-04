package com.ks.study.meteo.service;

import com.ks.study.meteo.city.model.CityGeographicData;
import com.ks.study.meteo.external.openweather.service.OpenWeatherService;
import com.ks.study.meteo.mapper.WeatherDataDtoMapper;
import com.ks.study.meteo.model.WeatherDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.ks.study.meteo.MainParameters.CITY_KEY;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final OpenWeatherService openWeatherService;
    private final WeatherDataDtoMapper weatherDataDtoMapper;

    public WeatherDataDto getWeatherData(String city, CityGeographicData cityGeographicData) {
        try {
            var weatherForCityResponse = openWeatherService
                    .getCurrentWeatherByGeoData(cityGeographicData.getLon(), cityGeographicData.getLat());
            return weatherDataDtoMapper.map(weatherForCityResponse, Map.of(CITY_KEY, city));
        } catch (Exception e) {
            return null;
        }
    }
}