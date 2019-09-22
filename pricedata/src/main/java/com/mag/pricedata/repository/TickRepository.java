package com.mag.pricedata.repository;

import com.mag.pricedata.entity.Tick;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TickRepository extends MongoRepository<Tick, String> {
}
