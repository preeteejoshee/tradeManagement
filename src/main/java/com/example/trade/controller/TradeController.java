package com.example.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.trade.constants.Constants;
import com.example.trade.exception.TradeNotFoundException;
import com.example.trade.model.Trade;
import com.example.trade.service.TradeService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class TradeController {
	@Autowired
	private TradeService tradeService;

	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS, response = String.class),
			@ApiResponse(code = 403, message = Constants.FORBIDDEN),
			@ApiResponse(code = 422, message = Constants.NOT_FOUND),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	@PostMapping(value="/trade") public ResponseEntity<String>
	validateAndStoreTrade(@RequestBody Trade trade)
	{
		if(tradeService.isValid(trade)) {
			tradeService.persist(trade);
		}
		else{
			throw new TradeNotFoundException(trade.getTradeId()+"  Trade Id is not found");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}