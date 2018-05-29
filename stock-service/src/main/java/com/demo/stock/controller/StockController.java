package com.demo.stock.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import yahoofinance.YahooFinance;

public class StockController {

	@GetMapping("/{usrname}")
	public List<String> getStock(@PathVariable("usrname") final String usrName) {
		
		try {
			YahooFinance.get("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
