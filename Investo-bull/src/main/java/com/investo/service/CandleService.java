package com.investo.service;

import java.util.List;

import com.investo.exception.CandleException;
import com.investo.exception.CandleIOException;
import com.investo.exception.InvalidInputException;
import com.investo.model.Candle;

public interface CandleService {

	/**
	 * Saves data into an in-memory store.
	 *
	 * @return A message indicating the success or failure of the operation.
	 * @throws CandleException If there's an issue related to candle data.
	 * @throws CandleIOException If there's an I/O error during the operation.
	 */
	public String saveDataIntoInMemory() throws CandleException, CandleIOException;
	
	
	/**
	 * Retrieves a candle by its unique candle ID.
	 *
	 * @param candleId The unique identifier for the candle.
	 * @return The retrieved Candle object.
	 * @throws CandleException If there's an issue related to candle data.
	 */
	public Candle retriveCandleByCandleId(Integer candleId) throws CandleException;
	
	
	/**
	 * Retrieves the first Opening Range Breakout (ORB) candle within a specified time frame.
	 *
	 * @param time The time frame for which to find the first ORB candle.
	 * @return A message indicating the first ORB candle's details.
	 * @throws InvalidInputException If the input time is invalid or out of range.
	 */
	public String getFirstORBCandle(Integer time) throws InvalidInputException;
	
	
	/**
	 * Retrieves a list of combined candles based on a specified time frame.
	 *
	 * @param time The time frame for which to combine candles.
	 * @return A list of combined Candle objects.
	 * @throws InvalidInputException If the input time is invalid or out of range.
	 */
	public List<Candle> getCombinedCandle(Integer time) throws InvalidInputException;
	
	
}
