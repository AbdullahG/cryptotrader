package com.mag.pricedata.api;

import com.mag.pricedata.entity.Tick;
import com.mag.pricedata.repository.TickRepository;
import com.mag.pricedata.service.TickApiService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController(value = "/api/tick")
public class TickApi {
    private final TickRepository tickRepository;
    private ObjectFactory<TickApiService> statefulTickServiceFactory;

    public TickApi(TickRepository tickRepository, ObjectFactory<TickApiService> statefulTickServiceFactory) {
        this.tickRepository = tickRepository;
        this.statefulTickServiceFactory = statefulTickServiceFactory;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Flux<Tick> get() {
        Date timestamp = Date.from(Instant.now().minus(2, ChronoUnit.SECONDS));
        return tickRepository.findAllByTimestampAfterOrderByTimestampAsc(timestamp);
    }

    @GetMapping(path = "test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Flux<Tick> getTest() {
        return statefulTickServiceFactory.getObject().get();
    }
}
