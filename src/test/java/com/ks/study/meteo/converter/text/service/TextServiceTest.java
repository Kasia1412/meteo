package com.ks.study.meteo.converter.text.service;

import com.ks.study.meteo.converter.text.mapper.WeatherDataDtoToStringMapper;
import com.ks.study.meteo.helper.WeatherDataDtoHelper;
import com.ks.study.meteo.model.WeatherDataDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.ks.study.meteo.converter.text.mapper.WeatherDataDtoToStringMapper.ERROR_RESULT_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;

class TextServiceTest {
    private final WeatherDataDtoToStringMapper weatherDataDtoToStringMapper =
            new WeatherDataDtoToStringMapper();

    private final TextService textService = new TextService(weatherDataDtoToStringMapper);

    @Test
    void shouldGetWeatherAsPlainText() {
        //given
        var weatherDataDto = WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder()
                .weatherTime(LocalDateTime.of(2020, 1, 1, 12, 0, 0, 0).toString())
                .temp(BigDecimal.ONE)
                .city("Krakow")
                .humidity(BigDecimal.TEN)
                .pressure(BigDecimal.TEN)
                .visibility(BigDecimal.TEN)
                .build();

        //when
        String result = textService.getWeatherAsPlainTextString(weatherDataDto);

        //then
        String expected = """
                Weather for city: Krakow at: 2020-01-01T12:00
                Temp: 1, Pressure: 10, Humidity: 10, Visibility: 10""";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldMapForNullableData() {
        //given
        WeatherDataDto weatherDataDto = null;

        //when
        String result = textService.getWeatherAsPlainTextString(weatherDataDto);

        //then
        assertThat(result).isEqualTo(ERROR_RESULT_PATTERN);
    }
}
