package com.ks.study.meteo.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestUtils {
    public static final String JSON_GENERATED_PATH = "src/test/resources/generated/json/";
    public static final String PDF_GENERATED_PATH = "src/test/resources/generated/pdf/";
    public static final String XML_GENERATED_PATH = "src/test/resources/generated/xml/";
    public static final String TEST_PATH = "src/test/resources/generated/test/";

    public static BigDecimal createRandomBigDecimal() {
        BigDecimal max = new BigDecimal(RandomStringUtils.randomNumeric(5) + ".0");
        BigDecimal randFromDouble = BigDecimal.valueOf(Math.random());
        return randFromDouble.divide(max, RoundingMode.DOWN);
    }
}
