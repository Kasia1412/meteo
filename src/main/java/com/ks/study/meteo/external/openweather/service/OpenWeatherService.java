package com.ks.study.meteo.external.openweather.service;

import com.ks.study.meteo.external.config.RestTimeoutProperties;
import com.ks.study.meteo.external.openweather.config.OpenWeatherProperties;
import com.ks.study.meteo.external.openweather.config.OpenWeatherWebClient;
import com.ks.study.meteo.external.openweather.model.WeatherForCityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class OpenWeatherService extends OpenWeatherWebClient {

    private static final String GET_WEATHER_ENDPOINT = "data/2.5/weather?lat=%s&lon=%s&appId=%s";

    public OpenWeatherService(RestTimeoutProperties restTimeoutProperties, OpenWeatherProperties openWeatherProperties) {
        super(restTimeoutProperties, openWeatherProperties);
    }

    public WeatherForCityResponse getCurrentWeatherByGeoData(BigDecimal lon, BigDecimal lat) {
        return openWeatherWebClient.get()
                .uri(String.format(GET_WEATHER_ENDPOINT, lat, lon, openWeatherProperties.getApiKey()))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, this::handle4xxError)
                .onStatus(HttpStatusCode::is5xxServerError, this::handle5xxError)
                .bodyToMono(WeatherForCityResponse.class)
                .block();
    }
}
