package com.ks.study.meteo.converter.json.mapper;

import com.ks.study.meteo.helper.WeatherDataDtoHelper;
import com.ks.study.meteo.model.WeatherDataDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherDataMapToJsonMapperTest {
    private static final String KRAKOW_CITY = "Krakow";
    private static final String POZNAN_CITY = "Poznan";
    private static final List<String> CITIES = List.of(KRAKOW_CITY, POZNAN_CITY);
    private final WeatherDataJsonMapper weatherDataJsonMapper = new WeatherDataJsonMapper();
    private final WeatherDataMapToJsonMapper weatherDataMapToJsonMapper = new WeatherDataMapToJsonMapper(weatherDataJsonMapper);

    @Test
    void testMapFully() {
        //given
        var map = WeatherDataDtoHelper.createSampleWeatherDataDtoMultiValueMap(CITIES);

        //when
        String result = weatherDataMapToJsonMapper.map(map);

        //then
        assertThat(result).isNotBlank();
    }

    @ParameterizedTest
    @NullSource
    void testMapEmpty(MultiValueMap<String, WeatherDataDto> map) {
        //when
        String result = weatherDataMapToJsonMapper.map(map);

        //then
        assertThat(result).isBlank();
    }
}
