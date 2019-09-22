package com.mag.pricedata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Tick {
    @Id
    private String id;

    @Column(nullable = false, updatable = false)
    private String exchange;

    @Column(nullable = false, updatable = false)
    private String base;
    @Column(nullable = false, updatable = false)
    private String counter;
    private BigDecimal open;
    @Column(nullable = false, updatable = false)
    private BigDecimal last;
    private BigDecimal bid;
    @Column(nullable = false, updatable = false)
    private BigDecimal ask;
    private BigDecimal high;
    @Column(nullable = false, updatable = false)
    private BigDecimal low;
    private BigDecimal vwap;
    private BigDecimal volume;
    private BigDecimal quoteVolume;
    @Column(nullable = false, updatable = false)
    private Date timestamp;
    private BigDecimal bidSize;
    private BigDecimal askSize;

    public Tick() {
    }

    public Tick(String exchange, String base, String counter, BigDecimal open, BigDecimal last, BigDecimal bid, BigDecimal ask, BigDecimal high, BigDecimal low, BigDecimal vwap, BigDecimal volume, BigDecimal quoteVolume, Date timestamp, BigDecimal bidSize, BigDecimal askSize) {
        this.exchange = exchange;
        this.base = base;
        this.counter = counter;
        this.open = open;
        this.last = last;
        this.bid = bid;
        this.ask = ask;
        this.high = high;
        this.low = low;
        this.vwap = vwap;
        this.volume = volume;
        this.quoteVolume = quoteVolume;
        this.timestamp = timestamp;
        this.bidSize = bidSize;
        this.askSize = askSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getVwap() {
        return vwap;
    }

    public void setVwap(BigDecimal vwap) {
        this.vwap = vwap;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(BigDecimal quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getBidSize() {
        return bidSize;
    }

    public void setBidSize(BigDecimal bidSize) {
        this.bidSize = bidSize;
    }

    public BigDecimal getAskSize() {
        return askSize;
    }

    public void setAskSize(BigDecimal askSize) {
        this.askSize = askSize;
    }

    @Override
    public String toString() {
        return "Tick{" +
                "id='" + id + '\'' +
                ", exchange='" + exchange + '\'' +
                ", base='" + base + '\'' +
                ", counter='" + counter + '\'' +
                ", open=" + open +
                ", last=" + last +
                ", bid=" + bid +
                ", ask=" + ask +
                ", high=" + high +
                ", low=" + low +
                ", vwap=" + vwap +
                ", volume=" + volume +
                ", quoteVolume=" + quoteVolume +
                ", timestamp=" + timestamp +
                ", bidSize=" + bidSize +
                ", askSize=" + askSize +
                '}';
    }
}
