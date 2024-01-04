package com.ks.study.meteo.converter.json.service;

import com.ks.study.meteo.converter.json.mapper.WeatherDataJsonMapper;
import com.ks.study.meteo.converter.json.mapper.WeatherDataMapToJsonMapper;
import com.ks.study.meteo.converter.json.properties.JsonProperties;
import com.ks.study.meteo.helper.WeatherDataDtoHelper;
import com.ks.study.meteo.model.FileExtension;
import com.ks.study.meteo.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.ks.study.meteo.helper.TestUtils.JSON_GENERATED_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JsonServiceTest {
    private final WeatherDataMapToJsonMapper weatherDataMapToJsonMapper =
            new WeatherDataMapToJsonMapper(new WeatherDataJsonMapper());
    private final JsonProperties jsonProperties = new JsonProperties();
    @Mock
    private FileService fileService;

    private JsonService jsonService;

    @BeforeEach
    void setup() {
        jsonProperties.setPath(JSON_GENERATED_PATH);
        jsonService = new JsonService(weatherDataMapToJsonMapper, fileService, jsonProperties);
    }

    @Test
    void shouldPrintAndSaveCorrectly() throws IOException {
        //given
        var city = "Warszawa";
        var map = WeatherDataDtoHelper.createSampleWeatherDataDtoMultiValueMapWithSpecifiedData(city, WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder()
                .city(city)
                .weatherTime(LocalDateTime.of(2023, 11, 11, 12, 0, 0).toString())
                .visibility(BigDecimal.ZERO)
                .humidity(BigDecimal.TEN)
                .temp(BigDecimal.valueOf(12L))
                .pressure(BigDecimal.valueOf(1200))
                .build());
        String name = "name";
        when(fileService.saveStringFile(eq(FileExtension.JSON), eq(jsonProperties.getPath()), anyString())).thenReturn(name);

        //when
        var result = jsonService.printAndSaveAsJson(map);

        //then
        var expected = """
                {
                  "Warszawa": [
                    {
                      "weatherTime": "2023-11-11T12:00",
                      "pressure": 1200,
                      "temp": 12,
                      "humidity": 10,
                      "visibility": 0
                    }
                  ]
                }""";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldNotPrintAndSaveJsonWhenExceptionThrows() throws IOException {
        var city = "Warszawa";
        var map = WeatherDataDtoHelper.createSampleWeatherDataDtoMultiValueMap(List.of(city));
        IOException ioException = new IOException();
        doThrow(ioException).when(fileService).saveStringFile(eq(FileExtension.JSON), eq(jsonProperties.getPath()), anyString());

        //when then
        assertThatThrownBy(() -> jsonService.printAndSaveAsJson(map))
                .isInstanceOf(IOException.class)
                .isEqualTo(ioException);
    }
}
