package com.example.trade.service;

import org.springframework.stereotype.Service;

import com.example.trade.model.Trade;

@Service
public interface TradeService {
	 public boolean isValid(Trade trade);

	public void persist(Trade trade);
	
	public void updateTradeExpiryFlag() ;
}
