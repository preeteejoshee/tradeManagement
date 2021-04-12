package com.example.trade.exception;

public class TradeNotFoundException extends RuntimeException{
	
	private String id;

	public String getId() {
		return id;
	}

	public TradeNotFoundException(String id) {
		super("Trade id not found " +id );
		this.id=id;
	}
	

}
