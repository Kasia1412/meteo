package com.ks.study.meteo.external.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebClientHelper {

    public static ReactorClientHttpConnector getConnectorWithTimeouts(RestTimeoutProperties restTimeoutProperties) {
        return new ReactorClientHttpConnector(HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, restTimeoutProperties.getConnectTimeout())
                .doOnConnected(conn -> conn
                        .addHandlerFirst(new ReadTimeoutHandler(restTimeoutProperties.getReadTimeout(), TimeUnit.MILLISECONDS))
                        .addHandlerFirst(
                                new WriteTimeoutHandler(restTimeoutProperties.getWriteTimeout(), TimeUnit.MILLISECONDS))));
    }
}
