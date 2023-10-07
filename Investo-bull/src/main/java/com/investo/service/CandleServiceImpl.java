package com.investo.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.investo.exception.CandleException;
import com.investo.exception.CandleIOException;
import com.investo.exception.InvalidInputException;
import com.investo.model.Candle;
import com.investo.repository.CandleRepository;

@Service
public class CandleServiceImpl implements CandleService {

	@Autowired
	private CandleRepository candleRepository;

	@Override
	public String saveDataIntoInMemory() throws CandleException, CandleIOException {

		ClassPathResource resource = new ClassPathResource("data.json");

		ObjectMapper mapper = new ObjectMapper();

		mapper.registerModule(new JavaTimeModule());

		// Candle candle = mapper.readValue(new File("src/main/resources/candles.json"),Candle.class);
		System.out.println("before request for data");

		try {
			JsonNode rootNode = mapper.readTree(resource.getInputStream());

			if (rootNode.has("candles") && rootNode.get("candles").isArray()) {
				List<Candle> candles = mapper.readValue(rootNode.get("candles").toString(),
						new TypeReference<ArrayList<Candle>>() {
						});

				Collections.sort(candles, Comparator.comparing(Candle::getLastTradeTime));

				candleRepository.saveAll(candles);

				System.out.println("after request for data");
			} else {
				throw new CandleIOException("The 'candles' property is missing or not an array in the JSON file.");
			}

			return "Candles stored in the database";

		} catch (IOException ex) {
			throw new CandleIOException("Error while reading the candle.json file " + ex);
		}
	}
	
	
	
	@Override
	public Candle retriveCandleByCandleId(Integer candleId) throws CandleException {

		Optional<Candle> candleOpt = candleRepository.findById(candleId);

		if (!candleOpt.isPresent()) {
			throw new CandleException("Candle does not exit with this candle Id " + candleId);
		}

		return candleOpt.get();
	}
	
	
	
	@Override
	public List<Candle> getFirstAndLastCandlesOfTheDay() throws CandleException {
		
		List<Candle> candles = candleRepository.getFirstAndLastCandlesOfTheDay();
		
		if(candles.isEmpty()) {
			throw new CandleException("Candle does not exit in Record");
		}
		
		return candles;
	}

	
	
	@Override
	public String getFirstORBCandle(Integer time) throws InvalidInputException {

		if (time % 5 != 0) {
			throw new InvalidInputException("Invalid time interval");
		}
		
		int numberOfCandles = time / 5;

		List<Candle> candleList = candleRepository.findAll();

		double highestHigh = Double.MIN_VALUE;
		double lowestLow = Double.MAX_VALUE;

		for (int i = 0; i < numberOfCandles; i++) {
			highestHigh = Math.max(highestHigh, candleList.get(i).getHigh());
			lowestLow = Math.min(lowestLow, candleList.get(i).getLow());
		}

		String msg = "Not found any ORB candle";
		
		for (int i = numberOfCandles; i < candleList.size(); i++) {
			Candle candle = candleList.get(i);
			if (candle.getClose() > highestHigh || candle.getClose() < lowestLow) {
				msg = "ORB candle generated at " + candle.getLastTradeTime().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"))+" .";
				break;
			}
		}
		System.out.println(msg);
		return msg;
	}

	
	
	
	@Override
	public List<Candle> getCombinedCandle(Integer time) throws InvalidInputException {

		if (time % 5 != 0) {
			throw new InvalidInputException("Invalid time interval");
		}

		Integer numberOfCandle = time / 5;

		double interval = Math.ceil(75 / (double) numberOfCandle);

		List<Candle> combinedCandles = new ArrayList<>();

		for (int i = 0; i < interval; i++) {

			Pageable pageable = PageRequest.of(i, numberOfCandle);

			Page<Candle> page = candleRepository.findAll(pageable);

			List<Candle> list = page.getContent();

			double open = list.get(0).getOpen(); 
			double close = list.get(list.size() - 1).getClose(); 
			LocalDateTime lastTime = list.get(0).getLastTradeTime(); 
																		
			Long totalTrad = list.stream().mapToLong(Candle::getTradedQty).sum(); 
			Double highestHigh = list.stream().mapToDouble(Candle::getHigh).max().getAsDouble(); 
																									
			Double lowestLow = list.stream().mapToDouble(Candle::getLow).min().getAsDouble(); 
																							
			Candle candle = new Candle();
			candle.setCandleId(i);
			candle.setLow(lowestLow);
			candle.setTradedQty(totalTrad);
			candle.setClose(close);
			candle.setLastTradeTime(lastTime);
			candle.setHigh(highestHigh);
			candle.setQuotationLot(1);
			candle.setOpen(open);
			candle.setOpenInterest(0);

			
			combinedCandles.add(candle);

		}

		return combinedCandles;
	}


}
