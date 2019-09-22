package com.mag.pricedata.service;

import com.google.common.collect.Sets;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.binance.BinanceExchange;
import org.knowm.xchange.kucoin.KucoinExchange;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SyncConfigurationService {

    @Cacheable("exchanges")
    public Set<Exchange> getExchanges() {
        return Sets.newHashSet(
                ExchangeFactory.INSTANCE.createExchange(KucoinExchange.class),
                ExchangeFactory.INSTANCE.createExchange(BinanceExchange.class)
        );
    }
}
