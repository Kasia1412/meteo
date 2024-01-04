package com.ks.study.meteo.converter.xml.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WeatherDataXml {
    private String weatherTime;
    private BigDecimal pressure;
    private BigDecimal temp;
    private BigDecimal humidity;
    private BigDecimal visibility;
}
