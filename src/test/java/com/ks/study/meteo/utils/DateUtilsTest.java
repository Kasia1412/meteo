package com.ks.study.meteo.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DateUtilsTest {

    @Test
    void shouldGetFormattedLocalDateTime() {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2010, 1, 1, 12, 0, 0, 0);

        //when
        String result = DateUtils.getFormattedLocalDateTime(localDateTime);

        //then
        String expectedResult = "2010-01-01 12:00:00";
        assertThat(result).isEqualTo(expectedResult);
    }
}
