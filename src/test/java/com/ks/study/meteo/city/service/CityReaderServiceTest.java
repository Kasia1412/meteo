package com.ks.study.meteo.city.service;

import com.ks.study.meteo.city.properties.CityReaderProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class CityReaderServiceTest {

    private static final String CITY_PATH = "src/test/resources/cities/test-cities.json";
    private final CityReaderProperties cityReaderProperties = new CityReaderProperties();
    private CityReaderService cityReaderService;

    @BeforeEach
    void setup() {
        cityReaderProperties.setCityFilePath(CITY_PATH);
        cityReaderService = new CityReaderService(cityReaderProperties);
    }

    @Test
    void shouldLoadCitiesFileToMap() throws IOException {
        assertThat(cityReaderService.readDataFromCityFile())
                .containsKeys("Krakow", "Gdansk", "Lodz", "Poznan", "Rzeszow");
    }

    @Test
    void shouldReturnExceptionWhenTryToLoadCities() {
        cityReaderProperties.setCityFilePath("a");
        assertThatThrownBy(() -> cityReaderService.readDataFromCityFile())
                .isInstanceOf(IOException.class);
    }

}
