package com.investo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investo.exception.InvalidInputException;
import com.investo.model.Candle;
import com.investo.service.CandleService;

@RestController
@RequestMapping("/investo")
public class CandleContoller {

	@Autowired
	private CandleService candleService;
	
	
	@PostMapping("/save-candles")   // http://localhost:8088/investo/save-candles
	public ResponseEntity<String> saveDataIntoInMemoryHandler(){
		
		String str = candleService.saveDataIntoInMemory();
		
		return new ResponseEntity<String>(str,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/candle/{candleId}")  // http://localhost:8088/investo/candle/1
	public ResponseEntity<Candle> retriveCandleByCandleIdHandler(@PathVariable Integer candleId){
		
		Candle candle = candleService.retriveCandleByCandleId(candleId);
		
		return new ResponseEntity<Candle>(candle, HttpStatus.OK);
	}
	
	@GetMapping("/first-last-candle")  // http://localhost:8088/investo/first-last-candle
	public ResponseEntity<List<Candle>> getFirstAndLastCandlesOfTheDayHandler(){
	
		List<Candle> candles = candleService.getFirstAndLastCandlesOfTheDay();
		
		return new ResponseEntity<List<Candle>>(candles ,HttpStatus.OK); 
	}
	
	
	
	@GetMapping("/ORB/{mintues}")   // http://localhost:8088/investo/ORB/45
	public ResponseEntity<String> getFirstORBCandleHandler(@PathVariable("mintues") Integer time) {
	
		String str = candleService.getFirstORBCandle(time);
		
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/candles/{mintues}")   // http://localhost:8088/investo/candles/15
	public ResponseEntity<List<Candle>> getCombinedCandleHandler(@PathVariable("mintues") Integer time){
		
		List<Candle> candles = candleService.getCombinedCandle(time);
		
		return new ResponseEntity<List<Candle>>(candles,HttpStatus.CREATED);
	}
}
