package com.ks.study.meteo.mapper;

import com.ks.study.meteo.external.openweather.model.WeatherForCityResponse;
import com.ks.study.meteo.helper.WeatherForCityResponseHelper;
import com.ks.study.meteo.model.WeatherDataDto;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.ks.study.meteo.MainParameters.CITY_KEY;
import static org.assertj.core.api.Assertions.assertThat;

class WeatherDataDtoMapperTest {
    private final WeatherDataDtoMapper weatherDataDtoMapper = new WeatherDataDtoMapper();

    @Test
    void testMapCorrectly() {
        //given
        WeatherForCityResponse source = WeatherForCityResponseHelper.createSampleResponse();
        var city = "Warszawa";
        Map<String, String> params = Map.of(CITY_KEY, city);

        //when
        WeatherDataDto result = weatherDataDtoMapper.map(source, params);

        //then
        assertThat(result)
                .returns(city, WeatherDataDto::getCity)
                .returns(source.getVisibility(), WeatherDataDto::getVisibility)
                .returns(source.getMain().getTemp(), WeatherDataDto::getTemp)
                .returns(source.getMain().getHumidity(), WeatherDataDto::getHumidity)
                .returns(source.getMain().getPressure(), WeatherDataDto::getPressure);

    }
}
