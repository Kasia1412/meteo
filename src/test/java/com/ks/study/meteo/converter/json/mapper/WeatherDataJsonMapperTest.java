package com.ks.study.meteo.converter.json.mapper;

import com.ks.study.meteo.converter.json.model.WeatherDataJson;
import com.ks.study.meteo.helper.WeatherDataDtoHelper;
import com.ks.study.meteo.model.WeatherDataDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherDataJsonMapperTest {
    private final WeatherDataJsonMapper mapper = new WeatherDataJsonMapper();

    @Test
    void shouldMapFully() {
        //given
        WeatherDataDto weatherDataDto1 = WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder().build();
        WeatherDataDto weatherDataDto2 = WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder().build();
        List<WeatherDataDto> weatherDataDtoList = List.of(weatherDataDto1, weatherDataDto2);

        //when
        List<WeatherDataJson> result = mapper.map(weatherDataDtoList);

        //then
        assertThat(result).hasSize(2);
        assertThat(result.get(0))
                .returns(weatherDataDto1.getWeatherTime(), WeatherDataJson::getWeatherTime)
                .returns(weatherDataDto1.getTemp(), WeatherDataJson::getTemp)
                .returns(weatherDataDto1.getVisibility(), WeatherDataJson::getVisibility)
                .returns(weatherDataDto1.getHumidity(), WeatherDataJson::getHumidity)
                .returns(weatherDataDto1.getPressure(), WeatherDataJson::getPressure);

        assertThat(result.get(1))
                .returns(weatherDataDto2.getWeatherTime(), WeatherDataJson::getWeatherTime)
                .returns(weatherDataDto2.getTemp(), WeatherDataJson::getTemp)
                .returns(weatherDataDto2.getVisibility(), WeatherDataJson::getVisibility)
                .returns(weatherDataDto2.getHumidity(), WeatherDataJson::getHumidity)
                .returns(weatherDataDto2.getPressure(), WeatherDataJson::getPressure);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testWithEmptyList(List<WeatherDataDto> weatherDataDtoList) {
        //when
        List<WeatherDataJson> result = mapper.map(weatherDataDtoList);

        //then
        assertThat(result).isEmpty();
    }
}
