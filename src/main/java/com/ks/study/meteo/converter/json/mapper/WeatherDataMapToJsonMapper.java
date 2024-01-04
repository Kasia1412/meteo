package com.ks.study.meteo.converter.json.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ks.study.meteo.base.Mapper;
import com.ks.study.meteo.converter.json.model.WeatherDataJson;
import com.ks.study.meteo.model.WeatherDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class WeatherDataMapToJsonMapper implements Mapper<MultiValueMap<String, WeatherDataDto>, String> {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final WeatherDataJsonMapper weatherDataJsonMapper;

    @Override
    public String map(MultiValueMap<String, WeatherDataDto> source) {
        if (source == null || source.isEmpty()) {
            return "";
        }
        MultiValueMap<String, WeatherDataJson> weatherDataJsonMultiValueMap = new LinkedMultiValueMap<>();
        source.forEach((key, value) -> weatherDataJsonMultiValueMap.put(key, weatherDataJsonMapper.map(value)));
        return GSON.toJson(weatherDataJsonMultiValueMap);
    }
}
