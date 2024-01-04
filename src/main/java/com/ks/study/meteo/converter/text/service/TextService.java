package com.ks.study.meteo.converter.text.service;

import com.ks.study.meteo.converter.text.mapper.WeatherDataDtoToStringMapper;
import com.ks.study.meteo.model.WeatherDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextService {
    private final WeatherDataDtoToStringMapper weatherDataDtoToStringMapper;

    public String getWeatherAsPlainTextString(WeatherDataDto weatherDataDto) {
        return weatherDataDtoToStringMapper.map(weatherDataDto);
    }
}
