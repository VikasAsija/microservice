package com.demo.model;

public class Stock {
	
	private String quote;
	private Integer price;
	
	public Stock(String quote, Integer price) {
		this.quote = quote;
		this.price = price;
	}
	
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
