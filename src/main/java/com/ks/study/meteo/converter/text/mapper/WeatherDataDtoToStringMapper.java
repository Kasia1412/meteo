package com.ks.study.meteo.converter.text.mapper;

import com.ks.study.meteo.base.Mapper;
import com.ks.study.meteo.model.WeatherDataDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataDtoToStringMapper implements Mapper<WeatherDataDto, String> {
    private static final String RESULT_PATTERN = "Weather for city: %s at: %s\nTemp: %s, Pressure: %s, Humidity: %s, Visibility: %s";
    public static final String ERROR_RESULT_PATTERN = "Can not read data from openweathermap.";

    @Override
    public String map(WeatherDataDto source) {
        if (source != null) {
            return String.format(RESULT_PATTERN, source.getCity(),
                    source.getWeatherTime(), source.getTemp(),
                    source.getPressure(), source.getHumidity(), source.getVisibility());
        }
        return ERROR_RESULT_PATTERN;
    }
}
