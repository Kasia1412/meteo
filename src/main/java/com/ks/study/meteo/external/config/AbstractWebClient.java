package com.ks.study.meteo.external.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractWebClient {

    private final RestTimeoutProperties restTimeoutProperties;

    protected abstract String getUrl();

    protected WebClient baseApiWebClient() {
        return WebClient.builder()
                .baseUrl(getUrl()).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(logRequest())
                .clientConnector(WebClientHelper.getConnectorWithTimeouts(restTimeoutProperties))
                .build();
    }

    ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {

            StringBuilder sb = new StringBuilder("Request: \n");
            //append clientRequest method and url
            log.info(clientRequest.url().toString());
            clientRequest
                    .headers()
                    .forEach((name, values) -> values.forEach(log::info));
            log.debug(sb.toString());

            return Mono.just(clientRequest);
        });
    }
}
