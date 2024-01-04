package com.ks.study.meteo.service;

import com.ks.study.meteo.model.FileExtension;
import com.ks.study.meteo.utils.DateUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
public class FileService {

    private static final String FILE_NAME_PATTERN = "Weather-%s.%s";

    public String saveStringFile(FileExtension fileExtension, String path, String value) throws IOException {
        File file = new File(path + generateFileName(fileExtension));
        FileUtils.writeStringToFile(file, value, StandardCharsets.UTF_8);
        return file.getName();
    }

    public String generateFileName(FileExtension fileExtension) {
        return String.format(FILE_NAME_PATTERN, DateUtils.getCurrentDateTimeAsString(), fileExtension.getValue());
    }
}
