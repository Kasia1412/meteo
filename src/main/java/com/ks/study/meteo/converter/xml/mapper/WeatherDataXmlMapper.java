package com.ks.study.meteo.converter.xml.mapper;

import com.ks.study.meteo.base.Mapper;
import com.ks.study.meteo.converter.xml.model.WeatherDataXml;
import com.ks.study.meteo.model.WeatherDataDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class WeatherDataXmlMapper implements Mapper<List<WeatherDataDto>, List<WeatherDataXml>> {
    @Override
    public List<WeatherDataXml> map(List<WeatherDataDto> source) {
        if (CollectionUtils.isEmpty(source)) {
            return List.of();
        }
        return source.stream().map(val -> WeatherDataXml.builder()
                .weatherTime(val.getWeatherTime())
                .visibility(val.getVisibility())
                .temp(val.getTemp())
                .pressure(val.getPressure())
                .humidity(val.getHumidity())
                .build()).toList();
    }
}
