package com.ks.study.meteo.city.service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.ks.study.meteo.city.model.CityGeographicData;
import com.ks.study.meteo.city.properties.CityReaderProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CityReaderService {

    private final CityReaderProperties cityReaderProperties;

    public Map<String, CityGeographicData> readDataFromCityFile() throws IOException {
        var cityInputStream = new FileInputStream(cityReaderProperties.getCityFilePath());
        var jsonData = IOUtils.toString(cityInputStream, Charset.defaultCharset());
        var mapType = new TypeToken<Map<String, CityGeographicData>>() {
        }.getType();

        return new Gson().fromJson(jsonData, mapType);
    }
}
