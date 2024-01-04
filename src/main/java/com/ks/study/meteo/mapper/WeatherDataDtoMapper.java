package com.ks.study.meteo.mapper;

import com.ks.study.meteo.base.MapperParametrized;
import com.ks.study.meteo.external.openweather.model.WeatherForCityResponse;
import com.ks.study.meteo.model.WeatherDataDto;
import com.ks.study.meteo.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

import static com.ks.study.meteo.MainParameters.CITY_KEY;

@Component
public class WeatherDataDtoMapper implements MapperParametrized<WeatherForCityResponse, WeatherDataDto, String> {
    @Override
    public WeatherDataDto map(WeatherForCityResponse source, Map<String, String> params) {
        return WeatherDataDto.builder()
                .weatherTime(DateUtils.getFormattedLocalDateTime(LocalDateTime.now()))
                .city(params.get(CITY_KEY))
                .visibility(source.getVisibility())
                .humidity(source.getMain().getHumidity())
                .pressure(source.getMain().getPressure())
                .temp(source.getMain().getTemp())
                .build();
    }
}
