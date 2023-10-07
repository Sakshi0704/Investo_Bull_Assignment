package com.investo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.investo.model.Candle;

@Repository
public interface CandleRepository  extends JpaRepository<Candle,Integer>, PagingAndSortingRepository<Candle, Integer>{
	
	 @Query("SELECT c FROM Candle c WHERE c.lastTradeTime = ("
	 		+ "SELECT MIN(c1.lastTradeTime) FROM Candle c1) "
	 		+ "OR c.lastTradeTime = ("
	 		+ "SELECT MAX(c2.lastTradeTime) FROM Candle c2)")
	    List<Candle> getFirstAndLastCandlesOfTheDay();
	
}
