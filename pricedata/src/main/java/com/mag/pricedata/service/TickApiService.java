package com.mag.pricedata.service;

import com.mag.pricedata.entity.Tick;
import com.mag.pricedata.repository.TickRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TickApiService {

    private static final Logger logger = LoggerFactory.getLogger(TickApiService.class);

    private final TickRepository tickRepository;
    private final TickShareService tickShareService;

    public TickApiService(TickRepository tickRepository, TickShareService tickShareService) {
        this.tickRepository = tickRepository;
        this.tickShareService = tickShareService;
    }

    public Flux<Tick> get() {
        Date timestamp = Date.from(Instant.now().minus(2, ChronoUnit.SECONDS));
        return tickRepository.findAllByTimestampAfterOrderByTimestampAsc(timestamp)
                .concatWith(tickShareService.subscribe());
    }
}
