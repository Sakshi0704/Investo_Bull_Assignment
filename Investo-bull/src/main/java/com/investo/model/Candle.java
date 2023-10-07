package com.investo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Candle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer candleId;

	@JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
	@JsonProperty("LastTradeTime")
	private LocalDateTime lastTradeTime;
	
	@JsonProperty("QuotationLot")
	private Integer quotationLot;
	
	@JsonProperty("TradedQty")
	private Long tradedQty;
	
	@JsonProperty("OpenInterest")
	private Integer openInterest;
	
	@JsonProperty("Open")
	private Double open;
	
	@JsonProperty("High")
	private Double high;
	
	@JsonProperty("Low")
	private Double low;
	
	@JsonProperty("Close")
	private Double close;

	public Candle() {
		super();
	}
	
	public Candle(Integer candleId, LocalDateTime lastTradeTime, Integer quotationLot, Long tradedQty, Integer openInterest,
			Double open, Double high, Double low, Double close) {
		super();
		this.candleId = candleId;
		this.lastTradeTime = lastTradeTime;
		this.quotationLot = quotationLot;
		this.tradedQty = tradedQty;
		this.openInterest = openInterest;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
	}


	public Integer getCandleId() {
		return candleId;
	}

	public void setCandleId(Integer candleId) {
		this.candleId = candleId;
	}

	public LocalDateTime getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(LocalDateTime lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	public Integer getQuotationLot() {
		return quotationLot;
	}

	public void setQuotationLot(Integer quotationLot) {
		this.quotationLot = quotationLot;
	}

	public Long getTradedQty() {
		return tradedQty;
	}

	public void setTradedQty(Long tradedQty) {
		this.tradedQty = tradedQty;
	}

	public Integer getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(Integer openInterest) {
		this.openInterest = openInterest;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	
}
