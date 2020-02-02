package com.mag.trader.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(name = "price-data-service")
//@Headers({ "Accept: application/json" })
public interface PriceDataClient {

    @GetMapping("/test")
    Flux<Object> get();
}
