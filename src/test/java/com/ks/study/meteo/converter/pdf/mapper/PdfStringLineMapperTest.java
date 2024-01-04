package com.ks.study.meteo.converter.pdf.mapper;

import com.ks.study.meteo.helper.WeatherDataDtoHelper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PdfStringLineMapperTest {
    private final PdfStringLineMapper pdfStringLineMapper = new PdfStringLineMapper();

    @Test
    void shouldMapFully() {
        //given
        var dto = WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder()
                .city("Krakow")
                .weatherTime(LocalDateTime.of(2023, 11, 11, 12, 0, 0).toString())
                .visibility(BigDecimal.ZERO)
                .humidity(BigDecimal.TEN)
                .temp(BigDecimal.valueOf(12L))
                .pressure(BigDecimal.valueOf(1200))
                .build();

        //when
        String result = pdfStringLineMapper.map(dto);

        //then
        String expected = """
                Weather time: 2023-11-11T12:00
                Pressure: 1200
                Temperature: 12
                Humidity: 10
                Visibility: 0""";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldMapNullable() {
        //when
        String result = pdfStringLineMapper.map(null);

        //then
        assertThat(result).isEmpty();
    }
}
