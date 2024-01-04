package com.ks.study.meteo.service;

import com.ks.study.meteo.external.openweather.service.OpenWeatherService;
import com.ks.study.meteo.helper.CityGeographicDataHelper;
import com.ks.study.meteo.helper.WeatherForCityResponseHelper;
import com.ks.study.meteo.mapper.WeatherDataDtoMapper;
import com.ks.study.meteo.model.WeatherDataDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private OpenWeatherService openWeatherService;

    private final WeatherDataDtoMapper weatherDataDtoMapper = new WeatherDataDtoMapper();
    private WeatherService weatherService;

    @BeforeEach
    void setup() {
        weatherService = new WeatherService(openWeatherService, weatherDataDtoMapper);
    }

    @Test
    void shouldGetWeatherData() {
        //given
        var data = CityGeographicDataHelper.createSampleCityGeographicDataBuilder()
                .lat(BigDecimal.valueOf(19.56))
                .lon(BigDecimal.valueOf(50.04))
                .build();
        var city = "Krakow";
        var response = WeatherForCityResponseHelper.createSampleResponse();

        when(openWeatherService.getCurrentWeatherByGeoData(data.getLon(), data.getLat())).thenReturn(response);

        //when
        WeatherDataDto result = weatherService.getWeatherData(city, data);

        //then
        assertThat(result)
                .returns(city, WeatherDataDto::getCity)
                .returns(response.getVisibility(), WeatherDataDto::getVisibility)
                .returns(response.getMain().getTemp(), WeatherDataDto::getTemp)
                .returns(response.getMain().getHumidity(), WeatherDataDto::getHumidity)
                .returns(response.getMain().getPressure(), WeatherDataDto::getPressure);
    }
}
