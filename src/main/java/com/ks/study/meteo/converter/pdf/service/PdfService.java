package com.ks.study.meteo.converter.pdf.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.ks.study.meteo.converter.pdf.mapper.PdfStringLineMapper;
import com.ks.study.meteo.converter.pdf.properties.PdfProperties;
import com.ks.study.meteo.model.FileExtension;
import com.ks.study.meteo.model.WeatherDataDto;
import com.ks.study.meteo.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {
    private static final String NEW_LINE = "\n";
    private final FileService fileService;
    private final PdfProperties pdfProperties;
    private final PdfStringLineMapper pdfStringLineMapper;

    public void printToPdf(MultiValueMap<String, WeatherDataDto> weatherDataDtoMultiValueMap) throws FileNotFoundException, DocumentException {
        Document pdfDoc = createPdfDocument();
        Font mainFont = createFont(16);
        Font weatherDataFont = createFont(12);
        for (String value : weatherDataDtoMultiValueMap.keySet()) {
            addParagraph(value, mainFont, pdfDoc);
            List<WeatherDataDto> weatherData = weatherDataDtoMultiValueMap.get(value);
            for (WeatherDataDto data : weatherData) {
                addParagraph(pdfStringLineMapper.map(data), weatherDataFont, pdfDoc);
                addParagraph("--------------------------------", weatherDataFont, pdfDoc);
            }
            addParagraph("#################################", weatherDataFont, pdfDoc);
        }
        pdfDoc.close();
    }

    private void addParagraph(String text, Font font, Document pdfDoc) throws DocumentException {
        Paragraph para = new Paragraph(text, font);
        para.setAlignment(Element.ALIGN_LEFT);
        pdfDoc.add(para);
    }

    private Document createPdfDocument() throws FileNotFoundException, DocumentException {
        Document pdfDoc = new Document(PageSize.A4);
        PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdfProperties.getPath() + fileService.generateFileName(FileExtension.PDF)))
                .setPdfVersion(PdfWriter.PDF_VERSION_1_7);
        pdfDoc.open();
        pdfDoc.add(new Paragraph(NEW_LINE));

        return pdfDoc;
    }

    private static Font createFont(int size) {
        Font myfont = new Font();
        myfont.setStyle(Font.NORMAL);
        myfont.setSize(size);
        return myfont;
    }

}
