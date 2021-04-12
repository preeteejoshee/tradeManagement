package com.example.trade.service;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.trade.model.Trade;
import com.example.trade.repository.TradeRepository;

public class TradeServiceImpl implements TradeService {
	 private static final Logger log = LoggerFactory.getLogger(TradeServiceImpl.class);

	@Autowired
	TradeRepository tradeRepository;

	@Override
	public boolean isValid(Trade trade) {
		// TODO Auto-generated method stub
		if(maturityDateValidation(trade)) {
			Optional<Trade> exsitingTrade = tradeRepository.findById(trade.getTradeId());
			if (exsitingTrade.isPresent()) {
				return versionValidation(trade, exsitingTrade.get());
			}else{
				return true;
			}
		}
		return false;
	}

	private boolean versionValidation(Trade trade, Trade oldTrade) {
		// TODO Auto-generated method stub
		
		// If a lower version is received, it will be rejected by the store
		if(trade.getVersion() >= oldTrade.getVersion()){
			return true;
		}
		return false;
	}
	
	//Store should not allow the trade which has less maturity date then today date.
	private boolean maturityDateValidation(Trade trade){
		return trade.getMaturityDate().isBefore(LocalDate.now())  ? false:true;
	}

	@Override
	public void persist(Trade trade) {
		// TODO Auto-generated method stub
		 tradeRepository.save(trade);		
	}
	
	public void updateTradeExpiryFlag() {
		tradeRepository.findAll().stream().forEach(t->{
			if(!maturityDateValidation(t)) {
				t.setExpiredFlag("Y");
				log.info("Trade updated ", t);
                tradeRepository.save(t);
			}
		});
	}


}
