package com.ks.study.meteo.converter.json.mapper;

import com.ks.study.meteo.base.Mapper;
import com.ks.study.meteo.converter.json.model.WeatherDataJson;
import com.ks.study.meteo.model.WeatherDataDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class WeatherDataJsonMapper implements Mapper<List<WeatherDataDto>, List<WeatherDataJson>> {
    @Override
    public List<WeatherDataJson> map(List<WeatherDataDto> source) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        return source.stream().map(val -> WeatherDataJson.builder()
                .weatherTime(val.getWeatherTime())
                .visibility(val.getVisibility())
                .temp(val.getTemp())
                .pressure(val.getPressure())
                .humidity(val.getHumidity())
                .build()).toList();
    }
}
