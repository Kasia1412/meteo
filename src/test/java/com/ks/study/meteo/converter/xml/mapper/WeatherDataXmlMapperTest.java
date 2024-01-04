package com.ks.study.meteo.converter.xml.mapper;

import com.ks.study.meteo.converter.xml.model.WeatherDataXml;
import com.ks.study.meteo.helper.WeatherDataDtoHelper;
import com.ks.study.meteo.model.WeatherDataDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherDataXmlMapperTest {
    private final WeatherDataXmlMapper weatherDataXmlMapper = new WeatherDataXmlMapper();

    @Test
    void shouldMapFully() {
        //given
        WeatherDataDto weatherDataDto1 = WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder().build();
        WeatherDataDto weatherDataDto2 = WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder().build();
        List<WeatherDataDto> weatherDataXmlList = List.of(weatherDataDto1, weatherDataDto2);

        //when
        List<WeatherDataXml> result = weatherDataXmlMapper.map(weatherDataXmlList);

        //then
        assertThat(result).hasSize(2);
        assertThat(result.get(0))
                .returns(weatherDataDto1.getWeatherTime(), WeatherDataXml::getWeatherTime)
                .returns(weatherDataDto1.getTemp(), WeatherDataXml::getTemp)
                .returns(weatherDataDto1.getVisibility(), WeatherDataXml::getVisibility)
                .returns(weatherDataDto1.getPressure(), WeatherDataXml::getPressure)
                .returns(weatherDataDto1.getHumidity(), WeatherDataXml::getHumidity);

        assertThat(result.get(1))
                .returns(weatherDataDto2.getWeatherTime(), WeatherDataXml::getWeatherTime)
                .returns(weatherDataDto2.getTemp(), WeatherDataXml::getTemp)
                .returns(weatherDataDto2.getVisibility(), WeatherDataXml::getVisibility)
                .returns(weatherDataDto2.getPressure(), WeatherDataXml::getPressure)
                .returns(weatherDataDto2.getHumidity(), WeatherDataXml::getHumidity);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldMapEmptyOrNullable(List<WeatherDataDto> weatherDataDtos) {
        //when
        List<WeatherDataXml> result = weatherDataXmlMapper.map(weatherDataDtos);

        //then
        assertThat(result).isEmpty();
    }


}
