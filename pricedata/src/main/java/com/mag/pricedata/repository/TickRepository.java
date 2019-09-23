package com.mag.pricedata.repository;

import com.mag.pricedata.entity.Tick;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TickRepository extends ReactiveMongoRepository<Tick, String> {
}
