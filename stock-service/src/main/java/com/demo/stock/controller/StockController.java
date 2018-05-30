package com.demo.stock.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RequestMapping("/rest/stock")
@RestController
public class StockController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/{usrname}")
	public List<Stock> getStock(@PathVariable("usrname") final String usrName) {
		
		List<Stock> stockList = new ArrayList<Stock>();
		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/"+usrName, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
		List<String> quotes = quoteResponse.getBody();
		for(String quote : quotes) {
			stockList.add(getStockPrice(quote));
		}
		return stockList;
	}
	
	private Stock getStockPrice(String quote) {
		
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
