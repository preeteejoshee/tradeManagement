package com.example.trade.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.trade.service.TradeService;

public class TradeScheduler {
	
	private static final Logger log = LoggerFactory.getLogger(TradeScheduler.class);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	TradeService tradeService;
	
	@Scheduled(cron = "${schedule.trade.expiry}")// setup for 45 min as of now
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
		tradeService.updateTradeExpiryFlag();
	}


}
