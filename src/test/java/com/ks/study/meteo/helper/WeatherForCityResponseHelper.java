package com.ks.study.meteo.helper;

import com.ks.study.meteo.external.openweather.model.CurrentWeatherData;
import com.ks.study.meteo.external.openweather.model.WeatherForCityResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WeatherForCityResponseHelper {
    public static WeatherForCityResponse createSampleResponse() {
        WeatherForCityResponse weatherForCityResponse = new WeatherForCityResponse();
        weatherForCityResponse.setName(RandomStringUtils.randomAlphabetic(10));
        weatherForCityResponse.setVisibility(BigDecimal.TEN);
        weatherForCityResponse.setMain(createSampleCurrentWeatherData());
        return weatherForCityResponse;
    }

    public static CurrentWeatherData createSampleCurrentWeatherData() {
        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setHumidity(BigDecimal.TEN);
        currentWeatherData.setTemp(BigDecimal.ONE);
        currentWeatherData.setPressure(BigDecimal.ONE);

        return currentWeatherData;
    }
}
