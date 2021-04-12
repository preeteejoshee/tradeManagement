package com.example.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trade.model.Trade;

public interface TradeRepository extends JpaRepository<Trade,String> {

}
