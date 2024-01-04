package com.ks.study.meteo.converter.pdf.service;

import com.itextpdf.text.pdf.PdfWriter;
import com.ks.study.meteo.converter.pdf.mapper.PdfStringLineMapper;
import com.ks.study.meteo.converter.pdf.properties.PdfProperties;
import com.ks.study.meteo.helper.TestUtils;
import com.ks.study.meteo.helper.WeatherDataDtoHelper;
import com.ks.study.meteo.model.FileExtension;
import com.ks.study.meteo.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class PdfServiceTest {

    @Mock
    private FileService fileService;
    private final PdfProperties pdfProperties = new PdfProperties();
    private final PdfStringLineMapper pdfStringLineMapper = new PdfStringLineMapper();

    private PdfService pdfService;

    @BeforeEach
    void setup() {
        pdfProperties.setPath(TestUtils.PDF_GENERATED_PATH);
        pdfService = new PdfService(fileService, pdfProperties, pdfStringLineMapper);
    }

    @Test
    void shouldPrintToPdf() {
        //given
        var city1 = "Krakow";
        var city2 = "Warszawa";
        var map = WeatherDataDtoHelper.createSampleWeatherDataDtoMultiValueMap(List.of(city1, city2));

        var filename = "name.pdf";
        Mockito.when(fileService.generateFileName(FileExtension.PDF)).thenReturn(filename);
        PdfWriter pdfWriter = mock();
        try (MockedStatic<PdfWriter> writer = Mockito.mockStatic(PdfWriter.class)) {
            writer.when(() -> PdfWriter.getInstance(any(), any())).thenReturn(pdfWriter);
        }

        //when
        assertThatCode(() -> pdfService.printToPdf(map))
                .doesNotThrowAnyException();
    }

}
