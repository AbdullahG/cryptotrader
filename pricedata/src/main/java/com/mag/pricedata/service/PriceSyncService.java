package com.mag.pricedata.service;

import com.mag.pricedata.entity.Tick;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Optional;

@Service
public class PriceSyncService {

    private static Logger logger = LoggerFactory.getLogger(PriceSyncService.class);

    private final SyncConfigurationService configurationService;
    private final TickService tickService;

    public PriceSyncService(SyncConfigurationService configurationService, TickService tickService) {
        this.configurationService = configurationService;
        this.tickService = tickService;
    }

    /**
     * we can retrieve the next tick
     * even if the previous one is still in progress
     * because ticks are coming with their own timestamps
     */
    @Scheduled(fixedRate = 900L)
    public void sync() {
        configurationService.getExchanges().stream()
                .parallel()
                .forEach(this::retrieveAndSaveTick);
    }

    private void retrieveAndSaveTick(Exchange exchange) {
        retrieveTick(exchange)
                .ifPresent(ticker -> this.saveTick(exchange, ticker).subscribe(tick -> logger.info("Saved tick: {}", tick)));
    }

    private Optional<Ticker> retrieveTick(Exchange exchange) {
        MarketDataService marketDataService = exchange.getMarketDataService();
        try {
            Ticker ticker = marketDataService.getTicker(CurrencyPair.XRP_USDT);
            return Optional.of(ticker);
        } catch (IOException e) {
            logger.error("Error while retrieving tick from exchange {}", exchange.getDefaultExchangeSpecification().getExchangeName(), e);
            return Optional.empty();
        }
    }

    private Mono<Tick> saveTick(Exchange exchange, Ticker ticker) {
        return tickService.save(
                exchange.getDefaultExchangeSpecification().getExchangeName(),
                ticker
        );
    }
}
