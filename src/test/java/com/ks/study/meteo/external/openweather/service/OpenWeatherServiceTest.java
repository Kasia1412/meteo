package com.ks.study.meteo.external.openweather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.study.meteo.external.config.RestTimeoutProperties;
import com.ks.study.meteo.external.openweather.config.OpenWeatherProperties;
import com.ks.study.meteo.external.openweather.model.CurrentWeatherData;
import com.ks.study.meteo.external.openweather.model.WeatherForCityResponse;
import com.ks.study.meteo.helper.WeatherForCityResponseHelper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OpenWeatherServiceTest {

    private MockWebServer mockWebServer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private OpenWeatherService openWeatherService;

    @BeforeEach
    void setup() {
        mockWebServer = new MockWebServer();
        OpenWeatherProperties openWeatherProperties = new OpenWeatherProperties();
        openWeatherProperties.setUrl(mockWebServer.url("/").url().toString());
        openWeatherProperties.setApiKey(RandomStringUtils.randomNumeric(10));

        RestTimeoutProperties restTimeoutSettings = new RestTimeoutProperties();
        restTimeoutSettings.setConnectTimeout(1000);
        restTimeoutSettings.setReadTimeout(1000);
        restTimeoutSettings.setWriteTimeout(1000);

        openWeatherService = new OpenWeatherService(restTimeoutSettings, openWeatherProperties);
    }


    @Test
    void shouldGetResponse() throws InterruptedException, JsonProcessingException {
        //given
        BigDecimal lon = BigDecimal.ONE;
        BigDecimal lat = BigDecimal.TEN;
        WeatherForCityResponse response = WeatherForCityResponseHelper.createSampleResponse();
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(objectMapper.writeValueAsString(response))
        );

        //when
        WeatherForCityResponse result = openWeatherService.getCurrentWeatherByGeoData(lon, lat);

        //then
        RecordedRequest recorderRequest = mockWebServer.takeRequest();
        assertThat(recorderRequest.getMethod()).isEqualTo("GET");
        assertThat(result)
                .returns(response.getName(), WeatherForCityResponse::getName)
                .returns(response.getVisibility(), WeatherForCityResponse::getVisibility);
        assertThat(result.getMain())
                .returns(response.getMain().getHumidity(), CurrentWeatherData::getHumidity)
                .returns(response.getMain().getPressure(), CurrentWeatherData::getPressure)
                .returns(response.getMain().getTemp(), CurrentWeatherData::getTemp);
    }
}
