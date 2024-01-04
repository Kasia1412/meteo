package com.ks.study.meteo.converter.xml.mapper;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.ks.study.meteo.base.Mapper;
import com.ks.study.meteo.converter.xml.model.WeatherDataXml;
import com.ks.study.meteo.model.WeatherDataDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class WeatherDataMapToXmlMapper implements Mapper<MultiValueMap<String, WeatherDataDto>, String> {
    private static final String ROOT_NAME_VALUE = "weatherData";
    private final WeatherDataXmlMapper weatherDataXmlMapper;
    private final XmlMapper xmlMapper;


    public WeatherDataMapToXmlMapper(WeatherDataXmlMapper weatherDataXmlMapper) {
        this.weatherDataXmlMapper = weatherDataXmlMapper;
        xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    }

    @Override
    public String map(MultiValueMap<String, WeatherDataDto> source) {
        if (CollectionUtils.isEmpty(source)) {
            return "";
        }
        MultiValueMap<String, WeatherDataXml> weatherData = new LinkedMultiValueMap<>();
        source.forEach((key, value) -> weatherData.put(key, weatherDataXmlMapper.map(value)));
        try {
            return xmlMapper.writerWithDefaultPrettyPrinter()
                    .withRootName(ROOT_NAME_VALUE)
                    .writeValueAsString(weatherData);
        } catch (Exception e) {
            return null;
        }
    }
}
