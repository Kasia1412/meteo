package com.ks.study.meteo.converter.pdf.mapper;

import com.ks.study.meteo.base.Mapper;
import com.ks.study.meteo.model.WeatherDataDto;
import org.springframework.stereotype.Component;

import static com.ks.study.meteo.MainParameters.NEW_LINE;

@Component
public class PdfStringLineMapper implements Mapper<WeatherDataDto, String> {

    @Override
    public String map(WeatherDataDto source) {
        if (source == null) {
            return "";
        }
        return "Weather time: " + source.getWeatherTime() +
                NEW_LINE +
                "Pressure: " + source.getPressure() +
                NEW_LINE +
                "Temperature: " + source.getTemp() +
                NEW_LINE +
                "Humidity: " + source.getHumidity() +
                NEW_LINE +
                "Visibility: " + source.getVisibility();
    }
}
