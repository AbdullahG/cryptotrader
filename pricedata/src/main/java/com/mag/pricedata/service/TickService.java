package com.mag.pricedata.service;

import com.mag.pricedata.entity.Tick;
import com.mag.pricedata.repository.TickRepository;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class TickService {

    private final TickRepository tickRepository;

    public TickService(TickRepository tickRepository) {
        this.tickRepository = tickRepository;
    }

    Mono<Tick> save(final String exchange, Ticker ticker) {
        Tick tick = new Tick(
                exchange,
                ticker.getCurrencyPair().base.getCurrencyCode(),
                ticker.getCurrencyPair().counter.getCurrencyCode(),
                ticker.getOpen(),
                ticker.getLast(),
                ticker.getBid(),
                ticker.getAsk(),
                ticker.getHigh(),
                ticker.getLow(),
                ticker.getVwap(),
                ticker.getVolume(),
                ticker.getQuoteVolume(),
                nowIfEmpty(ticker.getTimestamp()),
                ticker.getBidSize(),
                ticker.getAskSize()
        );

        return tickRepository.save(tick);
    }

    private Date nowIfEmpty(Date date) {
        if (date == null) {
            return new Date();
        } else {
            return date;
        }
    }
}
