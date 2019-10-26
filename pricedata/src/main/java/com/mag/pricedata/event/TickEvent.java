package com.mag.pricedata.event;

import com.mag.pricedata.entity.Tick;
import org.springframework.context.ApplicationEvent;

public class TickEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param tick the object on which the event initially occurred (never {@code null})
     */
    public TickEvent(Tick tick) {
        super(tick);
    }

    public Tick getTick() {
        return (Tick) source;
    }
}
