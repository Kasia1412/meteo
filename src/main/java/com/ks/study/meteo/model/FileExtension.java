package com.ks.study.meteo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileExtension {
    XML("xml"), PDF("pdf"), JSON("json"), TXT("txt");

    private final String value;
}
