package com.ks.study.meteo.external.openweather.exception;

public class OpenWeatherClientException extends RuntimeException {
    public OpenWeatherClientException(String message) {
        super(message);
    }
}
