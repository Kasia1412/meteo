package com.ks.study.meteo.converter.xml.service;

import com.ks.study.meteo.converter.xml.mapper.WeatherDataMapToXmlMapper;
import com.ks.study.meteo.converter.xml.properties.XmlProperties;
import com.ks.study.meteo.model.FileExtension;
import com.ks.study.meteo.model.WeatherDataDto;
import com.ks.study.meteo.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class XmlService {
    private final XmlProperties xmlProperties;
    private final FileService fileService;
    private final WeatherDataMapToXmlMapper weatherDataMapToXmlMapper;

    public String printAndSaveAsXml(MultiValueMap<String, WeatherDataDto> weatherDataDtoMultiValueMap) throws IOException {
        String formatted = weatherDataMapToXmlMapper.map(weatherDataDtoMultiValueMap);
        fileService.saveStringFile(FileExtension.XML, xmlProperties.getPath(), formatted);
        return formatted;
    }
}
