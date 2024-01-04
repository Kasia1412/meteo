package com.ks.study.meteo.converter.json.service;

import com.ks.study.meteo.converter.json.mapper.WeatherDataMapToJsonMapper;
import com.ks.study.meteo.converter.json.properties.JsonProperties;
import com.ks.study.meteo.model.FileExtension;
import com.ks.study.meteo.model.WeatherDataDto;
import com.ks.study.meteo.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JsonService {

    private final WeatherDataMapToJsonMapper weatherDataMapToJsonMapper;
    private final FileService fileService;
    private final JsonProperties jsonProperties;

    public String printAndSaveAsJson(MultiValueMap<String, WeatherDataDto> weatherDataDtoMultiValueMap) throws IOException {
        String formatted = weatherDataMapToJsonMapper.map(weatherDataDtoMultiValueMap);
        fileService.saveStringFile(FileExtension.JSON, jsonProperties.getPath(), formatted);
        return formatted;
    }


}
