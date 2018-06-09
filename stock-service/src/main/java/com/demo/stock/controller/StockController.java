package com.demo.stock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.model.Stock;


@RequestMapping("/rest/stock")
@RestController
public class StockController {
	
	@Autowired
	RestTemplate restTemplate;
	
	static Map<String, Integer> stockPrice = new HashMap<String, Integer>();
	
	static{
		
		stockPrice.put("GOOG", 100);
		stockPrice.put("APPL", 90);
		stockPrice.put("AMZN", 80);
	}
	
	@GetMapping("/{usrname}")
	public List<Stock> getStock(@PathVariable("usrname") final String usrName) {
		
		List<Stock> stockList = new ArrayList<Stock>();
		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/"+usrName, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
		List<String> quotes = quoteResponse.getBody();
		for(String quote : quotes) {
			Stock stock = getStockPrice(quote);
			if(stock != null)
				stockList.add(stock);
		}
		return stockList;
	}
	
	private Stock getStockPrice(String quote) {
		
		Integer price = stockPrice.get(quote);
		if(price != null) {
			return new Stock(quote, price);
		}
		return null;
	}
}
