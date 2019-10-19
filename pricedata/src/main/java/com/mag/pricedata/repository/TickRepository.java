package com.mag.pricedata.repository;

import com.mag.pricedata.entity.Tick;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface TickRepository extends ReactiveMongoRepository<Tick, String> {

    Flux<Tick> findAllByTimestampAfterOrderByTimestampAsc(Date timestamp);
    Flux<Tick> findTop5ByOrderByTimestampDesc();
}
