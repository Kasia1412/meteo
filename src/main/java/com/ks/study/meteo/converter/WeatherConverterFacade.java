package com.ks.study.meteo.converter;

import com.ks.study.meteo.converter.json.service.JsonService;
import com.ks.study.meteo.converter.pdf.service.PdfService;
import com.ks.study.meteo.converter.text.service.TextService;
import com.ks.study.meteo.converter.xml.service.XmlService;
import com.ks.study.meteo.model.WeatherDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import static com.ks.study.meteo.log.LogMessages.JSON_GENERATION_EXCEPTION_MESSAGE;
import static com.ks.study.meteo.log.LogMessages.PDF_GENERATION_EXCEPTION_MESSAGE;
import static com.ks.study.meteo.log.LogMessages.PDF_GENERATION_SUCCESS_MESSAGE;
import static com.ks.study.meteo.log.LogMessages.XML_GENERATION_EXCEPTION_MESSAGE;

@Service
@RequiredArgsConstructor
public class WeatherConverterFacade {

    private final TextService textService;
    private final JsonService jsonService;
    private final PdfService pdfService;
    private final XmlService xmlService;

    public String getWeatherAsString(WeatherDataDto weatherDataDto) {
        return textService.getWeatherAsPlainTextString(weatherDataDto);
    }

    public String printToPdf(MultiValueMap<String, WeatherDataDto> weatherDataDtoMultiValueMap) {
        try {
            pdfService.printToPdf(weatherDataDtoMultiValueMap);
            return PDF_GENERATION_SUCCESS_MESSAGE;
        } catch (Exception e) {
            return String.format(PDF_GENERATION_EXCEPTION_MESSAGE, e.getMessage());
        }
    }

    public String printToJson(MultiValueMap<String, WeatherDataDto> weatherDataDtoMultiValueMap) {
        try {
            return jsonService.printAndSaveAsJson(weatherDataDtoMultiValueMap);
        } catch (Exception e) {
            return String.format(JSON_GENERATION_EXCEPTION_MESSAGE, e.getMessage());
        }
    }

    public String printToXml(MultiValueMap<String, WeatherDataDto> weatherDataDtoMultiValueMap) {
        try {
            return xmlService.printAndSaveAsXml(weatherDataDtoMultiValueMap);
        } catch (Exception e) {
            return String.format(XML_GENERATION_EXCEPTION_MESSAGE, e.getMessage());
        }
    }
}
