package com.ks.study.meteo.service;

import com.ks.study.meteo.helper.TestUtils;
import com.ks.study.meteo.model.FileExtension;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class FileServiceTest {
    private final FileService fileService = new FileService();

    @Test
    void shouldSaveFile() throws IOException {
        //given
        var extension = FileExtension.TXT;
        String value = RandomStringUtils.randomAlphabetic(10);

        //when
        String result = fileService.saveStringFile(extension, TestUtils.TEST_PATH, value);

        //then
        Path fileName = Path.of(TestUtils.TEST_PATH + result);
        String expectedStringValue = Files.readString(fileName);
        assertThat(expectedStringValue).isEqualTo(value);
    }

    @Test
    void shouldGenerateFileName() {
        //given
        var extension = FileExtension.TXT;

        //when
        String result = fileService.generateFileName(extension);

        //then
        String[] extensionString = result.split("\\.");
        assertThat(extensionString).hasSize(3);
        assertThat(extensionString[2]).isEqualTo(extension.getValue());

    }
}
