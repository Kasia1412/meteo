package com.ks.study.meteo.external.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties("external.rest")
public class RestTimeoutProperties {
    private int connectTimeout;
    private int readTimeout;
    private int writeTimeout;
}
