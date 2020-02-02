package com.mag.trader.api;

import com.mag.trader.client.PriceDataClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TickApi {

    private final PriceDataClient client;

    public TickApi(PriceDataClient client) {
        this.client = client;
    }

    @GetMapping("/abc")
    public Flux<Object> get() {
        return client.get();
    }
}
