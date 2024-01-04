package com.ks.study.meteo.external.openweather.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties("external.open-weather")
public class OpenWeatherProperties {
    private String url;
    private String apiKey;
}
