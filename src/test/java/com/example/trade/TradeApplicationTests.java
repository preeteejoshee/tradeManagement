package com.example.trade;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.trade.controller.TradeController;
import com.example.trade.exception.TradeNotFoundException;
import com.example.trade.model.Trade;

@SpringBootTest
class TradeApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private TradeController tradeController;

	@Test
	void testTradeValidateAndStore_success() {
		ResponseEntity responseEntity = tradeController.validateAndStoreTrade(createTrade("T1",1,LocalDate.now()));
		Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(),responseEntity);
	}
	@Test
	void testTradeValidateAndStoreWhenMaturityDatePast() {
		try {
			LocalDate localDate = getLocalDate(2015, 05, 21);
			ResponseEntity responseEntity = tradeController.validateAndStoreTrade(createTrade("T2", 1, localDate));
		}catch (TradeNotFoundException ie) {
			Assertions.assertEquals("Invalid Trade: T2  Trade Id is not found", ie.getMessage());
		}
	}
	private Trade createTrade(String tradeId,int version,LocalDate  maturityDate){
		Trade trade = new Trade();
		trade.setTradeId(tradeId);
		trade.setBookId(tradeId+"B1");
		trade.setVersion(version);
		trade.setCounterParty(tradeId+"Cpty");
		trade.setMaturityDate(maturityDate);
		trade.setExpiredFlag("Y");
		return trade;
	}
	public static LocalDate getLocalDate(int year,int month, int day){
		LocalDate localDate = LocalDate.of(year,month,day);
		return localDate;
	}



}
