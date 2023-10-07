package com.investo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.investo.model.Candle;

@Repository
public interface CandleRepository  extends JpaRepository<Candle,Integer>, PagingAndSortingRepository<Candle, Integer>{

}
