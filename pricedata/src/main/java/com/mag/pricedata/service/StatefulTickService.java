package com.mag.pricedata.service;

import com.mag.pricedata.entity.Tick;
import com.mag.pricedata.repository.TickRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StatefulTickService {

    private static final Logger logger = LoggerFactory.getLogger(StatefulTickService.class);

    private final TickRepository tickRepository;

    public StatefulTickService(TickRepository tickRepository) {
        this.tickRepository = tickRepository;
    }

    public Flux<Tick> get() {
        Date timestamp = Date.from(Instant.now().minus(2, ChronoUnit.SECONDS));
        Set<String> ids = Collections.newSetFromMap(new ConcurrentHashMap<>());
        return getAndLog(tickRepository.findAllByTimestampAfterOrderByTimestampAsc(timestamp))
                .doOnEach(t -> {
                    if (t.get() != null) {
                        ids.add(t.get().getId());
                    }
                })
                .concatWith(
                        Flux.defer(() -> getAndLog(tickRepository.findTop5ByOrderByTimestampDesc())
                                .filter(t -> ids.add(t.getId()))
                        ).repeat().delaySequence(Duration.ofSeconds(1)).doFinally(e -> ids.clear())
                );
    }

    Flux<Tick> getAndLog(Flux<Tick> flux) {
        return flux
                .doOnEach(f -> {
                    if (f.get() != null) {
                        logger.info(f.get().getId());
                    }
                });
    }
}
