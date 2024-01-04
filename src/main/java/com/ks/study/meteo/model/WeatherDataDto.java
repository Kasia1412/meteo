package com.ks.study.meteo.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WeatherDataDto {
    private String city;
    private String weatherTime;
    private BigDecimal pressure;
    private BigDecimal temp;
    private BigDecimal humidity;
    private BigDecimal visibility;
}
