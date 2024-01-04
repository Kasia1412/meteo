package com.ks.study.meteo.helper;

import com.ks.study.meteo.city.model.CityGeographicData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CityGeographicDataHelper {

    public static CityGeographicData.CityGeographicDataBuilder createSampleCityGeographicDataBuilder() {
        return CityGeographicData.builder()
                .lat(BigDecimal.ONE)
                .lon(BigDecimal.TEN);
    }
}
