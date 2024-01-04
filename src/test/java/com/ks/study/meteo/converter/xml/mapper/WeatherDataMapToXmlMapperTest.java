package com.ks.study.meteo.converter.xml.mapper;


import com.ks.study.meteo.helper.WeatherDataDtoHelper;
import com.ks.study.meteo.model.WeatherDataDto;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.ks.study.meteo.helper.WeatherDataDtoHelper.createSampleWeatherDataDtoMultiValueMapWithSpecifiedData;
import static org.assertj.core.api.Assertions.assertThat;

class WeatherDataMapToXmlMapperTest {
    private static final String KRAKOW_CITY = "Krakow";
    private final WeatherDataXmlMapper weatherDataXmlMapper = new WeatherDataXmlMapper();
    private final WeatherDataMapToXmlMapper weatherDataMapToXmlMapper = new WeatherDataMapToXmlMapper(weatherDataXmlMapper);

    @Test
    void shouldMapFully() {
        //given
        MultiValueMap<String, WeatherDataDto> source = createSampleWeatherDataDtoMultiValueMapWithSpecifiedData(KRAKOW_CITY, WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder()
                .weatherTime(LocalDateTime.of(2023, 11, 11, 12, 0, 0).toString())
                .visibility(BigDecimal.ZERO)
                .humidity(BigDecimal.TEN)
                .temp(BigDecimal.valueOf(12L))
                .pressure(BigDecimal.valueOf(1200))
                .build());

        //when
        String result = weatherDataMapToXmlMapper.map(source);

        //then
        String expected = """
                  <?xml version='1.0' encoding='UTF-8'?>
                  <weatherData>
                    <Krakow>
                      <weatherTime>2023-11-11T12:00</weatherTime>
                      <pressure>1200</pressure>
                      <temp>12</temp>
                      <humidity>10</humidity>
                      <visibility>0</visibility>
                    </Krakow>
                  </weatherData>""";
        assertThat(result).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void shouldMapForNullable() {
        //given
        MultiValueMap<String, WeatherDataDto> source = null;

        //when
        String result = weatherDataMapToXmlMapper.map(source);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldMapForEmpty() {
        //given
        MultiValueMap<String, WeatherDataDto> source = new LinkedMultiValueMap<>();

        //when
        String result = weatherDataMapToXmlMapper.map(source);

        //then
        assertThat(result).isEmpty();
    }
}
