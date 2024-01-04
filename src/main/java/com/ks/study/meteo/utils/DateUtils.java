package com.ks.study.meteo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getFormattedLocalDateTime(LocalDateTime localDateTime) {
        return DATE_TIME_FORMATTER.format(localDateTime);
    }

    public static String getCurrentDateTimeAsString() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
