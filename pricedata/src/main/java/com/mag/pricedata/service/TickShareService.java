package com.mag.pricedata.service;

import com.mag.pricedata.entity.Tick;
import com.mag.pricedata.event.TickEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class TickShareService {

    private final Queue<Tick> ticks = new LinkedList<>();
    private final Logger logger = LoggerFactory.getLogger(TickShareService.class);
    private final Flux<Tick> tickFlux;

    public TickShareService() {
        tickFlux = Flux.defer(this::shareLastTicks)
                .delaySequence(Duration.ofSeconds(1))
                .repeat()
                .share();
    }

    @Async
    @EventListener
    public void tickEventListener(final TickEvent tickEvent) {
        logger.info("new tick received {}", tickEvent.getTick());
        synchronized (ticks) {
            ticks.add(tickEvent.getTick());
        }
    }

    Flux<Tick> subscribe() {
        return tickFlux;
    }

    private Flux<Tick> shareLastTicks() {
        ArrayList<Tick> tickList = new ArrayList<>();

        synchronized (ticks) {
            if (!ticks.isEmpty()) {
                tickList.add(ticks.poll());
            }
        }
        logger.info("Sharing {} ticks", tickList.size());
        return Flux.fromIterable(tickList);
    }
}
