package com.ks.study.meteo.external.openweather.config;

import com.ks.study.meteo.external.config.AbstractWebClient;
import com.ks.study.meteo.external.config.RestTimeoutProperties;
import com.ks.study.meteo.external.openweather.exception.OpenWeatherClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.ks.study.meteo.log.LogMessages.REST_CLIENT_EXCEPTION_MESSAGE;
import static com.ks.study.meteo.log.LogMessages.REST_SERVER_EXCEPTION_MESSAGE;

@Slf4j
@Component
public class OpenWeatherWebClient extends AbstractWebClient {

    protected final OpenWeatherProperties openWeatherProperties;
    protected final WebClient openWeatherWebClient;


    public OpenWeatherWebClient(RestTimeoutProperties restTimeoutProperties, OpenWeatherProperties openWeatherProperties) {
        super(restTimeoutProperties);
        this.openWeatherProperties = openWeatherProperties;
        this.openWeatherWebClient = baseApiWebClient();
    }

    @Override
    protected String getUrl() {
        return openWeatherProperties.getUrl();
    }

    protected Mono<OpenWeatherClientException> handle4xxError(ClientResponse response) {
        log.error(String.format(REST_CLIENT_EXCEPTION_MESSAGE, response.statusCode().value()));
        return response.bodyToMono(String.class).map(OpenWeatherClientException::new);
    }

    protected Mono<OpenWeatherClientException> handle5xxError(ClientResponse response) {
        log.error(String.format(REST_SERVER_EXCEPTION_MESSAGE, response.statusCode().value()));

        return response.bodyToMono(String.class).map(OpenWeatherClientException::new);
    }
}
