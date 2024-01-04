package com.ks.study.meteo.helper;

import com.ks.study.meteo.model.WeatherDataDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.List;

import static com.ks.study.meteo.helper.TestUtils.createRandomBigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WeatherDataDtoHelper {
    public static WeatherDataDto.WeatherDataDtoBuilder createSampleWeatherDataDtoBuilder() {
        return WeatherDataDto.builder()
                .temp(createRandomBigDecimal())
                .pressure(createRandomBigDecimal())
                .humidity(createRandomBigDecimal())
                .city(RandomStringUtils.randomAlphabetic(10))
                .weatherTime(LocalDateTime.now().toString())
                .visibility(createRandomBigDecimal());
    }

    public static MultiValueMap<String, WeatherDataDto> createSampleWeatherDataDtoMultiValueMap(List<String> cites) {
        MultiValueMap<String, WeatherDataDto> map = new LinkedMultiValueMap<>();
        cites.forEach(city -> map.add(city, createSampleWeatherDataDtoBuilder()
                .city(city)
                .build()));

        return map;
    }

    public static MultiValueMap<String, WeatherDataDto> createSampleWeatherDataDtoMultiValueMapWithSpecifiedData(String city, WeatherDataDto weatherDataDto) {
        MultiValueMap<String, WeatherDataDto> map = new LinkedMultiValueMap<>();
        map.add(city, weatherDataDto);

        return map;
    }
}
