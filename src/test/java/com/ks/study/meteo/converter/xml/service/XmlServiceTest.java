package com.ks.study.meteo.converter.xml.service;

import com.ks.study.meteo.converter.xml.mapper.WeatherDataMapToXmlMapper;
import com.ks.study.meteo.converter.xml.mapper.WeatherDataXmlMapper;
import com.ks.study.meteo.converter.xml.properties.XmlProperties;
import com.ks.study.meteo.helper.WeatherDataDtoHelper;
import com.ks.study.meteo.model.FileExtension;
import com.ks.study.meteo.model.WeatherDataDto;
import com.ks.study.meteo.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.ks.study.meteo.helper.TestUtils.XML_GENERATED_PATH;
import static com.ks.study.meteo.helper.WeatherDataDtoHelper.createSampleWeatherDataDtoMultiValueMapWithSpecifiedData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class XmlServiceTest {
    private static final String KRAKOW_CITY = "Krakow";
    @Mock
    private FileService fileService;

    private final XmlProperties xmlProperties = new XmlProperties();
    private XmlService xmlService;

    @BeforeEach
    void setup() {
        xmlProperties.setPath(XML_GENERATED_PATH);
        WeatherDataMapToXmlMapper weatherDataMapToXmlMapper = new WeatherDataMapToXmlMapper(new WeatherDataXmlMapper());
        xmlService = new XmlService(xmlProperties, fileService, weatherDataMapToXmlMapper);
    }

    @Test
    void shouldPrintAndSaveCorrectly() throws IOException {
        //given
        MultiValueMap<String, WeatherDataDto> source = createSampleWeatherDataDtoMultiValueMapWithSpecifiedData(KRAKOW_CITY, WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder()
                .weatherTime(LocalDateTime.of(2023, 11, 11, 12, 0, 0).toString())
                .visibility(BigDecimal.ZERO)
                .humidity(BigDecimal.TEN)
                .temp(BigDecimal.valueOf(12L))
                .pressure(BigDecimal.valueOf(1200))
                .build());

        String name = "name";
        when(fileService.saveStringFile(eq(FileExtension.XML), eq(XML_GENERATED_PATH), anyString())).thenReturn(name);

        //when
        String result = xmlService.printAndSaveAsXml(source);

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
                </weatherData>
                """;
        assertThat(result).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void shouldNotPrintAndSaveWhenExceptionThrows() throws IOException {
        //given
        MultiValueMap<String, WeatherDataDto> source = createSampleWeatherDataDtoMultiValueMapWithSpecifiedData(KRAKOW_CITY, WeatherDataDtoHelper.createSampleWeatherDataDtoBuilder()
                .weatherTime(LocalDateTime.of(2023, 11, 11, 12, 0, 0).toString())
                .visibility(BigDecimal.ZERO)
                .humidity(BigDecimal.TEN)
                .temp(BigDecimal.valueOf(12L))
                .pressure(BigDecimal.valueOf(1200))
                .build());

        IOException ioException = new IOException();
        doThrow(ioException).when(fileService).saveStringFile(eq(FileExtension.XML), eq(XML_GENERATED_PATH), anyString());

        //when then
        assertThatThrownBy(() -> xmlService.printAndSaveAsXml(source))
                .isInstanceOf(IOException.class)
                .isEqualTo(ioException);
    }

}
